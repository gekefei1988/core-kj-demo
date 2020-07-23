package com.direction.core.modules.sys.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.modules.sys.user.entity.SuperAdmin;
import com.direction.core.modules.sys.user.service.SuperAdminService;
import com.direction.core.modules.sys.user.service.UserService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 系统用户控制层.
 * 
 * <pre>
 * 系统用户控制层
 * </pre>
 * 
 * @author qiwei
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/super-admin")
public class SuperAdminController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private SuperAdminService superAdminService;
	
	@Autowired
	private UserService userService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<SuperAdmin> listPage(SuperAdmin user, VuePage<SuperAdmin> page) {

		return this.superAdminService.findPage(user, page);
	}
	
	/**
	 * 获取用户信息.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/get")
	public SuperAdmin get(String userId) {
		
		SuperAdmin user = null;
		
		if (StringUtils.isNotBlank(userId)) {
			user = this.superAdminService.get(userId);
		}
		
		if (user == null) {
			user = new SuperAdmin();
			user.setAllowPC(true);
			user.setStatus(IUserInfo.UserStatus.ENABLE);
		}
		
		return user;
	}

	/**
	 * 保存、修改.
	 */
	@RequestMapping("/save")
	public ResultJson save(SuperAdmin user) {

		superAdminService.save(user);

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
		return this.superAdminService.validateOnlyOne(propName, propValue, id);
	}
	
	/**
	 * 检核属性是否重复.
	 * 
	 * @param id
	 * @param propValue
	 * @return
	 */
	@RequestMapping(value = "/checkUserName")
	public ResultJson checkUserName(String id, String propValue) {
		return this.userService.checkUserName(id, propValue);
	}
}
