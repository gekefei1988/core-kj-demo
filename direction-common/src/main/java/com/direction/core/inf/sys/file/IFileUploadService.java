package com.direction.core.inf.sys.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.direction.core.modules.sys.file.entity.FileUpload;

// ~ File Information
// ====================================================================================================================

public interface IFileUploadService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 上传文件.
	 * 
	 * @param file
	 * @param categoryNo
	 * @return
	 */
	IFileUpload upload(MultipartFile file, String categoryNo);
	
	/**
	 * 上传文件.
	 * 
	 * @param file
	 * @param categoryNo
	 * @param busiId
	 * @return
	 */
	IFileUpload upload(MultipartFile file, String categoryNo, String busiId);
	
	/**
	 * 上传文件.
	 * 
	 * @param file
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @return
	 */
	IFileUpload upload(MultipartFile file, String categoryNo, String busiType, String busiId);
	
	/**
	 * 上传文件.
	 * 
	 * @param file
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @param subBusiId
	 * @return
	 */
	IFileUpload upload(MultipartFile file, String categoryNo, String busiType, String busiId, String subBusiId);
	
	/**
	 * 获取文件清单.
	 * 
	 * @param categoryNo
	 * @return
	 */
	List<IFileUpload> getFileList(String categoryNo);

	/**
	 * 获取文件清单.
	 * 
	 * @param categoryNo
	 * @param busiId
	 * @return
	 */
	List<IFileUpload> getFileList(String categoryNo, String busiId);
	
	/**
	 * 获取文件清单.
	 * 
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @return
	 */
	List<IFileUpload> getFileList(String categoryNo, String busiType, String busiId);
	
	/**
	 * 获取文件清单.
	 * 
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @param subBusiId
	 * @return
	 */
	List<IFileUpload> getFileList(String categoryNo, String busiType, String busiId, String subBusiId);
	
	/**
	 * 获取文件.
	 * 
	 * @param id
	 * @return
	 */
	public FileUpload get(String id);
	
	/**
	 * 删除文件.
	 * 
	 * @param id
	 * @return
	 */
	public void delete(String fileId);
	
	/**
	 * 获取文件.
	 * 
	 * @param categoryNo
	 * @return
	 */
	IFileUpload getFile(String categoryNo);

	/**
	 * 获取文件.
	 * 
	 * @param categoryNo
	 * @param busiId
	 * @return
	 */
	IFileUpload getFile(String categoryNo, String busiId);
	
	/**
	 * 获取文件.
	 * 
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @return
	 */
	IFileUpload getFile(String categoryNo, String busiType, String busiId);
	
	/**
	 * 获取文件.
	 * 
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @param subBusiId
	 * @return
	 */
	IFileUpload getFile(String categoryNo, String busiType, String busiId, String subBusiId);
}