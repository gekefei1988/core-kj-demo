package com.direction.core.modules.sys.org.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.common.tree.TreeNode;
import com.direction.core.modules.sys.area.entity.Area;
import com.direction.core.modules.sys.area.service.AreaService;
import com.direction.core.modules.sys.org.entity.OrgTree;
import com.direction.core.modules.sys.org.entity.OrgTreeNode;
import com.direction.core.modules.sys.org.repository.OrgTreeNodeRepository;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class OrgTreeNodeService extends BaseService<OrgTreeNodeRepository, OrgTreeNode, String>{

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private OrgTreeNodeRepository orgTreeNodeRepository;

	@Autowired
	private OrgTreeService orgTreeService;
	
	@Autowired
	private AreaService areaService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 获取机构最高级别的.
	 * 
	 * @param orgIds
	 * @return
	 */
	public Integer getHightTreeNodeLevel(List<String> orgIds) {
		
		if (orgIds == null || orgIds.size() == 0) {
			return null;
		}
		
		return this.orgTreeNodeRepository.getHightTreeNodeLevel(orgIds);
	}
	
	/**
	 * 获取指定级别的组织机构IDS.
	 * 
	 * @param orgIds
	 * @param nodeLevel
	 * @return
	 */
	public List<String> getHightOrgIds(List<String> orgIds, int nodeLevel) {
		return this.orgTreeNodeRepository.getHightOrgIds(orgIds, nodeLevel);
	}
	
	/**
	 * 根据租户,编号,级别获取组织节点.
	 * 
	 * @param tenantId
	 * @param code
	 * @param nodeLevel
	 * @return
	 */
	public List<OrgTreeNode> getTreeNodeByProps(String tenantId, String code, int nodeLevel) {
		return this.orgTreeNodeRepository.getTreeNodeByProps(tenantId, code, nodeLevel);
	}
	
	/**
	 * 根据租户获取默认顶级节点.
	 * 
	 * @param tenantId
	 * @return
	 */
	public List<OrgTreeNode> getDefaultTopTreeNodes(String tenantId) {
		OrgTree orgTree = this.orgTreeService.getDefaultTree(tenantId);
		if (orgTree == null) {
			return null;
		}
		
		return this.orgTreeNodeRepository.getTreeNodeByProps(tenantId, orgTree.getTreeCode(), 0);
	}

	/**
	 * 保存组织树.
	 * 
	 * @param orgNode
	 */
	@Transactional(readOnly = false)
	public OrgTreeNode save(OrgTreeNode orgNode) {

		try {
			if (orgNode != null) {
				// 判断简称
				if (StringUtils.isBlank(orgNode.getAbbrNodeName())) {
					orgNode.setAbbrNodeName(orgNode.getNodeName());
				}
				
				// 全称
				if (StringUtils.isBlank(orgNode.getFullNodeName())) {
					orgNode.setFullNodeName(orgNode.getNodeName());
				}

				// 父级
				if (StringUtils.isBlank(orgNode.getParentId())) {
					orgNode.setParentId("0");
					orgNode.setParentIds("0");
					orgNode.setNodeLevel(0);
				}
				
				// 判断父级
				if (!StringUtils.equals(orgNode.getParentId(), "0")) {

					// 获取父级
					OrgTreeNode parentNode = this.get(orgNode.getParentId());
					if (parentNode == null) {
						throw new ServiceException("父级机构已不存在!");
					}

					orgNode.setParentIds(parentNode.getParentIds() + "," + parentNode.getId());
					orgNode.setNodeLevel((parentNode.getNodeLevel() == null ? 0 : parentNode.getNodeLevel()) + 1);
					orgNode.setTreeCode(parentNode.getTreeCode());
				}

				// 区域
				if (StringUtils.isNotBlank(orgNode.getAreaCodes())) {
					String[] areaCodes = StringUtils.split(orgNode.getAreaCodes(), ",");
					if (areaCodes.length > 0) {
						Area area = this.areaService.getAreaByCode(areaCodes[areaCodes.length - 1]);
						if (area != null) {
							orgNode.setAreaNames(area.getAllAreaName());
						}
					}
				}

				this.orgTreeNodeRepository.save(orgNode);
			}
			return orgNode;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存组织树失败, 错误信息:" + e.getMessage());
		}
	}

	/**
	 * 删除组织树.
	 * 
	 * @param orgNodeId
	 */
	@Transactional(readOnly = false)
	public void delete(String orgNodeId) {

		try {
			if (StringUtils.isNotBlank(orgNodeId)) {

				OrgTreeNode orgNode = this.get(orgNodeId);
				if (orgNode != null) {

					// 删除子节点
					this.orgTreeNodeRepository.deleteSubsByParentIds(orgNode.getParentIds() + "," + orgNode.getId());

					// 删除当前树
					this.orgTreeNodeRepository.delete(orgNode);
				}
			}
		} catch (Exception e) {
			throw new ServiceException("组织树删除失败");
		}
	}
	
	/**
	 * 根据租户删除相关节点信息.
	 * 
	 * @param tenantId
	 */
	@Transactional(readOnly = false)
	public void deleteByTenant(String tenantId) {
		if (StringUtils.isNotBlank(tenantId)) {
			this.orgTreeNodeRepository.deleteByTenant(tenantId);
		}
	}
	
	/**
	 * 验证属性是否唯一.
	 * 
	 * @param tenantId
	 * @param propName
	 * @param propValue
	 * @param id
	 * @return
	 */
	public ResultJson validateOnlyOne(String tenantId, String propName, String propValue, String id) {

		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}

		Specification<OrgTreeNode> params = new Specification<OrgTreeNode>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<OrgTreeNode> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(id)) {

					return criteriaBuilder.and(criteriaBuilder.notEqual(root.get("id").as(String.class), id),
							criteriaBuilder.equal(root.get("tenantId"), tenantId),
					    criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				} else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};

		if (this.orgTreeNodeRepository.count(params) == 0) {
			return ResultJson.success();
		}

		return ResultJson.fail("您输入的内容已存在, 请重新输入...");
	}
	
	/**
	 * 获取所有下级节点ID
	 * 
	 * @param treeNodeId
	 * @return
	 */
	public List<String> getOrgNodeIds(String treeNodeId) {
		
		List<String> results = new ArrayList<String>();
		
		if (StringUtils.isNotBlank(treeNodeId)) {
			
			OrgTreeNode node = this.get(treeNodeId);
			
			if (node != null) {
				results.addAll(this.orgTreeNodeRepository.getSubIdsByParents(node.getParentIds() + "," + node.getId()));
			}
		}
		
		return results;
	}

	/**
	 * 获取组织节点树.
	 * 
	 * @param tenantId
	 * @param treeCode
	 * @param selectedNode
	 * @param defaultTree
	 * @return
	 */
	public List<TreeNode> getOrgTreeNode(String tenantId, String treeCode, String selectedNode, Boolean defaultTree) {

		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		
		OrgTree orgTree = null;
		
		// 如果传递treeCode, 则查询
		if (StringUtils.isNotBlank(treeCode)) {
			orgTree = this.orgTreeService.getOrgTreeByCode(tenantId, treeCode);
			if (orgTree == null) {
				return treeNodes;
			}
		}
		else if (defaultTree != null && defaultTree) {
			// 获取默认行政组织树
			orgTree = this.orgTreeService.getDefaultTree(tenantId);
		}
		
		System.out.println(orgTree);
		
		// 如果不是空的, 则查询奇下的节点
		if (orgTree != null) {

			// 获取下级节点
			List<OrgTreeNode> nodes = orgTreeNodeRepository.getTreeNodesByCode(tenantId, orgTree.getTreeCode());
			if (nodes != null && nodes.size() > 0) {

				Map<String, TreeNode> maps = new LinkedHashMap<String, TreeNode>();

				TreeNode treeNodeTemp = null;
				for (OrgTreeNode child : nodes) {
					treeNodeTemp = new TreeNode(child.getId(), child.getNodeName(), child.getParentId());
					treeNodeTemp.setNodeLevel(child.getNodeLevel());
					treeNodeTemp.setIcon(child.getNodeIcon());
					treeNodeTemp.setExpand(true);
					treeNodeTemp.setStatus(child.getStatus());
					treeNodeTemp.setNodeType(child.getNodeType());
					treeNodeTemp.setSelected(StringUtils.equals(child.getId(), selectedNode));
					treeNodeTemp.setNodeCode(child.getNodeCode());
					maps.put(child.getId(), treeNodeTemp);
				}

				for (TreeNode treeNode : maps.values()) {
					if (StringUtils.equals("0", treeNode.getParentNodeId())) {
						treeNodes.add(treeNode);
					} else {
						maps.get(treeNode.getParentNodeId()).getChildren().add(treeNode);
					}
				}
			}
		}

		return treeNodes;
	}
	
	/**
	 * 获取组织节点树.
	 * 
	 * @param tenantId
	 * @param defaultTree
	 * @return
	 */
	public List<TreeNode> getOrgDefaultTreeNodes(String tenantId, String[] orgIds) {
		
		if (orgIds == null) {
			orgIds = new String[] {};
		}

		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		
		OrgTree orgTree = this.orgTreeService.getDefaultTree(tenantId);

		// 如果不是空的, 则查询奇下的节点
		if (orgTree != null) {

			// 获取下级节点
			List<OrgTreeNode> nodes = orgTreeNodeRepository.getTreeNodesByCode(tenantId, orgTree.getTreeCode());
			if (nodes != null && nodes.size() > 0) {

				Map<String, TreeNode> maps = new LinkedHashMap<String, TreeNode>();

				TreeNode treeNodeTemp = null;
				for (OrgTreeNode child : nodes) {
					treeNodeTemp = new TreeNode(child.getId(), child.getNodeName(), child.getParentId());
					treeNodeTemp.setNodeLevel(child.getNodeLevel());
					treeNodeTemp.setIcon(child.getNodeIcon());
					treeNodeTemp.setExpand(true);
					treeNodeTemp.setChecked(ArrayUtils.contains(orgIds, child.getOrgId()));
					treeNodeTemp.setStatus(child.getStatus());
					treeNodeTemp.setNodeType(child.getNodeType());
					maps.put(child.getId(), treeNodeTemp);
				}

				for (TreeNode treeNode : maps.values()) {
					if (StringUtils.equals("0", treeNode.getParentNodeId())) {
						treeNodes.add(treeNode);
					} else {
						maps.get(treeNode.getParentNodeId()).getChildren().add(treeNode);
					}
				}
			}
		}

		return treeNodes;
	}
}
