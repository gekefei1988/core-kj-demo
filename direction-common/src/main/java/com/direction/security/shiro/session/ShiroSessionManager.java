package com.direction.security.shiro.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

/**
 * Shiro session 管理器.
 * 
 * <pre>
 * 	shiro框架 自定义session获取方式 可自定义session获取规则。这里采用ajax请求头authToken携带sessionId的方式
 * </pre>
 * 
 * @author qiwei
 * @since $Rev$
 *
 */
public class ShiroSessionManager extends DefaultWebSessionManager {

	public static String AUTHORIZATION = "auth-token";

	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

	public ShiroSessionManager() {
		super();
	}

	/**
	 * 获取sessionId.
	 * 
	 * @see org.apache.shiro.web.session.mgt.DefaultWebSessionManager#getSessionId(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

		// 如果请求头中有 Authorization 则其值为sessionId
		String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
		
		// 如果sessionId非空
		if (StringUtils.isNotBlank(sessionId)) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return sessionId;
		} else {

			// 否则按默认规则从cookie取sessionId
			return super.getSessionId(request, response);
		}
	}
}
