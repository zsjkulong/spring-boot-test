package com.zsj.cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableCaching
// 标注启动缓存.
public class CacheConfiguration {

	@Bean
	public EhCacheCacheManager ehCacheCacheManager(
			EhCacheManagerFactoryBean bean) {
		System.out.println("CacheConfiguration.ehCacheCacheManager()");
		return new EhCacheCacheManager(bean.getObject());
	}

	/*
	 * 据shared与否的设置, Spring分别通过CacheManager.create() 或new
	 * CacheManager()方式来创建一个ehcache基地.
	 * 
	 * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
	 */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		System.out.println("CacheConfiguration.ehCacheManagerFactoryBean()");
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		System.out.println(new ClassPathResource("/"));
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource(
				"ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}
}
