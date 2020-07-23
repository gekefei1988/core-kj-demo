package com.direction.core.modules.sys.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.tree.TreeNode;
import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.WeightsConst;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.modules.sys.menu.entity.Menu;
import com.direction.core.modules.sys.menu.entity.Menu.MenuType;
import com.direction.core.modules.sys.menu.entity.MenuPermsVO;
import com.direction.core.modules.sys.menu.entity.MenuVO;
import com.direction.core.modules.sys.menu.repository.MenuRepository;
import com.direction.core.modules.sys.role_menu.entity.RoleMenuVO;
import com.direction.core.modules.sys.role_menu.repository.RoleMenuRepository;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class MenuService extends BaseService<MenuRepository, Menu, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private RoleMenuRepository roleMenuRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 清空缓存.
	 * 
	 */
	public void clearCache() {
		CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_all", "getMenuVOTrees_superAdmin", "getMenuVOTrees_admin");
	}

	/**
	 * 保存菜单信息.
	 * 
	 * @param menu
	 * @return
	 */
	@Transactional(readOnly = false)
	public Menu save(Menu menu) {

		try {

			// 如果菜单父级是空的, 则赋值为0
			if (StringUtils.isBlank(menu.getParentId())) {
				menu.setParentId("0");
			}

			// 如果非顶级菜单
			if (!StringUtils.equals(menu.getParentId(), "0")) {
				Menu parentMenu = this.get(menu.getParentId());
				if (parentMenu == null) {
					throw new ServiceException("上级菜单已不存在");
				}

				menu.setParentIds(parentMenu.getParentIds() + "," + parentMenu.getMenuId());
			} else {
				menu.setParentIds(menu.getParentId());
			}
			
			// 是否为修改, 修改时需要判断下级
			if (StringUtils.isNotBlank(menu.getMenuId())) {
				
				// 获取原始
				Menu baseMenu = this.get(menu.getMenuId());
				
				// 判断父级是否发生变更, 如果发生变更需要调整下级
				if (!StringUtils.equals(menu.getParentId(), baseMenu.getParentId())) {
					
					// 修改下级菜单父级ID
					this.updateSubMenuParentIds(menu);
				}
			}
			
			// 保存菜单信息
			this.getBaseRepository().save(menu);

			// 清空缓存
			clearCache();

			return menu;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("菜单保存失败, 错误信息: " + e.getMessage());
		}
	}
	
	/**
	 * 同步更新下级菜单父级.
	 * 
	 * @param menu
	 */
	@Transactional(readOnly = false)
	public void updateSubMenuParentIds(Menu menu) {
			
		// 获取下级菜单
		List<Menu> children = this.getBaseRepository().getSubMenusByParent(menu.getMenuId());
		
		if (children != null && children.size() > 0) {
			for (Menu child : children) {
				child.setParentIds(menu.getParentIds() + "," + menu.getMenuId());
				this.getBaseRepository().save(child);
				
				// 更改下级
				this.updateSubMenuParentIds(child);
			}
		}
		return;
	}

	/**
	 * 根据主键获取菜单信息.
	 * 
	 * @param id
	 * @return
	 */
	public Menu get(String id) {

		if (StringUtils.isBlank(id)) {
			return null;
		}

		return this.getBaseRepository().get(id);
	}

	/**
	 * 根据编号获取菜单信息.
	 * 
	 * @param menuCode
	 * @return
	 */
	public Menu getCode(String menuCode) {

		if (StringUtils.isBlank(menuCode)) {
			return null;
		}
		return this.getBaseRepository().getCode(menuCode);
	}

	/**
	 * 根据ID删除菜单信息.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {

		Menu menu = this.get(id);

		if (menu == null) {
			throw new ServiceException("该菜单已不存在");
		}

		// 删除所有下级
		this.getBaseRepository().deleteSubsByParent(menu.getParentIds() + "," + menu.getMenuId());

		// 删除菜单
		this.getBaseRepository().delete(menu);

		// 清空缓存
		clearCache();
	}

	/**
	 * 根据用户ID查询权限
	 * 
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	public List<String> findPermsByUserId(String userId) {
		
		if (StringUtils.isBlank(userId)) {
			return new ArrayList<>();
		}

		return getBaseRepository().findPermsByUserId(userId);
	}

	/**
	 * 查询所有用户权限.
	 * 
	 * @return
	 */
	public List<String> findAllPerms() {

		return getBaseRepository().findAllPerms();
	}

	/**
	 * 系统初始化时
	 * 
	 * @return
	 */
	public List<MenuPermsVO> findPermsByRole() {

		// 返回数据必须是2个字段
		return getBaseRepository().findPermsByRole();
	}

	/**
	 * 获取菜单树结构.
	 * 
	 * @param menuType
	 * @return
	 */
	public List<TreeNode> getMenuTrees(String menuType) {

		List<TreeNode> treeNodes = new ArrayList<TreeNode>();

		// 查询所有菜单
		List<Menu> allChildrens = null;

		if (StringUtils.isNotBlank(menuType)) {
			allChildrens = this.getBaseRepository().getMenusByType(menuType);
		} else {
			allChildrens = this.getBaseRepository().findAll(new Sort(Sort.Direction.ASC, "displayOrder"));
		}

		if (allChildrens != null && allChildrens.size() > 0) {

			Map<String, TreeNode> maps = new LinkedHashMap<String, TreeNode>();

			TreeNode treeNodeTemp = null;
			for (Menu child : allChildrens) {
				treeNodeTemp = new TreeNode(child.getMenuId(), child.getMenuName(), child.getParentId());
				treeNodeTemp.setIcon(child.getIcon());
				treeNodeTemp.setExpand(StringUtils.equals(child.getUrl(), "Main"));
				treeNodeTemp.setDisabled(!StringUtils.equals(StatusConst.ENABLE, child.getStatus()));
				maps.put(child.getMenuId(), treeNodeTemp);
			}

			for (TreeNode treeNode : maps.values()) {
				if (StringUtils.equals("0", treeNode.getParentNodeId())) {
					treeNodes.add(treeNode);
				} else {
					maps.get(treeNode.getParentNodeId()).getChildren().add(treeNode);
				}
			}
		}

		return treeNodes;
	}

	/**
	 * 获取菜单结构.
	 * 
	 * @return
	 */
	public List<Menu> getAllMenus() {
		
		@SuppressWarnings("unchecked")
		List<Menu> menus = (List<Menu>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_all");
		
		if (menus != null) {
			return menus;
		}
		
		menus = this.getBaseRepository().getMenusByType(MenuType.MENU);
		
		// 加入缓存
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_all", menus);
		
		return menus;
	}

	/**
	 * 获取超级管理员菜单.
	 * 
	 * @return
	 */
	public List<MenuVO> getMenuVOTreesBySuperAdmin() {

		// 缓存中获取菜单列表
		@SuppressWarnings("unchecked")
		List<MenuVO> treeNodes = (List<MenuVO>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_superAdmin");

		if (treeNodes != null) {
			return treeNodes;
		}

		treeNodes = new ArrayList<MenuVO>();

		// 查询所有菜单
		List<Menu> allChildrens = this.getAllMenus();

		if (allChildrens != null && allChildrens.size() > 0) {

			Map<String, MenuVO> maps = new LinkedHashMap<String, MenuVO>();

			MenuVO treeNodeTemp = null;
			for (Menu child : allChildrens) {
				
				// 菜单启用, 并且是超级管理员权限
				if (StringUtils.equals(child.getStatus(), StatusConst.ENABLE) 
						&& ((child.getWeights() & WeightsConst.WEIGHTS_SUPER_MANAGER) == WeightsConst.WEIGHTS_SUPER_MANAGER)) {
					treeNodeTemp = new MenuVO(child.getMenuId(), child.getMenuCode(), child.getMenuType(), child.getParentId(),
					    child.getUrl());
					treeNodeTemp.setWeights(child.getWeights());
					treeNodeTemp.getMeta().setIcon(child.getIcon());
					treeNodeTemp.getMeta().setNotCache(!child.isUsedCache());
					treeNodeTemp.getMeta().setShowAlways(child.isShowedFolder());
					treeNodeTemp.getMeta().setTitle(child.getMenuName());
					treeNodeTemp.getMeta().setHideInMenu(!child.isVisibled() || !StringUtils.equals(StatusConst.ENABLE, child.getStatus()));
					maps.put(child.getMenuId(), treeNodeTemp);
				}
			}

			for (MenuVO treeNode : maps.values()) {
				if (StringUtils.equals("0", treeNode.getParentId())) {
					treeNodes.add(treeNode);
				} else if (maps.containsKey(treeNode.getParentId())) {
					maps.get(treeNode.getParentId()).getChildren().add(treeNode);
				}
			}
		}

		// 将超级管理员菜单放入缓存
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_superAdmin", treeNodes);

		return treeNodes;
	}

	/**
	 * 管理员角色.
	 * 
	 * @return
	 */
	public List<MenuVO> getMenuVOTreesByAdmin() {

		// 缓存中获取菜单列表
		@SuppressWarnings("unchecked")
		List<MenuVO> treeNodes = (List<MenuVO>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_admin");

		if (treeNodes != null) {
			return treeNodes;
		}

		treeNodes = new ArrayList<MenuVO>();

		// 查询所有菜单
		List<Menu> allChildrens = this.getAllMenus();

		if (allChildrens != null && allChildrens.size() > 0) {

			Map<String, MenuVO> maps = new LinkedHashMap<String, MenuVO>();

			MenuVO treeNodeTemp = null;
			for (Menu child : allChildrens) {
				
				// 菜单启用, 并且包含二级管理员权限
				if (StringUtils.equals(child.getStatus(), StatusConst.ENABLE) 
						&& ((child.getWeights() & WeightsConst.WEIGHTS_MANAGER) == WeightsConst.WEIGHTS_MANAGER)) {
					treeNodeTemp = new MenuVO(child.getMenuId(), child.getMenuCode(), child.getMenuType(), child.getParentId(),
					    child.getUrl());
					treeNodeTemp.setWeights(child.getWeights());
					treeNodeTemp.getMeta().setIcon(child.getIcon());
					treeNodeTemp.getMeta().setNotCache(!child.isUsedCache());
					treeNodeTemp.getMeta().setShowAlways(child.isShowedFolder());
					treeNodeTemp.getMeta().setTitle(child.getMenuName());
					treeNodeTemp.getMeta().setHideInMenu(!child.isVisibled() || !StringUtils.equals(StatusConst.ENABLE, child.getStatus()));
					maps.put(child.getMenuId(), treeNodeTemp);
				}
			}

			for (MenuVO treeNode : maps.values()) {
				if (StringUtils.equals("0", treeNode.getParentId())) {
					treeNodes.add(treeNode);
				} else if (maps.containsKey(treeNode.getParentId())) {
					maps.get(treeNode.getParentId()).getChildren().add(treeNode);
				}
			}
		}

		// 管理员缓存
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_admin", treeNodes);

		return treeNodes;
	}

	/**
	 * 用户菜单权限.
	 * 
	 * @param user
	 * @return
	 */
	public List<MenuVO> getMenuVOTreesByUser(List<String> roleIds) {

		// 缓存中获取菜单列表
		List<MenuVO> treeNodes = new ArrayList<MenuVO>();

		// 查询所有菜单
		List<Menu> allChildrens = this.getAllMenus();

		if (allChildrens != null && allChildrens.size() > 0) {

			// 查询所有角色菜单
			List<RoleMenuVO> roleMenus = this.roleMenuRepository.findByRoleIds(roleIds);

			Map<String, String> menuMaps = new HashMap<String, String>();

			if (roleMenus != null && roleMenus.size() > 0) {
				for (RoleMenuVO roleMenu : roleMenus) {
					menuMaps.put(roleMenu.getMenuId(), roleMenu.getRoleId());
				}
			}

			Map<String, MenuVO> maps = new LinkedHashMap<String, MenuVO>();

			MenuVO treeNodeTemp = null;
			for (Menu child : allChildrens) {
				
				// 菜单启用, 且角色分配, 且普通用户菜单
				if (StringUtils.equals(child.getStatus(), StatusConst.ENABLE) 
						&& menuMaps.containsKey(child.getMenuId())
						&& ((child.getWeights() & WeightsConst.WEIGHTS_USER) == WeightsConst.WEIGHTS_USER)) {
					treeNodeTemp = new MenuVO(child.getMenuId(), child.getMenuCode(), child.getMenuType(), child.getParentId(),
					    child.getUrl());
					treeNodeTemp.setWeights(child.getWeights());
					treeNodeTemp.getMeta().setIcon(child.getIcon());
					treeNodeTemp.getMeta().setNotCache(!child.isUsedCache());
					treeNodeTemp.getMeta().setShowAlways(child.isShowedFolder());
					treeNodeTemp.getMeta().setTitle(child.getMenuName());
					treeNodeTemp.getMeta().setHideInMenu(
					    !child.isVisibled() || !StringUtils.equals(StatusConst.ENABLE, child.getStatus()));
					maps.put(child.getMenuId(), treeNodeTemp);
				}
			}

			for (MenuVO treeNode : maps.values()) {
				if (StringUtils.equals("0", treeNode.getParentId())) {
					treeNodes.add(treeNode);
				} else if (maps.containsKey(treeNode.getParentId())) {
					maps.get(treeNode.getParentId()).getChildren().add(treeNode);
				}
			}
		}

		return treeNodes;
	}

	/**
	 * 根据用户获取菜单-普通用户.
	 * 
	 * @param userProfile
	 * @return
	 */
	public List<MenuVO> getMenuVOTrees(UserProfile userProfile) {

		List<MenuVO> menuVOs = new ArrayList<MenuVO>();

		// 判断用户是否存在
		if (userProfile != null) {

			switch (userProfile.getWeights()) {
				// 超级管理员
				case WeightsConst.WEIGHTS_SUPER_MANAGER: {
					menuVOs.addAll(this.getMenuVOTreesBySuperAdmin());
					break;
				}
				// 租户管理员
				case WeightsConst.WEIGHTS_MANAGER: {
					menuVOs.addAll(this.getMenuVOTreesByAdmin());
					break;
				}
				// 普通员工
				case WeightsConst.WEIGHTS_USER: {
					menuVOs.addAll(this.getMenuVOTreesByUser(userProfile.getAccess()));
					break;
				}
				default:
					break;
			}
		}

		return menuVOs;
	}

	/**
	 * 获取角色选中菜单树结构.
	 * 
	 * @param menuType
	 * @param roleId
	 * 
	 * @return
	 */
	public List<TreeNode> getCheckedMenuTrees(String menuType, String roleId) {

		List<TreeNode> treeNodes = new ArrayList<TreeNode>();

		// 查询所有菜单
		List<Menu> allChildrens = null;

		if (StringUtils.isNotBlank(menuType)) {
			allChildrens = this.getBaseRepository().getMenusByType(menuType);
		} else {
			allChildrens = this.getBaseRepository().findAll(new Sort(Sort.Direction.ASC, "displayOrder"));
		}

		if (allChildrens != null && allChildrens.size() > 0) {

			List<RoleMenuVO> roleMenus = roleMenuRepository.findByRoleId(roleId);
			Map<String, String> roleMaps = new HashMap<String, String>();

			if (roleMenus != null && roleMenus.size() > 0) {
				for (RoleMenuVO roleMenu : roleMenus) {
					roleMaps.put(roleMenu.getMenuId(), roleMenu.getMenuId());
				}
			}

			Map<String, TreeNode> maps = new LinkedHashMap<String, TreeNode>();

			TreeNode treeNodeTemp = null;
			for (Menu child : allChildrens) {
				
				// 只能分配普通用户菜单
				if ((child.getWeights() & WeightsConst.WEIGHTS_USER) == WeightsConst.WEIGHTS_USER || 
						(child.getWeights() & WeightsConst.WEIGHTS_MANAGER) == WeightsConst.WEIGHTS_MANAGER) {
					treeNodeTemp = new TreeNode(child.getMenuId(), child.getMenuName(), child.getParentId());
					treeNodeTemp.setIcon(child.getIcon());
					treeNodeTemp.setExpand(true);
					treeNodeTemp.setChecked(roleMaps.containsKey(child.getMenuId()));
					treeNodeTemp.setNodeType(child.getMenuType());
					maps.put(child.getMenuId(), treeNodeTemp);
				}
			}

			for (TreeNode treeNode : maps.values()) {
				if (StringUtils.equals("0", treeNode.getParentNodeId())) {
					treeNodes.add(treeNode);
				} else if (maps.containsKey(treeNode.getParentNodeId())) {
					maps.get(treeNode.getParentNodeId()).getChildren().add(treeNode);
				}
			}
		}

		return treeNodes;
	}

	/**
	 * 菜单分页.
	 * 
	 * @param menu
	 * @param page
	 * @return
	 */
	public VuePage<Menu> findPage(Menu menu, VuePage<Menu> page) {

		// 封装查询条件
		Specification<Menu> dictParams = new Specification<Menu>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();

				if (menu != null) {
					// 判断关键字
					if (StringUtils.isNotBlank(menu.getMenuName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("menuName").as(String.class), "%" + menu.getMenuName() + "%"),
						    criteriaBuilder.like(root.get("menuCode").as(String.class), "%" + menu.getMenuName() + "%")));
					}

					// 菜单类型
					if (StringUtils.isNotBlank(menu.getMenuType())) {
						predicateList.add(criteriaBuilder.equal(root.get("menuType").as(String.class), menu.getMenuType()));
					}

					// 判断状态
					if (StringUtils.isNotBlank(menu.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), menu.getStatus()));
					}

					// 父级
					if (StringUtils.isNotBlank(menu.getParentId())) {
						predicateList.add(criteriaBuilder.equal(root.get("parentId").as(String.class), menu.getParentId()));
					}
				}

				// 父级
				if (menu == null || StringUtils.isBlank(menu.getParentId())) {
					predicateList.add(criteriaBuilder.equal(root.get("parentId").as(String.class), "0"));
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		return this.findPage(dictParams, page);
	}
}
