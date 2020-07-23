package com.direction.core.modules.sys.company.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 企业备案基础信息.
 * 
 * <pre>
 * 企业备案基础信息
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseCompanyInfo extends TenantDataEntity {

	private static final long serialVersionUID = 4716753265763114818L;

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_company_info_pk")
	@GenericGenerator(name = "sys_company_info_pk", strategy = "assigned")
	@Column(name = "company_id", unique = true, nullable = false)
	private String companyId;

	// 统一社会信用代码
	private String socialCreditCode;

	// 法人责任主体类型
	private String legalType;

	// 法人代表名称
	private String legalRepresent;

	// 法人身份证号码
	private String legalId;

	// 备案日期，格式：yyyy-mm-dd
	private Date recordDate;

	// 注册日期
	private Date regDate;

	// 证照有效期 --工商注册登记证的有效期截止日期，格式：yyyy-mm-dd
	private Date validityDate;
	
	// 联系人--责任主体联系人名称
	private String contacts;

	// 联系电话--指责任主体负责人的固定电话、手机等主要联络方式，区号-固话号码-分机号
	private String tel;

	// 传真
	private String fax;

	// 备案类型--00：节点， 01：经营者
	private String regType;
	
	// 企业网址
	private String companyWeb;
	
	// 所属行业 -企业类型或自然人从事行业代码代码：使用 GB/T 4754 规定 的国民经济行业分类代码，详细到小类，共 5 位。
	private String industryCode;
	
	// 品种代码
	private String varietyCode;

	// 企业类型--00：种养殖，10：生产加工企业，11：屠宰厂，20：批发市 场，21：配送企业， 22：超 市，23：菜市场 30：团体消费 单位，40：电商
	private String enterpriseType;

	// 经营地址GPS经度--经营地址 GPS经度，保留 10 位小数
	private double longitude;

	// 经营地址GPS纬度--经营地址GPS纬度，保留 10 位小数
	private double latitude;

	// 企业荣誉
	private String enterpriseHonor;

	// 经营范围
	private String businessScope;

	// 企业简介
	private String companyContent;

	// 企业logo
	private String logo;
	
	// 企业行业类型

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

	public String getSocialCreditCode() {

		return socialCreditCode;
	}

	public void setSocialCreditCode(String socialCreditCode) {

		this.socialCreditCode = socialCreditCode;
	}

	public String getLegalType() {

		return legalType;
	}

	public void setLegalType(String legalType) {

		this.legalType = legalType;
	}

	public String getLegalRepresent() {

		return legalRepresent;
	}

	public void setLegalRepresent(String legalRepresent) {

		this.legalRepresent = legalRepresent;
	}

	public Date getRecordDate() {

		return recordDate;
	}

	public void setRecordDate(Date recordDate) {

		this.recordDate = recordDate;
	}

	public Date getRegDate() {

		return regDate;
	}

	public void setRegDate(Date regDate) {

		this.regDate = regDate;
	}

	public Date getValidityDate() {

		return validityDate;
	}

	public void setValidityDate(Date validityDate) {

		this.validityDate = validityDate;
	}

	public double getLongitude() {

		return longitude;
	}

	public void setLongitude(double longitude) {

		this.longitude = longitude;
	}

	public double getLatitude() {

		return latitude;
	}

	public void setLatitude(double latitude) {

		this.latitude = latitude;
	}

	public String getContacts() {

		return contacts;
	}

	public void setContacts(String contacts) {

		this.contacts = contacts;
	}

	public String getTel() {

		return tel;
	}

	public void setTel(String tel) {

		this.tel = tel;
	}

	public String getRegType() {

		return regType;
	}

	public void setRegType(String regType) {

		this.regType = regType;
	}

	public String getEnterpriseType() {

		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {

		this.enterpriseType = enterpriseType;
	}

	public String getLegalId() {

		return legalId;
	}

	public void setLegalId(String legalId) {

		this.legalId = legalId;
	}

	public String getEnterpriseHonor() {

		return enterpriseHonor;
	}

	public void setEnterpriseHonor(String enterpriseHonor) {

		this.enterpriseHonor = enterpriseHonor;
	}

	public String getBusinessScope() {

		return businessScope;
	}

	public void setBusinessScope(String businessScope) {

		this.businessScope = businessScope;
	}

	public String getCompanyContent() {

		return companyContent;
	}

	public void setCompanyContent(String companyContent) {

		this.companyContent = companyContent;
	}

	public String getLogo() {

		return logo;
	}

	public void setLogo(String logo) {

		this.logo = logo;
	}

	public String getFax() {

		return fax;
	}

	public void setFax(String fax) {

		this.fax = fax;
	}

	public String getCompanyWeb() {

		return companyWeb;
	}

	public void setCompanyWeb(String companyWeb) {

		this.companyWeb = companyWeb;
	}

	public String getIndustryCode() {

		return industryCode;
	}

	public void setIndustryCode(String industryCode) {

		this.industryCode = industryCode;
	}

	public String getVarietyCode() {

		return varietyCode;
	}

	public void setVarietyCode(String varietyCode) {

		this.varietyCode = varietyCode;
	}
}
