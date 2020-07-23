package com.direction.security.shiro.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// ~ File Information
// ====================================================================================================================

@Component
public class AccessControlInterceptor implements HandlerInterceptor {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 允许跨域,不能放在postHandle内
		response.setHeader("Access-Control-Allow-Origin", "*");

		if (request.getMethod().equals("OPTIONS")) {
			response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
			// response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization, Authorization");
			response.addHeader("Access-Control-Allow-Headers", "*");
		}

		return true;
	}
}
