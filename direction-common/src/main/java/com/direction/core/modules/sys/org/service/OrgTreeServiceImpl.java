package com.direction.core.modules.sys.org.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.tree.TreeNode;
import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;
import com.direction.core.inf.sys.org.IOrgTreeNode;
import com.direction.core.inf.sys.org.IOrgTreeService;

// ~ File Information
// ====================================================================================================================

/**
 * 组织树.
 * 
 * <pre>
 * 组织树
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class OrgTreeServiceImpl implements IOrgTreeService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private OrgTreeNodeService orgTreeNodeService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取行政组织树.
	 * 
	 * @see com.direction.core.inf.sys.org.IOrgTreeService#getXZOrgTree(java.lang.String)
	 */
	@Override
	public List<TreeNode> getXZOrgTree(String tenantId) {
		
		// 缓存中获取行政组织树
		@SuppressWarnings("unchecked")
		List<TreeNode> results = (List<TreeNode>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_ORG_TREE, "getXZOrgTree");
		
		
		if (results != null && results.size() > 0) {
			return results;
		}
		else {
			results = new ArrayList<TreeNode>();
		}
		
		results = this.orgTreeNodeService.getOrgTreeNode(tenantId, null, null, true);
		
		// 放入缓存中
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_ORG_TREE, "getXZOrgTree", results);
		
		return results;
	}

	/**
	 * 根据ID查询机构.
	 * 
	 * @see com.direction.core.inf.sys.org.IOrgTreeService#getOrgTreeNode(java.lang.String)
	 */
	@Override
	public IOrgTreeNode getOrgTreeNode(String id) {
		
		IOrgTreeNode treeNode = (IOrgTreeNode) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_ORG_TREE, "_node_" + id);
		
		if (treeNode != null) {
			return treeNode;
		}
		
		treeNode = orgTreeNodeService.get(id);
		
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_ORG_TREE, "_node_" + id, treeNode);
		
		return treeNode;
	}

	/**
	 * 获取机构下所有节点的ID.
	 * 
	 * @see com.direction.core.inf.sys.org.IOrgTreeService#getOrgTreeNodeIds(java.lang.String)
	 */
	@Override
	public List<String> getOrgTreeNodeIds(String id) {

		return this.orgTreeNodeService.getOrgNodeIds(id);
	}

	/**
	 * 获取机构名称.
	 * 
	 * @see com.direction.core.inf.sys.org.IOrgTreeService#getOrgName(java.lang.String)
	 */
	@Override
	public String getOrgName(String id) {
		
		IOrgTreeNode treeNode = this.getOrgTreeNode(id);
		
		if (treeNode != null) {
			return treeNode.getOrgName();
		}

		return null;
	}
}
