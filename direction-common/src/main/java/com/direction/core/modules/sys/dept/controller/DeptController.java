package com.direction.core.modules.sys.dept.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.modules.sys.dept.entity.Dept;
import com.direction.core.modules.sys.dept.service.DeptService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

@RestController
@RequestMapping("/core/sys/dept")
public class DeptController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private DeptService deptService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 分页查询.
	 * 
	 * @param dept
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<Dept> list(Dept dept, VuePage<Dept> page) {
		
		return this.deptService.findPage(dept, page);
	}

	/**
	 * 根据部门ID获取部门.
	 * 
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/get")
	public Dept get(String deptId) {
		return this.deptService.get(deptId);
	}
	
	/**
	 * 保存部门信息.
	 * 
	 * @param dept
	 * @return
	 */
	@RequestMapping("/save")
	public ResultJson save(Dept dept) {
		this.deptService.save(dept);
		return success();
	}
	
	/**
	 * 删除部门信息.
	 * 
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultJson delete(String deptId) {
		
		this.deptService.delete(deptId);
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
	public ResultJson validateOnlyOne(String propName, String propValue, String deptId) {
		return this.deptService.validateOnlyOne(propName, propValue, deptId);
	}
}
