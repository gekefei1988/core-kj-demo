package com.direction.core.modules.sys.role_menu.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;
import com.direction.core.modules.sys.role.entity.Role;
import com.direction.core.modules.sys.role.service.RoleService;
import com.direction.core.modules.sys.role_menu.entity.RoleMenu;
import com.direction.core.modules.sys.role_menu.repository.RoleMenuRepository;

// ~ File Information
// ====================================================================================================================

/**
 * 角色菜单业务逻辑层.
 * 
 * <pre>
 * 	角色菜单业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class RoleMenuService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	@Autowired
	private RoleService roleService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 为角色分配菜单.
	 * 
	 * @param menuIds
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public void assignMenu(String menuIds, String id) {
		
		if (StringUtils.isBlank(id)) {
			throw new ServiceException("获取角色失败, 角色主键为空...");
		}

		try {

			// 先删除之前的菜单
			roleMenuRepository.deleteByRoleId(id);

			String[] idsList = menuIds.split(",");

			if (idsList != null && idsList.length > 0) {
				for (String str : idsList) {

					RoleMenu roleMenu = new RoleMenu();
					roleMenu.setRoleId(id);
					roleMenu.setMenuId(str);
					this.roleMenuRepository.save(roleMenu);
				}
			}
			
			// 清空缓存
			CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_all");
		} catch (Exception e) {
			throw new ServiceException("菜单授权失败...");
		}
	}
	
	/**
	 * 赋值角色菜单.
	 * 
	 * @param menuId
	 */
	@Transactional(readOnly = false)
	public void assignTenantRoleMenu(String menuId) {
		
		Role role = roleService.getTenantTemporaryRole();
		
		if (role == null) {
			throw new ServiceException("保存失败，获取角色失败");
		}

		try {

			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(role.getRoleId());
			roleMenu.setMenuId(menuId);
			this.roleMenuRepository.save(roleMenu);
			
			// 清空缓存
			CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_MENU, "getMenuVOTrees_all");
		} catch (Exception e) {
			throw new ServiceException("菜单授权失败...");
		}
	}
}
