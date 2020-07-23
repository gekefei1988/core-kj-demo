package com.direction.core.modules.sys.data_perm.entity;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.EIPService;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.inf.sys.user.IUserInfo.UserStatus;
import com.direction.spring.mvc.entity.BaseEntity;

// ~ File Information
// ====================================================================================================================

public class DataPermVO extends BaseEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 4265587442906704952L;

	// ~ Fields
	// ==================================================================================================================

	private String userId;

	private String orgId;

	private String nickName;

	private String userName;

	private String status;

	// ~ Constructors
	// ==================================================================================================================
	
	public DataPermVO () {}
	
	public DataPermVO (String userId, String orgId, String nickName, String userName, String status) {
		this.userId = userId;
		this.orgId = orgId;
		this.nickName = nickName;
		this.userName = userName;
		this.status = status;
	}

	// ~ Methods
	// ==================================================================================================================
	
	public String getOrgName() {
		return EIPService.getOrgTreeService().getOrgName(this.getOrgId());
	}
	
	public String getStatusDesc() {
		if (StringUtils.isNotBlank(this.getStatus())) {
			return UserStatus.getUserStatusMap().get(this.getStatus());
		}
		return UserStatus.getUserStatusMap().get(IUserInfo.UserStatus.ENABLE);
	}

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

	public String getNickName() {

		return nickName;
	}

	public void setNickName(String nickName) {

		this.nickName = nickName;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}
}
