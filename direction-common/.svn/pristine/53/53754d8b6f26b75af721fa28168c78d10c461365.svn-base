package com.direction.core.modules.sys.tenant.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.modules.sys.tenant.entity.base.BaseTenantReg;

// ~ File Information
// ====================================================================================================================

/**
 * 租户注册.
 * 
 * <pre>
 * 租户注册
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_tenant_reg")
public class TenantReg extends BaseTenantReg {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 1991052843349992175L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public String getTenantTypeDesc() {
		return TenantType.getTenantTypeMap().get(this.getTenantType());
	}
	
	public String getStatusDesc() {
		return TenantRegStauts.getTenantStatusMap().get(this.getStatus());
	}
	
	public String getTenantTypeColor() {
		return TenantType.getTenantTypeColor().get(this.getTenantType());
	}
	
	public String getStatusColor() {
		return TenantRegStauts.getTenantStatusColor().get(this.getStatus());
	}

	// ~ Classes
	// ==================================================================================================================
	
	public static class TenantType {

		public static final String PERSONAL = "0";
		public static final String ENTERPRISE = "1";
		
		public static final String DEFAULT = PERSONAL;
		
		public static Map<String, String> getTenantTypeMap() {
			Map<String, String> results = new HashMap<String, String>();
			
			results.put(PERSONAL, "个人");
			results.put(ENTERPRISE, "企业");
			
			return results;
		}
		
		public static Map<String, String> getTenantTypeColor() {
			Map<String, String> results = new HashMap<String, String>();
			
			results.put(PERSONAL, "default");
			results.put(ENTERPRISE, "primary");
			
			return results;
		}
	}
	
	public static class TenantRegStauts {

		public static final String PASS = "0";
		public static final String REVIEWING = "1";
		public static final String REFUSE = "2";
		public static final String UN_SUBMIT = "3";
		
		public static final String DEFAULT = UN_SUBMIT;
		
		public static Map<String, String> getTenantStatusMap() {
			Map<String, String> results = new HashMap<String, String>();
			
			results.put(PASS, "已通过");
			results.put(REVIEWING, "待审核");
			results.put(REFUSE, "已拒绝");
			results.put(UN_SUBMIT, "未提交");
			
			return results;
		}
		
		public static Map<String, String> getTenantStatusColor() {
			Map<String, String> results = new HashMap<String, String>();
			
			results.put(PASS, "success");
			results.put(REVIEWING, "orange");
			results.put(REFUSE, "red");
			results.put(UN_SUBMIT, "default");
			
			return results;
		}
	}
}
