package com.zsj.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zsj.shiro.service.UserInfoService;

@Component
@Configurable(autowire = Autowire.BY_TYPE)
public class ShiroConfiguration {
	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 * 
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 * 
	 */
	@Autowired
	private UserInfoService userInfoService;

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");

		
		filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");
		
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index1");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	@Bean
	public MyShiroRealm myShiroRealm() {
		
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setUserInfoService(userInfoService);
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		// this.
		// myShiroRealm.
		// myShiroRealm.asdf="1111";
		return myShiroRealm;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于
														// md5(md5(""));

		return hashedCredentialsMatcher;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	
	@Bean
    public EhCacheManager ehCacheManager(){
       System.out.println("ShiroConfiguration.getEhCacheManager()");
       EhCacheManager cacheManager = new EhCacheManager();
       cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
       return cacheManager;
    }
	// @Bean
	// public UserInfoService userInfoService(){
	// UserInfoService myShiroRealm = new UserInfoServiceImpl();
	// // this.
	// // myShiroRealm.
	// //myShiroRealm.asdf="1111";
	// return myShiroRealm;
	// }

	@Bean
    public SimpleCookie rememberMeCookie(){
       System.out.println("ShiroConfiguration.rememberMeCookie()");
       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       simpleCookie.setMaxAge(259200);
       return simpleCookie;
    }
	
	
	 @Bean
	 public CookieRememberMeManager rememberMeManager(){
	       System.out.println("ShiroConfiguration.rememberMeManager()");
	       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	       cookieRememberMeManager.setCookie(rememberMeCookie());
	       return cookieRememberMeManager;
	    }
}
