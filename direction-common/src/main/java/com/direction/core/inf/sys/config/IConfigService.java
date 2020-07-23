package com.direction.core.inf.sys.config;

// ~ File Information
// ====================================================================================================================

public interface IConfigService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据键名获取配置信息.
	 * 
	 * @param configNo
	 * @return
	 */
	IConfig getConfig(String configNo);
	
	/**
	 * 获取系统配置信息.
	 * 
	 * @param configNo
	 * @return
	 */
	String getConfigValue(String configNo);
}
