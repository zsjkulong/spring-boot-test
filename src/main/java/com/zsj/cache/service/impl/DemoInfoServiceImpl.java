package com.zsj.cache.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.zsj.cache.bean.DemoInfo;
import com.zsj.cache.repository.DemoInfoRepository;
import com.zsj.cache.service.DemoInfoService;

@Service
public class DemoInfoServiceImpl implements DemoInfoService {

	@Resource
	private DemoInfoRepository demoInfoRepository;

//	@Resource
//	private RedisTemplate<String, String> redisTemplate;

	
	public DemoInfo findById(long id) {
		System.err
				.println("DemoInfoServiceImpl.findById()=========����ݿ��н��л�ȡ��....id="
						+ id);
		return demoInfoRepository.findOne(id);
	}

	
	public void deleteFromCache(long id) {
		System.out.println("DemoInfoServiceImpl.delete().�ӻ�����ɾ��.");
	}

	public void test() {
		/*ValueOperations<String, String> valueOperations = redisTemplate
				.opsForValue();
		valueOperations.set("mykey4", "random1=" + Math.random());
		System.out.println(valueOperations.get("mykey4"));*/
	}

}
