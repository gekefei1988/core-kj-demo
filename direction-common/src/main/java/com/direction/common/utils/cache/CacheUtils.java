package com.direction.common.utils.cache;


/**
 * 
 *  缓存处理通用接口
 * 
 * <pre>
 * 	定义缓存处理通用接口，便于统一处理
 * </pre>
 * 
 * @author qiwei
 * @since 2019-7-9
 *
 */
public interface CacheUtils {
	
	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	
	/**
	 * 写入缓存.
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public  void put(String cacheName, String key, Object value)  ;
	
	/**
	 * 写入缓存
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 */
	public  void put(String cacheName, String key, Object value, int cacheSeconds) ;
	
	/**
	 * 获取缓存.
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public  Object get(String cacheName, String key) ;
	/**
	 * 从缓存中移除
	 * 
	 * @param cacheName
	 * @param key
	 */
	public  void remove(String cacheName, String key) ;
	/**
	 * 重置缓存数据.
	 * 
	 * @param key
	 * @param value
	 */
	public  void reset(String cacheName, String key, Object value) ;
	
	
	
   
}
