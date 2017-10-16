package com.zsj.cache.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Configurable
@EnableCaching
//@Component
public class RedisCacheConfig extends CachingConfigurerSupport{
	/**
     * ���������.
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
       CacheManager cacheManager = new RedisCacheManager(redisTemplate);
       return cacheManager;
//    	return null;
    }
 
   
    /**
     * redisģ�������,������jdbcTemplate��һ����;
     *
     * ��ȻCacheManagerҲ�ܻ�ȡ��Cache���󣬵��ǲ�������û����ô��
     *
     * ��������չ�£�RedisTemplate����಻��úܺò��������ǿ����ڽ�����չһ������
     *
     * �Լ��Ļ����࣬���磺RedisStorage��;
     *
     * @param factory : ͨ��Spring����ע�룬������application.properties�������ã�
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
       RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
       redisTemplate.setConnectionFactory(factory);
      
       //key���л���ʽ;����Ȼ���������;��,�����������Long�ȷ�String���͵Ļ����ᱨ����ת������
       //������û���Լ�����key��ɲ��Ե�ʱ������������뽨�鲻Ҫ��ôд�����Բ����û����Լ�ʵ��ObjectRedisSerializer
       //����JdkSerializationRedisSerializer���л���ʽ;
     RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long���Ͳ����Ի�����쳣��Ϣ;
     redisTemplate.setKeySerializer(redisSerializer);
     redisTemplate.setHashKeySerializer(redisSerializer);
       
       
       return redisTemplate;
    }
    
    
    @Bean
    public KeyGenerator keyGenerator() {
       System.out.println("RedisCacheConfig.keyGenerator()");
       return new RedisKeyGenerator();
    }
}
