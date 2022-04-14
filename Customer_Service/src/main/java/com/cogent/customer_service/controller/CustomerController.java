package com.cogent.customer_service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.customer_service.payload.request.SecretAnswerRequest;
import com.cogent.customer_service.payload.request.UpdateCustomerRequest;
import com.cogent.customer_service.payload.request.UpdatePasswordRequest;
import com.cogent.customer_service.service.CustomerService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@GetMapping("/{customerID}")
	public ResponseEntity<?> getCustomer(@PathVariable("customerID") Long customerId) {
		return ResponseEntity.ok(customerService.getCustomer(customerId));
	}

	@PutMapping("/{customerID}")
	public ResponseEntity<?> updateCustomer(@Valid @PathVariable("customerID") Long customerId,
			@RequestBody UpdateCustomerRequest request) {
		return ResponseEntity.ok(customerService.updateCustomer(customerId, request));
	}

	@GetMapping("/{username}/forgot/question")
	public ResponseEntity<?> getQuestion(@PathVariable("username") String username) {
		return ResponseEntity.ok(customerService.getQuestion(username));
	}

	@GetMapping("/{username}/forgot/answer")
	public ResponseEntity<?> getQuestion(@PathVariable("username") String username,
			@RequestBody SecretAnswerRequest answer) {
		// May want this to produce JWT token on success, as per authenticate?
		return ResponseEntity.ok(customerService.validateAnswer(username, answer));
	}
	
	@PutMapping("/{username}/forgot")
	public ResponseEntity<?> updatePassword(@Valid @PathVariable("username") String username,
			@RequestBody UpdatePasswordRequest request) {
		return ResponseEntity.status(200).body(customerService.updatePassword(username, request));
	}

}
