package com.direction.core.task;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.direction.common.utils.lang.DateUtils;
import com.direction.core.modules.sys.tenant.service.TenantService;

// ~ File Information
// ====================================================================================================================

/**
 * 系统缓存数据初始化.
 * 
 * <pre>
 * 	系统缓存数据初始化
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Configuration
public class SystemScheduleInitData {

	// ~ Static Fields
	// ==================================================================================================================
	
	private static final Logger logger = LoggerFactory.getLogger(SystemScheduleInitData.class);

	// ~ Fields
	// ==================================================================================================================
	
//	@Autowired
//	private OrgTreeService orgTreeService;
	
	@Autowired
	private TenantService tenantService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 初始化系统缓存数据.
	 * 
	 */
	@PostConstruct
	public void initializationCacheData() {
		
		// 初始化租户信息
		logger.debug("初始化租户信息, 开始: " + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		tenantService.getTenantMaps();
		
		logger.debug("初始化租户信息, 结束: " + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		// 初始化机构树数据
		logger.debug("初始化机构树数据, 开始: " + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		
		logger.debug("初始化机构树数据, 结束: " + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
}
