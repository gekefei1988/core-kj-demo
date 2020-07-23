package com.direction.core.modules.sys.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direction.core.inf.sys.config.IConfig;
import com.direction.core.inf.sys.config.IConfigService;

// ~ File Information
// ====================================================================================================================

@Service
public class ConfigServiceImpl implements IConfigService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private ConfigService configService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据编号获取参数信息.
	 * 
	 * @see com.direction.core.inf.sys.config.IConfigService#getConfig(java.lang.String)
	 */
	@Override
	public IConfig getConfig(String configNo) {
		
		return configService.getConfigMaps().get(configNo);
	}

	/**
	 * 获取系统配置值.
	 * 
	 * @see com.direction.core.inf.sys.config.IConfigService#getConfigValue(java.lang.String)
	 */
	@Override
	public String getConfigValue(String configNo) {
		
		IConfig config = this.getConfig(configNo);
		
		if (config != null) {
			return config.getConfigValue();
		}

		return null;
	}
}
