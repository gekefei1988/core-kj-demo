package com.direction.core.modules.sys.menu.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.DataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseMenu extends DataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -5262139055644017184L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_menu_pk")
	@GenericGenerator(name = "sys_menu_pk", strategy = "uuid")
	@Column(name = "menu_id", unique = true, nullable = false)
	private String menuId;

	@Column(name = "menu_code")
	@NotBlank(message = "菜单编号不能为空")
	private String menuCode;

	@Column(name = "menu_name")
	@NotBlank(message = "菜单名称不能为空")
	private String menuName;

	@Column(name = "parent_id")
	private String parentId;

	@Column(name = "parent_ids")
	private String parentIds;

	@Column(name = "order_num")
	@NotNull(message = "菜单显示排序不能为空")
	private Integer displayOrder;

	@Column(name = "url")
	private String url;

	@Column(name = "menu_type", columnDefinition = "CHAR")
	@NotBlank(message = "菜单类型不能为空")
	private String menuType;

	@Column(name = "perms")
	private String perms;

	// 是否显示菜单
	@Column(name = "visible", columnDefinition = "CHAR")
	private String visible;

	// 是否显示目录, 面包屑
	@Column(name = "show_folder", columnDefinition = "CHAR")
	private String showFolder;

	@Column(name = "use_cache", columnDefinition = "CHAR")
	private String useCache;

	@Column(name = "icon")
	private String icon;

	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	@Column(name = "tenant_id")
	private String tenantId;

	// 权重
	@Column(name = "weights")
	private Integer weights;

	@Column(name = "extend_s1")
	private String extendS1;

	@Column(name = "extend_s2")
	private String extendS2;

	@Column(name = "extend_s3")
	private String extendS3;

	@Column(name = "extend_s4")
	private String extendS4;

	@Column(name = "extend_d1")
	private Date extendD1;

	@Column(name = "extend_d2")
	private Date extendD2;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getMenuId() {

		return menuId;
	}

	public String getTenantId() {

		return tenantId;
	}

	public void setTenantId(String tenantId) {

		this.tenantId = tenantId;
	}

	public void setMenuId(String menuId) {

		this.menuId = menuId;
	}

	public String getMenuName() {

		return menuName;
	}

	public void setMenuName(String menuName) {

		this.menuName = menuName;
	}

	public String getParentId() {

		return parentId;
	}

	public void setParentId(String parentId) {

		this.parentId = parentId;
	}

	public Integer getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {

		this.displayOrder = displayOrder;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public String getMenuType() {

		return menuType;
	}

	public void setMenuType(String menuType) {

		this.menuType = menuType;
	}

	public String getVisible() {

		return visible;
	}

	public void setVisible(String visible) {

		this.visible = visible;
	}

	public String getShowFolder() {

		return showFolder;
	}

	public void setShowFolder(String showFolder) {

		this.showFolder = showFolder;
	}

	public Integer getWeights() {

		return weights;
	}

	public void setWeights(Integer weights) {

		this.weights = weights;
	}

	public String getUseCache() {

		return useCache;
	}

	public void setUseCache(String useCache) {

		this.useCache = useCache;
	}

	public String getPerms() {

		return perms;
	}

	public void setPerms(String perms) {

		this.perms = perms;
	}

	public String getIcon() {

		return icon;
	}

	public void setIcon(String icon) {

		this.icon = icon;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getParentIds() {

		return parentIds;
	}

	public void setParentIds(String parentIds) {

		this.parentIds = parentIds;
	}

	public String getExtendS1() {

		return extendS1;
	}

	public void setExtendS1(String extendS1) {

		this.extendS1 = extendS1;
	}

	public String getExtendS2() {

		return extendS2;
	}

	public void setExtendS2(String extendS2) {

		this.extendS2 = extendS2;
	}

	public String getExtendS3() {

		return extendS3;
	}

	public void setExtendS3(String extendS3) {

		this.extendS3 = extendS3;
	}

	public String getExtendS4() {

		return extendS4;
	}

	public void setExtendS4(String extendS4) {

		this.extendS4 = extendS4;
	}

	public String getMenuCode() {

		return menuCode;
	}

	public void setMenuCode(String menuCode) {

		this.menuCode = menuCode;
	}

	public Date getExtendD1() {

		return extendD1;
	}

	public void setExtendD1(Date extendD1) {

		this.extendD1 = extendD1;
	}

	public Date getExtendD2() {

		return extendD2;
	}

	public void setExtendD2(Date extendD2) {

		this.extendD2 = extendD2;
	}
}
