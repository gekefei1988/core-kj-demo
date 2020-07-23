package com.direction.core.modules.sys.cache.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.common.utils.cache.CacheCategoryManage;
import com.direction.common.utils.cache.CacheManagerUtils;
import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheVO;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

@RestController
@RequestMapping("/core/sys/cache")
public class CacheController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取缓存类型名称.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCacheNames")
	public List<Map<String, String>> getCacheNames() {

		// 返回结果
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();

		Map<String, String> maps = CacheCategoryManage.getCacheNames();
		Map<String, String> tempMap = null;

		for (String key : maps.keySet()) {
			tempMap = new HashMap<String, String>();
			tempMap.put("key", key);
			tempMap.put("value", maps.get(key));
			results.add(tempMap);
		}

		return results;
	}

	/**
	 * 获取缓存列表数据.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCacheList")
	public List<CacheVO> getCacheList(String cacheType) {

		// 判断缓存类型
		if (StringUtils.isBlank(cacheType)) {
			cacheType = CacheCategoryManage.SYSTEME_CACHE;
		}

		List<CacheVO> results = new ArrayList<CacheVO>();

		@SuppressWarnings("unchecked")
		List<String> keys = CacheSysManageUtils.getCache().getKeys();

		if (keys != null && keys.size() > 0) {
			for (String key : keys) {
				results.add(new CacheVO(key, cacheType));
			}
		}

		return results;
	}

	/**
	 * 根据key值清空缓存.
	 * 
	 * @param cache
	 * @return
	 */
	@RequestMapping(value = "/clearCache")
	public ResultJson clearCache(CacheVO cache) {

		if (StringUtils.isNoneBlank(cache.getKey(), cache.getCacheType())) {
			CacheManagerUtils.remove(cache.getCacheType(), cache.getKey());
		} else {
			return error("缓存键值不能为空!");
		}

		return success();
	}
}
