package com.direction.core.modules.sys.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.modules.sys.log.service.ErrorLogService;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.entity.UserOnline;
import com.direction.core.modules.sys.user.repository.UserOnlineRepository;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 在线用户业务逻辑层.
 * 
 * <pre>
 * 	在线用户业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class UserOnlineService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private UserOnlineRepository userOnlineRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ErrorLogService errorLogService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据用户id查询用户登录信息.
	 * 
	 * @param loginName
	 * @return
	 */
	public UserOnline selectByUserId(String userId) {

		return this.userOnlineRepository.selectByUserId(userId);
	}

	/**
	 * 保存在线用户.
	 * 
	 * @param userOnline
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean save(UserOnline userOnline) {

		boolean ret = false;
		try {
			userOnline = this.userOnlineRepository.save(userOnline);
			if (userOnline != null && !userOnline.getId().isEmpty()) {
				ret = true;
			}
		} catch (Exception e) {
			throw new ServiceException("在线用户信息保存或修改失败");
		}
		return ret;
	}

	/**
	 * 在线用户信息登记.
	 * 
	 * @param user
	 */
	@Async
	@Transactional(readOnly = false)
	public void saveUserOline(User user, char status) {

		UserOnline userOnline = new UserOnline();

		try {
			// 判断用户之前是否登录过.登录过则更新：最后访问时间;没有登录记录则生成：创建时间
			UserOnline userOnlineInfo = this.selectByUserId(user.getUserId());
			
			if (userOnlineInfo != null && userOnlineInfo.getLoginCount() > 0) {
				// 用户登录过
				userOnlineInfo.setIpaddr(UserProfileUtils.getReqIp());
				userOnlineInfo.setLastAccessTime(new Date());
				userOnlineInfo.setLoginCount(userOnlineInfo.getLoginCount() + 1);
				userOnlineInfo.setStatus(status);
				this.save(userOnlineInfo);

			} else {
				userOnline.setIpaddr(UserProfileUtils.getReqIp());
				userOnline.setUserId(user.getUserId());
//				userOnline.setLoginName(user.getLoginName());

				// 根据登录用户获取部门名称
//				Dept dept = deptService.get(user.getDeptId());
//				if (dept != null && dept.getDeptName() != null) {
//					userOnline.setDeptName(dept.getDeptName());
//					userOnline.setDeptId(dept.getDeptId());
//				}

				// 首次登录
				userOnline.setCreateTimestamp(new Date());
				userOnline.setLoginCount(1);
				userOnline.setStatus('0');
				this.save(userOnline);
			}
		} catch (Exception e) {
			// 保存登记在线用户信息，出错信息
			errorLogService.saveError(e, "e0001");
		}
	}

	/**
	 * 获取全部数据
	 * 
	 * @return
	 */
	public List<UserOnline> getALL() {

		return this.userOnlineRepository.findAll();
	}

	/**
	 * 字典类型分页.
	 * 
	 * @param searchValue
	 * @param status
	 * @param page
	 * @return
	 */
	public VuePage<UserOnline> findPage(String searchValue, String deptId, Integer status, VuePage<UserOnline> page) {

		// 封装查询条件
		Specification<UserOnline> dictParams = new Specification<UserOnline>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<UserOnline> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();

				// 判断关键字
				if (StringUtils.isNotBlank(searchValue)) {
					predicateList.add(
					    criteriaBuilder.or(criteriaBuilder.like(root.get("loginame").as(String.class), "%" + searchValue + "%"),
					        criteriaBuilder.like(root.get("deptName").as(String.class), "%" + searchValue + "%")));
				}

				// 根据部门id查询人员
				if (deptId != null && !deptId.isEmpty()) {
					predicateList.add(criteriaBuilder.equal(root.get("deptId").as(String.class), deptId));
				}

				// 判断状态
				if (status != null) {
					predicateList.add(criteriaBuilder.equal(root.get("status").as(Integer.class), status));
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<UserOnline> AreaPage = userOnlineRepository.findAll(dictParams, page.getPageable());
		page.setPage(AreaPage);

		return page;
	}
}
