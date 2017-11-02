package com.zsj;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
// @EnableJpaRepositories("com.zsj.cache")
// @EntityScan("com.zsj.cache")
// @ComponentScan("com.zsj.cache")
@EnableRedisHttpSession
@MapperScan("com.zsj.*.mapper")
@EnableAsync
public class TestApplication {
	private static Logger logger = LoggerFactory.getLogger(TestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);

		logger.debug("日志输出测试 Debug");
		logger.trace("日志输出测试 Trace");
		logger.info("日志输出测试 Info");
		logger.error("日志输出测试error");
	}

}
