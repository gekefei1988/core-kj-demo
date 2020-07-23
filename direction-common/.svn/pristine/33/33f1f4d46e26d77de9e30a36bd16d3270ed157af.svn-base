package com.direction.security.shiro.filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.direction.core.inf.sys.role.IRole;
import com.direction.core.modules.sys.menu.entity.MenuPermsVO;
import com.direction.core.modules.sys.menu.service.MenuService;

// ~ File Information
// ====================================================================================================================

/**
 * Shiro权限过滤类.
 * 
 * <pre>
 * Shiro权限过滤类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class SimpleShiroFilterFactory extends ShiroFilterFactoryBean {

	// ~ Static Fields
	// ==================================================================================================================

	private static final Logger logger = LoggerFactory.getLogger(SimpleShiroFilterFactory.class);

	// ~ Fields
	// ==================================================================================================================

	private MenuService menuService;
	
	private String anonFilters;

	// ~ Constructors
	// ==================================================================================================================

	public SimpleShiroFilterFactory() {

	}
	
	public SimpleShiroFilterFactory(MenuService menuService) {

		this.menuService = menuService;
	}

	public SimpleShiroFilterFactory(MenuService menuService, String anonFilters) {

		this.menuService = menuService;
		this.anonFilters = anonFilters;
	}

	public SimpleShiroFilterFactory(org.apache.shiro.mgt.SecurityManager securityManager) {

		this.createShiroFilter(securityManager);
	}

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 创建shiro权限.
	 * 
	 */
	public void createShiroFilter(org.apache.shiro.mgt.SecurityManager securityManager) {

		this.setSecurityManager(securityManager);

		// 设置filters
		Map<String, Filter> filtersMap = this.getDefaultFilters();
		this.setFilters(filtersMap);

		// 登录
		this.setLoginUrl("/login");

		// 首页
		this.setSuccessUrl("/home");

		Map<String, String> maps = getDefaultFilterMap();

		for (String key : maps.keySet()) {
			logger.debug(String.format("key: %s, value: %s", key, maps.get(key)));
		}

		// 加载默认设置
		this.setFilterChainDefinitionMap(maps);
	}

	/**
	 * 获取默认的filters.
	 * 
	 * @return
	 */
	private Map<String, Filter> getDefaultFilters() {

		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();

		// 是否登陆鉴权
		filtersMap.put("authc", new SimpleFormAuthenticationFilter());

		// 角色鉴权
		filtersMap.put("rolesAuth", new SimpleAuthorizationFilter());

		// perms
		filtersMap.put("perms", new URLPermissionsFilter());

		return filtersMap;
	}

	/**
	 * 获取默认的配置.
	 * 
	 * @return
	 */
	public Map<String, String> getDefaultFilterMap() {

		Map<String, String> filterMap = new LinkedHashMap<String, String>();
		
		// 验证码
		filterMap.put("/core/sys/verify-code/**", "anon");
		
		// 注册信息相关及密码找回和重置密码
		filterMap.put("/core/sys/reg/**", "anon");
		
		// 密码找回
		filterMap.put("/core/sys/retrieve-pwd/**", "anon");
		
		// 读取配置文件的anon-filters
		if (StringUtils.isNotBlank(anonFilters)) {
			String[] anons = StringUtils.split(anonFilters, ";");
			if (anons.length > 0) {
				for (String anon : anons) {
					filterMap.put(anon, "anon");
				}
			}
		}
		
		filterMap.put("/core/open-api/sys/**", "authc");

		// 登出
		filterMap.put("/logout", "authc");
		filterMap.put("/login", "anon");
		filterMap.put("/login-admin", "anon");
		filterMap.put("/wclogin", "anon");
		filterMap.put("/login/**", "authc");

		logger.info("加载系统全部权限...");

		try {
			List<MenuPermsVO> menuPerms = menuService.findPermsByRole();
			
			if (menuPerms != null && menuPerms.size() > 0) {

				Map<String, Set<String>> urlMaps = new HashMap<String, Set<String>>();
				Map<String, Set<String>> permMaps = new HashMap<String, Set<String>>();

				for (MenuPermsVO permsVO : menuPerms) {

					// 判断url权限过滤角色
					if (StringUtils.isNotBlank(permsVO.getUrl())) {

						if (!urlMaps.containsKey(permsVO.getUrl())) {
							urlMaps.put(permsVO.getUrl(), new HashSet<String>());

							// 增加一个默认超级管理员角色
							urlMaps.get(permsVO.getUrl()).add(IRole.DEFAULT_SUPER_ROLE);
						}

						if (StringUtils.isNotBlank(permsVO.getRoleId())) {
							urlMaps.get(permsVO.getUrl()).add(permsVO.getRoleId());
						}
					}

					// 按钮权限过滤标识符
					if (StringUtils.isNoneBlank(permsVO.getUrl(), permsVO.getPerms())) {

						if (!permMaps.containsKey(permsVO.getUrl())) {
							permMaps.put(permsVO.getUrl(), new HashSet<String>());
						}

						permMaps.get(permsVO.getUrl()).add(permsVO.getPerms());
					}
				}
				
				// 移除相应的key值
				urlMaps.remove("Main");
				urlMaps.remove("parentView");
				permMaps.remove("Main");
				permMaps.remove("parentView");

				// 角色权限
				for (String mapKey : urlMaps.keySet()) {
					filterMap.put(mapKey, "authc,rolesAuth[\"" + StringUtils.join(urlMaps.get(mapKey), "\",\"") + "\"]");
				}

				// 权限标识权
				for (String mapKey : permMaps.keySet()) {
					if (filterMap.containsKey(mapKey)) {
						filterMap.put(mapKey, filterMap.get(mapKey) + ",perms[\"" + StringUtils.join(permMaps.get(mapKey), "\",\"") + "\"]");
					} else {
						filterMap.put(mapKey, "authc,perms[\"" + StringUtils.join(permMaps.get(mapKey), "\",\"") + "\"]");
					}
				}
			} else {
				logger.debug("加载系统全部权限... 为空值");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("全局授权异常, 请联系管理员, 确认是否设置授权...");
		}

		filterMap.put("/**", "authc");

		return filterMap;
	}

	/**
	 * 更新权限配置.
	 * 
	 * @param filterMap
	 */
	public synchronized void updatePermission(Map<String, String> filterMap) {

		AbstractShiroFilter abstractShiroFilter = null;

		try {
			abstractShiroFilter = (AbstractShiroFilter) this.getObject();

			// 获取过滤管理器
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter
			  .getFilterChainResolver();
			DefaultFilterChainManager filterManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

			// 清空拦截管理器中的存储
			filterManager.getFilterChains().clear();

			/*
			 * 清空拦截工厂中的存储,如果不清空这里,还会把之前的带进去 ps:如果仅仅是更新的话,可以根据这里的 map 遍历数据修改,重新整理好权限再一起添加
			 */
			this.getFilterChainDefinitionMap().clear();

			// 相当于新建的 map, 因为已经清空了
			Map<String, String> chains = new LinkedHashMap<>();

			// 放入默认配置
			chains.putAll(this.getDefaultFilterMap());

			if (filterMap != null) {
				// 把修改后的 map 放进去
				chains.putAll(filterMap);
			}

			// 这个相当于是全量添加
			for (Map.Entry<String, String> entry : chains.entrySet()) {

				// 要拦截的地址
				String url = entry.getKey().trim().replace(" ", "");

				// 地址持有的权限
				String chainDefinition = entry.getValue().trim().replace(" ", "");

				logger.info(String.format("拦截地址:%s, 持有权限:%s", url, chainDefinition));

				// 生成拦截
				filterManager.createChain(url, chainDefinition);
			}
		} catch (Exception e) {
			logger.error("加载shiro访问权限失败, 失败原因:" + e.getMessage());
		}
	}
}
