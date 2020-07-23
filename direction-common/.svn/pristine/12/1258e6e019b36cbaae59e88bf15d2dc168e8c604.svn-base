package com.direction.security.shiro.token;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.direction.core.modules.sys.login.ClientConst;
import com.direction.core.modules.sys.user.entity.WeChatSession;

// ~ File Information
// ====================================================================================================================

public class ShiroUsernamePasswordToken extends UsernamePasswordToken {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 1375504929931176939L;

	// ~ Fields
	// ==================================================================================================================

	// 用来传递其他参数
	private Map<String, Object> params = new HashMap<>();

	// 微信session
	private WeChatSession weChatSession;

	// 登陆平台
	private String client;

	// 登陆用户 超级管理员 or 普通用户 普通管理员
	private Boolean adminLogin;

	// ~ Constructors
	// ==================================================================================================================

	public ShiroUsernamePasswordToken() {

	}

	public ShiroUsernamePasswordToken(final String username, final String password) {

		this.setUsername(username);
		this.setPassword(password.toCharArray());
	}

	public ShiroUsernamePasswordToken(final String username, final String password, final String client) {

		this.setUsername(username);
		this.setPassword(password.toCharArray());
		this.setClient(client);
	}

	public ShiroUsernamePasswordToken(final String username, final String password, final String client, final Boolean adminLogin) {

		this.setUsername(username);
		this.setPassword(password.toCharArray());
		this.setClient(client);
		this.setAdminLogin(adminLogin);
	}

	public ShiroUsernamePasswordToken(final WeChatSession weChatSession) {

		String openid = weChatSession == null ? "" : weChatSession.getOpenid();

		this.setUsername(openid);
		this.setPassword(openid.toCharArray());

		this.setWeChatSession(weChatSession);
		this.setClient(ClientConst.CLIENT_WE_CHAT);
	}

	// ~ Methods
	// ==================================================================================================================

	/**
	 * @return 返回 params。
	 */
	public Map<String, Object> getParams() {

		return params;
	}

	/**
	 * @param params
	 *          要设置的 params。
	 */
	public void setParams(Map<String, Object> params) {

		this.params = params;
	}

	public String getClient() {

		return client;
	}

	public void setClient(String client) {

		this.client = client;
	}

	public Boolean isAdminLogin() {
		
		return adminLogin == null ? false : adminLogin;
	}

	public void setAdminLogin(Boolean adminLogin) {

		this.adminLogin = adminLogin;
	}

	public WeChatSession getWeChatSession() {

		return weChatSession;
	}

	public void setWeChatSession(WeChatSession weChatSession) {

		this.weChatSession = weChatSession;
	}
}