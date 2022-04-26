package com.spring.cloud.gateway.SpringCloudGatewayServer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
//	@RequestMapping("/accountFallBack")
//	public Mono<String> accountServiceFallBack(){
//		return Mono.just("Account Service is taking too long to respond or is down. Please try again later");
//	}
//	
//	@RequestMapping("/transactionFallBack")
//	public Mono<String> transactionServiceFallBack(){
//		return Mono.just("Transaction Service is taking too long to respond or is down. Please try again later");
//	}
//	
//	@RequestMapping("/customerFallBack")
//	public Mono<String> customerServiceFallBack(){
//		return Mono.just("Customer Service is taking too long to respond or is down. Please try again later");
//	}
	
	@RequestMapping("/accountFallBack")
	public ResponseEntity<String> accountServiceFallBack(Exception e){
		return new ResponseEntity<String>("Account Service is taking too long to respond or is down. Please try again later",HttpStatus.OK);
	}
	
	@RequestMapping("/transactionFallBack")
	public ResponseEntity<String> transactionServiceFallBack(){
		return new ResponseEntity<String>("Transaction Service is taking too long to respond or is down. Please try again later",HttpStatus.OK);
	}
	
	@RequestMapping("/customerFallBack")
	public ResponseEntity<String> customerServiceFallBack(){
		return new ResponseEntity<String>("Customer Service is taking too long to respond or is down. Please try again later",HttpStatus.OK);
	}


}
