package com.direction.core.inf;

import java.util.HashMap;
import java.util.Map;

// ~ File Information
// ====================================================================================================================

/**
 * 共用状态类.
 * 
 * <pre>
 * 	共用状态类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class StatusConst {

	// ~ Static Fields
	// ==================================================================================================================
	
	public static final String ENABLE = "0";
	public static final String DISABLED = "1";

	// ~ Fields
	// ==================================================================================================================
	
	// 状态map
	private static Map<String, String> statusMap;
	
	// 状态颜色map
	private static Map<String, String> statusColorMap;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 返回状态map集合.
	 * 
	 * @return
	 */
	public static Map<String, String> getStatusMap() {
		
		if (statusMap == null) {
			statusMap = new HashMap<String, String>();
			statusMap.put(ENABLE, "启用");
			statusMap.put(DISABLED, "停用");
		}
		return statusMap;
	}
	
	/**
	 * 返回状态颜色map集合.
	 * 
	 * @return
	 */
	public static Map<String, String> getStatusColorMap() {
		
		if (statusColorMap == null) {
			statusColorMap = new HashMap<String, String>();
			statusColorMap.put(ENABLE, "success");
			statusColorMap.put(DISABLED, "error");
		}
		return statusColorMap;
	}
}
