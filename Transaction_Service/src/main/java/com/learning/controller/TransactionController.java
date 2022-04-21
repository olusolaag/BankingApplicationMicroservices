package com.learning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/api/transaction-service")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	KafkaTemplate<String, String> kafka;
	
	@Autowired
	KafkaListenerEndpointRegistry endpointRegistry;
	
	@PutMapping("/customer/transfer")
	public ResponseEntity<?> transferFunds(@Valid @RequestBody TransferRequest request) {
		
		return ResponseEntity.status(200).body(transactionService.transferFunds(request));
	}
	
	@PutMapping("/staff/transfer")
	public ResponseEntity<?> staffTransfer(@RequestBody TransferRequestStaff request) {
		return ResponseEntity.ok(transactionService.staffTransferFunds(request));
	}
}
