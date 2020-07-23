package com.direction.core.modules.sys.tenant.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.OperDataEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 企业认证基础类.
 * 
 * <pre>
 * 企业认证基础类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseTenantComp extends OperDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -7894662493095322849L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_tenant_cert_comp_pk")
	@GenericGenerator(name = "sys_tenant_cert_comp_pk", strategy = "assigned")
	@Column(name = "id", unique = true, nullable = false)
	@NotBlank(message = "无法获取当前主体信息")
	private String id;

	@Column(name = "company_name")
	@NotBlank(message = "企业名称不能为空")
	private String companyName;

	@Column(name = "company_code")
	@NotBlank(message = "企业编号或英文名称不能为空")
	private String companyCode;

	@Column(name = "short_name")
	@NotBlank(message = "企业简称不能为空")
	private String shortName;

	@Column(name = "charge_person")
	@NotBlank(message = "企业法人不能为空")
	private String chargePerson;

	// 手机号码
	@Column(name = "telephone")
	@NotBlank(message = "手机号码不能为空")
	private String telephone;

	@Column(name = "office_phone")
	private String officePhone;

	@Column(name = "office_phone_code")
	private String officePhoneCode;

	// 邮件
	@Column(name = "mail")
	private String mail;

	// 归属区域
	@Column(name = "area_codes")
	private String areaCodes;

	@Column(name = "area_names")
	private String areaNames;

	// 详细地址
	@Column(name = "address")
	private String address;

	// 邮政编码
	@Column(name = "zip_code")
	private String zipCode;

	// 经度
	@Column(name = "lng")
	private String lng;

	// 纬度
	@Column(name = "lat")
	private String lat;

	// 企业介绍
	@Column(name = "company_desc")
	private String companyDesc;

	// 是否删除
	@Column(name = "is_delete", columnDefinition = "char")
	@Type(type = "true_false")
	private boolean delete;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getOfficePhoneCode() {

		return officePhoneCode;
	}

	public void setOfficePhoneCode(String officePhoneCode) {

		this.officePhoneCode = officePhoneCode;
	}

	public String getTelephone() {

		return telephone;
	}

	public void setTelephone(String telephone) {

		this.telephone = telephone;
	}

	public String getCompanyName() {

		return companyName;
	}

	public void setCompanyName(String companyName) {

		this.companyName = companyName;
	}

	public String getAreaNames() {

		return areaNames;
	}

	public void setAreaNames(String areaNames) {

		this.areaNames = areaNames;
	}

	public String getCompanyCode() {

		return companyCode;
	}

	public void setCompanyCode(String companyCode) {

		this.companyCode = companyCode;
	}

	public String getShortName() {

		return shortName;
	}

	public void setShortName(String shortName) {

		this.shortName = shortName;
	}

	public String getChargePerson() {

		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {

		this.chargePerson = chargePerson;
	}

	public String getOfficePhone() {

		return officePhone;
	}

	public void setOfficePhone(String officePhone) {

		this.officePhone = officePhone;
	}

	public String getMail() {

		return mail;
	}

	public void setMail(String mail) {

		this.mail = mail;
	}

	public String getAreaCodes() {

		return areaCodes;
	}

	public void setAreaCodes(String areaCodes) {

		this.areaCodes = areaCodes;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;
	}

	public String getLng() {

		return lng;
	}

	public void setLng(String lng) {

		this.lng = lng;
	}

	public String getLat() {

		return lat;
	}

	public void setLat(String lat) {

		this.lat = lat;
	}

	public String getCompanyDesc() {

		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {

		this.companyDesc = companyDesc;
	}

	public boolean isDelete() {

		return delete;
	}

	public void setDelete(boolean delete) {

		this.delete = delete;
	}
}
