package com.direction.core.modules.sys.org.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.common.tree.TreeNode;
import com.direction.common.utils.validation.HibernateValidationUtils;
import com.direction.core.inf.EIPService;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.UserDataPermsUtils;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.modules.sys.org.entity.OrgTree;
import com.direction.core.modules.sys.org.entity.OrgTreeNode;
import com.direction.core.modules.sys.org.service.OrgTreeNodeService;
import com.direction.core.modules.sys.org.service.OrgTreeService;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

/**
 * 组织机构控制层.
 * 
 * <pre>
 * 组织机构控制层
 * </pre>
 * 
 * @author gekefei
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/org-tree-node")
public class OrgTreeNodeController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private OrgTreeNodeService orgTreeNodeService;
	
	@Autowired
	private OrgTreeService orgTreeService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 查询节点树集合.
	 * 
	 * @param tenantId
	 * @param treeCode
	 * @param selectedNode 选中的节点
	 * @param defaultTree 默认行政树
	 * @param hideBase 隐藏根节点
	 * @param showAll 显示所有节点信息
	 * @return
	 */
	@RequestMapping(value = "/getOrgTreeNode")
	public List<TreeNode> getOrgTreeNode(String tenantId, String treeCode, String selectedNode, Boolean defaultTree, Boolean hideBase, Boolean showAll) {
		
		// 判断是否超级管理员, 超级管理员必须赋值租户ID
		if (UserProfileUtils.isSuperAdmin()) {
			if (StringUtils.isBlank(tenantId)) {
				return new ArrayList<>();
			}
		}
		else {
			tenantId = UserProfileUtils.getTenantId();
		}
		
		List<TreeNode> nodes = this.orgTreeNodeService.getOrgTreeNode(tenantId, treeCode, selectedNode, defaultTree);
		
		// 权限nodes
		List<TreeNode> tempNodes = null;
		
		// 返回结果
		List<TreeNode> results = null;
		
		if (showAll != null && showAll) {
			tempNodes = nodes;
		}
		else {
			// 获取权限下的nodes
			tempNodes = UserDataPermsUtils.getDataPermsTreeNode(nodes);
		}

		// 去掉根节点
		if (hideBase != null && !hideBase) {
			
			OrgTree orgTree = this.orgTreeService.getOrgTreeByCode(tenantId, treeCode);
			if (orgTree != null) {

				TreeNode baseNode = new TreeNode(orgTree.getTreeCode(), orgTree.getTreeName(), "0", "BASENODE");
				baseNode.setExpand(true);
				baseNode.setNodeLevel(0);
				baseNode.setNodeCode(orgTree.getTreeCode());
				baseNode.setStatus(orgTree.getStatus());
				baseNode.setIcon("md-home");
				baseNode.getChildren().addAll(tempNodes);
				
				results = new ArrayList<TreeNode>();
				results.add(baseNode);
			}
		}
		
		// 如果返回结果为空, 则获取临时节点
		if (results == null) {
			results = tempNodes;
		}
		
		// 如果没有选择的节点, 则赋值第一个
		if (StringUtils.isBlank(selectedNode) && results.size() > 0) {
			results.get(0).setSelected(true);
		}
		
		return results;
	}

	/**
	 * 
	 * 根据组织机构id获取组织结构内容
	 * 
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/get")
	public OrgTreeNode get(String id) {
		
		OrgTreeNode node = orgTreeNodeService.get(id);
		
		if (node == null) {
			node = new OrgTreeNode();
			node.setStatus(StatusConst.ENABLE);
			node.setDisplayOrder(0);
			node.setNodeType(EIPService.getDictService().getTopDefaultValue("sys-org-category"));
			node.setTenantId(UserProfileUtils.getTenantId());
			
			// 获取默认组织树
			OrgTree orgTree = this.orgTreeService.getDefaultTree(UserProfileUtils.getTenantId());
			if (orgTree != null) {
				node.setTreeCode(orgTree.getTreeCode());
			}
		}
		return node;
	}

	/**
	 * 保存或修改组织机构.
	 * 
	 * @param orgTreeNode
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResultJson save(OrgTreeNode orgTreeNode) {
		
		if (orgTreeNode != null && StringUtils.isBlank(orgTreeNode.getTenantId())) {
			orgTreeNode.setTenantId(UserProfileUtils.getTenantId());
		}
		
		ResultJson result = HibernateValidationUtils.validateEntity(orgTreeNode);
		
		if (result.isSuccess()) {
			this.orgTreeNodeService.save(orgTreeNode);
			result.setBody(orgTreeNode);
		}
		
		return result;
	}

	/**
	 * 
	 * 删除组织机构
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		
		OrgTreeNode orgTreeNode = this.orgTreeNodeService.get(id);
		if (orgTreeNode == null) {
			return error("该节点已不存在");
		}
		else if (StringUtils.equals("0", orgTreeNode.getParentId())) {
			return error("根节点不能进行删除");
		}
		
		this.orgTreeNodeService.delete(id);
		return success();
	}
	
	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String id) {
		return this.orgTreeNodeService.validateOnlyOne(UserProfileUtils.getTenantId(), propName, propValue, id);
	}
}
