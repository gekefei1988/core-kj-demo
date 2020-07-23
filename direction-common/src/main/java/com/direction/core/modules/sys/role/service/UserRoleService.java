package com.direction.core.modules.sys.role.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.core.modules.sys.role.entity.Role;
import com.direction.core.modules.sys.role.entity.UserRole;
import com.direction.core.modules.sys.role.repository.UserRoleRepository;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.pk.UserRolePK;

// ~ File Information
// ====================================================================================================================

/**
 * 用户角色逻辑层.
 * 
 * <pre>
 * 	用户角色逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class UserRoleService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleService roleService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 给角色分配用户.
	 * 
	 * @param roleId
	 * @param userIds
	 */
	@Transactional(readOnly = false)
	public void assignUser(String roleId, String userIds) {
		
		if (StringUtils.isAnyBlank(roleId, userIds)) {
			throw new ServiceException("保存失败, 未获取到角色信息");
		}
		
		Role role = roleService.get(roleId);
		if (role == null) {
			throw new ServiceException("保存失败, 未获取到角色信息");
		}
		
		if (StringUtils.isBlank(userIds)) {
			return;
		}
		
		String[] users = StringUtils.split(userIds, ",");
		
		if (users.length > 0) {
			
			// 保存用户角色授权
			UserRole userRole = null;
			for (String userId : users) {
				userRole = new UserRole(userId, roleId);
				this.userRoleRepository.save(userRole);
			}
		}
	}
	
	/**
	 * 给角色分配用户.
	 * 
	 * @param userId
	 */
	@Transactional(readOnly = false)
	public void assignTenantRoleUser(String userId) {
		
		if (StringUtils.isBlank(userId)) {
			throw new ServiceException("保存失败, 未获取到用户");
		}
		
		Role role = roleService.getTenantTemporaryRole();
		if (role == null) {
			throw new ServiceException("保存失败, 角色已不寻在");
		}
		
		// 保存用户角色授权
		UserRole userRole = new UserRole(userId, role.getRoleId());
		this.userRoleRepository.save(userRole);
	}
	
	/**
	 * 给角色分配用户.
	 * 
	 * @param userId
	 */
	@Transactional(readOnly = false)
	public void assignTenantManageRoleUser(String userId) {
		
		if (StringUtils.isBlank(userId)) {
			throw new ServiceException("保存失败, 未获取到用户");
		}
		
		Role role = roleService.getTenantManagerRole();
		if (role == null) {
			throw new ServiceException("保存失败, 角色已不寻在");
		}
		
		// 删除以前的角色
		this.userRoleRepository.deleteByUserId(userId);
		
		// 保存用户角色授权
		UserRole userRole = new UserRole(userId, role.getRoleId());
		this.userRoleRepository.save(userRole);
	}
	
	/**
	 * 根据角色查询角色下用户.
	 * 
	 * @param roleId
	 * @return
	 */
	public List<User> getUsersByRoleId(String roleId) {
		
		if (StringUtils.isBlank(roleId)) {
			return new ArrayList<User>();
		}
		
		return this.userRoleRepository.getUsersByRoleId(roleId);
	}
	
	/**
	 * 根据角色查询角色下用户.
	 * 
	 * @param roleId
	 * @param tenantId
	 * @return
	 */
	public List<User> getUsersByRoleTenant(String roleId, String tenantId) {
		
		if (StringUtils.isBlank(roleId)) {
			return new ArrayList<User>();
		}
		else if (StringUtils.isBlank(tenantId)) {
			return this.getUsersByRoleId(roleId);
		}
		
		return this.userRoleRepository.getUsersByRoleTenant(roleId, tenantId);
	}
	
	/**
	 * 删除授权信息.
	 * 
	 * @param roleId
	 * @param userId
	 */
	@Transactional(readOnly = false)
	public void delete(String roleId, String userId) {
		
		if (StringUtils.isAnyBlank(roleId, userId)) {
			throw new ServiceException("角色或用户信息为空, 不能进行删除");
		}
		
		UserRolePK pk = new UserRolePK(roleId, userId);
		this.userRoleRepository.deleteById(pk);
	}
}
