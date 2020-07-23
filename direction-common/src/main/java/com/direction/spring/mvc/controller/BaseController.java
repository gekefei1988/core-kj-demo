package com.direction.spring.mvc.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.common.utils.lang.DateUtils;
import com.direction.core.inf.EIPService;
import com.direction.core.inf.sys.log.IErrorLog;

// ~ File Information
// ====================================================================================================================

@CrossOrigin(origins = {
  "*"
})
public abstract class BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	/**
	 * 日志对象.
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	// ~ Fields
	// ==================================================================================================================

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	@ModelAttribute
	public void initRequestAndResponse(HttpServletRequest request, HttpServletResponse response, Model model) {

		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * @return 返回 paramsMap.
	 */
	public Map<String, Object> getParameterMap() {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		Map<String, String[]> params = request.getParameterMap();

		if (params != null && params.size() > 0) {
			String[] propValues = null;
			for (String propKey : params.keySet()) {

				propValues = params.get(propKey);

				if (propValues.length == 1) {
					parameterMap.put(propKey, propValues[0]);
				} else {
					parameterMap.put(propKey, propValues);
				}
			}
		}

		return parameterMap;
	}

	/**
	 * 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) {

				setValue(DateUtils.parseDate(text));
			}
		});
	}

	/**
	 * Service抛出的异常.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AuthenticationException.class)
	public @ResponseBody ResultJson AuthenticationException(Exception e) {

		logger.error("token异常:", e);
		saveErrorLog(e, IErrorLog.ErrorLogStatus.TOKEN);
		ResultJson result = ResultJson.fail("token异常:" + e.getMessage());

		return result;
	}

	/**
	 * Service抛出的异常.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public @ResponseBody ResultJson serviceException(Exception e) {

		logger.error("业务异常:", e);
		// 业务异常不在保存, 直接返回错误信息即可
//		saveErrorLog(e, IErrorLog.ErrorLogStatus.BUSINESS);
		ResultJson result = ResultJson.fail(e.getMessage());

		return result;
	}

	/**
	 * Service抛出的异常-全局异常.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody ResultJson Exception(Exception e) {

		logger.error("系统异常:", e);
		saveErrorLog(e, IErrorLog.ErrorLogStatus.SYSTEM);
		ResultJson result = ResultJson.fail("系统异常" + e.getMessage());

		return result;
	}

	/**
	 * 拦截未知的运行时异常.
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResultJson notFount(RuntimeException e) {

		logger.error("运行时异常:", e);
		saveErrorLog(e, IErrorLog.ErrorLogStatus.RUNTIME);
		return ResultJson.fail("运行时异常:" + e.getMessage());
	}

	/**
	 * 保存错误日志.
	 * 
	 * @param e
	 * @param expType
	 */
	private void saveErrorLog(Exception e, String expType) {

		EIPService.getErrorLogService().saveError(e, expType);
	}

	public HttpServletRequest getRequest() {

		return request;
	}

	public void setRequest(HttpServletRequest request) {

		this.request = request;
	}

	public HttpServletResponse getResponse() {

		return response;
	}

	public void setResponse(HttpServletResponse response) {

		this.response = response;
	}

	public HttpSession getSession() {

		return session;
	}

	public void setSession(HttpSession session) {

		this.session = session;
	}

	/**
	 * 返回成功
	 */
	public ResultJson success() {

		return ResultJson.success();
	}

	/**
	 * 返回失败消息
	 */
	public ResultJson error() {

		return ResultJson.fail();
	}

	/**
	 * 返回成功消息
	 */
	public ResultJson success(String message) {

		return ResultJson.success(message);
	}

	/**
	 * 返回失败消息
	 */
	public ResultJson error(String message) {

		return ResultJson.fail(message);
	}

	/**
	 * 返回错误码消息
	 */
	public ResultJson error(String code, String message) {

		return ResultJson.fail(code, message);
	}
}
