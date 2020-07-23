package com.direction.core.modules.sys.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.config.entity.Config;
import com.direction.core.modules.sys.config.service.ConfigService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@RestController
@RequestMapping("/core/sys/config")
public class ConfigController extends BaseController {

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
	 * 分页查询.
	 * 
	 * @param config
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<Config> getmenuypeList(Config config, VuePage<Config> page) {

		return this.configService.findPage(config, page);
	}
	
	/**
	 * 获取用户信息.
	 * 
	 * @param configId
	 * @return
	 */
	@RequestMapping("/get")
	public Config get(String configId) {
		
		Config config = this.configService.get(configId);
		
		if (config == null) {
			config = new Config();
			config.setStatus(StatusConst.ENABLE);
			config.setSystem(false);
		}
		
		return config;
	}

	/**
	 * 保存、修改.
	 */
	@RequestMapping("/save")
	public ResultJson save(Config config) {
		
		configService.save(config);

		return success();
	}
	
	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param configId
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String configId) {
		return this.configService.validateOnlyOne(null, propName, propValue, "configId", configId);
	}

	/**
	 * 删除.
	 * 
	 * @param configId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String configId) {

		configService.deleteById(configId);
		
		return success();
	}
}
