package com.direction.core.modules.sys.menu.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ~ File Information
// ====================================================================================================================

@JsonIgnoreProperties({
  "hibernateLazyInitializer",
  "handler"
})
public class MetaVO implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 5521793317711591938L;

	// ~ Fields
	// ==================================================================================================================

	private boolean hideInMenu = false;

	private boolean notCache = false;

	private boolean showAlways = true;

	private String title;

	private String icon;

	private Set<String> access = null;

	// ~ Constructors
	// ==================================================================================================================
	
	public MetaVO() {
		
//		this.access = new HashSet<String>();
//		
//		// 增加一个默认超级管理员权限
//		this.access.add(IRole.DEFAULT_SUPER_ROLE);
	}

	// ~ Methods
	// ==================================================================================================================

	public boolean isHideInMenu() {

		return hideInMenu;
	}

	public void setHideInMenu(boolean hideInMenu) {

		this.hideInMenu = hideInMenu;
	}

	public boolean isNotCache() {

		return notCache;
	}

	public void setNotCache(boolean notCache) {

		this.notCache = notCache;
	}

	public boolean isShowAlways() {

		return showAlways;
	}

	public void setShowAlways(boolean showAlways) {

		this.showAlways = showAlways;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getIcon() {

		return icon;
	}

	public void setIcon(String icon) {

		this.icon = icon;
	}

	public Set<String> getAccess() {

		return access;
	}

	public void setAccess(Set<String> access) {

		this.access = access;
	}
	
	public void addAccess(Set<String> access) {
		
		if (access != null) {
			this.access.addAll(access);
		}
	}
}
