package com.direction.core.modules.sys.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.inf.sys.user.IUserService;
import com.direction.core.modules.sys.role.service.UserRoleService;

// ~ File Information
// ====================================================================================================================

@Service
public class UserServiceImpl implements IUserService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据角色获取用户.
	 * 
	 * @see com.direction.core.inf.sys.user.IUserService#getUsersByRoleId(java.lang.String)
	 */
	@Override
	public List<IUserInfo> getUsersByRoleId(String roleId) {
		List<IUserInfo> users = new ArrayList<>();
		users.addAll(userRoleService.getUsersByRoleId(roleId));
		return users;
	}

	/**
	 * 根据ID查询用户.
	 * 
	 * @see com.direction.core.inf.sys.user.IUserService#get(java.lang.String)
	 */
	@Override
	public IUserInfo get(String userId) {

		return userService.get(userId);
	}

	/**
	 * 获取所有启用的用户, 不区分权限.
	 * 
	 * @see com.direction.core.inf.sys.user.IUserService#getAllUsers()
	 */
	@Override
	public List<IUserInfo> getAllUsers() {
		return this.getUsersByTenant();
	}

	/**
	 * 获取当前租户下的用户.
	 * 
	 * @see com.direction.core.inf.sys.user.IUserService#getUsersByTenant()
	 */
	@Override
	public List<IUserInfo> getUsersByTenant() {
		
		List<IUserInfo> users = new ArrayList<>();
		users.addAll(this.userService.getUsersByTenant());

		return users;
	}
}
