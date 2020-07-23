package com.direction.core.inf.sys.user;

import java.util.ArrayList;
import java.util.List;

// ~ File Information
// ====================================================================================================================

public interface IUserService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据角色获取用户.
	 * 
	 * @param roleId
	 * @return
	 */
	List<IUserInfo> getUsersByRoleId(String roleId);
	
	/**
	 * 查询用户.
	 * 
	 * @param userId
	 * @return
	 */
	IUserInfo get(String userId);
	
	/**
	 * 获取所有启用的用户, 不区分权限.
	 * 
	 * @return
	 */
	@Deprecated
	List<IUserInfo> getAllUsers();
	
	/**
	 * 查询当前租户下用户.
	 * 
	 * @return
	 */
	List<IUserInfo> getUsersByTenant();
	
	/**
	 * 获取所有启用的用户, 区分权限, 只获取权限内的.
	 * 
	 * @return
	 */
	default List<IUserInfo> getUsers() {
		return new ArrayList<IUserInfo>();
	}
	
	/**
	 * 根据机构获取所有启用的用户.
	 * 
	 * @param treeCode
	 * @param nodeNo
	 * @return
	 */
	default List<IUserInfo> getUsers(String treeCode, String nodeNo) {
		return new ArrayList<>();
	}
}
