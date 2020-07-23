package com.direction.core.modules.sys.org.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.core.inf.EIPService;
import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.org.entity.OrgTree;
import com.direction.core.modules.sys.org.entity.OrgTree.OrgAdministrative;
import com.direction.core.modules.sys.org.entity.OrgTreeNode;
import com.direction.core.modules.sys.org.repository.OrgTreeRepository;
import com.direction.core.modules.sys.tenant.entity.Tenant;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 组织数据业务逻辑层.
 * 
 * <pre>
 * 组织数据业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class OrgTreeService extends BaseService<OrgTreeRepository, OrgTree, String> {

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
	 * 获取所有组织树.
	 * 
	 * @param tenantId
	 * @param status
	 * @return
	 */
	public List<OrgTree> getOrgTreesByStatus(String tenantId, String status) {

		return getBaseRepository().getOrgTreesByStatus(tenantId, status);
	}

	/**
	 * 根据租户ID获取树结构.
	 * 
	 * @param tenantId
	 * @return
	 */
	public List<OrgTree> getTreesByTenant(String tenantId) {

		return getBaseRepository().getTreesByTenant(tenantId);
	}

	/**
	 * 获取默认组织树.
	 * 
	 * @param tenantId
	 * @return
	 */
	public OrgTree getDefaultTree(String tenantId) {

		return getBaseRepository().getDefaultTree(tenantId);
	}

	/**
	 * 根据编号获取组织树.
	 * 
	 * @param tenantId
	 * @param treeCode
	 * @return
	 */
	public OrgTree getOrgTreeByCode(String tenantId, String treeCode) {

		return getBaseRepository().getOrgTreeByCode(tenantId, treeCode);
	}
	
//	/**
//	 * 初始化组织结构树数据.
//	 * 
//	 */
//	@Deprecated
//	public void initOrgTreeDatas() {
//		
//		List<OrgTree> orgTrees = this.findAll();
//		
//		if (orgTrees != null && orgTrees.size() > 0) {
//			
//			List<OrgTreeNode> treeNodes = null;
//			for (OrgTree orgTree : orgTrees) {
//				//TODO
//			}
//		}
//		
//		// 放入缓存
//	}

	/**
	 * 保存组织树.
	 * 
	 * @param orgTree
	 * @return
	 */
	@Transactional(readOnly = false)
	public OrgTree save(OrgTree orgTree) {

		if (orgTree == null) {
			throw new ServiceException("组织树对象为空，不能进行保存");
		} else if (StringUtils.isBlank(orgTree.getTenantId())) {
			throw new ServiceException("租户对象为空，不能进行保存");
		}

		// 该组织树是否行政组织树, 如果是修改其他组织树为非行政
		if (orgTree.isDefaultTree()) {
			getBaseRepository().updateOrgTreeDefualt(orgTree.getTenantId());
		}

		getBaseRepository().save(orgTree);
		return orgTree;
	}

	/**
	 * 删除组织树.
	 * 
	 * @param treeId
	 */
	@Transactional(readOnly = false)
	public void delete(String treeId) {

		if (StringUtils.isBlank(treeId)) {
			throw new ServiceException("主键不存在!");
		}

		getBaseRepository().deleteById(treeId);
	}

	/**
	 * 删除组织树.
	 * 
	 * @param tenantId
	 * @param treeCode
	 */
	@Transactional(readOnly = false)
	public void deleteByCode(String tenantId, String treeCode) {

		if (StringUtils.isBlank(treeCode)) {
			throw new ServiceException("组织树编号不存在!");
		}

		getBaseRepository().deleteByCode(tenantId, treeCode);
	}

	/**
	 * 根据租户信息创建默认组织树.
	 * 
	 * @param tenant
	 */
	@Transactional(readOnly = false)
	public OrgTreeNode createTenantOrgTree(Tenant tenant) {

		if (tenant == null) {
			throw new ServiceException("租户信息为空, 不能创建组织树");
		}

		// 创建行政组织树
		OrgTree orgTree = getBaseRepository().getDefaultTree(tenant.getId());

		if (orgTree == null) {
			orgTree = new OrgTree();
		}

		orgTree.setDefaultTree(true);
		orgTree.setStatus(StatusConst.ENABLE);
		orgTree.setTenantId(tenant.getId());
		orgTree.setTreeCode(OrgAdministrative.ORG_DEFAULT_NO);
		orgTree.setTreeName(OrgAdministrative.ORG_DEFAULT_NAME);

		this.save(orgTree);

		// 清空组织节点信息
		this.orgTreeNodeService.deleteByTenant(tenant.getId());

		// 重新创建节点
		OrgTreeNode defaultNode = new OrgTreeNode();
		defaultNode.setAreaCodes(tenant.getTenantName());
		defaultNode.setDisplayOrder(0);
		defaultNode.setNodeCode(tenant.getTenantNo());
		defaultNode.setNodeName(tenant.getTenantName());
		defaultNode.setFullNodeName(tenant.getTenantName());
		defaultNode.setTreeCode(orgTree.getTreeCode());
		defaultNode.setTenantId(orgTree.getTenantId());
		defaultNode.setTelephone(tenant.getTelephone());
		defaultNode.setNodeType(EIPService.getDictService().getTopDefaultValue("sys-org-category"));
		defaultNode.setNodeLevel(0);
		defaultNode.setStatus(StatusConst.ENABLE);

		// 保存节点信息
		this.orgTreeNodeService.save(defaultNode);

		return defaultNode;
	}

	/**
	 * 组织树分页.
	 * 
	 * @param area
	 * @param page
	 * @return
	 */
	public VuePage<OrgTree> findPage(OrgTree orgTree, VuePage<OrgTree> page) {

		// 封装查询条件
		Specification<OrgTree> treeParams = new Specification<OrgTree>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<OrgTree> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();

				// 租户信息
				if (orgTree != null && StringUtils.isNotBlank(orgTree.getTenantId())) {
					predicateList.add(criteriaBuilder.equal(root.get("tenantId"), orgTree.getTenantId()));
				}

				if (orgTree != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(orgTree.getTreeName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("treeName").as(String.class), "%" + orgTree.getTreeName() + "%"),
						    criteriaBuilder.like(root.get("treeCode").as(String.class), "%" + orgTree.getTreeName() + "%")));
					}

					// 判断状态
					if (StringUtils.isNotBlank(orgTree.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), orgTree.getStatus()));
					}
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<OrgTree> treePage = getBaseRepository().findAll(treeParams, page.getPageable());
		page.setPage(treePage);

		return page;
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param tenantId
	 * @param propName
	 * @param propValue
	 * @param treeId
	 * @return
	 */
	public ResultJson validateOnlyOne(String tenantId, String propName, String propValue, String treeId) {

		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}

		Specification<OrgTree> params = new Specification<OrgTree>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<OrgTree> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(treeId)) {

					return criteriaBuilder.and(criteriaBuilder.notEqual(root.get("treeId").as(String.class), treeId),
					    criteriaBuilder.equal(root.get("tenantId"), tenantId),
					    criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				} else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};

		if (getBaseRepository().count(params) == 0) {
			return ResultJson.success();
		}

		return ResultJson.fail("您输入的内容已存在, 请重新输入...");
	}
}
