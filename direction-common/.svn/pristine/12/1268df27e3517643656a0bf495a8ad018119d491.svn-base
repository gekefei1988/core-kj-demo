package com.direction.core.modules.sys.role_menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.role_menu.entity.RoleMenu;
import com.direction.core.modules.sys.role_menu.entity.RoleMenuKey;
import com.direction.core.modules.sys.role_menu.entity.RoleMenuVO;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface RoleMenuRepository extends BaseRepository<RoleMenu, RoleMenuKey> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据角色查询菜单角色.
	 * 
	 * @param roleId
	 * @return
	 */
	@Query(value = "select new com.direction.core.modules.sys.role_menu.entity.RoleMenuVO(rm.menuId, rm.roleId, r.roleCode) "
			+ "from RoleMenu rm inner join Role r on r.roleId=rm.roleId and rm.roleId = ?1")
	List<RoleMenuVO> findByRoleId(String roleId);
	
	/**
	 * 根据角色查询菜单角色.
	 * 
	 * @param roleIds
	 * @return
	 */
	@Query(value = "select new com.direction.core.modules.sys.role_menu.entity.RoleMenuVO(rm.menuId, rm.roleId, r.roleCode) "
			+ "from RoleMenu rm inner join Role r on r.roleId=rm.roleId and rm.roleId in ?1")
	List<RoleMenuVO> findByRoleIds(List<String> roleIds);
	
	@Query(value = "select r from RoleMenu r where r.menuId = ?1")
	List<RoleMenu> findByMenuId(String menuId);
	
	/**
	 * 查询角色菜单, 只查询菜单.
	 * 
	 * @return
	 */
	@Query(value = "select new com.direction.core.modules.sys.role_menu.entity.RoleMenuVO(rm.menuId, rm.roleId, r.roleCode) "
			+ "from RoleMenu rm inner join Role r on r.roleId = rm.roleId")
	List<RoleMenuVO> findAllRoleMenu();

	@Modifying
	@Query("delete from RoleMenu where roleId = ?1")
	int deleteByRoleId(String roleId);
}
