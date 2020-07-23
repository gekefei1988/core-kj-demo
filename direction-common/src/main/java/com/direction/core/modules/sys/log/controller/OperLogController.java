package com.direction.core.modules.sys.log.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.sys.log.IOperLog;
import com.direction.core.modules.sys.log.entity.OperLog;
import com.direction.core.modules.sys.log.service.OperLogService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@RestController
@RequestMapping("/core/sys/log/oper")
public class OperLogController extends BaseController {

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
	 * 分页查询.
	 * 
	 * @param oper
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<OperLog> list(OperLog oper, Date startDate, Date endDate, VuePage<OperLog> page) {
		return this.operLogService.findPage(oper, startDate, endDate, page);
	}
	
	/**
	 * 获取日志.
	 */
	@RequestMapping(value = "/get")
	public OperLog get(String id) {
		
		OperLog log = this.operLogService.get(id);
		
		if (log == null) {
			log = new OperLog();
		}
		
		return log;
	}
	
	/**
	 * 获取操作日志类型.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getOperTypeMap")
	public Map<String, String> getOperTypeMap() {
		return IOperLog.OperLogType.getOperTypeMap();
	}

	/**
	 * 删除日志信息.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		
		this.operLogService.delete(id);
		return success();
	}
}
