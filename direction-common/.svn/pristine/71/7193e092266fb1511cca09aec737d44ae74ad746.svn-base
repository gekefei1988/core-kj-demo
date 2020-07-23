package com.direction.core.modules.sys.data_perm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.data_perm.entity.DataPerm;
import com.direction.core.modules.sys.data_perm.entity.DataPermKey;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface DataPermRepository extends BaseRepository<DataPerm, DataPermKey> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据用户查询数据权限.
	 * 
	 * @param userId
	 * @return
	 */
	@Query("from DataPerm where userId = ?1")
	List<DataPerm> getListByUser(String userId);
	
	/**
	 * 查询负责机构.
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select orgId from DataPerm where userId = ?1")
	List<String> getListOrgByUser(String userId);
	
	/**
	 * 查询下级权限.
	 * 
	 * @param orgId
	 * @return
	 */
	@Query("select orgId from DataPerm where orgIds like CONCAT(?1, '%')")
	List<String> getSubListOrg(String orgId);
	
	/**
	 * 查询下级权限.
	 * 
	 * @param orgId
	 * @return
	 */
	@Query("from DataPerm where orgIds like CONCAT(?1, '%')")
	List<DataPerm> getSubList(String orgId);
}
