package com.direction.core.inf.sys.log;

import java.util.LinkedHashMap;
import java.util.Map;

// ~ File Information
// ====================================================================================================================

public interface IOperLog {

	// ~ Static Fields
	// ==================================================================================================================
	
	// ~ Methods
	// ==================================================================================================================

	public static class OperLogType {
		
		public static final String SAVE = "0";
		
		public static final String UPDATE = "1";
		
		public static final String DELETE = "2";
		
		public static final String SEARCH = "9";
		
		public static Map<String, String> getOperTypeMap() {
			
			Map<String, String> result = new LinkedHashMap<String, String>();
			result.put(SAVE, "新增");
			result.put(UPDATE, "修改");
			result.put(DELETE, "删除");
//			result.put(SEARCH, "查询");
			
			return result;
		}
	}
}
