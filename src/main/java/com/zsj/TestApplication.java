package com.zsj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//@EnableJpaRepositories("com.zsj.cache")
//@EntityScan("com.zsj.cache")
//@ComponentScan("com.zsj.cache")
@EnableRedisHttpSession
public class TestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);  
	}
	
}
