package com.direction.core.modules.sys.tenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.tenant.entity.Tenant;
import com.direction.core.modules.sys.tenant.entity.Tenant.TenantUsedType;
import com.direction.core.modules.sys.tenant.service.TenantRegService;
import com.direction.core.modules.sys.tenant.service.TenantService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 *
 * 
 * <pre>
 * 租户管理控制层
 * </pre>
 * 
 * @author gekefei
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/tenant")
public class TenantController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private TenantService tenantService;

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
	public VuePage<Tenant> list(Tenant entity, VuePage<Tenant> page) {
		return this.tenantService.findPage(entity, page);
	}

	/**
	 * 保存信息.
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResultJson save(Tenant entity) {

		this.tenantService.save(entity);
		return success();
	}

	/**
	 * 获取租户列表.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTenants")
	public List<Tenant> getTenants() {

		return this.tenantService.getTenants();
	}

	/**
	 * 根据id获取数据,打开编辑页面.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get")
	public Tenant get(String id) {

		Tenant entity = this.tenantService.get(id);

		if (entity == null) {
			entity = new Tenant();
			entity.setUsedType(TenantUsedType.DEFAULT);
			entity.setStatus(StatusConst.ENABLE);
		}

		return entity;
	}

	/**
	 * 审核.
	 * 
	 * @param tenant
	 * @return
	 */
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public ResultJson review(Tenant tenant) {

		this.tenantService.review(tenant);
		return success();
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

		return this.tenantService.validateOnlyOne(propName, propValue, id);
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
