package com.direction.core.modules.sys.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.user.entity.SuperAdmin;
import com.direction.core.modules.sys.user.repository.SuperAdminRepository;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 超级管理员业务逻辑层.
 * 
 * <pre>
 * 	超级管理员业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class SuperAdminService extends BaseService<SuperAdminRepository, SuperAdmin, String>{

	// ~ Static Fields
	// ==================================================================================================================
	
	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private SuperAdminRepository superAdminRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据账号查询用户.
	 * 
	 * @param userName
	 * @return
	 */
	public SuperAdmin getByUserName(String userName) {
		if (StringUtils.isBlank(userName)) {
			return null;
		}
		
		return superAdminRepository.getByUserName(userName);
	}
	
	/**
	 * 修改登陆次数.
	 * 
	 * @param userName
	 */
	@Async
	@Transactional(readOnly = false)
	public void loginErrorCount(String userName) {
		SuperAdmin admin = this.getByUserName(userName);
		admin.setLoginErrorCount(admin.getLoginErrorCount() + 1);
		admin.setLoginErrorDate(new Date());
		this.superAdminRepository.save(admin);
	}
	
	/**
	 * 修改最后登陆日期.
	 * 
	 * @param userId
	 */
	@Async
	@Transactional(readOnly = false)
	public void updateLoginDate(String userId) {
		SuperAdmin user = this.get(userId);
		if (user != null) {
			user.setLastLoginDate(new Date());
			this.superAdminRepository.save(user);
		}
	}
	
	/**
	 * 重置错误次数.
	 * 
	 * @param userName
	 */
	@Transactional(readOnly = false)
	public void resetLoginErrorCount(String userName) {
		
		if (StringUtils.isNotBlank(userName)) {

			SuperAdmin admin = this.getByUserName(userName);
			if (admin != null) {
				admin.setLoginErrorCount(0);
				this.superAdminRepository.save(admin);
			}
		}
		else {
			int count = this.superAdminRepository.resetByErrorDate(new Date());
			LoggerFactory.getLogger(UserService.class).debug("重置密码错误, 重置超级管理员用户: " + count);
		}
	}
	
	/**
	 * 根据账号和密码查询用户.
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public SuperAdmin getSuperAdminBy(String loginName, String password) {
		return this.superAdminRepository.getSuperAdminBy(loginName, password);
	}

	/**
	 * 保存用户信息.
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public SuperAdmin save(SuperAdmin user) {

		try {
			
			if (user == null) {
				throw new ServiceException("管理员对象为空, 不能进行保存");
			}
			
			String password = new Md5Hash(user.getUserName() + user.getPassword()).toHex().toString();
			
			// 保存密码不为空, 则进行修改
			if (StringUtils.isNotBlank(user.getUserId())) {
				
				// 如果用户已存在, 比对用户密码, 确定是否修改密码
				if (this.superAdminRepository.comparisonPassword(user.getUserId(), user.getPassword()) == 0) {
					user.setPassword(password);
					user.setLastPwdUpdate(new Date());
				}
			}
			else {
				
				user.setPassword(password);
				user.setRegPassword(password);
			}

			this.superAdminRepository.save(user);
			return user;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 用户分页.
	 * 
	 * @param user
	 * @return
	 */
	public VuePage<SuperAdmin> findPage(SuperAdmin user, VuePage<SuperAdmin> page) {

		// 封装查询条件
		Specification<SuperAdmin> params = new Specification<SuperAdmin>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SuperAdmin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();

				if (user != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(user.getUserName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("nickName").as(String.class), "%" + user.getUserName() + "%"),
						    criteriaBuilder.like(root.get("userName").as(String.class), "%" + user.getUserName() + "%")));
					}

					// 判断状态
					if (StringUtils.isNotBlank(user.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), user.getStatus()));
					}
				}
				
				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};
		
		Page<SuperAdmin> userPage = superAdminRepository.findAll(params, page.getPageable());
		page.setPage(userPage);
		
		return page;
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param propName
	 * @param propValue
	 * @param userId
	 * @return
	 */
	public ResultJson validateOnlyOne(String propName, String propValue, String userId) {

		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}

		Specification<SuperAdmin> params = new Specification<SuperAdmin>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SuperAdmin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(userId)) {

					return criteriaBuilder.and(criteriaBuilder.notEqual(root.get("userId").as(String.class), userId),
					    criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				} else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};

		// 如果超级管理员没有重复的, 则验证用户
		if (this.superAdminRepository.count(params) == 0) {
			return ResultJson.success();
		}

		return ResultJson.fail("您填写的内容已存在, 请重新输入...");
	}
}
