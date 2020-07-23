package com.direction.core.modules.sys.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.tenant.entity.TenantCertWeb;
import com.direction.spring.mvc.repository.BaseRepository;


// ~ File Information
// ====================================================================================================================

@Repository
public interface TenantCertWebRepository extends BaseRepository<TenantCertWeb, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据租户删除网站信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Modifying
	@Query(value = "delete from TenantCertWeb where tenantId = ?1")
	int deleteByTenantId(String tenantId);
	
	/**
	 * 根据租户查询网站信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Query(value = "from TenantCertWeb where tenantId = ?1")
	List<TenantCertWeb> findByTenantId(String tenantId);
}
