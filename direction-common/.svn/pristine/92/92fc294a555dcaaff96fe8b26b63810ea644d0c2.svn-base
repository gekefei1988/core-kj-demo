package com.direction.common.utils.cache;

import org.apache.commons.lang3.StringUtils;

import net.sf.ehcache.Cache;

// ~ File Information
// ====================================================================================================================

public class CacheSysManageUtils {

	// ~ Static Fields
	// ==================================================================================================================

	// 系统缓存
	private static final String DEFAULT_CACHE = CacheCategoryManage.SYSTEME_CACHE;
	
	// 默认模块
	public static final String DEFAULT_MODULE = "DEFAULT_MODULE";

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 保存系统缓存.
	 * 
	 * @param modules
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 */
	public static void put(String modules, String key, Object value, int cacheSeconds) {
		
		if (StringUtils.isBlank(modules)) {
			modules = DEFAULT_MODULE;
		}
		CacheManagerUtils.put(DEFAULT_CACHE, modules + "_" + key, value, cacheSeconds);
	}
	
	/**
	 * 保存系统缓存.
	 * 
	 * @param modules
	 * @param key
	 * @param value
	 */
	public static void put(String modules, String key, Object value) {
		if (StringUtils.isBlank(modules)) {
			modules = DEFAULT_MODULE;
		}
		CacheManagerUtils.put(DEFAULT_CACHE, modules + "_" + key, value);
	}
	
	/**
	 * 获取系统缓存.
	 * 
	 * @param modules
	 * @param key
	 * @return
	 */
	public static Object get(String modules, String key) {
		
		if (StringUtils.isBlank(modules)) {
			modules = DEFAULT_MODULE;
		}
		
		return CacheManagerUtils.get(DEFAULT_CACHE, modules + "_" + key);
	}
	
	/**
	 * 移除缓存.
	 * 
	 * @param modules
	 * @param key
	 */
	public static void remove(String modules, String key) {
		
		if (StringUtils.isBlank(modules)) {
			modules = DEFAULT_MODULE;
		}

		CacheManagerUtils.remove(DEFAULT_CACHE, modules + "_" + key);
	}
	
	/**
	 * 移除多个系统缓存.
	 * 
	 * @param modules
	 * @param keys
	 */
	public static void remove(String modules, String ...keys) {
		
		if (StringUtils.isBlank(modules)) {
			modules = DEFAULT_MODULE;
		}
		
		if (keys != null && keys.length > 0) {
			for (String key : keys) {
				CacheManagerUtils.remove(DEFAULT_CACHE, modules + "_" + key);
			}
		}
	}
	
	/**
	 * 重置缓存.
	 * 
	 * @param modules
	 * @param key
	 * @param value
	 */
	public static void reset(String modules, String key, Object value) {
		remove(modules, key);
		put(modules, key, value);
	}
	
	/**
	 * 获取缓存.
	 * 
	 * @return
	 */
	public static Cache getCache() {
		return CacheManagerUtils.getCache(DEFAULT_CACHE);
	}
	
	/**
	 * 系统缓存分类.
	 * 
	 * <pre>
	 * 	系统缓存分类
	 * </pre>
	 * 
	 * @author liutao
	 * @since $Rev$
	 *
	 */
	public static class CacheSysModulesCategory {
		
		// 租户
		public static final String SYS_TENANT = "SYS_TENANT";
		
		// 字典
		public static final String SYS_DICT = "SYS_DICT";
		
		// 用户
		public static final String SYS_USER = "SYS_USER";
		
		// 系统配置
		public static final String SYS_CONFIG = "SYS_CONFIG";
		
		// 组织树
		public static final String SYS_ORG_TREE = "SYS_ORG_TREE";
		
		// 菜单
		public static final String SYS_MENU = "SYS_MENU";
		
		// 行政区划
		public static final String SYS_AREA = "SYS_AREA";
		
		// 部门
		public static final String SYS_DEPT = "SYS_DEPT";
		
		// 企业
		public static final String SYS_COMPANY = "SYS_COMPANY";
		
		// 政府
		public static final String SYS_GOV = "SYS_GOV";
		
		// 图片验证码
		public static final String VERIFY_IMAGE = "VERIFY_IMAGE";
		
		// 手机验证码
		public static final String VERIFY_TELEPHONE = "VERIFY_TELEPHONE";
	}
}
