package com.direction.core.inf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.direction.common.exception.ServiceException;
import com.direction.common.tree.TreeNode;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.inf.sys.user.IUserInfo.UserType;

// ~ File Information
// ====================================================================================================================

public class UserDataPermsUtils {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取用户权限.
	 * 
	 * @param userPropName
	 * @param root
	 * @param criteriaBuilder
	 */
	public static List<Predicate> getUserDataPermPredicates(String userPropName,
	    Root<?> root,
	    CriteriaBuilder criteriaBuilder) {

		if (StringUtils.isAnyBlank(userPropName)) {
			throw new ServiceException("用户属性为空, 不能进行查询");
		} else if (root == null) {
			throw new ServiceException("JPA ROOT IS NULL");
		} else if (criteriaBuilder == null) {
			throw new ServiceException("JPA CRITERIABUILDER IS NULL");
		}

		List<Predicate> predicateList = new ArrayList<>();

		UserProfile user = UserProfileUtils.getUserProfile();

		// 如果用户为空, 则赋值不存在的ID, 如果不为空则赋值当前用户的权限数据
		if (user != null) {

			// 判断用户
			if (WeightsConst.WEIGHTS_USER == user.getWeights()) {
				predicateList.add(criteriaBuilder.equal(root.get(userPropName).as(String.class), user.getUserId()));
			}
		} else {
			predicateList.add(criteriaBuilder.equal(root.get(userPropName).as(String.class), "IS_DATA_PREM_NULL"));
		}

		return predicateList;
	}

	/**
	 * 获取机构权限.
	 * 
	 * @param orgPropName
	 * @param root
	 * @param criteriaBuilder
	 */
	public static List<Predicate> getOrgDataPermPredicates(String orgPropName,
	    Root<?> root,
	    CriteriaBuilder criteriaBuilder) {

		if (StringUtils.isAnyBlank(orgPropName)) {
			throw new ServiceException("企业属性为空, 不能进行查询");
		} else if (root == null) {
			throw new ServiceException("JPA ROOT IS NULL");
		} else if (criteriaBuilder == null) {
			throw new ServiceException("JPA CRITERIABUILDER IS NULL");
		}

		List<Predicate> predicateList = new ArrayList<>();

		UserProfile user = UserProfileUtils.getUserProfile();

		// 如果用户为空, 则赋值不存在的ID, 如果不为空则赋值当前用户的权限数据
		if (user != null) {

			// 如果是超级管理员, 则不加入该权限认证, 非超级管理员, 则判断用户
			if (!user.isSuperAdmin()) {

				// 判断用户是否机构管理员用户, 如果是机构管理员, 则加载机构下所有ID
				if (WeightsConst.WEIGHTS_MANAGER == user.getWeights()) {
					CriteriaBuilder.In<String> in = criteriaBuilder.in(root.get(orgPropName).as(String.class));
					in.value(user.getOrgId());

					List<String> orgIds = EIPService.getOrgTreeService().getOrgTreeNodeIds(user.getOrgId());

					for (String id : orgIds) {
						in.value(id);
					}

					predicateList.add(in);
				}
				// 如果是用户级别, 则获取该用户所负责机构的ID, 如果没有负责机构的ID, 则获取自己的USER_ID作为查询条件
				else if (WeightsConst.WEIGHTS_USER == user.getWeights()) {

					// 获取该用户负责的机构IDS
					Set<String> orgIds = EIPService.getDataPermService().getSubListOrg(user.getUserId());
					CriteriaBuilder.In<String> inOrgIds = null;

					if (orgIds != null && orgIds.size() > 0) {
						inOrgIds = criteriaBuilder.in(root.get(orgPropName).as(String.class));
						for (String id : orgIds) {
							inOrgIds.value(id);
						}
					}

					if (inOrgIds != null) {
						predicateList.add(inOrgIds);
					} else {
						predicateList.add(criteriaBuilder.equal(root.get(orgPropName).as(String.class), "IS_DATA_PREM_NULL"));
					}
				} else {
					predicateList.add(criteriaBuilder.equal(root.get(orgPropName).as(String.class), "IS_DATA_PREM_NULL"));
				}
			}
		} else {
			predicateList.add(criteriaBuilder.equal(root.get(orgPropName).as(String.class), "IS_DATA_PREM_NULL"));
		}

		return predicateList;
	}

