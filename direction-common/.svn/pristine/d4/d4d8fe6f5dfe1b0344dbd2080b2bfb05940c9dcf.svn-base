package com.direction.core.modules.sys.tenant.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.spring.mvc.entity.OperTenantDataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseTenantCertWeb extends OperTenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -1898303466873923671L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_tenant_cert_web_pk")
	@GenericGenerator(name = "sys_tenant_cert_web_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "tenant_id")
	@NotBlank(message = "无法获取到当前主体信息")
	private String tenantId;

	@Column(name = "web_name")
	private String webName;

	@Column(name = "web_url")
	private String webUrl;

	@Column(name = "web_desc")
	private String webDesc;

	@Column(name = "is_default", columnDefinition = "char")
	@Type(type = "true_false")
	private boolean defaultWeb;

	@Column(name = "display_order")
	private int displayOrder;

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

	public String getTenantId() {

		return tenantId;
	}

	public void setTenantId(String tenantId) {

		this.tenantId = tenantId;
	}

	public String getWebName() {

		return webName;
	}

	public void setWebName(String webName) {

		this.webName = webName;
	}

	public String getWebUrl() {

		return webUrl;
	}

	public void setWebUrl(String webUrl) {

		this.webUrl = webUrl;
	}

	public String getWebDesc() {

		return webDesc;
	}

	public void setWebDesc(String webDesc) {

		this.webDesc = webDesc;
	}

	public boolean isDefaultWeb() {

		return defaultWeb;
	}

	public void setDefaultWeb(boolean defaultWeb) {

		this.defaultWeb = defaultWeb;
	}

	public int getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {

		this.displayOrder = displayOrder;
	}

	public boolean isDelete() {

		return delete;
	}

	public void setDelete(boolean delete) {

		this.delete = delete;
	}
}
