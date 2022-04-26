package com.spring.cloud.gateway.SpringCloudGatewayServer.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.cloud.gateway.SpringCloudGatewayServer.dto.ApiKey;
import com.spring.cloud.gateway.SpringCloudGatewayServer.util.AppConstants;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class GatewayConfig {
	@Autowired
	private RedisHashComponent redisHashComponent;

	@PostConstruct
	public void initKeysToRedis() {
		List<ApiKey> apiKeys = new ArrayList<>();
		apiKeys.add(new ApiKey(UUID.randomUUID().toString(),
				Stream.of(AppConstants.ACCOUNT_SERVICE_KEY).collect(Collectors.toList())));
		apiKeys.add(new ApiKey(UUID.randomUUID().toString(),
				Stream.of(AppConstants.TRANSACTION_SERVICE_KEY).collect(Collectors.toList())));
		apiKeys.add(new ApiKey(UUID.randomUUID().toString(),
				Stream.of(AppConstants.CUSTOMER_SERVICE_KEY).collect(Collectors.toList())));

		List<Object> lists = redisHashComponent.hValues(AppConstants.RECORD_KEY);
		if (lists.isEmpty()) {
			apiKeys.forEach(k -> redisHashComponent.hSet(AppConstants.RECORD_KEY, k.getKey(), k));
		}
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(AppConstants.ACCOUNT_SERVICE_KEY,
						r -> r.path("/api/account-service/**").filters(f -> f.stripPrefix(2).circuitBreaker(
								c -> c.setName("banking-account-microservice").setFallbackUri("/accountFallBack")))
								.uri("http://localhost:9015"))
				.route(AppConstants.TRANSACTION_SERVICE_KEY,
						r -> r.path("/api/transaction-service/**")
								.filters(f -> f.stripPrefix(2)
										.circuitBreaker(c -> c.setName("banking-transaction-microservice")
												.setFallbackUri("/transactionFallBack")))
								.uri("http://localhost:8100"))
				.route(AppConstants.CUSTOMER_SERVICE_KEY,
						r -> r.path("/api/transaction-service/**").filters(f -> f.stripPrefix(2).circuitBreaker(
								c -> c.setName("banking-customer-microservice").setFallbackUri("/customerFallBack")))
								.uri("http://localhost:9001"))
				.build();
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()).build());
	}

}
