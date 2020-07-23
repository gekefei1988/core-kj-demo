package com.direction.core.modules.sys.area.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.DataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseArea extends DataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 4670646653177676305L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_area_pk")
	@GenericGenerator(name = "sys_area_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "area_code")
	private String areaCode;

	@Column(name = "area_name")
	private String areaName;

	@Column(name = "parent_code")
	private String parentCode;

	@Column(name = "parent_codes")
	private String parentCodes;

	@Column(name = "parent_names")
	private String parentNames;

	@Column(name = "display_order")
	private Integer displayOrder;

	@Column(name = "area_type", columnDefinition = "CHAR")
	private String areaType;

	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	// 是否删除
	@Column(name = "is_delete", columnDefinition = "char")
	@Type(type = "true_false")
	private boolean delete;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getAreaCode() {

		return areaCode;
	}

	public void setAreaCode(String areaCode) {

		this.areaCode = areaCode;
	}

	public String getAreaName() {

		return areaName;
	}

	public void setAreaName(String areaName) {

		this.areaName = areaName;
	}

	public String getParentCode() {

		return parentCode;
	}

	public void setParentCode(String parentCode) {

		this.parentCode = parentCode;
	}

	public Integer getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {

		this.displayOrder = displayOrder;
	}

	public String getAreaType() {

		return areaType;
	}

	public void setAreaType(String areaType) {

		this.areaType = areaType;
	}

	public String getParentNames() {

		if (StringUtils.isBlank(this.parentNames)) {
			this.parentNames = "";
		}

		return parentNames;
	}

	public void setParentNames(String parentNames) {

		this.parentNames = parentNames;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getParentCodes() {

		return parentCodes;
	}

	public void setParentCodes(String parentCodes) {

		this.parentCodes = parentCodes;
	}

	public boolean isDelete() {

		return delete;
	}

	public void setDelete(boolean delete) {

		this.delete = delete;
	}
}
