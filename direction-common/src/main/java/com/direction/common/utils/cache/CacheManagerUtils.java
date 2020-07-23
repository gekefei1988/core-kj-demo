package com.direction.common.utils.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Cache工具类
 * 
 * @author liutao
 * @version 2019-5-29
 */
public class CacheManagerUtils {

	private static CacheManager cacheManager = CacheManager.getCacheManager("defaultCache");

	/**
	 * 获取缓存.
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {

		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	/**
	 * 写入缓存.
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {

		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 重置缓存数据.
	 * 
	 * @param key
	 * @param value
	 */
	public static void reset(String cacheName, String key, Object value) {

		remove(cacheName, key);
		put(cacheName, key, value);
	}

	/**
	 * 写入缓存
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 */
	public static void put(String cacheName, String key, Object value, int cacheSeconds) {

		Element element = new Element(key, value);

		if (cacheSeconds > 0) {
			element.setTimeToIdle(cacheSeconds);
		}

		getCache(cacheName).put(element);
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {

		getCache(cacheName).remove(key);
	}

	/**
	 * 获得一个Cache，没有则创建一个。
	 * 
	 * @param cacheName
	 * @return
	 */
	public static Cache getCache(String cacheName) {

		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	public static CacheManager getCacheManager() {

		return cacheManager;
	}
}
