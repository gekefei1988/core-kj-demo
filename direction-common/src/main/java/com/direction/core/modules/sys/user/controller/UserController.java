package com.direction.core.modules.sys.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.inf.sys.user.IUserInfo.UserType;
import com.direction.core.modules.sys.role.service.UserRoleService;
import com.direction.core.modules.sys.tenant.entity.Tenant;
import com.direction.core.modules.sys.tenant.service.TenantService;
import com.direction.core.modules.sys.user.entity.User;
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
@RequestMapping("/core/sys/user")
public class UserController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private SuperAdminService superAdminService;
	
	@Autowired
	private TenantService tennatService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param user
	 * @param page
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<User> listPage(User user, VuePage<User> page) {
		
		if (StringUtils.isBlank(user.getTenantId()) && !UserProfileUtils.isSuperAdmin()) {
			user.setTenantId(UserProfileUtils.getTenantId());
		}

		return this.userService.findPage(user, page);
	}
	
	/**
	 * 获取用户信息.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/get")
	public User get(String userId) {
		
		User user = null;
		
		if (StringUtils.isNotBlank(userId)) {
			user = this.userService.get(userId);
		}
		
		if (user == null) {
			user = new User();
			user.setAllowPC(true);
			user.setStatus(IUserInfo.UserStatus.ENABLE);
		}
		
		return user;
	}
	
	/**
	 * 获取用户明细信息, 个人信息, 机构信息.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getUserDetails")
	public Map<String, Object> getUserDetails(String userId) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		IUserInfo user = null;
		Tenant tenant = null;
		
		// 是否用户ID为空
		if (StringUtils.isBlank(userId)) {
			
			UserProfile userProfile = UserProfileUtils.getUserProfile();
			
			// 判断是否为超级管理员
			if (userProfile.isSuperAdmin()) {
				user = superAdminService.get(userProfile.getUserId());
			}
			// 判断是否管理员
			else if (StringUtils.equals(userProfile.getUserType(), UserType.MANAGE)) {
				user = this.userService.get(userProfile.getUserId());
				if (user != null) {
					tenant = this.tennatService.get(user.getTenantId());
				}
			}
			// 普通用户
			else if (StringUtils.equals(userProfile.getUserType(), UserType.EMPLOYEE)) {
				user = this.userService.get(UserProfileUtils.getUserId());
			}
			// 临时租户
			else if (StringUtils.equals(userProfile.getUserType(), UserType.TENANT)) {
				user = this.userService.get(UserProfileUtils.getUserId());
				if (user != null) {
					tenant = this.tennatService.get(user.getTenantId());
				}
			}
		}
		else {
			user = this.userService.get(userId);
			if (user != null) {
				tenant = this.tennatService.get(user.getTenantId());
			}
		}
		
		result.put("user", user);
		result.put("tenant", tenant);
		
		return result;
	}
	

	/**
	 * 根据角色查询角色下用户.
	 * 
	 * @param roleId
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value = "/getUsersByRole")
	public List<User> getUsersByRole(String roleId, String tenantId) {
		
		if (StringUtils.isBlank(tenantId)) {
			tenantId = UserProfileUtils.isSuperAdmin() ? null : UserProfileUtils.getTenantId();
		}
		
		return this.userRoleService.getUsersByRoleTenant(roleId, tenantId);
	}

	/**
	 * 保存、修改.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	public ResultJson save(User user) {

		userService.save(user);

		return success();
	}
	
	/**
	 * 修改自己的用户信息.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/modifyMyUser")
	public ResultJson modifyMyUser(User user) {
		
		userService.modifyMyUser(user);
		return success();
	}
	
	/**
	 * 修改用户密码.
	 * 
	 * @param userId
	 * @param password
	 * @param newPassword
	 * @param sureNewPassword
	 * @return
	 */
	@RequestMapping("/modifyUserPWD")
	public ResultJson modifyUserPWD(String userId, String password, String newPassword, String sureNewPassword) {
		
		if (!UserProfileUtils.isSuperAdmin() && !StringUtils.equals(UserProfileUtils.getUserId(), userId)) {
			return error("当前用户，不能进行密码修改");
		}
		
		this.userService.modifyUserPWD(userId, password, newPassword, sureNewPassword);
		return success();
	}
	
	/**
	 * 重置密码.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/resetPWD")
	public ResultJson resetPWD(String userId) {
		this.userService.resetPWD(userId);
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
		return this.userService.validateOnlyOne(propName, propValue, id);
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

	/**
	 * 删除.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String userId) {

		userService.delete(userId);
		
		return success();
	}
}
