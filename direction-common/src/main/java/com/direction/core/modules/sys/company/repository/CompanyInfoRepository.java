package com.direction.core.modules.sys.company.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.company.entity.CompanyInfo;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface CompanyInfoRepository extends BaseRepository<CompanyInfo, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据企业ID查询数据对象.
	 * 
	 * @param companyId
	 * @return
	 */
	@Query("from CompanyInfo where companyId = ?1")
	public CompanyInfo getOne(String companyId);
}
