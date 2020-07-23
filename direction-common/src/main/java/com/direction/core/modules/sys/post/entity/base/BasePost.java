package com.direction.core.modules.sys.post.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BasePost extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -1111800312729073020L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_post_pk")
	@GenericGenerator(name = "sys_post_pk", strategy = "uuid")
	@Column(name = "post_id", unique = true, nullable = false)
	private String postId;

	@Column(name = "post_no")
	private String postNo;

	@Column(name = "post_name")
	private String postName;

	@Column(name = "post_type", columnDefinition = "CHAR")
	private String postType;

	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	@Column(name = "display_order")
	private Integer displayOrder;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getPostId() {

		return postId;
	}

	public void setPostId(String postId) {

		this.postId = postId;
	}

	public String getPostNo() {

		return postNo;
	}

	public void setPostNo(String postNo) {

		this.postNo = postNo;
	}

	public String getPostName() {

		return postName;
	}

	public void setPostName(String postName) {

		this.postName = postName;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getPostType() {

		return postType;
	}

	public void setPostType(String postType) {

		this.postType = postType;
	}

	public Integer getDisplayOrder() {

		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {

		this.displayOrder = displayOrder;
	}
}
