package com.direction.core.modules.sys.tenant.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.core.modules.sys.tenant.entity.TenantCertWeb;
import com.direction.core.modules.sys.tenant.repository.TenantCertWebRepository;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class TenantCertWebService extends BaseService<TenantCertWebRepository, TenantCertWeb, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 保存网站信息.
	 * 
	 * @param certWeb
	 */
	@Transactional(readOnly = false)
	public void saveCertWeb(TenantCertWeb certWeb) {
		
		if (certWeb == null) {
			return;
		}
		
		if (StringUtils.isNotBlank(certWeb.getTenantId())) {
			getBaseRepository().deleteByTenantId(certWeb.getTenantId());
			getBaseRepository().save(certWeb);
		}
	}
	
	/**
	 * 根据租户查询网站信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	public List<TenantCertWeb> getCertWebsByTenant(String tenantId) {
		return getBaseRepository().findByTenantId(tenantId);
	}
	
	/**
	 * TODO: 备用方法, 以后回修改.
	 * 
	 * @param tenantId
	 * @return
	 */
	public TenantCertWeb getCertWebByTenant(String tenantId) {
		
		List<TenantCertWeb> webs = getBaseRepository().findByTenantId(tenantId);
		if (webs != null && webs.size() > 0) {
			return webs.get(0);
		}
		return null;
	}
	
	/**
	 * 根据租户id, 删除租户信息.
	 * 
	 * @param tenantId
	 */
	@Transactional(readOnly = false)
	public void deleteByTenantId(String tenantId) {
		this.getBaseRepository().deleteByTenantId(tenantId);
	}
}
