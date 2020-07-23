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
 * 租户认证-个人认证.
 * 
 * <pre>
 * 租户认证 - 个人认证
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseTenantPer extends OperDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 5636027274401068359L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_tenant_per_pk")
	@GenericGenerator(name = "sys_tenant_per_pk", strategy = "assigned")
	@Column(name = "id", unique = true, nullable = false)
	@NotBlank(message = "无法获取当前主体信息")
	private String id;

	// 姓名
	@Column(name = "name")
	@NotBlank(message = "姓名不能为空")
	private String name;

	// 手机号码
	@Column(name = "telephone")
	@NotBlank(message = "手机号码不能为空")
	private String telephone;

	// 身份证号
	@Column(name = "id_card")
	private String idCard;

	// 性别
	@Column(name = "sex", columnDefinition = "char")
	private String sex;

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

	// 所属行业
	@Column(name = "industry")
	private String industry;

	// 经营范围
	@Column(name = "business")
	private String business;

	@Column(name = "tenant_name")
	private String tenantName;

	// logo
	@Column(name = "logo")
	private String logo;

	@Column(name = "logo_file_id")
	private String logoFileId;

	// 个人介绍
	@Column(name = "per_desc")
	private String perDesc;

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

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getTelephone() {

		return telephone;
	}

	public void setTelephone(String telephone) {

		this.telephone = telephone;
	}

	public String getAreaNames() {

		return areaNames;
	}

	public void setAreaNames(String areaNames) {

		this.areaNames = areaNames;
	}

	public String getTenantName() {

		return tenantName;
	}

	public void setTenantName(String tenantName) {

		this.tenantName = tenantName;
	}

	public String getIdCard() {

		return idCard;
	}

	public void setIdCard(String idCard) {

		this.idCard = idCard;
	}

	public String getSex() {

		return sex;
	}

	public void setSex(String sex) {

		this.sex = sex;
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

	public String getIndustry() {

		return industry;
	}

	public void setIndustry(String industry) {

		this.industry = industry;
	}

	public String getBusiness() {

		return business;
	}

	public void setBusiness(String business) {

		this.business = business;
	}

	public String getLogo() {

		return logo;
	}

	public void setLogo(String logo) {

		this.logo = logo;
	}

	public String getLogoFileId() {

		return logoFileId;
	}

	public void setLogoFileId(String logoFileId) {

		this.logoFileId = logoFileId;
	}

	public String getPerDesc() {

		return perDesc;
	}

	public void setPerDesc(String perDesc) {

		this.perDesc = perDesc;
	}

	public boolean isDelete() {

		return delete;
	}

	public void setDelete(boolean delete) {

		this.delete = delete;
	}
}
