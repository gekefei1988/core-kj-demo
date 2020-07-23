package com.direction.core.modules.sys.home.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.modules.sys.tenant.entity.TenantCertHonor;
import com.direction.core.modules.sys.tenant.entity.TenantCertWeb;
import com.direction.core.modules.sys.tenant.entity.TenantComp;
import com.direction.core.modules.sys.tenant.entity.TenantCompCert;
import com.direction.core.modules.sys.tenant.entity.TenantPer;
import com.direction.core.modules.sys.tenant.entity.TenantReg;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantRegStauts;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantType;
import com.direction.core.modules.sys.tenant.service.TenantCertHonorService;
import com.direction.core.modules.sys.tenant.service.TenantCertWebService;
import com.direction.core.modules.sys.tenant.service.TenantCompCertService;
import com.direction.core.modules.sys.tenant.service.TenantCompService;
import com.direction.core.modules.sys.tenant.service.TenantPerService;
import com.direction.core.modules.sys.tenant.service.TenantRegService;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

/**
 * 主页相关接口信息.
 * 
 * <pre>
 * 	主页相关接口信息
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/home")
public class HomeController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantRegService tenantRegService;
	
	@Autowired
	private TenantPerService tenantPerService;
	
	@Autowired
	private TenantCompService tenantCompService;
	
	@Autowired
	private TenantCompCertService tenantCompCertService;
	
	@Autowired
	private TenantCertWebService tenantCertWebService;
	
	@Autowired
	private TenantCertHonorService tenantCertHonorService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 提交审核.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/submit", method = {RequestMethod.POST})
	public ResultJson submit(String tenantId) {
		TenantReg reg = this.tenantRegService.submit(tenantId);
		
		ResultJson result = success();
		result.setBody(reg);
		return result;
	}

	/**
	 * 获取当前租户注册信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/getTenantReg", method = RequestMethod.POST)
	public TenantReg getTenantReg(String tenantId) {
		
		TenantReg tenantReg = null;
		
		if (StringUtils.isNotBlank(tenantId)) {
			tenantReg = this.tenantRegService.get(tenantId);
		}
		else {
			UserProfile user = UserProfileUtils.getUserProfile();
			
			if (user != null && !user.isSuperAdmin()) {
				tenantReg = this.tenantRegService.get(user.getTenantId());
			}
		}
		
		// 如果注册信息为空, 则新建一个
		if (tenantReg == null) {
			tenantReg = new TenantReg();
			tenantReg.setStatus(TenantRegStauts.UN_SUBMIT);
			tenantReg.setTenantType(TenantType.DEFAULT);
		}
		return tenantReg;
	}
	
	/**
	 * 保存租户注册信息.
	 * 
	 * @param tenantReg
	 * @return
	 */
	@RequestMapping(value = "/saveTenantReg", method = RequestMethod.POST)
	public ResultJson saveTenantReg(TenantReg tenantReg) {
		
		if (tenantReg == null) {
			return error("保存失败");
		}

		this.tenantRegService.saveOrUpdate(tenantReg);
		
		ResultJson result = success();
		result.setBody(tenantReg);
		
		return result;
	}
	
	/**
	 * 获取个人基础信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/getTenantPer", method = RequestMethod.POST)
	public TenantPer getTenantPer(String tenantId) {
		
		TenantPer entity = null;
		
		if (StringUtils.isNotBlank(tenantId)) {
			entity = tenantPerService.get(tenantId);
		}
		else {
			UserProfile user = UserProfileUtils.getUserProfile();
			if (user != null && !user.isSuperAdmin()) {
				entity = this.tenantPerService.get(user.getTenantId());
			}
		}
		
		if (entity == null) {
			entity = new TenantPer();
			entity.setSex("M");
		}
		
		return entity;
	}
	
	/**
	 * 保存信息.
	 * 
	 * @param tenantPer
	 * @return
	 */
	@RequestMapping(value = "/saveTenantPer", method = {RequestMethod.POST})
	public ResultJson saveTenantPer(TenantPer tenantPer) {
		this.tenantPerService.saveTenantPer(tenantPer);
		return success();
	}
	
	/**
	 * 获取网站信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/getCertWeb", method = RequestMethod.POST)
	public TenantCertWeb getCertWeb(String tenantId) {
		
		TenantCertWeb entity = null;
		
		if (StringUtils.isNotBlank(tenantId)) {
			entity = this.tenantCertWebService.getCertWebByTenant(tenantId);
		}
		else {
			UserProfile user = UserProfileUtils.getUserProfile();
			if (user != null && !user.isSuperAdmin()) {
				entity = this.tenantCertWebService.getCertWebByTenant(user.getTenantId());
			}
		}
		
		if (entity == null) {
			entity = new TenantCertWeb();
		}
		
		return entity;
	}
	
	/**
	 * 保存网站信息.
	 * 
	 * @param certWeb
	 * @return
	 */
	@RequestMapping(value = "/saveCertWeb", method = {RequestMethod.POST})
	public ResultJson saveCertWeb(TenantCertWeb certWeb) {
		this.tenantCertWebService.saveCertWeb(certWeb);
		return success();
	}
	
	/**
	 * 获取荣誉信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/getCertHonor", method = RequestMethod.POST)
	public TenantCertHonor getCertHonor(String tenantId) {
		
		TenantCertHonor entity = null;
		
		if (StringUtils.isNotBlank(tenantId)) {
			entity = this.tenantCertHonorService.getCertHonorByTenant(tenantId);
		}
		else {
			UserProfile user = UserProfileUtils.getUserProfile();
			if (user != null && !user.isSuperAdmin()) {
				entity = this.tenantCertHonorService.getCertHonorByTenant(user.getTenantId());
			}
		}
		
		if (entity == null) {
			entity = new TenantCertHonor();
		}
		
		return entity;
	}
	
	/**
	 * 保存荣誉信息.
	 * 
	 * @param certHonor
	 * @return
	 */
	@RequestMapping(value = "/saveCertHonor", method = {RequestMethod.POST})
	public ResultJson saveCertHonor(TenantCertHonor certHonor) {
		this.tenantCertHonorService.saveCertHonor(certHonor);
		return success();
	}
	
	/**
	 * 获取企业信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/getTenantComp", method = RequestMethod.POST)
	public TenantComp getTenantComp(String tenantId) {
		
		TenantComp entity = null;
		
		if (StringUtils.isNotBlank(tenantId)) {
			entity = this.tenantCompService.get(tenantId);
		}
		else {
			UserProfile user = UserProfileUtils.getUserProfile();
			if (user != null && !user.isSuperAdmin()) {
				entity = this.tenantCompService.get(user.getTenantId());
			}
		}
		
		if (entity == null) {
			entity = new TenantComp();
		}
		
		return entity;
	}
	
	/**
	 * 保存企业基础信息.
	 * 
	 * @param tenantComp
	 * @return
	 */
	@RequestMapping(value = "/saveTenantComp", method = {RequestMethod.POST})
	public ResultJson saveTenantComp(TenantComp tenantComp) {
		this.tenantCompService.saveTenantComp(tenantComp);
		return success();
	}
	
	/**
	 * 获取企业行业信息.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/getCompCert", method = RequestMethod.POST)
	public TenantCompCert getCompCert(String tenantId) {
		
		TenantCompCert entity = null;
		
		if (StringUtils.isNotBlank(tenantId)) {
			entity = this.tenantCompCertService.get(tenantId);
		}
		else {
			UserProfile user = UserProfileUtils.getUserProfile();
			if (user != null && !user.isSuperAdmin()) {
				entity = this.tenantCompCertService.get(user.getTenantId());
			}
		}
		
		if (entity == null) {
			entity = new TenantCompCert();
		}
		
		return entity;
	}
	
	/**
	 * 保存企业行业基础信息.
	 * 
	 * @param tenantComp
	 * @return
	 */
	@RequestMapping(value = "/saveCompCert", method = {RequestMethod.POST})
	public ResultJson saveCompCert(TenantCompCert compCert) {
		this.tenantCompCertService.save(compCert);
		return success();
	}
}
