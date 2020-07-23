package com.direction.core.modules.sys.role_menu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.direction.spring.mvc.entity.BaseEntity;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_role_menu")
@IdClass(value = RoleMenuKey.class)
public class RoleMenu extends BaseEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4995910776841618506L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	private String menuId;

	@Id
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
}
