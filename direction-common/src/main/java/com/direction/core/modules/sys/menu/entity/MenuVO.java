package com.direction.core.modules.sys.menu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ~ File Information
// ====================================================================================================================

@JsonIgnoreProperties({
  "hibernateLazyInitializer",
  "handler"
})
public class MenuVO implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 7023522158827907412L;

	private static final String COMPONENT_MAIN = "Main";
	// private static final String COMPONENT_PARENTVIEW = "parentView";

	// ~ Fields
	// ==================================================================================================================

	private String menuId;

	private String parentId;

	private String menuType;

	private String name;

	private String path;

	private String component;

	private Integer weights;

	private MetaVO meta = new MetaVO();

	private List<MenuVO> children = new ArrayList<MenuVO>();

	// ~ Constructors
	// ==================================================================================================================

	public MenuVO() {

	}

	public MenuVO(String menuId, String name, String menuType, String parentId, String component) {

		this.menuId = menuId;
		this.name = name;
		this.path = name;
		this.menuType = menuType;
		this.parentId = parentId;
		this.component = component;
	}

	// ~ Methods
	// ==================================================================================================================

	public String getMenuId() {

		return menuId;
	}

	public void setMenuId(String menuId) {

		this.menuId = menuId;
	}

	public String getMenuType() {

		return menuType;
	}

	public void setMenuType(String menuType) {

		this.menuType = menuType;
	}

	public String getParentId() {

		return parentId;
	}

	public void setParentId(String parentId) {

		this.parentId = parentId;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getPath() {

		if (StringUtils.isBlank(path)) {
			this.path = this.name;
		}

		if (StringUtils.equals(COMPONENT_MAIN, this.component) && StringUtils.indexOf(this.path, "/") != 0) {
			this.path = "/" + this.path;
		}

		return path;
	}

	public void setPath(String path) {

		this.path = path;
	}

	public String getComponent() {

		if (StringUtils.isBlank(this.component)) {
			this.component = "Main";
		}

		return component;
	}

	public void setComponent(String component) {

		this.component = component;
	}

	public MetaVO getMeta() {

		return meta;
	}

	public void setMeta(MetaVO meta) {

		this.meta = meta;
	}

	public List<MenuVO> getChildren() {

		return children;
	}

	public void setChildren(List<MenuVO> children) {

		this.children = children;
	}

	public Integer getWeights() {

		return weights;
	}

	public void setWeights(Integer weights) {

		this.weights = weights;
	}
}
