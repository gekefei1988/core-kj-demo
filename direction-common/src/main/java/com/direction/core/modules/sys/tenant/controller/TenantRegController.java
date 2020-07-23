package com.direction.core.modules.sys.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.tenant.entity.TenantReg;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantRegStauts;
import com.direction.core.modules.sys.tenant.entity.TenantReg.TenantType;
import com.direction.core.modules.sys.tenant.service.TenantRegService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@RestController
@RequestMapping("/core/sys/tenant-reg")
public class TenantRegController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private TenantRegService tenantRegService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param entity
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<TenantReg> list(TenantReg entity, VuePage<TenantReg> page) {
		return this.tenantRegService.findPage(entity, page);
	}
	
	/**
	 * 保存信息, 并同步修改账号信息.
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResultJson save(TenantReg entity) {
		
		// 保存注册信息
		this.tenantRegService.saveOrUpdate(entity);
		
		return success();
	}
	
	/**
	 * 提交审核.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/submit", method = {RequestMethod.OPTIONS, RequestMethod.POST})
	public ResultJson submit(String tenantId) {
		this.tenantRegService.submit(tenantId);
		return success();
	}
	
	/**
	 * 撤回审核.
	 * 
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/quit", method = {RequestMethod.OPTIONS, RequestMethod.POST})
	public ResultJson quit(String tenantId) {
		this.tenantRegService.quit(tenantId);
		return success();
	}

	/**
	 * 根据id获取数据,打开编辑页面.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get")
	public TenantReg get(String id) {
		
		TenantReg entity = this.tenantRegService.get(id);
		
		if (entity == null) {
			entity = new TenantReg();
			entity.setTenantType(TenantType.DEFAULT);
			entity.setStatus(TenantRegStauts.DEFAULT);
		}
		
		return entity;
	}
	
	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String id) {
		return this.tenantRegService.validateOnlyOne(propName, propValue, id);
	}
	
	/**
	 * 删除.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		this.tenantRegService.delete(id);
		
		return success();
	}
}
