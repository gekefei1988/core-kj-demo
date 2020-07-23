package com.direction.core.modules.sys.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.org.entity.OrgTreeNode;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface OrgTreeNodeRepository extends BaseRepository<OrgTreeNode, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取机构.
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "from OrgTreeNode where id = ?1")
	OrgTreeNode get(String id);
	
	/**
	 * 删除租户下的节点.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Modifying
	@Query(value = "delete from OrgTreeNode where tenantId = ?1")
	int deleteByTenant(String tenantId);
	
	/**
	 * 根据节点获取组织树下的用户.
	 * 
	 * @param tenantId
	 * @param treeCode
	 * @param orgCode
	 * @return
	 */
	@Query(value = "from OrgTreeNode where nodeCode = :orgCode and treeCode = :treeCode and tenantId = :tenantId")
	OrgTreeNode getByParams(@Param("tenantId") String tenantId, @Param("treeCode") String treeCode, @Param("orgCode") String orgCode);
	
	/**
	 * 根据树结构编号查询.
	 * 
	 * @param tenantId
	 * @param code
	 * @return
	 */
	@Query(value = "from OrgTreeNode where tenantId=?1 and treeCode = ?2 order by displayOrder asc")
	List<OrgTreeNode> getTreeNodesByCode(String tenantId, String code);
	
	/**
	 * 根据租户,编号,级别获取组织节点.
	 * 
	 * @param tenantId
	 * @param code
	 * @param nodeLevel
	 * @return
	 */
	@Query(value = "from OrgTreeNode where tenantId=?1 and treeCode = ?2 and nodeLevel = ?3 order by displayOrder asc")
	List<OrgTreeNode> getTreeNodeByProps(String tenantId, String code, int nodeLevel);
	
	/**
	 * 根据父级删除所有下级.
	 * 
	 * @param parentIds
	 * @return
	 */
	@Modifying
	@Query("delete from OrgTreeNode where parentIds like CONCAT(?1, '%')")
	int deleteSubsByParentIds(String parentIds);
	
	/**
	 * 根据父级ID查询所有下级.
	 * 
	 * @param parentIds
	 * @return
	 */
	@Query("select id from OrgTreeNode where parentIds like CONCAT(?1, '%')")
	List<String> getSubIdsByParents(String parentIds);
	
	/**
	 * 获取机构最高级别的.
	 * 
	 * @param orgIds
	 * @return
	 */
	@Query(value = "select min(nodeLevel) from OrgTreeNode where id in ?1")
	int getHightTreeNodeLevel(List<String> orgIds);
	
	/**
	 * 获取指定级别的组织机构IDS.
	 * 
	 * @param orgIds
	 * @param nodeLevel
	 * @return
	 */
	@Query(value = "select id from OrgTreeNode where id in ?1 and nodeLevel = ?2")
	List<String> getHightOrgIds(List<String> orgIds, int nodeLevel);
}
