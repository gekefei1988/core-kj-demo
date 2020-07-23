package com.direction.core.modules.sys.role.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.direction.core.modules.sys.user.pk.UserRolePK;
import com.direction.spring.mvc.entity.BaseEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 用户角色实体.
 * 
 * <pre>
 * 	用户角色实体
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_user_role")
@IdClass(value = UserRolePK.class)
public class UserRole extends BaseEntity {

	// ~ Static Fields
	// ==================================================================================================================
	private static final long serialVersionUID = -1357949700959822124L;
	// ~ Fields
	// ==================================================================================================================

	@Id
	private String roleId;

	@Id
	private String userId;

	// ~ Constructors
	// ==================================================================================================================

	public UserRole() {

	}

	public UserRole(String userId, String roleId) {

		this.userId = userId;
		this.roleId = roleId;
	}

	// ~ Methods
	// ==================================================================================================================

	public String getRoleId() {

		return roleId;
	}

	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}
}
