package com.direction.core.modules.sys.tenant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;
import com.direction.core.modules.sys.org.service.OrgTreeService;
import com.direction.core.modules.sys.role.service.UserRoleService;
import com.direction.core.modules.sys.tenant.entity.Tenant;
import com.direction.core.modules.sys.tenant.entity.Tenant.TenantStatus;
import com.direction.core.modules.sys.tenant.repository.TenantRepository;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.service.UserService;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class TenantService extends BaseService<TenantRepository, Tenant, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantRegService tenantRegService;
	
	@Autowired
	private OrgTreeService orgTreeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 清空租户缓存.
	 * 
	 */
	@Override
	public void clearCache() {
		
		// 清除租户缓存信息.
		CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_TENANT, "getTenantMaps_all");
	}
	
	/**
	 * 获取租户列表.
	 * 
	 * @return
	 */
	public List<Tenant> getTenants() {
		return getBaseRepository().getTenants();
	}
	
	/**
	 * 获取租户Map集合.
	 * 
	 * @return
	 */
	public Map<String, Tenant> getTenantMaps() {
		
		@SuppressWarnings("unchecked")
		Map<String, Tenant> tenantMaps = (Map<String, Tenant>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_TENANT, "getTenantMaps_all");
		
		if (tenantMaps != null) {
			return tenantMaps;
		}
		
		tenantMaps = new HashMap<String, Tenant>();
		
		List<Tenant> tenants = this.findAll();
		
		if (tenants != null && tenants.size() > 0) {
			for (Tenant tenant : tenants) {
				tenantMaps.put(tenant.getId(), tenant);
			}
		}
		
		// 租户放入缓存
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_TENANT, "getTenantMaps_all", tenantMaps);
		
		return tenantMaps;
	}
	
	/**
	 * 审核.
	 * 
	 * @param tenant
	 * @return
	 */
	@Transactional(readOnly = false)
	public void review(Tenant tenant) {
		
		if (StringUtils.isBlank(tenant.getId())) {
			throw new ServiceException("未获取到租户信息");
		}
		
		// 获取基础信息
		Tenant baseTenant = this.get(tenant.getId());
		
		if (baseTenant == null) {
			throw new ServiceException("未获取到租户信息");
		}
		
		// 通过
		if (StringUtils.equals(tenant.getStatus(), TenantStatus.PASS)) {
			baseTenant.setStatus(TenantStatus.PASS);
			
			// 创建组织树和节点及默认管理员用户
			orgTreeService.createTenantOrgTree(baseTenant);
		}
		// 拒绝
		else if (StringUtils.equals(tenant.getStatus(), TenantStatus.UNPASS)) {
			baseTenant.setStatus(TenantStatus.UNPASS);
			baseTenant.setFreeze(tenant.getFreeze());
		}
		// 冻结
		else if (StringUtils.equals(tenant.getStatus(), TenantStatus.FREEZE)) {
			baseTenant.setStatus(TenantStatus.FREEZE);
			baseTenant.setFreeze(tenant.getFreeze());
		}
		else {
			throw new ServiceException("未获取到租户审核信息");
		}
		
		// 保存租户信息
		getBaseRepository().save(baseTenant);
		
		// 审核租户信息
		this.tenantRegService.review(baseTenant);
		
		// 修改用户信息, 修改临时租户为管理员用户
		User user = this.userService.updateUserByTenant(baseTenant);
		
		if (user != null) {
			// 创建角色-人员授权
			this.userRoleService.assignTenantManageRoleUser(user.getUserId());
		}
	}
}
