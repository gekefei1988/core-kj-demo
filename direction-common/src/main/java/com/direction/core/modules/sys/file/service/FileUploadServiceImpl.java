package com.direction.core.modules.sys.file.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.direction.common.utils.file.FTPClientUtils;
import com.direction.common.utils.file.FTPClientVO;
import com.direction.common.utils.file.FileUploadUtils;
import com.direction.common.utils.lang.DateUtils;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.file.IFileUpload;
import com.direction.core.inf.sys.file.IFileUploadService;
import com.direction.core.modules.sys.file.entity.FileUpload;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class FileUploadServiceImpl implements IFileUploadService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private FileUploadSettingService fileUploadSettingService;
	
	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#upload(org.springframework.web.multipart.MultipartFile, java.lang.String)
	 */
	@Override
	public IFileUpload upload(MultipartFile file, String categoryNo) {
		return this.upload(file, categoryNo, "", "", "");
	}

	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#upload(org.springframework.web.multipart.MultipartFile, java.lang.String, java.lang.String)
	 */
	@Override
	public IFileUpload upload(MultipartFile file, String categoryNo, String busiId) {
		return this.upload(file, categoryNo, "", busiId, "");
	}

	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#upload(org.springframework.web.multipart.MultipartFile, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public IFileUpload upload(MultipartFile file, String categoryNo, String busiType, String busiId) {
		return this.upload(file, categoryNo, busiType, busiId, "");
	}
	
	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#upload(org.springframework.web.multipart.MultipartFile, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false)
	public IFileUpload upload(MultipartFile file, String categoryNo, String busiType, String busiId, String subBusiId) {
		
		FileUpload fileUpload = null;
		
		try {
			
			fileUpload = new FileUpload();
			
			fileUpload.setFileName(file.getOriginalFilename());
			fileUpload.setFileSize(file.getSize());
			fileUpload.setFileType(file.getContentType());
			fileUpload.setStatus(StatusConst.ENABLE);
			fileUpload.setSuffixName(FileUploadUtils.getSuffixName(file.getOriginalFilename()));
			
			fileUpload.setCategoryNo(categoryNo);
			fileUpload.setBusiType(busiType);
			fileUpload.setBusiId(busiId);
			fileUpload.setSubBusiId(subBusiId);
			fileUpload.setTenantId(UserProfileUtils.getTenantId());
			
			// 文件名称
			String fileName = UUID.randomUUID() + "." + fileUpload.getSuffixName();
			
			String dateF = DateUtils.getDate("yyyyMMdd");
			
			// 文件地址
			String fileUrl = dateF + "/" + fileName;
	
			fileUpload.setFileUrl(fileUrl);
			
			// 上传文件服务器类型
			int serverType = fileUploadSettingService.getFileUploadServerType();
			
			if (serverType == FileUpload.FILE_UPLOAD_SERVER_FTP) {
				
				FTPClientVO ftpClientVO = fileUploadSettingService.getFTPClient();
				FTPClientUtils.uploadFile(ftpClientVO, dateF, fileName, file.getInputStream());
			}
			else {
				
				// 文件上传地址
				FileUploadUtils.fileUp(file, fileUploadSettingService.getConfigPath(dateF), fileName);
			}
			
			// 保存记录
			fileUploadService.saveFile(fileUpload);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileUpload;
	}
	
	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#get(java.lang.String)
	 */
	@Override
	public FileUpload get(String fileId) {
		return fileUploadService.get(fileId);
	}
	
	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#delete(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(String fileId) {
		fileUploadService.deleteById(fileId);
	}

	@Override
	public List<IFileUpload> getFileList(String categoryNo) {
		return getFileList(categoryNo, "", "", "");
	}

	@Override
	public List<IFileUpload> getFileList(String categoryNo, String busiId) {
		return getFileList(categoryNo, "", busiId, "");
	}

	@Override
	public List<IFileUpload> getFileList(String categoryNo, String busiType, String busiId) {
		return getFileList(categoryNo, busiType, busiId, "");
	}

	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#getFileList(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<IFileUpload> getFileList(String categoryNo, String busiType, String busiId, String subBusiId) {
		
		// 获取租户id
		String tenantId = UserProfileUtils.getTenantId();
		
		FileUpload fileUpload = new FileUpload();
		
		fileUpload.setCategoryNo(categoryNo);
		fileUpload.setBusiType(busiType);
		fileUpload.setBusiId(subBusiId);
		fileUpload.setSubBusiId(subBusiId);
		fileUpload.setTenantId(tenantId);
		
		List<FileUpload> uploadList = fileUploadService.getList(fileUpload);
		List<IFileUpload> fileUploads = new ArrayList<IFileUpload>();
		
		fileUploads.addAll(uploadList);
		
		return fileUploads;
	}

	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#getFile(java.lang.String)
	 */
	@Override
	public IFileUpload getFile(String categoryNo) {
		return getFile(categoryNo, "", "", "");
	}

	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#getFile(java.lang.String, java.lang.String)
	 */
	@Override
	public IFileUpload getFile(String categoryNo, String busiId) {
		return getFile(categoryNo, "", busiId, "");
	}

	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#getFile(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public IFileUpload getFile(String categoryNo, String busiType, String busiId) {
		return getFile(categoryNo, busiType, busiId, "");
	}

	/* (non-Javadoc)
	 * @see com.direction.common.core.modules.sys.file.IFileUploadService#getFile(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public IFileUpload getFile(String categoryNo, String busiType, String busiId, String subBusiId) {
		
		// 获取租户id
		String tenantId = UserProfileUtils.getTenantId();
		
		FileUpload fileUpload = new FileUpload();
		
		fileUpload.setCategoryNo(categoryNo);
		fileUpload.setBusiType(busiType);
		fileUpload.setBusiId(subBusiId);
		fileUpload.setSubBusiId(subBusiId);
		fileUpload.setTenantId(tenantId);
		
		FileUpload upload = fileUploadService.getFileUpload(fileUpload);
		
		return upload;
	}
}