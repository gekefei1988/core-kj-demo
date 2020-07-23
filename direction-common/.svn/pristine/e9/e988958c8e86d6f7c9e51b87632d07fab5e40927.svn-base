package com.direction.core.modules.sys.data_perm.entity;

import java.io.Serializable;

import javax.persistence.Column;

// ~ File Information
// ====================================================================================================================

public class DataPermKey implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 8138036372590386798L;

	// ~ Fields
	// ==================================================================================================================

	@Column(name = "user_id")
	private String userId;

	@Column(name = "org_id")
	private String orgId;

	// ~ Constructors
	// ==================================================================================================================
	
	public DataPermKey() {}
	
	public DataPermKey(String userId, String orgId) {
		this.userId = userId;
		this.orgId = orgId;
	}

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
}
