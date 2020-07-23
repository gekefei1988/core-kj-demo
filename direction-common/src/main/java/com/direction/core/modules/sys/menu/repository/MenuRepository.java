package com.direction.core.modules.sys.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.menu.entity.Menu;
import com.direction.core.modules.sys.menu.entity.MenuPermsVO;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface MenuRepository extends BaseRepository<Menu, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据ID获取菜单.
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "from Menu where menuId = ?1")
	Menu get(String id);
	
	/**
	 * 根据Code获取菜单.
	 * 
	 * @param menuCode
	 * @return
	 */
	@Query(value = "from Menu where menuCode = ?1")
	Menu getCode(String menuCode);
	
	/**
	 * 根据菜单类型查询菜单数据.
	 * 
	 * @param menuType
	 * @return
	 */
	@Query(value = "from Menu where menuType = ?1 order by displayOrder asc")
	List<Menu> getMenusByType(String menuType);
	
	/**
	 * 根据父级删除所有下级菜单.
	 * 
	 * @param parentIds
	 * @return
	 */
	@Modifying
	@Query(value = "delete from Menu where parentIds like CONCAT(?1,'%')")
	int deleteSubsByParent(String parentIds);
	
	/**
	 * 获取直接下级菜单.
	 * 
	 * @param parentId
	 * @return
	 */
	@Query("from Menu where parentId = ?1")
	List<Menu> getSubMenusByParent(String parentId);
	
	/**
	 * 根据用户id查询所有权限.
	 * 
	 * @param userid
	 * @return
	 */
	@Query("select m.perms "
			+ "from Menu m "
			+ "left join RoleMenu rm on m.menuId = rm.menuId "
			+ "left join UserRole ur on rm.roleId = ur.roleId "
			+ "where m.status = '0' and ur.userId = ?1")
	List<String> findPermsByUserId(String userId);
	
	/**
	 * 查询所有权限.
	 * 
	 * @return
	 */
	@Query(value = " select m.perms from Menu m ")
	List<String> findAllPerms();
	
	/**
	 * 查询所有关联角色的权限.
	 * 
	 * @return
	 */
	@Query("select new com.direction.core.modules.sys.menu.entity.MenuPermsVO(m.url, m.perms, r.roleId)"
			+ "from Menu m left join RoleMenu rm on m.menuId = rm.menuId left join Role r on r.roleId = rm.roleId and r.status = '0'")
	List<MenuPermsVO> findPermsByRole();
}
