package com.direction.core.modules.sys.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.tenant.entity.Tenant;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

/**
 * 租户数据层.
 * 
 * <pre>
 * 	租户数据层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Repository
public interface TenantRepository extends BaseRepository<Tenant, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 获取已启用的租户.
	 * 
	 * @return
	 */
	@Query("from Tenant where status=0 order by tenantName desc")
	List<Tenant> getTenants(); 
}
