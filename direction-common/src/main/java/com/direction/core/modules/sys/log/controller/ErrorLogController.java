package com.direction.core.modules.sys.log.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.log.entity.ErrorLog;
import com.direction.core.modules.sys.log.service.ErrorLogService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@RestController
@RequestMapping("/core/sys/log/error")
public class ErrorLogController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private ErrorLogService errorLogService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param expType
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<ErrorLog> list(String expType, Date startDate, Date endDate, VuePage<ErrorLog> page) {
		return this.errorLogService.findPage(expType, startDate, endDate, page);
	}
	
	/**
	 * 获取数据.
	 */
	@RequestMapping(value = "/get")
	public ErrorLog get(String id) {
		
		ErrorLog log = this.errorLogService.get(id);
		
		if (log == null) {
			log = new ErrorLog();
		}
		
		return log;
	}

	/**
	 * 删除异常信息.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		
		this.errorLogService.delete(id);
		return success();
	}
}
