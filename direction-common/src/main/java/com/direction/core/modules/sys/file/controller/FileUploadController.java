package com.direction.core.modules.sys.file.controller;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.core.inf.sys.file.IFileUpload;
import com.direction.core.modules.sys.file.service.FileUploadServiceImpl;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

/**
 * 文件上传Controller.
 * 
 * @author Evan
 *
 */
@RestController
@RequestMapping("/core/sys/file")
public class FileUploadController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private FileUploadServiceImpl fileUploadServiceImpl;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 文件上传.
	 * 
	 * @param file
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @param subBusiId
	 * @return
	 */
	@RequestMapping(value = "/upload")
	public ResultJson upload(MultipartFile file, String categoryNo, String busiType, String busiId, String subBusiId) {
		
		logger.info("文件上传>>>upload..." + MessageFormat.format("categoryNo:{0},busiType:{1},busiId:{2},subBusiId:{3}", categoryNo, busiType, busiId, subBusiId));
		
		ResultJson resultJson;
		IFileUpload fileUpload = null;
		
		try {
			fileUpload = this.fileUploadServiceImpl.upload(file, categoryNo, busiType, busiId, subBusiId);
			
			resultJson = ResultJson.success(fileUpload);
		}
		catch (ServiceException se) {
			resultJson = ResultJson.fail(se.getCode(), se.getMessage());
		}
		catch (Exception e) {
			logger.error("上传文件失败", e);
			resultJson = ResultJson.fail("上传文件失败." + e.getMessage());
		}
		
		return resultJson;
	}
	
	/**
	 * 获取上传的文件.
	 * 
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @param subBusiId
	 * @return
	 */
	@RequestMapping(value = "/getFileUploadList")
	public ResultJson getFileUploadList(String categoryNo, String busiType, String busiId, String subBusiId) {
		
		logger.info("获取上传文件>>>getFileUploadList..." + MessageFormat.format("categoryNo:{0},busiType:{1},busiId:{2},subBusiId:{3}", categoryNo, busiType, busiId, subBusiId));
		
		List<IFileUpload> fileList = fileUploadServiceImpl.getFileList(categoryNo, busiType, busiId, subBusiId);
		
		return ResultJson.success(fileList);
	}
	
	/**
	 * 获取上传的文件.
	 * 
	 * @param categoryNo
	 * @param busiType
	 * @param busiId
	 * @param subBusiId
	 * @return
	 */
	@RequestMapping(value = "/getFileUpload")
	public ResultJson getFileUpload(String categoryNo, String busiType, String busiId, String subBusiId) {
		
		logger.info("获取上传文件>>>getFileUpload..." + MessageFormat.format("categoryNo:{0},busiType:{1},busiId:{2},subBusiId:{3}", categoryNo, busiType, busiId, subBusiId));
		
		IFileUpload fileUpload = fileUploadServiceImpl.getFile(categoryNo, busiType, busiId, subBusiId);
		
		return ResultJson.success(fileUpload);
	}
	
	/**
	 * 获取上传的文件.
	 * 
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value = "/get")
	public ResultJson get(String fileId) {
		
		logger.info("获取上传文件>>>get..." + MessageFormat.format("fileId:{0}", fileId));
		
		IFileUpload fileUpload = fileUploadServiceImpl.get(fileId);
		
		return ResultJson.success(fileUpload);
	}
	
	/**
	 * 删除上传的文件.
	 * 
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String fileId) {
		
		logger.info("删除上传文件>>>delete..." + MessageFormat.format("fileId:{0}", fileId));
		
		ResultJson resultJson;
		
		try {
			fileUploadServiceImpl.delete(fileId);
			resultJson = success();
		}
		catch (ServiceException se) {
			resultJson = ResultJson.fail(se.getCode(), se.getMessage());
		}
		catch (Exception e) {
			logger.error("删除文件失败.", e);
			resultJson = ResultJson.fail("删除文件失败." + e.getMessage());
		}
		
		return resultJson;
	}
}