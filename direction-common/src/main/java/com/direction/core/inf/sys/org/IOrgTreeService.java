package com.direction.core.inf.sys.org;

import java.util.List;

import com.direction.common.tree.TreeNode;

// ~ File Information
// ====================================================================================================================

public interface IOrgTreeService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 获取树节点.
	 * 
	 * @param id
	 * @return
	 */
	IOrgTreeNode getOrgTreeNode(String id);
	
	/**
	 * 获取机构名称
	 * 
	 * @param id
	 * @return
	 */
	String getOrgName(String id);
	
	/**
	 * 获取机构下所有节点的ID.
	 * 
	 * @param id
	 * @return
	 */
	List<String> getOrgTreeNodeIds(String id);

	/**
	 * 获取行政组织树.
	 * 
	 * String tenantId
	 * @return
	 */
	List<TreeNode> getXZOrgTree(String tenantId);
}
