package com.direction.common.utils.file;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.direction.common.exception.ServiceException;

// ~ File Information
// ====================================================================================================================

public class FTPClientUtils {

	// ~ Static Fields
	// ==================================================================================================================

	private static final Logger logger = LoggerFactory.getLogger(FTPClientUtils.class);

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 判断文件是否存在.
	 * 
	 * @param ftpClient
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean existFile(FTPClient ftpClient, String path) throws IOException {

		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		if (ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * FTP上传文件.
	 * 
	 * @param ftpClientVO
	 * @param filePath
	 * @param fileName
	 * @param inputStream
	 * @return
	 */
	public static boolean uploadFile(FTPClientVO ftpClientVO, String filePath, String fileName, InputStream inputStream) {

		if (StringUtils.isAnyBlank(ftpClientVO.getHostName(), ftpClientVO.getUserName())) {
			throw new ServiceException("启用FTP远程文件上传，但未获取到服务器相关配置");
		}

		FTPClient ftpClient = new FTPClient();

		// 设置超时
		ftpClient.setConnectTimeout(new Long(ftpClientVO.getTimeout()).intValue());

		// 设置编码
		ftpClient.setControlEncoding("UTF-8");
		
		boolean result = true;

		try {

			// 连接FTP服务器
			ftpClient.connect(ftpClientVO.getHostName(), ftpClientVO.getPort());

			// 登录FTP服务器
			ftpClient.login(ftpClientVO.getUserName(), ftpClientVO.getPassword());

			// 是否成功登录FTP服务器
			int replyCode = ftpClient.getReplyCode();

			// 登陆失败
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				logger.error("FTP服务器，登录失败");
				throw new ServiceException("FTP服务器，登录失败");
			}

			logger.debug("FTP服务器，登录成功");

			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			ftpClient.changeWorkingDirectory(ftpClientVO.getPathName());
			
			if (!existFile(ftpClient, filePath)) {
				logger.debug("FTP服务器，目录:" + filePath + "，不存在开始创建文件夹");
				
				// 切换路径 创建路径
				result = ftpClient.makeDirectory(filePath);

				if (!result) {
					logger.error("FTP服务器，目录创建失败");
					throw new ServiceException("FTP服务器，目录创建失败");
				}
			}

			logger.debug("FTP服务器，目录创建成功，切换目录：" + ftpClientVO.getPathName() + filePath);

			ftpClient.changeWorkingDirectory(filePath);

			ftpClient.enterLocalPassiveMode();

			// 设置缓冲
			ftpClient.setBufferSize(1024 * 1024 * 20);

			// 保持连接
			ftpClient.setKeepAlive(true);
			result = ftpClient.storeFile(new String(fileName.getBytes("utf-8"), "iso-8859-1"), inputStream);

			if (!result) {
				logger.error("FTP服务器，文件创建失败");
				throw new ServiceException("FTP服务器，文件创建失败");
			}

			logger.debug("FTP服务器，文件创建成功");

			inputStream.close();
			ftpClient.logout();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FTP服务器，错误信息：" + e.getMessage());
			result = false;
		} finally {

			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
					result = false;
				}
			}
		}

		return result;
	}
}
