package com.direction.core.modules.sys.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.core.modules.sys.company.entity.CompanyInfo;
import com.direction.core.modules.sys.company.repository.CompanyInfoRepository;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class CompanyInfoService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private CompanyInfoRepository companyInfoRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取企业备案信息.
	 * 
	 * @param companyId
	 * @return
	 */
	public CompanyInfo get(String companyId) {
		
		return this.companyInfoRepository.getOne(companyId);
	}
	
	/**
	 * 保存企业详细信息.
	 * 
	 * @param info
	 */
	@Transactional(readOnly = false)
	public void save(CompanyInfo info) {

		try {
			this.companyInfoRepository.save(info);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
