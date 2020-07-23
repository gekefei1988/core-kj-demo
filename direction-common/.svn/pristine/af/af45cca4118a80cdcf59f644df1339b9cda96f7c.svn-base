package com.direction.security.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.UserProfileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 通过字符串验证权限.
 * 
 * @author
 */
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {
	
	/**
	 * mappedValue 访问该url时需要的权限 subject.isPermitted 判断访问的用户是否拥有mappedValue权限 重写拦截器，只要符合配置的一个权限，即可通过
	 */
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
	    throws IOException {

		Subject subject = getSubject(request, response);
		String[] perms = (String[]) mappedValue;
		boolean isPermitted = false;
		
		if (perms != null && perms.length > 0) {
			for (String str : perms) {
				if (subject.isPermitted(str)) {
					isPermitted = true;
					break;
				}
			}
		}
		
		// 前后端分离, 每次ajax发送请求时会发送一个OPTIONS请求, 如果是OPTIONS请求就先自动略过
		if (!isPermitted && subject.getPrincipal() == null) {
				
			// 判断options
			String method = WebUtils.toHttp(request).getMethod();
			if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
				return true;
			}
		}

		return isPermitted;
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