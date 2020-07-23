package com.direction.core.modules.sys.tenant.repository;

import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.tenant.entity.TenantCompCert;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

/**
 * 主体认证-企业备案.
 * 
 * <pre>
 * 	主体认证-企业备案信息
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Repository
public interface TenantCompCertRepository extends BaseRepository<TenantCompCert, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

}
