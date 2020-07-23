package com.direction.security.shiro;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.direction.core.modules.sys.menu.service.MenuService;
import com.direction.security.shiro.filter.SimpleShiroFilterFactory;
import com.direction.security.shiro.realm.AuthorizingRealm;
import com.direction.security.shiro.session.ShiroSessionManager;

// ~ File Information
// ====================================================================================================================

/**
 * Shiro安全验证配置.
 * 
 * <pre>
 * Shiro安全验证配置
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Configuration
public class ConfigurationShiro {

	// ~ Static Fields
	// ==================================================================================================================

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationShiro.class);

	// ~ Fields
	// ==================================================================================================================

	public ConfigurationShiro() {

		logger.debug("加载系统安全配置...");
	}

	@Autowired
	private MenuService menuService;
	
	@Value("${spring.shiro.filter.anon.filters:}")
	private String annoFilters;
	
	@Value("${shrio.cache.session.timeout:3600}")
	private Long shrioCacheTimeout;
	
	@Value("${shrio.cache.ehcache.config:classpath:cache/ehcache-shrio.xml}")
	private String shiroConfigPath;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取验证实例.
	 * 
	 * @return
	 */
	@Bean
	public AuthorizingRealm shiroRealm() {
		return new AuthorizingRealm();
	}

	/**
	 * 缓存管理器 使用Ehcache实现.
	 * 
	 */
	@Bean(name = "shiroCacheManager")
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile(shiroConfigPath);
		return em;
	}

	/**
	 * 自定义sessionManager.
	 * 
	 * @return
	 */
	@Bean
	public SessionManager sessionManager() {

		ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
		
		// 设置失效时间, 受缓存影响
		shiroSessionManager.setGlobalSessionTimeout(shrioCacheTimeout * 1000);

		// 这里可以不设置。Shiro有默认的session管理。如果缓存为Redis则需改用Redis的管理
		shiroSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
		return shiroSessionManager;
	}

	/**
	 * 安全验证管理器.
	 * 
	 * @return
	 */
	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager() {

		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());

		// securityManager.setAuthenticator(modularRealmAuthenticator());
		// 设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
		// securityManager.setRealms(Arrays.asList(jwtShiroRealm(),
		// shiroRealm()));
		
		// 自定义session管理
		securityManager.setSessionManager(sessionManager());
		// 设置多个realm认证策略，一个成功即跳过其它的
		
		// 使用spring boot 默认缓存
		securityManager.setCacheManager(new MemoryConstrainedCacheManager());
		
		// 使用自定义缓存: 注入缓存管理器;
		// securityManager.setCacheManager(getEhCacheManager());
		

		return securityManager;
	}

	/**
	 * 安全验证过滤器.
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public SimpleShiroFilterFactory shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
		
		SimpleShiroFilterFactory shiroFilterFactoryBean = new SimpleShiroFilterFactory(menuService, annoFilters);
		shiroFilterFactoryBean.createShiroFilter(securityManager);

		logger.info("加载shiro配置信息...");

		return shiroFilterFactoryBean;
	}

	/**
	 * cookie加密及有效期.
	 * 
	 * @return
	 */
	@Bean
	public CookieRememberMeManager cookieRememberMeManager() {

		CookieRememberMeManager cookie = new CookieRememberMeManager();
		cookie
		  .setCipherKey(new String("#{T(org.apache.shiro.codec.Base64).decode('4AvVhmFLUs0KTA3Kprsdag==')}").getBytes());

		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(7 * 24 * 60 * 60);

		cookie.setCookie(simpleCookie);

		return cookie;
	}

	/**
	 * 授权源管理.
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
	    org.apache.shiro.mgt.SecurityManager securityManager) {

		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
