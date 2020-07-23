package com.direction.core.modules.sys.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direction.common.tree.TreeNode;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.menu.IMenuService;
import com.direction.core.modules.sys.menu.entity.MenuVO;

// ~ File Information
// ====================================================================================================================

@Service
public class MenuServiceImpl implements IMenuService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private MenuService menuService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取菜单树.
	 * 
	 * @see com.direction.core.inf.sys.menu.IMenuService#getMenuVOTrees()
	 */
	@Override
	public List<MenuVO> getMenuVOTrees() {
		return menuService.getMenuVOTrees(UserProfileUtils.getUserProfile());
	}

	/**
	 * 获取选中的菜单树.
	 * 
	 * @see com.direction.core.inf.sys.menu.IMenuService#getCheckedMenuTrees(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TreeNode> getCheckedMenuTrees(String menuType, String roleId) {
		return menuService.getCheckedMenuTrees(menuType, roleId);
	}

}
