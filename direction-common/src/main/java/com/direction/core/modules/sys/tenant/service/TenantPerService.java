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
import com.direction.core.modules.sys.tenant.entity.TenantPer;
import com.direction.core.modules.sys.tenant.entity.TenantReg;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantRegStauts;
import com.direction.core.modules.sys.tenant.repository.TenantPerRepository;
import com.direction.core.modules.sys.tenant.vo.TenantPerVO;
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
public class TenantPerService extends BaseService<TenantPerRepository, TenantPer, String> {

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
	private AreaService areaService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 保存完善的资料.
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void saveTenantPer(TenantPer entity) {
		
		if (entity == null) {
			throw new ServiceException("实体不存在，不能进行保存");
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
	public void saveTenantPerVO(TenantPerVO entity) {

		// 保存基本信息
		this.saveTenantPer(entity.getTenantPer());

		if (entity.getTenantPer() != null && StringUtils.isNotBlank(entity.getTenantPer().getId())) {
			// 保存注册信息
			TenantReg reg = this.tenantRegService.get(entity.getTenantPer().getId());
			if (reg != null) {
				reg.setTenantName(entity.getTenantPer().getTenantName());
				this.tenantRegService.save(reg);
			}
		}

		// 保存网站信息
		TenantCertWeb web = entity.getTenantCertWeb();
		web.setTenantId(entity.getTenantPer().getId());
		this.tenantCertWebService.saveCertWeb(web);

		// 保存荣誉认证信息
		TenantCertHonor honor = entity.getTenantCertHonor();
		honor.setTenantId(entity.getTenantPer().getId());
		this.tenantCertHonorService.saveCertHonor(honor);
	}

	/**
	 * 提交个人资料审核.
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
		TenantPer per = this.get(reg.getId());

		if (per == null) {
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
		tenant.setTenantNo(per.getIdCard());
		tenant.setUsedType(TenantUsedType.DEFAULT);
		tenant.setVersion(EIPService.getDictService().getTopDefaultValue("sys-version"));
		tenant.setIsDelete(false);

		// 保存租户信息
		this.tenantService.save(tenant);

		reg.setStatus(TenantRegStauts.REVIEWING);
		this.tenantRegService.save(reg);
	}

	/**
	 * 提交个人资料审核.
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
	 * 删除租户信息-个人.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		
		// 删除网站信息
		this.tenantCertWebService.deleteByTenantId(id);
		
		// 删除荣誉信息
		this.tenantCertHonorService.deleteByTenantId(id);
		
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
			
			TenantPer per = this.get(id);
			if (per != null) {
				this.getBaseRepository().save(per);
			}
		}
	}
}
