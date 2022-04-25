package com.spring.cloud.gateway.SpringCloudGatewayServer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	@RequestMapping("/accountFallBack")
	public Mono<String> accountServiceFallBack(){
		return Mono.just("Account Service is taking too long to respond or is down. Please try again later");
	}
	
	@RequestMapping("/transactionFallBack")
	public Mono<String> transactionServiceFallBack(){
		return Mono.just("Transaction Service is taking too long to respond or is down. Please try again later");
	}
	
	@RequestMapping("/customerFallBack")
	public Mono<String> customerServiceFallBack(){
		return Mono.just("Customer Service is taking too long to respond or is down. Please try again later");
	}


}
