package com.direction.core.modules.sys.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.core.inf.sys.log.IErrorLogService;

// ~ File Information
// ====================================================================================================================

/**
 * 错误日志保存接口实现.
 * 
 * <pre>
 * 	错误日志保存接口实现
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = false)
public class ErrorLogServiceImpl implements IErrorLogService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private ErrorLogService errorLogService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 保存错误日志.
	 * 
	 * @see com.direction.core.inf.sys.log.IErrorLogService#saveError(java.lang.Exception)
	 */
	@Override
	public void saveError(Exception e) {
		this.errorLogService.saveError(e);
	}

	/**
	 * 保存错误日志.
	 * 
	 * @see com.direction.core.inf.sys.log.IErrorLogService#saveError(java.lang.Exception, java.lang.String)
	 */
	@Override
	public void saveError(Exception e, String expType) {
		this.errorLogService.saveError(e, expType);
	}
}
