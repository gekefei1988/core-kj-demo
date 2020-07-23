package com.direction.core.inf.sys.log;

import java.util.LinkedHashMap;
import java.util.Map;

// ~ File Information
// ====================================================================================================================

public interface IErrorLog {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	//~ Classs
	// ===================================================================================================================
	
	public static class ErrorLogStatus {
		
		public static final String SYSTEM = "e0001";
		
		public static final String TOKEN = "e0002";
		
		public static final String BUSINESS = "e0003";
		
		public static final String RUNTIME = "e0009";
		
		public static Map<String, String> getErrorLogStatus() {
			Map<String, String> maps = new LinkedHashMap<String, String>();
			maps.put(SYSTEM, "系统异常");
			maps.put(TOKEN, "Token异常");
			maps.put(BUSINESS, "业务异常");
			maps.put(RUNTIME, "运行异常");
			
			return maps;
		}
	}
}