	/**
	 * 获取机构和用户数据权限.
	 * 
	 * @param orgPropName
	 * @param userPropName
	 * @param root
	 * @param criteriaBuilder
	 */
	public static List<Predicate> getDataPermPredicates(String orgPropName,
	    String userPropName,
	    Root<?> root,
	    CriteriaBuilder criteriaBuilder) {

		return UserDataPermsUtils.getDataPermPredicates(orgPropName, userPropName, null, root, criteriaBuilder);
	}

	/**
	 * 获取机构和用户数据权限.
	 * 
	 * @param orgPropName
	 * @param userPropName
	 * @param type
	 *          空值或者其他都代表user, EMPLOYEE代表员工
	 * @param root
	 * @param criteriaBuilder
	 */
	public static List<Predicate> getDataPermPredicates(String orgPropName,
	    String userPropName,
	    String type,
	    Root<?> root,
	    CriteriaBuilder criteriaBuilder) {

		if (StringUtils.isAnyBlank(orgPropName, userPropName)) {
			throw new ServiceException("企业属性或者用户属性为空, 不能进行查询");
		} else if (root == null) {
			throw new ServiceException("JPA ROOT IS NULL");
		} else if (criteriaBuilder == null) {
			throw new ServiceException("JPA CRITERIABUILDER IS NULL");
		}

		List<Predicate> predicateList = new ArrayList<>();

		UserProfile user = UserProfileUtils.getUserProfile();

		// 如果用户为空, 则赋值不存在的ID, 如果不为空则赋值当前用户的权限数据
		if (user != null) {

			// 如果是超级管理员, 则不加入该权限认证, 非超级管理员, 则判断用户
			if (!user.isSuperAdmin()) {

				// 判断用户是否机构管理员用户, 如果是机构管理员, 则加载机构下所有ID
				if (WeightsConst.WEIGHTS_MANAGER == user.getWeights()) {
					CriteriaBuilder.In<String> in = criteriaBuilder.in(root.get(orgPropName).as(String.class));
					in.value(user.getOrgId());

					List<String> orgIds = EIPService.getOrgTreeService().getOrgTreeNodeIds(user.getOrgId());

					for (String id : orgIds) {
						in.value(id);
					}

					predicateList.add(in);
				}
				// 如果是用户级别, 则获取该用户所负责机构的ID, 如果没有负责机构的ID, 则获取自己的USER_ID作为查询条件
				else if (WeightsConst.WEIGHTS_USER == user.getWeights()) {

					// 获取该用户负责的机构IDS
					Set<String> orgIds = EIPService.getDataPermService().getSubListOrg(user.getUserId());
					CriteriaBuilder.In<String> inOrgIds = null;
					Predicate eq = null;

					if (orgIds != null && orgIds.size() > 0) {
						inOrgIds = criteriaBuilder.in(root.get(orgPropName).as(String.class));
						for (String id : orgIds) {
							inOrgIds.value(id);
						}
					}

					if (StringUtils.isNotBlank(type) && StringUtils.equals(type, "EMPLOYEE")) {
						// 增加用户权限
						eq = criteriaBuilder.equal(root.get(userPropName).as(String.class), user.getEmpId());
					} else {
						eq = criteriaBuilder.equal(root.get(userPropName).as(String.class), user.getUserId());
					}

					// 判断是否有所负责的机构, 有加入, 没有不加入
					if (inOrgIds == null) {
						predicateList.add(eq);
					} else {
						predicateList.add(criteriaBuilder.or(inOrgIds, eq));
					}
				} else {
					predicateList.add(criteriaBuilder.equal(root.get(orgPropName).as(String.class), "IS_DATA_PREM_NULL"));
				}
			}
		} else {
			predicateList.add(criteriaBuilder.equal(root.get(orgPropName).as(String.class), "IS_DATA_PREM_NULL"));
		}

		return predicateList;
	}

