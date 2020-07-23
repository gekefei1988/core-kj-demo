package com.direction.spring.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// ~ File Information
// ====================================================================================================================

/**
 * 数据实体.
 * 
 * <pre>
 * 	包含新增人和时间,修改人和时间.
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class DataEntity extends BaseEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -1045490629466024532L;

	// ~ Fields
	// ==================================================================================================================
	
	@Column(name = "create_by", updatable = false)
	@CreatedBy
	private String createBy;

	@Column(name = "update_by")
	@LastModifiedBy
	private String updateBy;

	@Column(name = "create_date", updatable = false)
	@CreatedDate
	private Date createDate;

	@Column(name = "update_date")
	@LastModifiedDate
	private Date updateDate;

	@Column(name = "remarks")
	private String remarks;

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

	public String getUpdateBy() {

		return updateBy;
	}

	public void setUpdateBy(String updateBy) {

		this.updateBy = updateBy;
	}

	public Date getCreateDate() {

		return createDate;
	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;
	}

	public Date getUpdateDate() {

		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {

		this.updateDate = updateDate;
	}

	public String getRemarks() {

		return remarks;
	}

	public void setRemarks(String remarks) {

		this.remarks = remarks;
	}
}
