package com.direction.core.modules.sys.user.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

// ~ File Information
// ====================================================================================================================

public class UserRolePK implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 7379606775564277938L;

	// ~ Fields
	// ==================================================================================================================

	@Column(name = "user_id")
	private String userId;

	@Column(name = "role_id")
	private String roleId;

	// ~ Constructors
	// ==================================================================================================================
	
	public UserRolePK() {}
	
	public UserRolePK(String roleId, String userId) {
		this.roleId = roleId;
		this.userId = userId;
	}

	// ~ Methods
	// ==================================================================================================================

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public String getRoleId() {

		return roleId;
	}

	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserRolePK userRolePK = (UserRolePK) o;
		return Objects.equals(userId, userRolePK.userId) && Objects.equals(roleId, userRolePK.roleId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(userId, roleId);
	}
}
