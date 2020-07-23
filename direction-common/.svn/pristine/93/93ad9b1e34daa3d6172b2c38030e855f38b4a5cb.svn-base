package com.direction.security.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.user.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

// ~ File Information
// ====================================================================================================================

/**
 * 权限过滤是否登陆权限.
 * 
 * <pre>
 * 权限过滤是否登陆权限
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class SimpleFormAuthenticationFilter extends FormAuthenticationFilter {

	// ~ Static Fields
	// ==================================================================================================================

	private static final Logger logger = LoggerFactory.getLogger(SimpleFormAuthenticationFilter.class);

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 用来判断是否session已过期或者丢失.
	 * 
	 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onAccessDenied(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		UserProfile userProfile = UserProfileUtils.getUserProfile();

		if (userProfile == null) {

			logger.debug("未登录, 或者权限丢失...");

			ResultJson result = ResultJson.fail("登陆已失效, 请重新登陆!");
			result.setErrorCode("unauthc");

			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			HttpServletRequest  httpServletRequest = (HttpServletRequest ) request;
			
			httpServletResponse.setHeader("Access-Control-Allow-Origin",  httpServletRequest.getHeader("Origin"));
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpServletResponse.setContentType("application/json; charset=utf-8");
			httpServletResponse.setCharacterEncoding("UTF-8");

			httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
			return false;
		}

		return true;
	}

	/**
	 * 是否允许访问.
	 * 
	 * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#isAccessAllowed(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		
		boolean isAllowed = super.isAccessAllowed(request, response, mappedValue);
		
		if (!isAllowed) {

			String method = WebUtils.toHttp(request).getMethod();
			if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
				return true;
			}
		}
		
		return isAllowed;
	}
}
