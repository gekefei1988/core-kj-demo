package com.direction.security.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.direction.common.exception.ServiceException;
import com.direction.common.utils.shiro.PermsDataUtils;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.WeightsConst;
import com.direction.core.inf.sys.role.IRole;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.inf.sys.user.IUserInfo.UserType;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.modules.sys.login.service.LoginService;
import com.direction.core.modules.sys.menu.service.MenuService;
import com.direction.core.modules.sys.role.service.RoleService;
import com.direction.security.shiro.token.ShiroUsernamePasswordToken;

// ~ File Information
// ====================================================================================================================

/**
 * 自定义权限验证.
 * 
 * <pre>
 * 自定义权限验证
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {

	// ~ Static Fields
	// ==================================================================================================================

	private static final Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);

	// ~ Fields
	// ==================================================================================================================
	@Autowired
	private LoginService loginService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	// TODO 增加在线用户
	// @Autowired
	// private UserOnlineService userOnlineService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 权限验证.
	 * 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		logger.info("权限验证中...");

		// 获取当前登录用户
		UserProfile userProfile = UserProfileUtils.getUserProfile();

		// 角色列表
		Set<String> roles = new HashSet<String>();

		// 功能列表
		Set<String> perms = new HashSet<String>();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 判断是否为超级管理员
		if (userProfile.isSuperAdmin()) {
			roles.addAll(userProfile.getAccess());
			perms = PermsDataUtils.getResults(menuService.findAllPerms());
		}
		// 管理员
		else if (StringUtils.equals(userProfile.getUserType(), UserType.MANAGE)) {
			// TODO 权限待定
			roles.addAll(userProfile.getAccess());
			perms = PermsDataUtils.getResults(menuService.findAllPerms());
		}
		// 普通员工
		else {
			roles.addAll(roleService.getRoleIdsByUser(userProfile.getUserId()));
			perms = PermsDataUtils.getResults(menuService.findPermsByUserId(userProfile.getUserId()));
		}

		// 角色加入AuthorizationInfo认证对象
		info.setRoles(roles);

		// 权限加入AuthorizationInfo认证对象
		info.setStringPermissions(perms);

		return info;
	}

	/**
	 * 登陆验证.
	 * 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

		logger.info("登陆验证中...");

		ShiroUsernamePasswordToken shiroToken = (ShiroUsernamePasswordToken) authcToken;

		String username = shiroToken.getUsername();
		String password = "";

		// 获取密码
		if (shiroToken.getPassword() != null) {
			password = new String(shiroToken.getPassword());
		}

		// 用户
		UserProfile userProfile = null;

		try {

			IUserInfo userInfo = loginService.login(shiroToken);

			// 获取sessionId, 把sessionId 作为临时token
			String sessionId = SecurityUtils.getSubject().getSession().getId().toString();

			// 获取角色Code 作为前端过滤权限
			Set<String> access = new HashSet<String>();

			// 超级管理员
//			if (userInfo.getWeights() == WeightsConst.WEIGHTS_SUPER_MANAGER) {
//				access = new HashSet<String>();
//				access.add(IRole.DEFAULT_SUPER_ROLE);
//			} else if (userInfo.getWeights() == WeightsConst.WEIGHTS_MANAGER) {
//				// TODO 权限待定
//				access = new HashSet<String>();
//				access.add(IRole.DEFAULT_SUPER_ROLE);
//			} else if (userInfo.getWeights() == WeightsConst.WEIGHTS_USER) {
//				access = roleService.getRoleIdsByUser(userInfo.getUserId());
//			}
			
			// 超级管理员
			if (userInfo.getWeights() == WeightsConst.WEIGHTS_SUPER_MANAGER) {
				
				access.add(IRole.DEFAULT_SUPER_ROLE);
			} else {
				
				Set<String> set = roleService.selectRoleKeys(userInfo.getUserId());
				
				if(set.contains(IRole.DEFAULT_GOV_ROLE)) {
					
					access.add(IRole.DEFAULT_GOV_ROLE);
				}
				if (userInfo.getWeights() == WeightsConst.WEIGHTS_MANAGER) {
					
					access.add(IRole.DEFAULT_SUPER_ROLE);
				}
				else {
				
					access.addAll(roleService.getRoleIdsByUser(userInfo.getUserId()));
				}
			}

			userProfile = new UserProfile(userInfo, new ArrayList<String>(access), sessionId);
		} catch (ServiceException e) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过{", e.getMessage() + "}");
			throw new AuthenticationException(e.getMessage());
		}

		return new SimpleAuthenticationInfo(userProfile, password, getName());
	}

	/**
	 * 清理缓存权限
	 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}
