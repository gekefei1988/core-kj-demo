package com.direction.spring.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UpdateDataEntity extends BaseEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4429570003876200316L;

	// ~ Fields
	// ==================================================================================================================

	@Column(name = "update_by")
	@LastModifiedBy
	private String updateBy;

	@Column(name = "update_date")
	@LastModifiedDate
	private Date updateDate;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getUpdateBy() {

		return updateBy;
	}

	public void setUpdateBy(String updateBy) {

		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {

		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {

		this.updateDate = updateDate;
	}
}
