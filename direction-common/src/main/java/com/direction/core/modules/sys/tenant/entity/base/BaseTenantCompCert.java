package com.direction.core.modules.sys.tenant.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.OperDataEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 企业认证信息.
 * 
 * <pre>
 * 企业认证信息
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseTenantCompCert extends OperDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -7265147019975854868L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_tenant_cert_comp_pk")
	@GenericGenerator(name = "sys_tenant_cert_comp_pk", strategy = "assigned")
	@Column(name = "id", unique = true, nullable = false)
	@NotBlank(message = "无法获取当前主体信息")
	private String id;
	
	// 注册时间
	@Column(name = "set_up_date")
	@NotNull(message = "企业注册日期不能为空")
	private Date setUpDate;

	// 状态
	@Column(name = "status", columnDefinition = "char")
	private String status;

	// 社会统一信用代码
	@Column(name = "credit_code")
	@NotBlank(message = "社会统一信用代码不能为空")
	private String creditCode;

	// 企业类型
	@Column(name = "comp_type")
	@NotBlank(message = "企业类型不能为空")
	private String compType;

	// 所属行业
	@Column(name = "industry")
	private String industry;

	// 注册机关
	@Column(name = "reg_dept")
	private String regDept;

	// 有效期
	@Column(name = "validity_date")
	private Date validityDate;

	// 注册地址
	@Column(name = "reg_address")
	private String regAddress;

	// 经营范围
	@Column(name = "business")
	private String business;

	// 经营范围说明
	@Column(name = "business_desc")
	private String businessDesc;

	// 营业执照
	@Column(name = "business_license")
	private String businessLicense;

	// 文件地址
	@Column(name = "business_license_file_id")
	private String businessLicenseFileId;

	// logo
	@Column(name = "logo")
	private String logo;

	@Column(name = "logo_file_id")
	private String logoFileId;

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

	public Date getSetUpDate() {

		return setUpDate;
	}

	public void setSetUpDate(Date setUpDate) {

		this.setUpDate = setUpDate;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getCreditCode() {

		return creditCode;
	}

	public void setCreditCode(String creditCode) {

		this.creditCode = creditCode;
	}

	public String getCompType() {

		return compType;
	}

	public void setCompType(String compType) {

		this.compType = compType;
	}

	public String getIndustry() {

		return industry;
	}

	public void setIndustry(String industry) {

		this.industry = industry;
	}

	public String getRegDept() {

		return regDept;
	}

	public void setRegDept(String regDept) {

		this.regDept = regDept;
	}

	public Date getValidityDate() {

		return validityDate;
	}

	public void setValidityDate(Date validityDate) {

		this.validityDate = validityDate;
	}

	public String getRegAddress() {

		return regAddress;
	}

	public void setRegAddress(String regAddress) {

		this.regAddress = regAddress;
	}

	public String getBusiness() {

		return business;
	}

	public void setBusiness(String business) {

		this.business = business;
	}

	public String getBusinessDesc() {

		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {

		this.businessDesc = businessDesc;
	}

	public String getBusinessLicense() {

		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {

		this.businessLicense = businessLicense;
	}

	public String getBusinessLicenseFileId() {

		return businessLicenseFileId;
	}

	public void setBusinessLicenseFileId(String businessLicenseFileId) {

		this.businessLicenseFileId = businessLicenseFileId;
	}

	public boolean isDelete() {

		return delete;
	}

	public void setDelete(boolean delete) {

		this.delete = delete;
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
}
