package com.direction.core.modules.sys.tenant.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.core.modules.sys.tenant.entity.TenantCertHonor;
import com.direction.core.modules.sys.tenant.repository.TenantCertHonorRepository;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 租户/主体认证-个人认证.
 * 
 * <pre>
 * 租户 / 主体认证 - 个人认证
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class TenantCertHonorService extends BaseService<TenantCertHonorRepository, TenantCertHonor, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 保存荣誉信息.
	 * 
	 * @param honor
	 */
	@Transactional(readOnly = false)
	public void saveCertHonor(TenantCertHonor honor) {
		
		if (honor == null) {
			return;
		}
		
		if (StringUtils.isNotBlank(honor.getTenantId())) {
			getBaseRepository().deleteByTenantId(honor.getTenantId());
			getBaseRepository().save(honor);
		}
	}
	
	/**
	 * 根据租户查询信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	public List<TenantCertHonor> getCertHonorsByTenant(String tenantId) {
		return getBaseRepository().findByTenantId(tenantId);
	}
	
	/**
	 * TODO: 备用方法, 以后回修改.
	 * 
	 * @param tenantId
	 * @return
	 */
	public TenantCertHonor getCertHonorByTenant(String tenantId) {
		
		List<TenantCertHonor> honors = getBaseRepository().findByTenantId(tenantId);
		if (honors != null && honors.size() > 0) {
			return honors.get(0);
		}
		return null;
	}
	
	/**
	 * 根据租户删除荣誉信息
	 * 
	 * @param tenantId
	 */
	@Transactional(readOnly = false)
	public void deleteByTenantId(String tenantId) {
		this.getBaseRepository().deleteByTenantId(tenantId);
	}
}
