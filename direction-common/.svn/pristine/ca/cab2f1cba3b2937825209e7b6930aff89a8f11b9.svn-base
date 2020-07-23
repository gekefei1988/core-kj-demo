package com.direction.core.modules.sys.company.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseCompany extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 444671425035837381L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_company_pk")
	@GenericGenerator(name = "sys_company_pk", strategy = "uuid")
	@Column(name = "company_id", unique = true, nullable = false)
	private String companyId;

	// 编号
	@Column(name = "company_code")
	private String companyCode;

	// 名称-简称
	@Column(name = "company_name")
	private String companyName;

	// 全称
	@Column(name = "company_full_name")
	private String companyFullName;

	// 联系地址
	@Column(name = "address")
	private String address;

	// 所属区域
	@Column(name = "area_codes")
	private String areaCodes;

	// 所属区域
	@Column(name = "area_names")
	private String areaNames;

	// 负责人
	@Column(name = "charge_person")
	private String chargePerson;

	// 电话
	@Column(name = "phone")
	private String phone;

	// 状态
	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	// 企业邮箱
	@Column(name = "mail")
	private String mail;

	// 邮政编码
	@Column(name = "zip_code")
	private String zipCode;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getCompanyId() {

		return companyId;
	}

	public void setCompanyId(String companyId) {

		this.companyId = companyId;
	}

	public String getCompanyCode() {

		return companyCode;
	}

	public void setCompanyCode(String companyCode) {

		this.companyCode = companyCode;
	}

	public String getCompanyName() {

		return companyName;
	}

	public void setCompanyName(String companyName) {

		this.companyName = companyName;
	}

	public String getCompanyFullName() {

		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {

		this.companyFullName = companyFullName;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
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

	public String getAreaCodes() {

		return areaCodes;
	}

	public void setAreaCodes(String areaCodes) {

		this.areaCodes = areaCodes;
	}

	public String getAreaNames() {

		return areaNames;
	}

	public void setAreaNames(String areaNames) {

		this.areaNames = areaNames;
	}

	public String getMail() {

		return mail;
	}

	public void setMail(String mail) {

		this.mail = mail;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;
	}
}
