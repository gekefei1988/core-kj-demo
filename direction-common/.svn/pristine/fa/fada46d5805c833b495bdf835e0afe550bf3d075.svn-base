package com.direction.core.modules.sys.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.tenant.entity.TenantCertHonor;
import com.direction.core.modules.sys.tenant.entity.TenantCertWeb;
import com.direction.core.modules.sys.tenant.entity.TenantPer;
import com.direction.core.modules.sys.tenant.service.TenantCertHonorService;
import com.direction.core.modules.sys.tenant.service.TenantCertWebService;
import com.direction.core.modules.sys.tenant.service.TenantPerService;
import com.direction.core.modules.sys.tenant.vo.TenantPerVO;
import com.direction.spring.mvc.controller.BaseController;
import com.fasterxml.jackson.core.JsonProcessingException;

// ~ File Information
// ====================================================================================================================

/**
 * 租户/主体-个人认证.
 * 
 * <pre>
 * 	租户/主体-个人认证
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/tenant-per")
public class TenantPerController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantPerService tenantPerService;
	
	@Autowired
	private TenantCertWebService tenantCertWebService;
	
	@Autowired
	private TenantCertHonorService tenantCertHonorService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 保存信息.
	 * 
	 * @param entity
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/save", method = {RequestMethod.OPTIONS, RequestMethod.POST})
	public ResultJson save(TenantPerVO entity) {
		this.tenantPerService.saveTenantPerVO(entity);
		return success();
	}

	/**
	 * 根据id获取数据,打开编辑页面.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get")
	public TenantPer get(String id) {
		
		TenantPer entity = this.tenantPerService.get(id);
		
		if (entity == null) {
			entity = new TenantPer();
			entity.setSex("M");
		}
		
		return entity;
	}
	
	/**
	 * 返回数据对象.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getTenantPerVO")
	public TenantPerVO getTenantPerVO(String id) {
		
		TenantPerVO perVO = new TenantPerVO();
		
		TenantPer entity = this.tenantPerService.get(id);
		TenantCertWeb certWeb = this.tenantCertWebService.getCertWebByTenant(id);
		TenantCertHonor certHonor = this.tenantCertHonorService.getCertHonorByTenant(id);
		
		if (entity != null) {
			perVO.setTenantPer(entity);
		}
		
		if (certWeb != null) {
			perVO.setTenantCertWeb(certWeb);
		}
		
		if (certHonor != null) {
			perVO.setTenantCertHonor(certHonor);
		}
		
		return perVO;
	}
	
	/**
	 * 删除.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		this.tenantPerService.deleteById(id);
		return success();
	}
}
