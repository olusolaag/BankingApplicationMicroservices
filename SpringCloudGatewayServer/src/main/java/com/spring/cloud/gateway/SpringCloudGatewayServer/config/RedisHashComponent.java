package com.spring.cloud.gateway.SpringCloudGatewayServer.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.spring.cloud.gateway.SpringCloudGatewayServer.util.MapperUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisHashComponent {
	
	private final RedisTemplate<String, Object> redisTemplate;
	
	public RedisHashComponent(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	
	public void hSet(String key, Object hashKey, Object value) {
		Map ruleHash = MapperUtils.objectMapper(value, Map.class);
		redisTemplate.opsForHash().put(key, hashKey, ruleHash);
	}
	
	//method check the list of object present in the redis database
	public List<Object> hValues(String key){
		return redisTemplate.opsForHash().values(key);
	}
	
	//method takes api key and service value
	public Object hGet(String key, Object hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}

}
