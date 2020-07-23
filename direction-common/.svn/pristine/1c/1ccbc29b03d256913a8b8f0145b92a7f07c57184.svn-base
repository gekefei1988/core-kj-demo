package com.direction.core.modules.sys.tenant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.common.utils.validation.HibernateValidationUtils;
import com.direction.core.modules.sys.role.service.UserRoleService;
import com.direction.core.modules.sys.tenant.entity.Tenant;
import com.direction.core.modules.sys.tenant.entity.Tenant.TenantStatus;
import com.direction.core.modules.sys.tenant.entity.TenantReg;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantRegStauts;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantType;
import com.direction.core.modules.sys.tenant.repository.TenantRegRepository;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.service.UserService;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class TenantRegService extends BaseService<TenantRegRepository, TenantReg, String>{

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantPerService tenantPerService;
	
	@Autowired
	private TenantCompService tenantCompService;
	
	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 保存并修改.
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(TenantReg entity) {
		try {
			if (StringUtils.isBlank(entity.getId())) {
				entity.setRegDate(new Date());
			}

			this.save(entity);
		} catch (Exception e) {
			throw new ServiceException("注册失败, 错误信息:" + e.getMessage());
		}
	}
	
	/**
	 * 保存注册信息, 只保留基础验证.
	 * 
	 * @param tenantReg
	 * @return
	 */
	@Transactional(readOnly = false)
	public ResultJson tenantReg(TenantReg tenantReg) {

		if (tenantReg == null) {
			return ResultJson.fail("租户对象为空");
		}
		else if (!StringUtils.equals(tenantReg.getPassword(), tenantReg.getInitPwd())) {
			return ResultJson.fail("输入的密码和确认密码不相同");
		}

		ResultJson result = HibernateValidationUtils.validateEntity(tenantReg);

		// 如果实体验证不通过, 直接返回
		if (!result.isSuccess()) {
			return result;
		}

		// 保存注册的租户
		this.saveOrUpdate(tenantReg);

		// 创建租户临时用户
		User user = userService.createUserByReg(tenantReg);

		// 创建角色-人员授权
		this.userRoleService.assignTenantRoleUser(user.getUserId());

		return ResultJson.success();
	}
	
	/**
	 * 审核.
	 * 
	 * @param tenant
	 */
	@Transactional(readOnly = false)
	public void review(Tenant tenant) {
		
		if (tenant == null) {
			throw new ServiceException("未获取到租户信息");
		}
		else if (StringUtils.equalsAny(tenant.getStatus(), TenantStatus.PASS, TenantStatus.UNPASS)) {
			
			TenantReg tenantReg = this.get(tenant.getId());
			
			if (tenantReg == null) {
				throw new ServiceException("未获取到租户信息");
			}
			
			// 通过
			if (StringUtils.equals(tenant.getStatus(), TenantStatus.PASS)) {
				tenantReg.setStatus(TenantRegStauts.PASS);
			}
			// 拒绝
			else if (StringUtils.equals(tenant.getStatus(), TenantStatus.UNPASS)) {
				tenantReg.setStatus(TenantRegStauts.REFUSE);
				tenantReg.setRefuse(tenant.getFreeze());
			}
			getBaseRepository().save(tenantReg);
		}
	}
	
	/**
	 * 提交审核.
	 * 
	 * @param tenantId
	 */
	@Transactional(readOnly = false)
	public TenantReg submit(String tenantId) {
		
		TenantReg reg = this.get(tenantId);
		if (reg == null) {
			throw new ServiceException("未获取到租户信息");
		}
		
		if (StringUtils.equals(reg.getTenantType(), TenantType.PERSONAL)) {
			this.tenantPerService.submit(reg);
		}
		else if (StringUtils.equals(reg.getTenantType(), TenantType.ENTERPRISE)) {
			this.tenantCompService.submit(reg);
		}
		else {
			throw new ServiceException("未获取到租户类型");
		}
		return reg;
	}
	
	/**
	 * 撤回审核.
	 * 
	 * @param tenantId
	 */
	@Transactional(readOnly = false)
	public void quit(String tenantId) {
		
		TenantReg reg = this.get(tenantId);
		if (reg == null) {
			throw new ServiceException("未获取到租户信息");
		}
		
		// 获取租户信息
		Tenant tenant = this.tenantService.get(tenantId);
		if (tenant == null) {
			throw new ServiceException("未获取到租户信息");
		}
		
		tenant.setIsDelete(true);
		this.tenantService.save(tenant);
		
		reg.setStatus(TenantRegStauts.UN_SUBMIT);
		this.save(reg);
	}
	
	/**
	 * 根据主键删除注册信息.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		
		TenantReg reg = this.get(id);
		
		if (reg != null) {
			
			// 假删除
			if (StringUtils.equals(reg.getTenantType(), TenantType.PERSONAL)) {
				this.tenantPerService.fakeDelete(id);
			}
			else if (StringUtils.equals(reg.getTenantType(), TenantType.ENTERPRISE)) {
				this.tenantCompService.fakeDelete(id);
			}
			
			// 租户
			Tenant tenant = this.tenantService.get(id);
			
			if (tenant != null) {
				tenant.setIsDelete(true);
				this.tenantService.save(tenant);
			}
			
			reg.setIsDelete(true);
			
			// 删除注册信息
			this.getBaseRepository().save(reg);
		}
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param propName
	 * @param propValue
	 * @param id
	 * @return
	 */
	public ResultJson validateOnlyOne(String propName, String propValue, String id) {
		
		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}
		
		Specification<TenantReg> postParams = new Specification<TenantReg>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TenantReg> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(id)) {
					
					return criteriaBuilder.and(
						criteriaBuilder.notEqual(root.get("id").as(String.class), id), 
						criteriaBuilder.equal(root.get(propName).as(String.class), propValue)
					);
				}
				else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};
		
		if (this.getBaseRepository().count(postParams) == 0) {
			return ResultJson.success();
		}
		
		return ResultJson.fail("已存在, 请重新输入...");
	}
	
	/**
	 * 验证唯一性.
	 * 
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public ResultJson validateOnlyOne(String propName, String propValue) {
		return this.validateOnlyOne(propName, propValue, null);
	}
	
	/**
	 * 分页查询.
	 * 
	 * @param tenant
	 * @param page
	 * @return
	 */
	public VuePage<TenantReg> findPage(TenantReg tenantReg, VuePage<TenantReg> page) {

		// 封装查询条件
		Specification<TenantReg> params = new Specification<TenantReg>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TenantReg> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
				
				predicateList.add(criteriaBuilder.equal(root.get("isDelete"), false));
				
				if (tenantReg != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(tenantReg.getTelephone())) {
						predicateList.add(criteriaBuilder.like(root.get("telephone").as(String.class), "%" + tenantReg.getTelephone() + "%"));
					}
					
					// 判断类型
					if (StringUtils.isNotBlank(tenantReg.getTenantType())) {
						predicateList.add(criteriaBuilder.equal(root.get("tenantType").as(String.class), tenantReg.getTenantType()));
					}

					// 判断状态
					if (StringUtils.isNotBlank(tenantReg.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), tenantReg.getStatus()));
					}
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		return findPage(params, page);
	}
}
