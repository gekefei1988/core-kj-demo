package com.direction.spring.scheduling.registrar;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

// ~ File Information
// ====================================================================================================================

@Component
public class CronTaskRegistrar implements DisposableBean {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// 任务池
	private final Map<Runnable, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>(20);

	@Autowired
	private TaskScheduler taskScheduler;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 新增定时任务
	 * 
	 * @param task
	 * @param cronExpression
	 */
	public void addCronTask(Runnable task, String cronExpression) {
		
		addCronTask(new CronTask(task, cronExpression));
	}

	/**
	 * 新增定时任务.
	 * 
	 * @param cronTask
	 */
	public void addCronTask(CronTask cronTask) {
		
		if (cronTask != null) {
			Runnable task = cronTask.getRunnable();
			if (this.scheduledTasks.containsKey(task)) {
				removeCronTask(task);
			}

			this.scheduledTasks.put(task, getScheduledFuture(cronTask));
		}
	}
	
	/**
	 * 根据key值移除定时任务.
	 * 
	 * @param key
	 */
	public void removeCronTask(Object key) {
		
		for (Runnable runnable : this.scheduledTasks.keySet()) {
			if (runnable.equals(key)) {
				this.removeCronTask(runnable);
				return;
			}
		}
	}
	
	/**
	 * 判断是否存在该定时任务.
	 * 
	 * @param key
	 * @return
	 */
	public boolean isExistsCronTask(Object key) {
		for (Runnable runnable : this.scheduledTasks.keySet()) {
			if (runnable.equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 移除定时任务.
	 * 
	 * @param task
	 */
	public void removeCronTask(Runnable task) {

		ScheduledFuture<?> scheduledTask = this.scheduledTasks.remove(task);
		if (scheduledTask != null) {
			scheduledTask.cancel(true);
		}
	}

	public ScheduledFuture<?> getScheduledFuture(CronTask cronTask) {

		ScheduledFuture<?> future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
		return future;
	}

	/**
	 * 销毁定时任务.
	 * 
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {

		for (ScheduledFuture<?> task : this.scheduledTasks.values()) {
			task.cancel(true);
		}

		this.scheduledTasks.clear();
	}
}
