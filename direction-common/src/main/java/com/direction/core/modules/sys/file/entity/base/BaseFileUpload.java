package com.direction.core.modules.sys.file.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.TenantDataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseFileUpload extends TenantDataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 444671425035837381L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_file_pk")
	@GenericGenerator(name = "sys_file_pk", strategy = "uuid")
	@Column(name = "file_id", unique = true, nullable = false)
	private String fileId;

	// 文件名称
	@Column(name = "file_name")
	private String fileName;

	// 文件类型
	@Column(name = "file_type")
	private String fileType;

	// 后缀名
	@Column(name = "suffix_name")
	private String suffixName;

	// 文件大小
	@Column(name = "file_size", columnDefinition = "DECIMAL")
	private Long fileSize;

	// 文件路径
	@Column(name = "file_url")
	private String fileUrl;

	// 文件分组
	@Column(name = "category_no")
	private String categoryNo;
	
	// 业务分类
	@Column(name = "busi_type")
	private String busiType;
	
	// 业务id
	@Column(name = "busi_id")
	private String busiId;
	
	// 下级业务id
	@Column(name = "sub_busi_id")
	private String subBusiId;

	@Column(name = "category_name")
	private String categoryName;
	
	// 状态
	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getFileId() {

		return fileId;
	}

	public void setFileId(String fileId) {

		this.fileId = fileId;
	}

	public String getFileName() {

		return fileName;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	public String getFileType() {

		return fileType;
	}

	public void setFileType(String fileType) {

		this.fileType = fileType;
	}

	public String getSuffixName() {

		return suffixName;
	}

	public void setSuffixName(String suffixName) {

		this.suffixName = suffixName;
	}

	public Long getFileSize() {

		return fileSize;
	}

	public void setFileSize(Long fileSize) {

		this.fileSize = fileSize;
	}

	public String getFileUrl() {

		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {

		this.fileUrl = fileUrl;
	}

	public String getCategoryNo() {

		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {

		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {

		return categoryName;
	}

	public void setCategoryName(String categoryName) {

		this.categoryName = categoryName;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getSubBusiId() {
		return subBusiId;
	}

	public void setSubBusiId(String subBusiId) {
		this.subBusiId = subBusiId;
	}
}