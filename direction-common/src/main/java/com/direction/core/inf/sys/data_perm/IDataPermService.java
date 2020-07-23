package com.direction.core.inf.sys.data_perm;

import java.util.List;
import java.util.Set;

import com.direction.core.modules.sys.data_perm.entity.DataPerm;

// ~ File Information
// ====================================================================================================================

public interface IDataPermService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据用户获取权限.
	 * 
	 * @param userId
	 * @return
	 */
	List<String> getListOrgByUser(String userId);
	
	/**
	 * 根据用户获取权限.
	 * 
	 * @param userId
	 * @return
	 */
	List<DataPerm> getListByUser(String userId);
	
	/**
	 * 根据用户获取权限.
	 * 
	 * @param userId
	 * @return
	 */
	Set<String> getSubListOrg(String userId);
	
	/**
	 * 根据用户获取权限.
	 * 
	 * @param userId
	 * @return
	 */
	Set<DataPerm> getSubList(String userId);
}
