//package com.direction.common.cross;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//// ~ File Information
//// ====================================================================================================================
//
///**
// * 过滤器.
// * 
// * <pre>
// * 	域名允许过滤器.
// * </pre>
// * 
// * @author liutao
// * @since $Rev$
// *
// */
////@Component
//public class CorsFilter implements Filter {
//
//	// ~ Static Fields
//	// ==================================================================================================================
//
//	// ~ Fields
//	// ==================================================================================================================
//
//	// ~ Constructors
//	// ==================================================================================================================
//
//	// ~ Methods
//	// ==================================================================================================================
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//	    throws IOException, ServletException {
//
//		HttpServletResponse response = (HttpServletResponse) res;
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
//		response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization, auth-token, x-requested-with");
//		response.setHeader("Access-Control-Max-Age", "3600");
//
//		chain.doFilter(req, res);
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//
//	}
//
//	@Override
//	public void destroy() {
//
//	}
//}
