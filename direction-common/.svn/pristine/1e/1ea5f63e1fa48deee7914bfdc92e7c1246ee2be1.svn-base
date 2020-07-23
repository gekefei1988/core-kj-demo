package com.direction.core.modules.sys.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.role.entity.Role;
import com.direction.core.modules.sys.role.entity.UserRole;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.pk.UserRolePK;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

/**
 * 用户角色关联表.
 * 
 * <pre>
 * 	用户角色关联表
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, UserRolePK> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据角色删除用户角色数据.
	 * 
	 * @param roleId
	 * @return
	 */
	@Modifying
	@Query("delete from UserRole where roleId = ?1")
	int deleteByRoleId(String roleId);
	
	/**
	 * 根据用户删除用户角色数据.
	 * 
	 * @param userId
	 * @return
	 */
	@Modifying
	@Query("delete from UserRole where userId = ?1")
	int deleteByUserId(String userId);

	/**
	 * 根据角色查询角色下用户.
	 * 
	 * @param roleId
	 * @return
	 */
	@Query("select u from User u inner join UserRole r on u.userId = r.userId and r.roleId = ?1")
	public List<User> getUsersByRoleId(String roleId);
	
	/**
	 * 根据角色查询角色下用户.
	 * 
	 * @param roleId
	 * @param tenantId
	 * @return
	 */
	@Query("select u from User u inner join UserRole r on u.userId = r.userId and r.roleId = ?1 and u.tenantId = ?2")
	public List<User> getUsersByRoleTenant(String roleId, String tenantId);
	
	/**
	 * 根据用户查询用户下角色.
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select r from Role r inner join UserRole u on u.roleId = r.roleId and u.userId = ?1")
	public List<Role> getRolesByUserId(String userId);
	
	/**
	 * 根据用户查询角色ID.
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select roleId from UserRole where userId = ?1")
	public List<String> getRoleIdsByUserId(String userId);
}
