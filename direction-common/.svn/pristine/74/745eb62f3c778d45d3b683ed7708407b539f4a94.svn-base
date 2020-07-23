package com.direction.core.modules.sys.role.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.TenantConst;
import com.direction.core.modules.sys.role.RoleConst;
import com.direction.core.modules.sys.role.entity.Role;
import com.direction.core.modules.sys.role.repository.RoleRepository;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<RoleRepository, Role, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private RoleRepository roleRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 获取临时租户角色.
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public Role getTenantTemporaryRole() {
		
		Role role = this.roleRepository.getCode(TenantConst.SYSTEM, RoleConst.TENANT_TEMPORARY_ROLE);
		
		if (role == null) {
			role = new Role();
			role.setRoleCode(RoleConst.TENANT_TEMPORARY_ROLE);
			role.setRoleName(RoleConst.TENANT_TEMPORARY_ROLE_NAME);
			role.setDisplayOrder(0);
			role.setStatus(StatusConst.ENABLE);
			role.setSys(true);
			this.roleRepository.save(role);
		}
		
		return role;
	}
	
	/**
	 * 获取租户管理员.
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public Role getTenantManagerRole() {
		
		Role role = this.roleRepository.getCode(TenantConst.SYSTEM, RoleConst.TENANT_MANAGE_ROLE);
		
		if (role == null) {
			role = new Role();
			role.setRoleCode(RoleConst.TENANT_MANAGE_ROLE);
			role.setRoleName(RoleConst.TENANT_MANAGE_ROLE_NAME);
			role.setDisplayOrder(10);
			role.setStatus(StatusConst.ENABLE);
			role.setSys(true);
			this.roleRepository.save(role);
		}
		
		return role;
	}

	/**
	 * 保存或修改实体.
	 * 
	 * @param role
	 */
	@Transactional(readOnly = false)
	public Role save(Role role) {
		
		if (role == null) {
			throw new ServiceException("角色对象为空，不能进行保存");
		}

		this.roleRepository.save(role);
		return role;
	}

	/**
	 * 角色查询分页.
	 * 
	 * @param Role
	 * @param page
	 * @return
	 */
	public VuePage<Role> findPage(Role role, VuePage<Role> page) {

		// 封装查询条件
		Specification<Role> roleParams = new Specification<Role>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();

				if (role != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(role.getRoleName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("roleName").as(String.class), "%" + role.getRoleName() + "%"),
						    criteriaBuilder.like(root.get("roleCode").as(String.class), "%" + role.getRoleName() + "%")));
					}

					// 判断状态
					if (StringUtils.isNotBlank(role.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), role.getStatus()));
					}
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<Role> rolePage = roleRepository.findAll(roleParams, page.getPageable());
		page.setPage(rolePage);

		return page;
	}

	/**
	 * 删除租户信息(包含批量删除）
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String roleId) {

		this.roleRepository.deleteById(roleId);
	}

	/**
	 * 根据用户ID查询角色
	 * 
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	public Set<String> getRoleIdsByUser(String id) {
		
		if (StringUtils.isBlank(id)) {
			return new HashSet<String>();
		}

		return roleRepository.getRoleIdsByUser(id);
	}
	
	/**
	 * 根据用户ID查询角色
	 * 
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	public Set<String> selectRoleKeys(String id) {

		return roleRepository.selectRoleKeys(id);
	}
}
