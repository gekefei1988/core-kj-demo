package com.direction.core.modules.sys.tenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direction.core.inf.sys.tenant.ITenant;
import com.direction.core.inf.sys.tenant.ITenantService;

// ~ File Information
// ====================================================================================================================

@Service
public class TenantServiceImpl implements ITenantService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantService tenantService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取租户信息.
	 * 
	 * @see com.direction.core.inf.sys.tenant.ITenantService#getTenant(java.lang.String)
	 */
	@Override
	public ITenant getTenant(String id) {
		return tenantService.getTenantMaps().get(id);
	}

	/**
	 * 获取租户名称
	 * 
	 * @see com.direction.core.inf.sys.tenant.ITenantService#getTenantName(java.lang.String)
	 */
	@Override
	public String getTenantName(String id) {
		ITenant tenant = this.getTenant(id);
		
		if (tenant != null) {
			return tenant.getTenantName();
		}
		return null;
	}
}
