package com.direction.core.modules.sys.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.tenant.entity.TenantCertHonor;
import com.direction.core.modules.sys.tenant.entity.TenantCertWeb;
import com.direction.core.modules.sys.tenant.entity.TenantComp;
import com.direction.core.modules.sys.tenant.entity.TenantCompCert;
import com.direction.core.modules.sys.tenant.service.TenantCertHonorService;
import com.direction.core.modules.sys.tenant.service.TenantCertWebService;
import com.direction.core.modules.sys.tenant.service.TenantCompCertService;
import com.direction.core.modules.sys.tenant.service.TenantCompService;
import com.direction.core.modules.sys.tenant.vo.TenantCompVO;
import com.direction.spring.mvc.controller.BaseController;
import com.fasterxml.jackson.core.JsonProcessingException;

// ~ File Information
// ====================================================================================================================

/**
 * 租户/主体-企业认证.
 * 
 * <pre>
 * 	租户/主体-企业认证
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/tenant-comp")
public class TenantCompController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantCompService tenantCompService;
	
	@Autowired
	private TenantCompCertService tenantCompCertServcie;
	
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
	@RequestMapping(value = "/save")
	public ResultJson save(TenantCompVO entity) {
		this.tenantCompService.saveTenantCompVO(entity);
		return success();
	}

	/**
	 * 根据id获取数据,打开编辑页面.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get")
	public TenantComp get(String id) {
		
		TenantComp entity = this.tenantCompService.get(id);
		
		if (entity == null) {
			entity = new TenantComp();
		}
		
		return entity;
	}
	
	/**
	 * 返回数据对象.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getTenantCompVO")
	public TenantCompVO getTenantCompVO(String id) {
		
		TenantCompVO compVO = new TenantCompVO();
		
		TenantComp entity = this.tenantCompService.get(id);
		TenantCompCert compCert = this.tenantCompCertServcie.get(id);
		TenantCertWeb certWeb = this.tenantCertWebService.getCertWebByTenant(id);
		TenantCertHonor certHonor = this.tenantCertHonorService.getCertHonorByTenant(id);
		
		if (entity != null) {
			compVO.setTenantComp(entity);
		}
		
		if (compCert != null) {
			compVO.setTenantCompCert(compCert);
		}
		
		if (certWeb != null) {
			compVO.setTenantCertWeb(certWeb);
		}
		
		if (certHonor != null) {
			compVO.setTenantCertHonor(certHonor);
		}
		
		return compVO;
	}
	
	/**
	 * 删除.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		this.tenantCompService.deleteById(id);
		
		return success();
	}
}
