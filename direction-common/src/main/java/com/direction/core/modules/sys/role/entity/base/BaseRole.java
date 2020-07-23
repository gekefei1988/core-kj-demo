package com.direction.core.modules.sys.role.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.DataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseRole extends DataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -7217982827515729304L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_role_pk")
	@GenericGenerator(name = "sys_role_pk", strategy = "uuid")
	@Column(name = "role_id", unique = true, nullable = false)
	private String roleId;

	@NotBlank(message = "角色编码不能为空")
	@Column(name = "role_code")
	private String roleCode;

	@NotBlank(message = "角色名称不能为空")
	@Column(name = "role_name")
	private String roleName;

	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	@Column(name = "display_order")
	private Integer displayOrder;

	@Column(name = "is_sys", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private Boolean sys;

	@Column(name = "is_delete", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private Boolean isDelete;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

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

	public String getRoleName() {

		return roleName;
	}

	public void setRoleName(String roleName) {

		this.roleName = roleName;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public Integer getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {

		this.displayOrder = displayOrder;
	}

	public Boolean getSys() {

		return sys;
	}

	public void setSys(Boolean sys) {

		this.sys = sys;
	}

	public Boolean getIsDelete() {

		return isDelete = (isDelete == null ? false : isDelete);
	}

	public void setIsDelete(Boolean isDelete) {

		this.isDelete = isDelete;
	}
}
