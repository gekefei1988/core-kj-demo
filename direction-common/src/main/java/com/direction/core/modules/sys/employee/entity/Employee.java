package com.direction.core.modules.sys.employee.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.EIPService;
import com.direction.core.inf.sys.org.IOrgTreeNode;
import com.direction.core.modules.sys.employee.entity.base.BaseEmployee;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_employee")
public class Employee extends BaseEmployee {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 7763191671837197074L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 机构名称.
	 * 
	 * @return
	 */
	public String getOrgName() {
		
		String orgName = null;
		
		if (StringUtils.isNotBlank(this.getOrgId())) {
			IOrgTreeNode node = EIPService.getOrgTreeService().getOrgTreeNode(this.getOrgId());
			if (node != null) {
				orgName = node.getOrgName();
			}
		}
		return orgName;
	}
	
	/**
	 * 获取状态说明.
	 * 
	 * @return
	 */
	public String getStatusDesc() {
		if (StringUtils.isNotBlank(this.getStatus())) {
			return EmployeeStatus.getEmployeeStatusMap().get(this.getStatus());
		}
		return EmployeeStatus.getEmployeeStatusMap().get(EmployeeStatus.IN_SERVICE);
	}
	
	public String getEducationDesc() {
//		return EIPService.getDictService().getTopValueMap("sys_education_level").get(this.getEducation());
		return "";
	}
	
	public static class EmployeeStatus {
		
		// 在职
		public static final String IN_SERVICE = "0";
		
		// 停职
		public static final String DIS_SERVICE = "1";
		
		// 离职
		public static final String LEAVE = "2";
		
		public static Map<String, String> getEmployeeStatusMap() {

			Map<String, String> maps = new HashMap<String, String>();
			maps.put(IN_SERVICE, "在职");
			maps.put(DIS_SERVICE, "停职");
			maps.put(LEAVE, "离职");
			return maps;
		}
	}
}
