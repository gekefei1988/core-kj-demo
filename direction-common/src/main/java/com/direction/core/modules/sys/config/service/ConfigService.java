package com.direction.core.modules.sys.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;
import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.config.entity.Config;
import com.direction.core.modules.sys.config.repository.ConfigRepository;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class ConfigService extends BaseService<ConfigRepository, Config, String>{

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private ConfigRepository configRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================


	/**
	 * 清空缓存.
	 * 
	 * @see com.direction.spring.mvc.service.BaseService#clearCache()
	 */
	@Override
	public void clearCache() {
		CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_CONFIG, "getConfigMaps_all");
	}
	
	/**
	 * 获取系统配置的MAP集合.
	 * 
	 * @return
	 */
	public Map<String, Config> getConfigMaps() {
		
		@SuppressWarnings("unchecked")
		Map<String, Config> configMaps = (Map<String, Config>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_CONFIG, "getConfigMaps_all");
		
		if (configMaps != null) {
			return configMaps;
		}
		
		configMaps = new HashMap<String, Config>();
		
		List<Config> configs = this.findByProp("status", StatusConst.ENABLE);
		
		if (configs != null && configs.size() > 0) {
			for (Config config : configs) {
				configMaps.put(config.getConfigNo(), config);
			}
		}
		
		// 租户放入缓存
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_CONFIG, "getConfigMaps_all", configMaps);
		
		return configMaps;
	}
	
	/**
	 * 获取参数设置对象.
	 * 
	 * @param configNo
	 * @return
	 */
	public Config getConfig(String configNo) {

		if (StringUtils.isBlank(configNo)) {
			return null;
		}

		// 缓存中获取
		Config config = (Config) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_CONFIG, "get_config_" + configNo);

		if (config != null) {
			return config;
		}

		config = this.configRepository.getConfig(configNo);

		// 放入缓存
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_CONFIG, "get_config_" + configNo, config);

		return config;
	}

	/**
	 * 分页查询.
	 * 
	 * @param Config
	 * @param page
	 * @return
	 */
	public VuePage<Config> findPage(Config config, VuePage<Config> page) {

		Specification<Config> params = new Specification<Config>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();

				if (config != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(config.getConfigDesc())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("configDesc").as(String.class), "%" + config.getConfigDesc() + "%"),
						    criteriaBuilder.like(root.get("configNo").as(String.class), "%" + config.getConfigDesc() + "%")));
					}

					// 判断状态
					if (StringUtils.isNotBlank(config.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), config.getStatus()));
					}
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<Config> dictTypePage = configRepository.findAll(params, page.getPageable());
		page.setPage(dictTypePage);

		return page;
	}
}
