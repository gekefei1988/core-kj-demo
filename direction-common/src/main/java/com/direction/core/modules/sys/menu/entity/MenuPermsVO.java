package com.direction.core.modules.sys.menu.entity;

// ~ File Information
// ====================================================================================================================

/**
 * 菜单权限.
 * 
 * <pre>
 * 菜单权限
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class MenuPermsVO {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	private String url;

	private String perms;

	private String roleId;

	// ~ Constructors
	// ==================================================================================================================

	public MenuPermsVO() {

	}

	public MenuPermsVO(String perms) {

		this.perms = perms;
	}

	public MenuPermsVO(String url, String perms, String roleId) {

		this.url = url;
		this.perms = perms;
		this.roleId = roleId;
	}

	// ~ Methods
	// ==================================================================================================================

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public String getPerms() {

		return perms;
	}

	public void setPerms(String perms) {

		this.perms = perms;
	}

	public String getRoleId() {

		return roleId;
	}

	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}
}
