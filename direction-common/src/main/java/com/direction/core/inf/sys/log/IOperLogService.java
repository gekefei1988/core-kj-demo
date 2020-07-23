package com.direction.core.inf.sys.log;

// ~ File Information
// ====================================================================================================================

public interface IOperLogService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 保存操作日志.
	 * 
	 * @param obj
	 */
	void saveOperLog(Object obj);
	
	/**
	 * 保存操作日志.
	 * 
	 * @param title
	 * @param operType
	 * @param params
	 */
	void saveOperLog(String title, String operType, Object params);
	
	/**
	 * 保存操作日志.
	 * 
	 * @param title
	 * @param operType
	 * @param method
	 * @param operResult
	 * @param params
	 */
	void saveOperLog(String title, String operType, String method, String operResult, Object params);
}
