package com.direction.core.modules.sys.file.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.common.utils.file.FileUploadUtils;
import com.direction.common.utils.lang.DateUtils;
import com.direction.core.inf.EIPService;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.config.IConfig;
import com.direction.core.modules.sys.file.entity.FileUpload;
import com.direction.core.modules.sys.file.repository.FileUploadRepository;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class FileUploadService extends BaseService<FileUploadRepository, FileUpload, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private FileUploadRepository fileUploadRepository;
	
//	@Autowired
//	private FTPClientVO ftpClientVO;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 保存上传文件.
	 * 
	 * @param fileUpload
	 */
	@Transactional(readOnly = false)
	public void saveFile(FileUpload fileUpload) {
		
		this.fileUploadRepository.save(fileUpload);
	}
	
	/**
	 * 获取上传文件列表.
	 * 
	 * @param fileUpload
	 * @return
	 */
	public List<FileUpload> getList(FileUpload fileUpload) {
		
		List<FileUpload> uploads = findList(fileUpload, Sort.by(Sort.Direction.DESC, "updateDate"));
		
		return uploads;
	}
	
	/**
	 * 获取上传文件信息.
	 * 
	 * @param fileUpload
	 * @return
	 */
	public FileUpload getFileUpload(FileUpload fileUpload) {
		
		FileUpload upload = null;
		
		List<FileUpload> uploads = getList(fileUpload);
		
		if (uploads != null && uploads.size() > 0) {
			upload = uploads.get(0);
		}
		
		return upload;
	}
	
	/**
	 * 上传文件.
	 * 
	 * @param file
	 * @param category
	 * @return
	 */
	@Transactional(readOnly = false)
	public ResultJson upload(MultipartFile file, String category) {
		
		ResultJson result = ResultJson.success();

		try {
			
			FileUpload fileEntity = new FileUpload();
			fileEntity.setFileName(file.getOriginalFilename());
			fileEntity.setFileSize(file.getSize());
			fileEntity.setCategoryNo(category);
			fileEntity.setFileType(file.getContentType());
			fileEntity.setStatus(StatusConst.ENABLE);
			fileEntity.setSuffixName(FileUploadUtils.getSuffixName(file.getOriginalFilename()));
			fileEntity.setTenantId(UserProfileUtils.getTenantId());
			
			// 文件名称
			String fileName = UUID.randomUUID() + "." + fileEntity.getSuffixName();
			
			String dateF = DateUtils.getDate("yyyyMMdd");
			
			// 文件地址
			String fileUrl = dateF + "/" + fileName;

			fileEntity.setFileUrl(fileUrl);
			
			// 文件上传地址
			FileUploadUtils.fileUp(file, getConfigPath(dateF), fileName);
			
//			FTPClientUtils.uploadFile(ftpClientVO, dateF, fileName, file.getInputStream());
			
			result.put("file", fileEntity);
			
			// 保存文件
			this.fileUploadRepository.save(fileEntity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("文件上传失败, 错误信息:" + e.getMessage());
		}

		return result;
	}
	
	/**
	 * 获取上传地址.
	 * 
	 * @param path
	 * @return
	 */
	private String getConfigPath(String path) {
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

		return filePath;
	}
}
