package com.direction.core.modules.sys.file.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.EIPService;
import com.direction.core.inf.sys.config.IConfig;
import com.direction.core.inf.sys.file.IFileUpload;
import com.direction.core.modules.sys.file.entity.base.BaseFileUpload;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_file_upload")
public class FileUpload extends BaseFileUpload implements IFileUpload {

	// ~ Static Fields
	// ==================================================================================================================
	
	private static final long serialVersionUID = -4545549009138368869L;

	private static final String CONFIG_STATIC_FILE_PATH = "sys_static_file_path";
	
	// 本地服务器
	public static final int FILE_UPLOAD_SERVER_LOCAL = 1;
	
	// FTP服务器
	public static final int FILE_UPLOAD_SERVER_FTP = 2;
	
	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public String getStaticFileUrl() {
		
		IConfig config = EIPService.getConfigService().getConfig(CONFIG_STATIC_FILE_PATH);
		String fileUrlPath = "";
		
		if (config != null && StringUtils.isNotBlank(config.getConfigValue())) {
			
			// 判断末尾是否是"/"
			if (StringUtils.lastIndexOf(config.getConfigValue(), "/") == (config.getConfigValue().length() - 1)) {
				fileUrlPath = config.getConfigValue() + this.getFileUrl();
			}
			else {
				fileUrlPath = config.getConfigValue() + "/" + this.getFileUrl();
			}
		}

		return fileUrlPath;
	}
}
