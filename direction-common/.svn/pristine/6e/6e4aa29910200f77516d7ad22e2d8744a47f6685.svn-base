package com.direction.core.modules.sys.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.org.entity.OrgTree;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface OrgTreeRepository extends BaseRepository<OrgTree, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取组织树.
	 * 
	 * @param treeId
	 * @return
	 */
	@Query(value = "from OrgTree where treeId = ?1")
	OrgTree get(String treeId);
	
	/**
	 * 根据租户ID获取树结构.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Query(value = "from OrgTree where tenantId = ?1 order by defaultTree desc, status asc")
	List<OrgTree> getTreesByTenant(String tenantId);
	
	/**
	 * 获取默认组织树.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Query(value = "from OrgTree where defaultTree = true and tenantId=?1")
	OrgTree getDefaultTree(String tenantId);

	/**
	 * 根据编号查询组织树.
	 * 
	 * @param tenantId
	 * @param treeCode
	 * @return
	 */
	@Query(value = "from OrgTree where tenantId=?1 and treeCode = ?2")
	OrgTree getOrgTreeByCode(String tenantId, String treeCode);
	
	/**
	 * 根据状态获取树.
	 * 
	 * @param tenantId
	 * @param status
	 * @return
	 */
	@Query("from OrgTree where status=?1 order by defaultTree desc")
	List<OrgTree> getOrgTreesByStatus(String tenantId, String status);
	
	/**
	 * 修改所有组织树为非行政.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Modifying
	@Query("update OrgTree set defaultTree = false where tenantId=?1")
	int updateOrgTreeDefualt(String tenantId);
	
	/**
	 * 根据组织树编号, 删除组织树.
	 * 
	 * @param tenantId
	 * @param treeCode
	 * @return
	 */
	@Modifying
	@Query("delete from OrgTree where tenantId=?1 and treeCode = ?2")
	int deleteByCode(String tenantId, String treeCode);
	
	/**
	 * 删除组织树.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Modifying
	@Query("delete from OrgTree where tenantId=?1")
	int deleteByTenant(String tenantId);
}
