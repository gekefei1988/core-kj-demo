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

/**
 * 租户荣誉基础类.
 * 
 * <pre>
 * 	租户荣誉基础类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
public class BaseTenantCertHonor extends OperTenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================
	private static final long serialVersionUID = -7509643339169984622L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_tenant_cert_honor_pk")
	@GenericGenerator(name = "sys_tenant_cert_honor_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "tenant_id")
	@NotBlank(message = "无法获取到当前主体信息")
	private String tenantId;

	@Column(name = "honor_desc")
	private String honorDesc;

	@Column(name = "image_urls")
	private String imageUrls;

	@Column(name = "image_file_ids")
	private String imageFileIds;

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

	public String getHonorDesc() {

		return honorDesc;
	}

	public void setHonorDesc(String honorDesc) {

		this.honorDesc = honorDesc;
	}

	public String getImageUrls() {

		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {

		this.imageUrls = imageUrls;
	}

	public String getImageFileIds() {

		return imageFileIds;
	}

	public void setImageFileIds(String imageFileIds) {

		this.imageFileIds = imageFileIds;
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
