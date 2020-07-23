package com.direction.core.modules.sys.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direction.core.inf.sys.company.ICompany;
import com.direction.core.inf.sys.company.ICompanyService;

// ~ File Information
// ====================================================================================================================

@Service
public class CompanyServiceImpl implements ICompanyService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private CompanyService companySerivce;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据企业ID获取企业信息.
	 * 
	 * @see com.direction.core.inf.sys.company.ICompanyService#getCompany(java.lang.String)
	 */
	@Override
	public ICompany getCompany(String companyId) {

		return this.companySerivce.get(companyId);
	}
}
