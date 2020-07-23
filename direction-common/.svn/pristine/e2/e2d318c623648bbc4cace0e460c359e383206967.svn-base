package com.direction.core.modules.sys.data_perm.entity;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.direction.core.inf.EIPService;
import com.direction.core.modules.sys.data_perm.entity.base.BaseDataPerm;

// ~ File Information
// ====================================================================================================================

/**
 * 数据权限.
 * 
 * <pre>
 * 	数据权限实体类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_data_perm")
@IdClass(value = DataPermKey.class)
public class DataPerm extends BaseDataPerm {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 9221429348526909000L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================
	
	public DataPerm() {}
	
	public DataPerm(String tenantId, String userId, String orgId) {
		this.setTenantId(tenantId);
		this.setUserId(userId);
		this.setOrgId(orgId);
	}
	
	public DataPerm(String tenantId, String userId, String orgId, String orgIds) {
		this.setTenantId(tenantId);
		this.setUserId(userId);
		this.setOrgId(orgId);
		this.setOrgIds(orgIds);
	}

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 机构名称.
	 * 
	 * @return
	 */
	public String getOrgName() {
		return EIPService.getOrgTreeService().getOrgName(this.getOrgId());
	}
}
