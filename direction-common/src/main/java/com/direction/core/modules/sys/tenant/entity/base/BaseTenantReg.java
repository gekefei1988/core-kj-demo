package com.direction.core.modules.sys.tenant.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
@MappedSuperclass
public class BaseTenantReg implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 1991052843349992175L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_tenant_reg_pk")
	@GenericGenerator(name = "sys_tenant_reg_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "tenant_name")
	@NotBlank(message = "租户名称不能为空")
	private String tenantName;

	@Column(name = "contact_person")
	@NotBlank(message = "负责人不能为空")
	private String contactPerson;

	// 手机号码
	@Column(name = "telephone")
	@NotBlank(message = "手机号码不能为空")
	private String telephone;

	// 密码
	@Column(name = "password")
	@NotBlank(message = "密码不能为空")
	private String password;

	// 注册初始密码
	@Column(name = "init_pwd")
	private String initPwd;

	// 租户类型
	@Column(name = "tenant_type", columnDefinition = "char")
	@NotBlank(message = "租户类型不能为空")
	private String tenantType;

	// 状态
	@Column(name = "status", columnDefinition = "char")
	@NotBlank(message = "租户状态不能为空")
	private String status;

	// 拒绝原因
	@Column(name = "refuse")
	private String refuse;

	// 是否删除
	@Column(name = "is_delete", columnDefinition = "char")
	@Type(type = "true_false")
	private Boolean isDelete;

	// 提交时间
	@Column(name = "submit_date")
	private Date submitDate;

	// 注册时间
	@Column(name = "reg_date")
	@NotNull(message = "注册时间不能为空")
	private Date regDate;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getId() {

		return id;
	}

	public String getTenantName() {

		return tenantName;
	}

	public void setTenantName(String tenantName) {

		this.tenantName = tenantName;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getContactPerson() {

		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {

		this.contactPerson = contactPerson;
	}

	public String getTelephone() {

		return telephone;
	}

	public void setTelephone(String telephone) {

		this.telephone = telephone;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getInitPwd() {

		return initPwd;
	}

	public void setInitPwd(String initPwd) {

		this.initPwd = initPwd;
	}

	public String getTenantType() {

		return tenantType;
	}

	public void setTenantType(String tenantType) {

		this.tenantType = tenantType;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getRefuse() {

		return refuse;
	}

	public void setRefuse(String refuse) {

		this.refuse = refuse;
	}

	public Boolean getIsDelete() {
		return isDelete = isDelete == null ? false : isDelete;
	}

	public void setIsDelete(Boolean isDelete) {

		this.isDelete = isDelete;
	}

	public Date getSubmitDate() {

		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {

		this.submitDate = submitDate;
	}

	public Date getRegDate() {

		return regDate;
	}

	public void setRegDate(Date regDate) {

		this.regDate = regDate;
	}
}
