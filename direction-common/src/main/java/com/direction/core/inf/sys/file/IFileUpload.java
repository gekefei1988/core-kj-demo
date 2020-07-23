package com.direction.core.inf.sys.file;

//~ File Information
//====================================================================================================================

/**
 * 上传文件接口.
 * 
 * @author Evan
 *
 */
public interface IFileUpload {

	// ~ Static Fields
	// ==================================================================================================================
	
	// ~ Methods
	// ==================================================================================================================
	
	public String getFileId();

	public String getFileName();

	public String getFileType();

	public String getSuffixName();

	public Long getFileSize();

	public String getFileUrl();

	public String getCategoryNo();

	public String getCategoryName();

	public String getStatus();

	public String getBusiType();

	public String getBusiId();

	public String getSubBusiId();
}