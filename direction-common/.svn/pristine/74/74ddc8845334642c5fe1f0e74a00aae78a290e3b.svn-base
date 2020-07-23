package com.direction.core.modules.sys.role.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.role.entity.Role;
import com.direction.core.modules.sys.role.service.RoleService;
import com.direction.core.modules.sys.role.service.UserRoleService;
import com.direction.core.modules.sys.role_menu.service.RoleMenuService;
import com.direction.security.shiro.filter.SimpleShiroFilterFactory;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 角色控制层.
 * 
 * <pre>
 * 角色控制层
 * </pre>
 * 
 * @author gekefei
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/role")
public class RoleController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleMenuService roleMenuService;

	// shiro 权限过滤工厂
	@Autowired
	private SimpleShiroFilterFactory simpleShiroFilterFactory;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param role
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<Role> list(Role role, VuePage<Role> page) {
		return this.roleService.findPage(role, page);
	}

	/**
	 * 保存或修改
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResultJson save(Role role) {
		this.roleService.save(role);
		return success();
	}

	/**
	 * 根据id获取角色信息.
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/get")
	public Role get(String roleId) {

		Role role = roleService.get(roleId);

		if (role == null) {
			role = new Role();
			role.setStatus(StatusConst.ENABLE);
			role.setDisplayOrder(0);
			role.setSys(false);
		}

		return role;
	}

	/**
	 * 删除角色.
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String roleId) {

		if (StringUtils.isBlank(roleId)) {
			return error("删除失败, 无法获取参数roleId");
		}

		this.roleService.delete(roleId);

		return success();
	}

	/**
	 * 给角色分配菜单.
	 * 
	 * @param menuIds
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/assignMenu")
	public ResultJson assignMenu(String menuIds, String id) {

		this.roleMenuService.assignMenu(menuIds, id);

		return success();
	}

	/**
	 * 给用户授权角色.
	 * 
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	@RequestMapping(value = "/assignUser")
	public ResultJson assignUser(String roleId, String userIds) {

		this.userRoleService.assignUser(roleId, userIds);
		return success();
	}
	
	/**
	 * 删除授权用户.
	 * 
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/deleteAssingUser")
	public ResultJson deleteAssingUser(String roleId, String userId) {
		
		this.userRoleService.delete(roleId, userId);
		return success();
	}

	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String roleId) {
		return this.roleService.validateOnlyOne(null, propName, propValue, "roleId", roleId);
	}

	/**
	 * 跟新权限.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateOperControl", method = RequestMethod.POST)
	public ResultJson updateOperControl() {

		this.simpleShiroFilterFactory.updatePermission(new HashMap<>());

		return success();
	}
}
