package com.direction.core.modules.sys.log.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseOperLog implements Serializable {

	// ~ Static Fields
	// ==================================================================================================================
	private static final long serialVersionUID = -6902546210166012448L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_oper_log_pk")
	@GenericGenerator(name = "sys_oper_log_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	// 标题
	@Column(name = "title")
	private String title;

	// 操作类型
	@Column(columnDefinition = "CHAR", name = "oper_type")
	private String operType;

	// 操作方法
	@Column(name = "method")
	private String method;

	// 参数或者对象
	@Column(columnDefinition = "text", name = "params")
	private String params;

	// 操作结果
	@Column(columnDefinition = "CHAR", name = "oper_result")
	private String operResult;

	// 操作人
	@Column(name = "create_by")
	private String createBy;

	@CreationTimestamp
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "tenant_id")
	private String tenantId;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getId() {

		return id;
	}

	public String getTenantId() {

		return tenantId;
	}

	public void setTenantId(String tenantId) {

		this.tenantId = tenantId;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getOperType() {

		return operType;
	}

	public void setOperType(String operType) {

		this.operType = operType;
	}

	public String getMethod() {

		return method;
	}

	public void setMethod(String method) {

		this.method = method;
	}

	public String getParams() {

		return params;
	}

	public void setParams(String params) {

		this.params = params;
	}

	public String getOperResult() {

		return operResult;
	}

	public void setOperResult(String operResult) {

		this.operResult = operResult;
	}

	public String getCreateBy() {

		return createBy;
	}

	public void setCreateBy(String createBy) {

		this.createBy = createBy;
	}

	public Date getCreateDate() {

		return createDate;
	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;
	}
}
