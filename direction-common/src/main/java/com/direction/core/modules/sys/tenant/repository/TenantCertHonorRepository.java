package com.direction.core.modules.sys.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.tenant.entity.TenantCertHonor;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

/**
 * 主体认证-荣誉认证.
 * 
 * <pre>
 * 	主体认证-荣誉认证
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Repository
public interface TenantCertHonorRepository extends BaseRepository<TenantCertHonor, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据租户删除荣誉信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Modifying
	@Query(value = "delete from TenantCertHonor where tenantId = ?1")
	int deleteByTenantId(String tenantId);
	
	/**
	 * 根据租户查询荣誉信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Query(value = "from TenantCertHonor where tenantId = ?1")
	List<TenantCertHonor> findByTenantId(String tenantId);
}
