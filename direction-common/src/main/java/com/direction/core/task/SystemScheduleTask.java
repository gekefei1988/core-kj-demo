package com.direction.core.task;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.direction.common.utils.lang.DateUtils;
import com.direction.core.modules.sys.user.service.SuperAdminService;
import com.direction.core.modules.sys.user.service.UserService;

// ~ File Information
// ====================================================================================================================

/**
 * 系统定时任务类.
 * 
 * <pre>
 * 系统定时任务类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Configuration
public class SystemScheduleTask {

	// ~ Static Fields
	// ==================================================================================================================
	
	private static final Logger logger = LoggerFactory.getLogger(SystemScheduleTask.class);

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private SuperAdminService superAdminService;
	
	@Autowired
	private UserService userServie;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 用户次数超过限制, 定时任务.
	 * 
	 * <pre>
	 * 0 0 3 * * ?     每天3点执行
	 * 0 5 3 * * ?     每天3点5分执行
	 * 0 5 3 ? * *     每天3点5分执行，与上面作用相同
	 * 0 5/10 3 * * ?  每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行
	 * 0 10 3 ? * 1    每周星期天，3点10分 执行，注：1表示星期天    
	 * 0 10 3 ? * 1#3  每个月的第三个星期，星期天 执行，#号只能出现在星期的位置
	 * 
	 * 首次启动服务, 执行密码重置 @PostConstruct
	 * </pre>
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	@PostConstruct
	private void userPasswordResetTasks() {

		logger.debug("执行密码重置定时任务, 开始时间: " + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		this.superAdminService.resetLoginErrorCount(null);
		
		this.userServie.resetLoginErrorCount(null);
		
		logger.debug("执行密码重置定时任务, 结束时间: " + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
}
