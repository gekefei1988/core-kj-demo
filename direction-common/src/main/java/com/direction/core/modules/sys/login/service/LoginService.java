package com.direction.core.modules.sys.login.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.modules.sys.login.ClientConst;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.entity.WeChatSession;
import com.direction.core.modules.sys.user.entity.WeChatUser;
import com.direction.core.modules.sys.user.service.SuperAdminService;
import com.direction.core.modules.sys.user.service.UserService;
import com.direction.core.modules.sys.user.service.WeChatUserService;
import com.direction.security.shiro.token.ShiroUsernamePasswordToken;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class LoginService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private UserService userService;

	@Autowired
	private WeChatUserService weChatUserService;

	@Autowired
	private SuperAdminService superAdminService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据shiroToken信息进行登录.
	 * 
	 * @param shiroToken
	 * @return
	 */
	public IUserInfo login(ShiroUsernamePasswordToken shiroToken) {
		
		if (shiroToken == null) {
			throw new ServiceException("未获取到登录信息");
		}
		
		String userName = shiroToken.getUsername();
		String password = "";
		
		// 获取密码
		if (shiroToken.getPassword() != null) {
			password = new String(shiroToken.getPassword());
		}
		
		// 登录方式
		String client = shiroToken.getClient();
		
		IUserInfo userInfo = null;
		
		// 微信登录
		if (StringUtils.equalsIgnoreCase(client, ClientConst.CLIENT_WE_CHAT)) {
			userInfo = this.loginWeChat(shiroToken.getWeChatSession());
		}
		// 否则PC登录
		else {
			userInfo = this.login(userName, password, client, shiroToken.isAdminLogin()); 
		}
		
		return userInfo;
	}

	/**
	 * 用户登录验证.
	 * 
	 * @param userName
	 * @param password
	 * @param client
	 * @param isAdminLogin
	 */
	public IUserInfo login(String userName, String password, String client, boolean isAdminLogin) {
		
		if (isAdminLogin) {
			return this.loginAdmin(userName, password, client);
		}
		else {
			return this.login(userName, password, client);
		}
	}

	/**
	 * 微信登录.
	 * 
	 * @param openid
	 * @return
	 */
	public IUserInfo loginWeChat(WeChatSession session) {

		// 用户名或密码为空 错误
		if (session == null || StringUtils.isBlank(session.getOpenid())) {
			throw new ServiceException("openid为空");
		}

		String openid = session.getOpenid();
		WeChatUser wcUser = weChatUserService.getByOpenId(openid);

		// 如果微信绑定的用户为空，则创建一个系统用户（user）
		if (wcUser == null || StringUtils.isBlank(wcUser.getUserId())) {
			wcUser = weChatUserService.save(session);
		}

		String userId = wcUser.getUserId();
		User user = userService.get(userId);

		if (user == null) {
			throw new ServiceException("微信登录失败,未获取到绑定信息.");
		} else if (StringUtils.equals(user.getStatus(), IUserInfo.UserStatus.DISABLED)) {
			throw new ServiceException("该账号已停用, 不允许登陆");
		}

		return user;
	}
	
	/**
	 * 管理员登录.
	 * 
	 * @param userName
	 * @param password
	 * @param client
	 * @return
	 */
	public IUserInfo loginAdmin(String userName, String password, String client) {

		// 用户名或密码为空 错误
		if (StringUtils.isAnyBlank(userName, password)) {
			throw new ServiceException("用户名或密码为空");
		}

		// 查询用户信息
		IUserInfo user = superAdminService.getByUserName(userName);

		// 判断client是否为空
		if (StringUtils.isBlank(client)) {
			client = ClientConst.CLIENT_PC;
		}

		if (user == null) {
			throw new ServiceException("用户名或密码错误");
		} else if (user.getLoginErrorCount() >= 5) {
			throw new ServiceException("您登陆错误次数超过5次, 今天不允许再次登陆");
		} else if (!StringUtils.equals(password, user.getPassword())) {
			
			// 异步保存登录错误信息
			this.superAdminService.loginErrorCount(userName);
			throw new ServiceException("用户名或密码错误");
		} else if (StringUtils.equals(user.getStatus(), IUserInfo.UserStatus.DISABLED)) {
			throw new ServiceException("该账号已停用, 不允许登陆");
		}

		// 判断PC端
		if (StringUtils.equalsIgnoreCase(client, ClientConst.CLIENT_PC)) {
			if (!user.isAllowPC()) {
				throw new ServiceException("您的账号不允许在PC端进行登陆");
			}
		}
		// 判断移动端
		else if (StringUtils.equalsIgnoreCase(client, ClientConst.CLIENT_MOBILE)) {
			if (!user.isAllowMobile()) {
				throw new ServiceException("您的账号不允许在移动端进行登陆");
			}
		} else {
			throw new ServiceException("您登陆的客户端存在问题");
		}
		
		// update login date
		this.superAdminService.updateLoginDate(user.getUserId());

		return user;
	}

	/**
	 * 用户登陆.
	 * 
	 * @param userName
	 * @param password
	 * @param client
	 *          使用客户端 pc 和 mobile
	 * @return
	 */
	public IUserInfo login(String userName, String password, String client) {

		// 用户名或密码为空 错误
		if (StringUtils.isAnyBlank(userName, password)) {
			throw new ServiceException("用户名或密码为空");
		}

		// 查询用户信息
		IUserInfo user = this.userService.getUserByUserName(userName);

		// 判断client是否为空
		if (StringUtils.isBlank(client)) {
			client = ClientConst.CLIENT_PC;
		}

		if (user == null) {
			throw new ServiceException("用户名或密码错误");
		} else if (user.getLoginErrorCount() >= 5) {
			throw new ServiceException("您登陆错误次数超过5次, 今天不允许再次登陆");
		} else if (!StringUtils.equals(password, user.getPassword())) {

			// 异步保存错误次数
			this.userService.loginErrorCount(userName);

			throw new ServiceException("用户名或密码错误");
		} else if (StringUtils.equals(user.getStatus(), IUserInfo.UserStatus.DISABLED)) {
			throw new ServiceException("该账号已停用, 不允许登陆");
		}

		// 判断PC端
		if (StringUtils.equalsIgnoreCase(client, ClientConst.CLIENT_PC)) {
			if (!user.isAllowPC()) {
				throw new ServiceException("您的账号不允许在PC端进行登陆");
			}
		}
		// 判断移动端
		else if (StringUtils.equalsIgnoreCase(client, ClientConst.CLIENT_MOBILE)) {
			if (!user.isAllowMobile()) {
				throw new ServiceException("您的账号不允许在移动端进行登陆");
			}
		} else {
			throw new ServiceException("您登陆的客户端存在问题");
		}
		
		// update login date
		this.userService.updateLoginDate(user.getUserId());

		return user;
	}
}
