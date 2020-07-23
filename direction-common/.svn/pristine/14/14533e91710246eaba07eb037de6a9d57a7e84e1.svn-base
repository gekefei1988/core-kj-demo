package com.direction.common.result;

import java.util.LinkedHashMap;

// ~ File Information
// ====================================================================================================================

/**
 * json 返回结果.
 * 
 * <pre>
 * 	json 返回结果
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class ResultJson {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	private boolean success = true;

	private String errorCode = "-1";

	private String msg = "";
	
	private Object body;
	
	private LinkedHashMap<String, Object> bodys = new LinkedHashMap<String, Object>();

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 返回成功消息.
	 * 
	 * @return
	 */
	public static ResultJson success() {

		ResultJson resultJson = new ResultJson();
		resultJson.setSuccess(true);
		resultJson.setMsg("操作成功");
		resultJson.setErrorCode("0");

		return resultJson;
	}

	/**
	 * 返回成功消息.
	 * 
	 * @param message
	 * @return
	 */
	public static ResultJson success(String message) {

		ResultJson resultJson = ResultJson.success();
		resultJson.setMsg(message);

		return resultJson;
	}
	
	/**
	 * 返回成功消息.
	 * 
	 * @param object
	 * @return
	 */
	public static ResultJson success(Object object) {
		
		ResultJson resultJson = ResultJson.success();
		resultJson.setBody(object);
		
		return resultJson;
	}
	
	/**
	 * 返回失败信息.
	 * 
	 * @return
	 */
	public static ResultJson fail() {

		ResultJson resultJson = new ResultJson();
		resultJson.setSuccess(false);
		resultJson.setErrorCode("0");
		resultJson.setMsg("操作失败");

		return resultJson;
	}

	/**
	 * 返回错误信息.
	 * 
	 * @param message
	 * @return
	 */
	public static ResultJson fail(String message) {

		ResultJson resultJson = ResultJson.fail();
		resultJson.setMsg(message);

		return resultJson;
	}

	/**
	 * 错误信息和编号.
	 * 
	 * @param code
	 * @param message
	 * @param body
	 * @return
	 */
	public static ResultJson fail(String code, String message, LinkedHashMap<String, Object> body) {

		ResultJson resultJson = ResultJson.fail();
		resultJson.setMsg(message);
		resultJson.setErrorCode(code);
		resultJson.setBody(body);

		return resultJson;
	}

	/**
	 * 返回错误信息.
	 * 
	 * @param body
	 * @return
	 */
	public static ResultJson fail(LinkedHashMap<String, Object> body) {

		ResultJson resultJson = ResultJson.fail();
		resultJson.setBody(body);

		return resultJson;
	}

	/**
	 * 返回错误信息.
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static ResultJson fail(String code, String message) {

		ResultJson resultJson = ResultJson.fail();
		resultJson.setMsg(message);
		resultJson.setErrorCode(code);

		return resultJson;
	}

	public boolean isSuccess() {

		return success;
	}

	public void setSuccess(boolean success) {

		this.success = success;
	}

	public String getErrorCode() {

		return errorCode;
	}

	public void setErrorCode(String errorCode) {

		this.errorCode = errorCode;
	}

	public String getMsg() {

		return msg;
	}

	public void setMsg(String msg) {

		this.msg = msg;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
	public void put(String key, Object value) {
		
		Object bodyObject = this.getBody();
		
		if (bodyObject != null && !(bodyObject instanceof LinkedHashMap)) {
			bodys.put("object", bodyObject);
		}
		
		bodys.put(key, value);
		this.setBody(bodys);
	}
}