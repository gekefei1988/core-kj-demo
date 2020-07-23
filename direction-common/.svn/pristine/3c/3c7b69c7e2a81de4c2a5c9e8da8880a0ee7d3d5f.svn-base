package com.direction.common.utils.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ~ File Information
// ====================================================================================================================

/**
 * 文件相关配置类.
 * 
 * <pre>
 * 	文件相关配置类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Configuration
public class FileConfiguration {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// FTP IP
	@Value("${file.upload.hostName:}")
	private String hostName;

	// FTP 端口号
	@Value("${file.upload.port:21}")
	private int port;

	// FTP 登录账号
	@Value("${file.upload.userName:}")
	private String userName;

	// FTP 登录密码
	@Value("${file.upload.password:}")
	private String password;

	// FTP 工作路径
	@Value("${file.upload.pathName:}")
	private String pathName;
	
	// 超时时间
	@Value("${file.upload.timeout:0}")
	private long timeout;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取远程FTP参数配置.
	 * 
	 * @return
	 */
	@Bean
	public FTPClientVO getFtpClientVO() {
		
		FTPClientVO ftpVO = new FTPClientVO();
		ftpVO.setHostName(hostName);
		ftpVO.setPort(port);
		ftpVO.setUserName(userName);
		ftpVO.setPathName(pathName);
		ftpVO.setPassword(password);
		
		if (timeout > 0) {
			ftpVO.setTimeout(timeout);
		}

		return ftpVO;
	}
}