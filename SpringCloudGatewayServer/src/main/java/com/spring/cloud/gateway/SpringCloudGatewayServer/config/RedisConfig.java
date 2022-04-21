package com.spring.cloud.gateway.SpringCloudGatewayServer.config;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisConfig {
	private final Environment env;
	
	public RedisConfig(Environment env) {
		this.env = env;
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
				Objects.requireNonNull(env.getProperty("spring.redis.host")),
				Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.redis.port"))));
		return new JedisConnectionFactory(redisStandaloneConfiguration);
		
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setEnableTransactionSupport(true);
		return template;
	}

}
