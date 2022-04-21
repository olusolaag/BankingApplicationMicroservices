package com.spring.cloud.gateway.SpringCloudGatewayServer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.spring.cloud.gateway.SpringCloudGatewayServer.config.RedisHashComponent;
import com.spring.cloud.gateway.SpringCloudGatewayServer.dto.ApiKey;
import com.spring.cloud.gateway.SpringCloudGatewayServer.util.AppConstants;

@SpringBootApplication
public class SpringCloudGatewayServerApplication {
	
	@Autowired
	private RedisHashComponent redisHashComponent;
	
	
	
	@PostConstruct
	public void initKeysToRedis() {
		List<ApiKey> apiKeys = new ArrayList<>();
		apiKeys.add(new ApiKey("343C-ED08-4137-B27E", Stream.of(
				AppConstants.ACCOUNT_SERVICE_KEY).collect(Collectors.toList())));
		apiKeys.add(new ApiKey("FA48-EF0C-427E-8CCF", Stream.of(
				AppConstants.TRANSACTION_SERVICE_KEY).collect(Collectors.toList())));
		apiKeys.add(new ApiKey("AMDT-ED0C-657E-AKJC", Stream.of(
				AppConstants.CUSTOMER_SERVICE_KEY).collect(Collectors.toList())));
		
		List<Object> lists = redisHashComponent.hValues(AppConstants.RECORD_KEY);
		if(lists.isEmpty()) {
			apiKeys.forEach(k->redisHashComponent.hSet(AppConstants.RECORD_KEY, k.getKey(), k));
		}
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(AppConstants.ACCOUNT_SERVICE_KEY,r->r.path("/api/account-service/**")
						.filters(f->f.stripPrefix(2)).uri("http://localhost:9015"))
			
				.route(AppConstants.TRANSACTION_SERVICE_KEY,r->r.path("/api/transaction-service/**")
						.filters(f->f.stripPrefix(2)).uri("http://localhost:8100"))
				.route(AppConstants.CUSTOMER_SERVICE_KEY,r->r.path("/api/transaction-service/**")
						.filters(f->f.stripPrefix(2)).uri("http://localhost:9001"))
				.build();
				
						
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayServerApplication.class, args);
	}

}
