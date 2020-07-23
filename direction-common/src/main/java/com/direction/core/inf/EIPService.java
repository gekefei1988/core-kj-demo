package com.direction.core.inf;

import com.direction.core.inf.sys.company.ICompanyService;
import com.direction.core.inf.sys.config.IConfigService;
import com.direction.core.inf.sys.data_perm.IDataPermService;
import com.direction.core.inf.sys.dict.IDictService;
import com.direction.core.inf.sys.file.IFileUploadService;
import com.direction.core.inf.sys.log.IErrorLogService;
import com.direction.core.inf.sys.log.IOperLogService;
import com.direction.core.inf.sys.org.IOrgTreeService;
import com.direction.core.inf.sys.role.IRoleService;
import com.direction.core.inf.sys.tenant.ITenantService;
import com.direction.core.inf.sys.user.IUserService;
import com.direction.spring.spring_ctx.SpringContextHolder;

// ~ File Information
// ====================================================================================================================

public class EIPService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 字典接口.
	 * 
	 * @return
	 */
	public static IDictService getDictService() {

		return SpringContextHolder.getBean(IDictService.class);
	}

	/**
	 * 获取系统参数设置接口.
	 * 
	 * @return
	 */
	public static IConfigService getConfigService() {

		return SpringContextHolder.getBean(IConfigService.class);
	}

	/**
	 * 获取企业信息接口.
	 * 
	 * @return
	 */
	@Deprecated
	public static ICompanyService getICompanyService() {

		return SpringContextHolder.getBean(ICompanyService.class);
	}
	
	/**
	 * 组织机构.
	 * 
	 * @return
	 */
	public static IOrgTreeService getOrgTreeService() {

		return SpringContextHolder.getBean(IOrgTreeService.class);
	}
	
	/**
	 * 角色相关接口.
	 * 
	 * @return
	 */
	public static IRoleService getRoleService() {
		return SpringContextHolder.getBean(IRoleService.class);
	}

	/**
	 * 获取用戶接口.
	 * 
	 * @return
	 */
	public static IUserService getUserService() {

		return SpringContextHolder.getBean(IUserService.class);
	}
	
	/**
	 * 系统错误日志.
	 * 
	 * @return
	 */
	public static IErrorLogService getErrorLogService() {
		return SpringContextHolder.getBean(IErrorLogService.class);
	}
	
	/**
	 * 获取操作日志.
	 * 
	 * @return
	 */
	public static IOperLogService getOperLogService() {
		return SpringContextHolder.getBean(IOperLogService.class);
	}
	
	/**
	 * 获取租户接口.
	 * 
	 * @return
	 */
	public static ITenantService getTenantService() {
		return SpringContextHolder.getBean(ITenantService.class); 
	}
	
	/**
	 * 数据权限接口.
	 * 
	 * @return
	 */
	public static IDataPermService getDataPermService() {
		return SpringContextHolder.getBean(IDataPermService.class);
	}
	
	/**
	 * 获取文件上传接口.
	 * 
	 * @return
	 */
	public static IFileUploadService getFileUploadService() {
		return SpringContextHolder.getBean(IFileUploadService.class); 
	}
}