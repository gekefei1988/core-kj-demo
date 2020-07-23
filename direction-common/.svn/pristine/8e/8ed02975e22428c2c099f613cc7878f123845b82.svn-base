package com.direction.security.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.UserProfileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

// ~ File Information
// ====================================================================================================================

/**
 * 自定义角色过滤.
 * 
 * <pre>
 * 自定义角色过滤
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class SimpleAuthorizationFilter extends AuthorizationFilter {

	private static final Logger logger = LoggerFactory.getLogger(SimpleAuthorizationFilter.class);

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 是否权限允许.
	 * 
	 * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
	    throws Exception {

		Subject subject = getSubject(request, response);
		
		boolean isAllowed = false;
		
		String[] rolesArray = (String[]) mappedValue;

		// 没有角色限制,返回验证失败
		if (rolesArray == null || rolesArray.length == 0) {
			isAllowed = true;
		}
		else {
			// 若当前用户是rolesArray中的任何一个，则有权限访问
			for (int i = 0; i < rolesArray.length; i++) {
				
				if (subject.hasRole(rolesArray[i])) {
					logger.info("用户权限验证成功");
					isAllowed = true;
					break;
				}
			}
		}
		
		// 前后端分离, 每次ajax发送请求时会发送一个OPTIONS请求, 如果是OPTIONS请求就先自动略过
		if (!isAllowed && subject.getPrincipal() == null) {
				
			// 判断options
			String method = WebUtils.toHttp(request).getMethod();
			if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
				return true;
			}
		}
		
		return isAllowed;
	}

	/**
	 * 授权全控重写, 需要判断ajax.
	 * 
	 * @see org.apache.shiro.web.filter.authz.AuthorizationFilter#onAccessDenied(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String requestedWith = httpServletRequest.getHeader("X-Requested-With");
		
		if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {

			// 涉及到跨域问题
			httpServletResponse.setHeader("Access-Control-Allow-Origin",  httpServletRequest.getHeader("Origin"));
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpServletResponse.setContentType("application/json; charset=utf-8");
			httpServletResponse.setCharacterEncoding("UTF-8");
			
			ResultJson result = null;
			
			if (UserProfileUtils.getUserProfile() == null) {
				result = ResultJson.fail("登陆已失效, 请重新登陆!");
				result.setErrorCode("unauthc");
			}
			else {
				result = ResultJson.fail("您没有操作权限!");
				result.setErrorCode("302");
			}
			httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
			return false;
		} else {
			return super.onAccessDenied(request, response);
		}
	}
}
