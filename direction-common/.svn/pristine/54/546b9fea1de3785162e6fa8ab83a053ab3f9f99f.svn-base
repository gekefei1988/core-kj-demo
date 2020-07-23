package com.direction.core.modules.sys.data_perm.entity.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import com.direction.spring.mvc.entity.OperTenantDataEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 数据权限基础类.
 * 
 * <pre>
 * 数据权限基础类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseDataPerm extends OperTenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4703571899546844617L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@NotBlank(message = "用户主键不能为空")
	private String userId;

	@Id
	@NotBlank(message = "机构主键不能为空")
	private String orgId;

	@Column(name = "org_ids")
	private String orgIds;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public String getOrgId() {

		return orgId;
	}

	public void setOrgId(String orgId) {

		this.orgId = orgId;
	}

	public String getOrgIds() {

		return orgIds;
	}

	public void setOrgIds(String orgIds) {

		this.orgIds = orgIds;
	}
}
