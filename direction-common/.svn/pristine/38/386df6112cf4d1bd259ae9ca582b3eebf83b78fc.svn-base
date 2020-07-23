package com.direction.core.modules.sys.dept.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseDept extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 2785055855415006447L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_dept_pk")
	@GenericGenerator(name = "sys_dept_pk", strategy = "uuid")
	@Column(name = "dept_id", unique = true, nullable = false)
	private String deptId;

	@Column(name = "dept_code")
	private String deptCode;

	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "dept_full_name")
	private String deptFullName;

	@Column(name = "charge_person")
	private String chargePerson;

	@Column(name = "phone")
	private String phone;

	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	@Column(name = "company_id")
	private String companyId;

	@Column(name = "display_order")
	private Integer displayOrder;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getDeptId() {

		return deptId;
	}

	public void setDeptId(String deptId) {

		this.deptId = deptId;
	}

	public String getDeptCode() {

		return deptCode;
	}

	public void setDeptCode(String deptCode) {

		this.deptCode = deptCode;
	}

	public String getDeptName() {

		return deptName;
	}

	public void setDeptName(String deptName) {

		this.deptName = deptName;
	}

	public String getDeptFullName() {

		return deptFullName;
	}

	public void setDeptFullName(String deptFullName) {

		this.deptFullName = deptFullName;
	}

	public String getChargePerson() {

		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {

		this.chargePerson = chargePerson;
	}

	public String getPhone() {

		return phone;
	}

	public void setPhone(String phone) {

		this.phone = phone;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getCompanyId() {

		return companyId;
	}

	public void setCompanyId(String companyId) {

		this.companyId = companyId;
	}

	public Integer getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {

		this.displayOrder = displayOrder;
	}
}
