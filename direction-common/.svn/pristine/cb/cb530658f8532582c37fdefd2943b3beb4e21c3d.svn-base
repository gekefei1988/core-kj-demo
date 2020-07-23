package com.direction.core.modules.sys.tenant.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.core.inf.EIPService;
import com.direction.core.modules.sys.area.entity.Area;
import com.direction.core.modules.sys.area.service.AreaService;
import com.direction.core.modules.sys.tenant.entity.Tenant;
import com.direction.core.modules.sys.tenant.entity.Tenant.TenantStatus;
import com.direction.core.modules.sys.tenant.entity.Tenant.TenantUsedType;
import com.direction.core.modules.sys.tenant.entity.TenantCertHonor;
import com.direction.core.modules.sys.tenant.entity.TenantCertWeb;
import com.direction.core.modules.sys.tenant.entity.TenantComp;
import com.direction.core.modules.sys.tenant.entity.TenantCompCert;
import com.direction.core.modules.sys.tenant.entity.TenantReg;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantRegStauts;
import com.direction.core.modules.sys.tenant.repository.TenantCompRepository;
import com.direction.core.modules.sys.tenant.vo.TenantCompVO;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 租户/主体认证-个人认证.
 * 
 * <pre>
 * 租户 / 主体认证 - 个人认证
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class TenantCompService extends BaseService<TenantCompRepository, TenantComp, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private TenantRegService tenantRegService;
	
	@Autowired
	private TenantCertWebService tenantCertWebService;
	
	@Autowired
	private TenantCertHonorService tenantCertHonorService;
	
	@Autowired
	private TenantCompCertService tenantCompCertServcie;
	
	@Autowired
	private AreaService areaService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 保存企业信息.
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void saveTenantComp(TenantComp entity) {
		if (entity == null) {
			throw new ServiceException("实体对象为空, 不能进行保存");
		}
		
		// 赋值简称
		if (StringUtils.isBlank(entity.getShortName())) {
			entity.setShortName(entity.getCompanyName());
		}
		
		// 判断归属区域
		if (StringUtils.isNotBlank(entity.getAreaCodes())) {
			String[] areaCodes = StringUtils.split(entity.getAreaCodes(), ",");
			if (areaCodes.length > 0) {
				Area area = this.areaService.getAreaByCode(areaCodes[areaCodes.length - 1]);
				if (area != null) {
					entity.setAreaNames(area.getAllAreaName());
				}
			}
		}
		
		// 保存基本信息
		this.save(entity);
	}
	
	/**
	 * 保存完善的资料.
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void saveTenantCompVO(TenantCompVO entity) {
		
		if (entity == null) {
			throw new ServiceException("实体对象为空, 不能进行保存");
		}
		
		// 赋值简称
		if (StringUtils.isBlank(entity.getTenantComp().getShortName())) {
			entity.getTenantComp().setShortName(entity.getTenantComp().getCompanyName());
		}
		
		// 判断归属区域
		if (StringUtils.isNotBlank(entity.getTenantComp().getAreaCodes())) {
			String[] areaCodes = StringUtils.split(entity.getTenantComp().getAreaCodes(), ",");
			if (areaCodes.length > 0) {
				Area area = this.areaService.getAreaByCode(areaCodes[areaCodes.length - 1]);
				if (area != null) {
					entity.getTenantComp().setAreaNames(area.getAllAreaName());
				}
			}
		}
		
		// 保存基本信息
		this.save(entity.getTenantComp());
		
		// 修改注册信息主体名称
		TenantReg reg = this.tenantRegService.get(entity.getTenantComp().getId());
		if (reg != null) {
			reg.setTenantName(entity.getTenantComp().getCompanyName());
			this.tenantRegService.save(reg);
		}
		
		// 保存认证信息
		TenantCompCert compCert = entity.getTenantCompCert();
		compCert.setId(entity.getTenantComp().getId());
		this.tenantCompCertServcie.save(compCert);
	
		// 保存网站信息
		TenantCertWeb web = entity.getTenantCertWeb();
		web.setTenantId(entity.getTenantComp().getId());
		this.tenantCertWebService.saveCertWeb(web);
		
		// 保存荣誉认证信息
		TenantCertHonor honor = entity.getTenantCertHonor();
		honor.setTenantId(entity.getTenantComp().getId());
		this.tenantCertHonorService.saveCertHonor(honor);
	}
	
	/**
	 * 提交企业资料审核.
	 * 
	 * @param tenantId
	 */
	@Transactional(readOnly = false)
	public void submit(TenantReg reg) {
		
		// 获取注册信息
		if (reg == null) {
			throw new ServiceException("未获取到租户信息");
		}
		
		// 获取租户基本信息
		TenantComp comp = this.get(reg.getId());
		TenantCompCert compCert = this.tenantCompCertServcie.get(reg.getId());
		
		if (comp == null || compCert == null) {
			throw new ServiceException("租户信息不存在，请先完善资料后，再申请审核");
		}
		
		// 获取租户
		Tenant tenant = this.tenantService.get(reg.getId());
		
		if (tenant == null) {
			tenant = new Tenant();
			tenant.setId(reg.getId());
		}
		
		tenant.setTelephone(reg.getTelephone());
		tenant.setPassword(reg.getPassword());
		tenant.setInitPwd(reg.getInitPwd());
		tenant.setStatus(TenantStatus.DEFAULT);
		tenant.setTenantName(reg.getTenantName());
		tenant.setMaxUserNum(10);
		tenant.setTenantType(reg.getTenantType());
		tenant.setTenantNo(compCert.getCreditCode());
		tenant.setUsedType(TenantUsedType.USER_TRIAL);
		tenant.setVersion(EIPService.getDictService().getTopDefaultValue("sys-version"));
		tenant.setIsDelete(false);
		
		this.tenantService.save(tenant);
		
		reg.setStatus(TenantRegStauts.REVIEWING);
		this.tenantRegService.save(reg);
	}
	
	/**
	 * 提交企业资料审核.
	 * 
	 * @param tenantId
	 */
	@Transactional(readOnly = false)
	public void submit(String tenantId) {
		
		// 获取注册信息
		TenantReg reg = this.tenantRegService.get(tenantId);
		if (reg == null) {
			throw new ServiceException("未获取到租户信息");
		}
		this.submit(reg);
	}
	
	/**
	 * 删除租户信息-企业.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		
		// 删除网站信息
		this.tenantCertWebService.deleteByTenantId(id);
		
		// 删除荣誉信息
		this.tenantCertHonorService.deleteByTenantId(id);
		
		// 删除租户企业信息
		this.tenantCompCertServcie.deleteById(id);
		
		// 删除租户下
		
		// 删除租户信息
		this.deleteById(id);
	}
	
	/**
	 * 假删除租户信息.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void fakeDelete(String id) {
		
		if (StringUtils.isNotBlank(id)) {
			TenantComp comp = this.get(id);
			
			if (comp != null) {
				this.getBaseRepository().save(comp);
			}
		}
	}
}
