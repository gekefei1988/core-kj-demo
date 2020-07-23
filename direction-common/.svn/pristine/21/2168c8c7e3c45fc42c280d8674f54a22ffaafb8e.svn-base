package com.direction.core.modules.sys.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.core.inf.sys.log.IOperLogService;


// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class OperLogServiceImpl implements IOperLogService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private OperLogService operLogService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 保存操作日志.
	 * 
	 * @see com.direction.core.inf.sys.log.IOperLogService#saveOperLog(java.lang.Object)
	 */
	@Async
	@Transactional(readOnly = false)
	public void saveOperLog(Object obj) {
		operLogService.saveOperLog(obj);
	}

	/**
	 * 保存操作日志.
	 * 
	 * @see com.direction.core.inf.sys.log.IOperLogService#saveOperLog(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Async
	@Transactional(readOnly = false)
	public void saveOperLog(String title, String operType, Object params) {
		operLogService.saveOperLog(title, operType, params);
	}

	/**
	 * 保存操作日志.
	 * 
	 * @see com.direction.core.inf.sys.log.IOperLogService#saveOperLog(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Async
	@Transactional(readOnly = false)
	public void saveOperLog(String title, String operType, String method, String operResult, Object params) {
		this.operLogService.saveOperLog(title, operType, method, operResult, params);
	}
}
