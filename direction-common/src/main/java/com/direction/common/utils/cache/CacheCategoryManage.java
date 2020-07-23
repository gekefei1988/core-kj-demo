package com.direction.common.utils.cache;

import java.util.LinkedHashMap;
import java.util.Map;

// ~ File Information
// ====================================================================================================================

public class CacheCategoryManage {

	// ~ Static Fields
	// ==================================================================================================================

	// 系统缓存
	public static final String SYSTEME_CACHE = "systemCache";

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
		
	public static Map<String, String> getCacheNames() {
		
		Map<String, String> results = new LinkedHashMap<String, String>();
		results.put(SYSTEME_CACHE, "系统缓存");
		
		return results;
	}
}
