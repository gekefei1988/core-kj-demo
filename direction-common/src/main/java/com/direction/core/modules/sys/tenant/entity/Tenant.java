package com.direction.core.modules.sys.tenant.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.inf.EIPService;
import com.direction.core.inf.sys.tenant.ITenant;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantType;
import com.direction.core.modules.sys.tenant.entity.base.BaseTenant;

// ~ File Information
// ====================================================================================================================

/**
 * 系统租户.
 * 
 * <pre>
 * 系统租户
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_tenant")
public class Tenant extends BaseTenant implements ITenant {

	// ~ Static Fields
	// ==================================================================================================================
	private static final long serialVersionUID = -2404569172022519528L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public String getStatusDesc() {
		return TenantStatus.getTenantStatusMap().getOrDefault(this.getStatus(), TenantStatus.DEFAULT);
	}
	
	public String getStatusColor() {
		return TenantStatus.getTenantStatusColor().getOrDefault(this.getStatus(), TenantStatus.DEFAULT);
	}
	
	public String getUsedTypeDesc() {
		return TenantUsedType.getTenantUsedTypeMap().getOrDefault(this.getUsedType(), TenantUsedType.DEFAULT);
	}
	
	public String getUsedTypeColor() {
		return TenantUsedType.getTenantUsedTypeColor().getOrDefault(this.getUsedType(), TenantUsedType.DEFAULT);
	}
	
	public String getTenantTypeDesc() {
		return TenantType.getTenantTypeMap().getOrDefault(this.getTenantType(), TenantType.DEFAULT);
	}
	
	public String getTenantTypeColor() {
		return TenantType.getTenantTypeColor().getOrDefault(this.getTenantType(), TenantType.DEFAULT);
	}
	
	public String getVersionDesc() {
		return EIPService.getDictService().getTopValueMap("sys-version").get(this.getVersion());
	}

	public static class TenantStatus {

		public static final String PASS = "0";
		public static final String UNPASS = "1";
		public static final String FREEZE = "2";
		public static final String REVIEWING = "3";

		public static final String DEFAULT = REVIEWING;
		
		public static Map<String, String> getTenantStatusMap() {
			Map<String, String> results = new HashMap<String, String>();
			
			results.put(PASS, "已通过");
			results.put(UNPASS, "已拒绝");
			results.put(FREEZE, "已冻结");
			results.put(REVIEWING, "待审核");
			
			return results;
		}
		
		public static Map<String, String> getTenantStatusColor() {
			Map<String, String> results = new HashMap<String, String>();
			
			results.put(PASS, "success");
			results.put(UNPASS, "red");
			results.put(FREEZE, "orange");
			results.put(REVIEWING, "default");
			
			return results;
		}
	}
	
	public static class TenantUsedType {

		public static final String USER_TRIAL = "0";
		public static final String USER_CONTRACT = "1";

		public static final String DEFAULT = USER_TRIAL;
		
		public static Map<String, String> getTenantUsedTypeMap() {
			Map<String, String> results = new HashMap<String, String>();
			
			results.put(USER_TRIAL, "试用用户");
			results.put(USER_CONTRACT, "合约用户");
			
			return results;
		}
		
		public static Map<String, String> getTenantUsedTypeColor() {
			Map<String, String> results = new HashMap<String, String>();
			
			results.put(USER_TRIAL, "warning");
			results.put(USER_CONTRACT, "success");
			
			return results;
		}
	}
}
