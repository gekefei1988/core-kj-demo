package com.direction.core.modules.sys.role_menu.entity;

import com.direction.spring.mvc.entity.BaseEntity;

// ~ File Information
// ====================================================================================================================

public class RoleMenuVO extends BaseEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 3389097349694325079L;

	// ~ Fields
	// ==================================================================================================================

	private String menuId;

	private String roleId;

	private String roleCode;

	// ~ Constructors
	// ==================================================================================================================

	public RoleMenuVO() {

	}

	public RoleMenuVO(String menuId, String roleId, String roleCode) {

		this.menuId = menuId;
		this.roleId = roleId;
		this.roleCode = roleCode;
	}

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

	public String getRoleCode() {

		return roleCode;
	}

	public void setRoleCode(String roleCode) {

		this.roleCode = roleCode;
	}
}
