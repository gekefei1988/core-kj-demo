package com.direction.core.modules.sys.role_menu.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

// ~ File Information
// ====================================================================================================================

public class RoleMenuKey implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================
	private static final long serialVersionUID = 5414399382483306477L;

	// ~ Fields
	// ==================================================================================================================

	@Column(name = "menu_id")
	private String menuId;

	@Column(name = "role_id")
	private String roleId;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getMenuId() {

		return menuId;
	}

	public void setMenuId(String menuId) {

		this.menuId = menuId;
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
		RoleMenuKey modelKey = (RoleMenuKey) o;
		return Objects.equals(menuId, modelKey.menuId) && Objects.equals(roleId, modelKey.roleId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(menuId, roleId);
	}

}
