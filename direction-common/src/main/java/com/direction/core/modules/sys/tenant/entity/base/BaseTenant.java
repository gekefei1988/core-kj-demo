package com.direction.core.modules.sys.tenant.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.common.annotation.CombColumn;
import com.direction.common.annotation.QueryType;
import com.direction.common.annotation.RelationType;
import com.direction.common.annotation.SearchColumn;
import com.direction.spring.mvc.entity.DataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseTenant extends DataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 6701729625827428021L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_tenant_pk")
	@GenericGenerator(name = "sys_tenant_pk", strategy = "assigned")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

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

	// 租户编号
	@Column(name = "tenant_no")
	private String tenantNo;

	// 租户名称
	@Column(name = "tenant_name")
	@SearchColumn(queryType = QueryType.LIKE, combRelationType = RelationType.OR, combColumns = {
		@CombColumn(column = "tenantNo", queryType = QueryType.LIKE)
	})
	private String tenantName;

	// 使用类型
	@Column(name = "used_type", columnDefinition = "char")
	@NotBlank(message = "租户使用类型不能为空")
	private String usedType;

	// 冻结原因
	@Column(name = "freeze")
	private String freeze;

	// 有效期
	@Column(name = "expired_date")
	private Date expiredDate;

	// 最大用户数
	@Column(name = "max_user_num")
	private Integer maxUserNum;

	// 版本
	@Column(name = "version")
	@NotBlank(message = "使用版本不能为空")
	private String version;

	// 是否删除
	@Column(name = "is_delete", columnDefinition = "char")
	@Type(type = "true_false")
	private Boolean isDelete;

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

	public String getTenantNo() {

		return tenantNo;
	}

	public void setTenantNo(String tenantNo) {

		this.tenantNo = tenantNo;
	}

	public String getTenantName() {

		return tenantName;
	}

	public void setTenantName(String tenantName) {

		this.tenantName = tenantName;
	}

	public String getUsedType() {

		return usedType;
	}

	public void setUsedType(String usedType) {

		this.usedType = usedType;
	}

	public String getFreeze() {

		return freeze;
	}

	public void setFreeze(String freeze) {

		this.freeze = freeze;
	}

	public Date getExpiredDate() {

		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {

		this.expiredDate = expiredDate;
	}

	public Integer getMaxUserNum() {

		return maxUserNum;
	}

	public void setMaxUserNum(Integer maxUserNum) {

		this.maxUserNum = maxUserNum;
	}

	public String getVersion() {

		return version;
	}

	public void setVersion(String version) {

		this.version = version;
	}

	public Boolean getIsDelete() {

		return isDelete = isDelete == null ? false : isDelete;
	}

	public void setIsDelete(Boolean isDelete) {

		this.isDelete = isDelete;
	}
}
