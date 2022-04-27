package com.learning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.payload.request.TransferRequest;
import com.learning.payload.request.TransferRequestStaff;
import com.learning.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/transaction-service")
@Api
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	KafkaTemplate<String, String> kafka;
	
	@Autowired
	KafkaListenerEndpointRegistry endpointRegistry;
	
	@ApiOperation("Customer Money Transfer")
	@PutMapping(value = "/customer/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> transferFunds(@Valid @RequestBody TransferRequest request) {
		
		return ResponseEntity.status(200).body(transactionService.transferFunds(request));
	}
	
	@ApiOperation("Staff Money Transfer")
	@PutMapping("/staff/transfer")
	public ResponseEntity<?> staffTransfer(@RequestBody TransferRequestStaff request) {
		return ResponseEntity.ok(transactionService.staffTransferFunds(request));
	}
}
