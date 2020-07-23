package com.direction.core.modules.sys.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.company.entity.Company;
import com.direction.core.modules.sys.company.entity.CompanyInfo;
import com.direction.core.modules.sys.company.service.CompanyInfoService;
import com.direction.core.modules.sys.company.service.CompanyService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@RestController
@RequestMapping("/core/sys/company")
public class CompanyController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyInfoService companyInfoService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param company
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<Company> list(Company company, VuePage<Company> page) {

		return this.companyService.findPage(company, page);
	}

	/**
	 * 保存信息.
	 * 
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResultJson save(Company company) {
		
		this.companyService.saveOrUpdate(company);

		return success();
	}
	
	/**
	 * 保存信息.
	 * 
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/saveInfo")
	public ResultJson save(CompanyInfo info) {
		
		this.companyInfoService.save(info);

		return success();
	}

	/**
	 * 根据id获取数据,打开编辑页面
	 * 
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/get")
	public Company get(String companyId) {

		return this.companyService.get(companyId);
	}
	
	/**
	 * 获取企业备案信息.
	 * 
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/getInfo")
	public CompanyInfo getInfo(String companyId) {
		
		CompanyInfo info = this.companyInfoService.get(companyId);
		if (info == null) {
			info = new CompanyInfo();
			info.setCompanyId(companyId);
			
			// 种养殖
			info.setEnterpriseType("00");
			// 备案类型
			info.setRegType("01");
			
			// 企业法人
			info.setLegalType("0");
		}
		
		return info;
	}

	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String companyId) {

		return this.companyService.validateOnlyOne(propName, propValue, companyId);
	}

	/**
	 * 删除.
	 * 
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String companyId) {

		this.companyService.delete(companyId);

		return success();
	}
}
