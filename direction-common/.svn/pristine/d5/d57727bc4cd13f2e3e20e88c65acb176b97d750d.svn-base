package com.direction.core.modules.sys.log.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.BaseEntity;

// ~ File Information
// ====================================================================================================================

/**
 * 错误日志基础类.
 * 
 * <pre>
 * 错误日志基础类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseErrorLog extends BaseEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 1319727576612894149L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_error_log_pk")
	@GenericGenerator(name = "sys_error_log_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "exp_type")
	private String expType;

	@Column(name = "method_name")
	private String methodName;

	@Column(name = "method_param")
	private String methodParam;

	@Column(name = "class_name")
	private String className;

	@Column(name = "message")
	private String message;

	@Column(name = "create_date", updatable = false)
	private Date createDate;

	@Column(name = "create_by", updatable = false)
	private String createBy;

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

	public String getExpType() {

		return expType;
	}

	public void setExpType(String expType) {

		this.expType = expType;
	}

	public String getMethodName() {

		return methodName;
	}

	public void setMethodName(String methodName) {

		this.methodName = methodName;
	}

	public String getMethodParam() {

		return methodParam;
	}

	public void setMethodParam(String methodParam) {

		this.methodParam = methodParam;
	}

	public String getClassName() {

		return className;
	}

	public void setClassName(String className) {

		this.className = className;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public Date getCreateDate() {

		return createDate;
	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;
	}

	public String getCreateBy() {

		return createBy;
	}

	public void setCreateBy(String createBy) {

		this.createBy = createBy;
	}
}
