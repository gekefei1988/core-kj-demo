package com.direction.core.modules.sys.employee.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 员工信息.
 * 
 * <pre>
 * 员工信息
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseEmployee extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 2006237391839920814L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_employee_pk")
	@GenericGenerator(name = "sys_employee_pk", strategy = "uuid")
	@Column(name = "emp_id", unique = true, nullable = false)
	private String empId;

	// 员工编号
	@Column(name = "emp_no")
	private String empNo;

	// 姓名
	@Column(name = "emp_name")
	private String empName;

	// 电话
	@Column(name = "telephone")
	private String telephone;

	// 性别 M:男, F: 女
	@Column(name = "sex", columnDefinition = "CHAR")
	private String sex;

	// 邮箱
	@Column(name = "email")
	private String email;

	// 邮编
	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "open_account", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private Boolean openAccount;

	// 住址
	@Column(name = "address")
	private String address;

	// 身份证
	@Column(name = "id_card")
	private String idCard;

	// 归属区域
	@Column(name = "area_codes")
	private String areaCodes;

	@Column(name = "area_names")
	private String areaNames;

	@Column(name = "org_id")
	private String orgId;

	@Column(name = "org_ids")
	private String orgIds;

	// 岗位
	@Column(name = "post_id")
	private String postId;

	// 0正常, 1停职 , 2离职
	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	// 学历
	@Column(name = "education")
	private String education;

	@Column(name = "birthday")
	private Date birthday;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getEmpId() {

		return empId;
	}

	public void setEmpId(String empId) {

		this.empId = empId;
	}

	public String getEmpNo() {

		return empNo;
	}

	public void setEmpNo(String empNo) {

		this.empNo = empNo;
	}

	public String getEmpName() {

		return empName;
	}

	public void setEmpName(String empName) {

		this.empName = empName;
	}

	public String getTelephone() {

		return telephone;
	}

	public void setTelephone(String telephone) {

		this.telephone = telephone;
	}

	public String getSex() {

		return sex;
	}

	public void setSex(String sex) {

		this.sex = sex;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getIdCard() {

		return idCard;
	}

	public void setIdCard(String idCard) {

		this.idCard = idCard;
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

	public String getOrgId() {

		return orgId;
	}

	public void setOrgId(String orgId) {

		this.orgId = orgId;
	}

	public Date getBirthday() {

		return birthday;
	}

	public void setBirthday(Date birthday) {

		this.birthday = birthday;
	}

	public String getOrgIds() {

		return orgIds;
	}

	public void setOrgIds(String orgIds) {

		this.orgIds = orgIds;
	}

	public String getPostId() {

		return postId;
	}

	public void setPostId(String postId) {

		this.postId = postId;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;
	}

	public String getEducation() {

		return education;
	}

	public void setEducation(String education) {

		this.education = education;
	}

	public Boolean getOpenAccount() {

		return openAccount;
	}

	public boolean isOpenAccount() {

		if (this.getOpenAccount() == null) {
			return false;
		}

		return openAccount;
	}

	public void setOpenAccount(Boolean openAccount) {

		this.openAccount = openAccount;
	}
}
