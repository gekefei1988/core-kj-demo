package com.direction.core.modules.sys.data_perm.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.core.inf.sys.data_perm.IDataPermService;
import com.direction.core.modules.sys.data_perm.entity.DataPerm;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class DataPermServiceImple implements IDataPermService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private DataPermService dataPermService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 查询数据权限.
	 * 
	 * @see com.direction.core.inf.sys.data_perm.IDataPermService#getListOrgIdByUser(java.lang.String)
	 */
	@Override
	public List<String> getListOrgByUser(String userId) {

		return this.dataPermService.getListOrgByUser(userId);
	}

	/**
	 * 查询数据权限.
	 * 
	 * @see com.direction.core.inf.sys.data_perm.IDataPermService#getListByUser(java.lang.String)
	 */
	@Override
	public List<DataPerm> getListByUser(String userId) {

		return this.dataPermService.getListByUser(userId);
	}

	/**
	 * 查询数据权限.
	 * 
	 * @see com.direction.core.inf.sys.data_perm.IDataPermService#getSubListOrg(java.lang.String)
	 */
	@Override
	public Set<String> getSubListOrg(String userId) {

		return this.dataPermService.getSubListOrg(userId);
	}

	/**
	 * 查询数据权限.
	 * 
	 * @see com.direction.core.inf.sys.data_perm.IDataPermService#getSubList(java.lang.String)
	 */
	@Override
	public Set<DataPerm> getSubList(String userId) {

		return this.dataPermService.getSubList(userId);
	}
}
