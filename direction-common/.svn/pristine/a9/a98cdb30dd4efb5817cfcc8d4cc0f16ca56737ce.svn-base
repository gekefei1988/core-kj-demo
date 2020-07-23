package com.direction.spring.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CreateDataEntity extends BaseEntity {
	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 6487083762929032961L;

	// ~ Fields
	// ==================================================================================================================

	@Column(name = "create_by", updatable = false)
	@CreatedBy
	private String createBy;

	@Column(name = "create_date", updatable = false)
	@CreatedDate
	private Date createDate;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

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
