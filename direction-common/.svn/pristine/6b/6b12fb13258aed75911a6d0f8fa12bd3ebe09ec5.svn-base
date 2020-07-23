package com.direction.spring.scheduling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

// ~ File Information
// ====================================================================================================================

/**
 * 定时任务配置类.
 * 
 * <pre>
 * 	定时任务配置类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Component
public class SchedulingConfig {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	@Bean
	public TaskScheduler taskScheduler() {

		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		
		// 定时任务执行线程池核心线程数
		taskScheduler.setPoolSize(10);
		taskScheduler.setRemoveOnCancelPolicy(true);
		taskScheduler.setThreadNamePrefix("TaskSchedulerThreadPool-");
		return taskScheduler;
	}
}
