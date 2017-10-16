package com.zsj.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.zsj.cache.bean.DemoInfo;

public interface DemoInfoService {
	
	//@Cacheable(value = "demoInfo")
	@Cacheable(value = "demoInfo",key="'demoInfo_'+#id")
	public DemoInfo findById(long id);
	@CacheEvict(value = "demoInfo")
	public void deleteFromCache(long id);
	void test();
}
