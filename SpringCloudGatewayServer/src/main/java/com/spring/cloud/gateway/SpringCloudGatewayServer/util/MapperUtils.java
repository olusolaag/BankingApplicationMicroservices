package com.spring.cloud.gateway.SpringCloudGatewayServer.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

@UtilityClass //all methods in the class will be static, u don't need to define static manually
public class MapperUtils {
	
	ObjectMapper mapper = new ObjectMapper();
	
	public <T> T objectMapper(Object object, Class<T> contentClassType) {
		return mapper.convertValue(object, contentClassType);
	}

}