	/**
	 * 获取当前用户所能负责的组织树.
	 * 
	 * @param treeNodes
	 * @return
	 */
	public static List<TreeNode> getDataPermsTreeNode(List<TreeNode> treeNodes) {

		List<TreeNode> nodes = new ArrayList<TreeNode>();

		if (treeNodes == null) {
			return nodes;
		}

		// 获取当前登陆用户
		UserProfile user = UserProfileUtils.getUserProfile();

		if (user == null) {
			return nodes;
		}
		// 非超级管理员用户
		else if (!user.isSuperAdmin()) {
			
			// 管理员用户
			if (StringUtils.equals(user.getUserType(), UserType.MANAGE)) {

				TreeNode node = UserDataPermsUtils.getNode(user.getOrgId(), treeNodes);
				if (node != null) {
					nodes.add(node);
				}
			}
			// 如果是用户级别, 则获取该用户所负责机构的ID, 如果没有负责机构的ID, 则获取自己的USER_ID作为查询条件
			else if (StringUtils.equalsAny(user.getUserType(), UserType.EMPLOYEE, UserType.USER)) {

				// 获取管理的节点
				Set<String> orgIds = EIPService.getDataPermService().getSubListOrg(user.getUserId());

				if (orgIds != null && orgIds.size() > 0) {
					List<TreeNode> orgNodes = new ArrayList<>();
					UserDataPermsUtils.loadNodes(orgIds, treeNodes, orgNodes);
					nodes.addAll(orgNodes);

					if (!orgIds.contains(user.getOrgId())) {
						TreeNode node = UserDataPermsUtils.getNode(user.getOrgId(), treeNodes);
						if (node != null) {
							node.setChildren(new ArrayList<>());
							nodes.add(node);
						}
					}
				}
				// 当前用户节点
				else {
					TreeNode node = UserDataPermsUtils.getNode(user.getOrgId(), treeNodes);
					if (node != null) {
						node.setChildren(new ArrayList<>());
						nodes.add(node);
					}
				}
			}

			// 判断nodes是否为空如果不为空则选择第一个
//			if (nodes != null && nodes.size() > 0) {
//				nodes.get(0).setSelected(true);
//			}

			return nodes;
		}

		return treeNodes;
	}

	/**
	 * 获取组织树节点.
	 * 
	 * @param orgIds
	 * @param treeNodes
	 * @param values
	 */
	private static void loadNodes(Collection<String> orgIds, List<TreeNode> treeNodes, List<TreeNode> values) {

		if (treeNodes != null && treeNodes.size() > 0) {
			for (TreeNode treeNode : treeNodes) {
				if (orgIds.contains(treeNode.getNodeId())) {
					values.add(treeNode);
				} else {
					UserDataPermsUtils.loadNodes(orgIds, treeNode.getChildren(), values);
				}
			}
		}

		return;
	}

	/**
	 * 查询节点树.
	 * 
	 * @param orgNodeId
	 * @param treeNodes
	 * @return
	 */
	private static TreeNode getNode(String orgNodeId, List<TreeNode> treeNodes) {

		if (StringUtils.isNotBlank(orgNodeId) && treeNodes != null && treeNodes.size() > 0) {
			for (TreeNode node : treeNodes) {
				if (StringUtils.equals(node.getNodeId(), orgNodeId)) {
					return node;
				}

				// 获取下级
				TreeNode treeNode = UserDataPermsUtils.getNode(orgNodeId, node.getChildren());
				if (treeNode != null) {
					return treeNode;
				}
			}
		}

		return null;
	}
}
