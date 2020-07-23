package com.direction.core.modules.sys.file.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.utils.file.FTPClientVO;
import com.direction.core.inf.EIPService;
import com.direction.core.inf.sys.config.IConfig;
import com.direction.core.modules.sys.file.entity.FileUpload;

//~ File Information
//====================================================================================================================

@Service
@Transactional(readOnly = true)
public class FileUploadSettingService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	private final Logger logger = LoggerFactory.getLogger(FileUploadSettingService.class);
	
	@Autowired
	private FTPClientVO ftpClientVO;
	
	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 返回文件上传保存方式.
	 * 
	 * @return
	 */
	public int getFileUploadServerType() {
		
		int type = FileUpload.FILE_UPLOAD_SERVER_LOCAL;
		
//		type = FileUpload.FILE_UPLOAD_SERVER_FTP;
		
		logger.debug("文件上传服务器方式：" + type);
		
		return type;
	}
	
	/**
	 * 获取FTP参数
	 * 
	 * @return FTPClientVO
	 */
	public FTPClientVO getFTPClient() {
		return ftpClientVO;
	}
	
	/**
	 * 获取上传地址.
	 * 
	 * @param path
	 * @return
	 */
	public String getConfigPath(String path) {
		
		// 文件上传地址
		IConfig config = EIPService.getConfigService().getConfig("sys_file_upload_path");
		String filePath = "";
		
		if (config != null && StringUtils.isNotBlank(config.getConfigValue())) {
			
			// 判断末尾是否是"/"
			if (StringUtils.lastIndexOf(config.getConfigValue(), "/") == (config.getConfigValue().length() - 1)) {
				filePath = config.getConfigValue() + path;
			}
			else {
				filePath = config.getConfigValue() + "/" + path;
			}
		}
		
		logger.debug("本地文件上传地址：" + filePath);
		
		return filePath;
	}
}