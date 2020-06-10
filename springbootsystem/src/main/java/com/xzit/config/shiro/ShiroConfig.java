package com.xzit.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置文件
 * @author Charles
 *
 */
@Configuration
public class ShiroConfig {
	
	private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
	
	/**
	 * 指定用户验证使用自定义的Realm
	 * @return
	 */
	@Bean
	public MyRealm myAuthRealm() {
		
		MyRealm myRealm = new MyRealm();
		logger.info("==myRealm注册完成==");
		return myRealm;
	}
	
	/**
	 * 注入安全管理器
	 * @return
	 */
	@Bean
	public org.apache.shiro.web.mgt.WebSecurityManager securityManager() {
		//引入自定义的realm
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager(myAuthRealm());
		return manager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.web.mgt.WebSecurityManager securityManager) {
		//定义ShiroFilterFactortyBean
		ShiroFilterFactoryBean shirofilterFactoryBean = new ShiroFilterFactoryBean();
		//设置自定义的SecurityManager
		shirofilterFactoryBean.setSecurityManager(securityManager);
		
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("authc", new AjaxPermissionAuthorizationFilter());
		shirofilterFactoryBean.setFilters(filterMap);
		
		/**
		 * 定义shiro过滤链 anon表示开放权限;authc 需要身份认证; logout 注销
		 */
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/login/auth", "anon");
		filterChainDefinitionMap.put("login/logout", "amon");
		filterChainDefinitionMap.put("/error", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		
		shirofilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shirofilterFactoryBean;
	}
}
