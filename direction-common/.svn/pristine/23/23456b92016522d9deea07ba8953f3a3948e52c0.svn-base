package com.direction.core.modules.sys.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.inf.TenantConst;
import com.direction.core.inf.WeightsConst;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.modules.sys.user.entity.base.BaseSuperAdmin;

// ~ File Information
// ====================================================================================================================

/**
 * 超级管理员.
 * 
 * <pre>
 * 超级管理员
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_super_admin")
public class SuperAdmin extends BaseSuperAdmin implements IUserInfo {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -7467152950762028665L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getStatusDesc() {

		return IUserInfo.UserStatus.getUserStatusMap().get(this.getStatus());
	}

	/**
	 * 权重.
	 * 
	 * @see com.direction.core.inf.sys.user.IUserInfo#getWeights()
	 */
	@Override
	public int getWeights() {
		return WeightsConst.WEIGHTS_SUPER_MANAGER;
	}

	@Override
	public String getName() {

		return this.getNickName();
	}

	@Override
	public String getEmpName() {

		return null;
	}

	@Override
	public String getOrgId() {

		return null;
	}

	@Override
	public String getSign() {
		return null;
	}

	@Override
	public String getUserType() {
		return UserType.SUPER_ADMIN;
	}

	@Override
	public String getTenantId() {
		return TenantConst.SYSTEM;
	}
}
