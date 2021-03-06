package com.learning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.payload.request.ApproveAccountRequest;
import com.learning.payload.request.CreateAccountRequest;
import com.learning.payload.request.SetEnabledRequest;
import com.learning.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/account-service")
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping("/customer/{customerID}/account")
	public ResponseEntity<?> createAccount(@PathVariable("customerID") Long customerId,
			@Valid @RequestBody CreateAccountRequest request) {
		//System.out.println("Reached create account path.");
		return ResponseEntity.ok(accountService.addAccount(customerId, request));
	}

//	@PreAuthorize("hasRole('STAFF')")
	@PutMapping("/customer/{customerID}/account/{accountNo}")
	public ResponseEntity<?> approveAccount(@PathVariable("customerID") Long customerId,
			@PathVariable("accountNo") long accountNo, @Valid @RequestBody ApproveAccountRequest request) {
		return ResponseEntity.ok(accountService.approveAccount(customerId, accountNo, request));
	}
	
	@GetMapping("/customer/{customerID}/account")
	public ResponseEntity<?> getAccounts(@PathVariable("customerID") Long customerId) {
		return ResponseEntity.ok(accountService.getCustomerAccounts(customerId));
	}

	@GetMapping("/customer/{customerID}/account/{accountID}")
	public ResponseEntity<?> getCustomerAccount(@PathVariable("customerID") Long customerId,
			@PathVariable("accountID") Long accountId) {
		return ResponseEntity.ok(accountService.getCustomerAccount(customerId, accountId));
	}
	
	@PutMapping("/staff/accountStatus")
	public ResponseEntity<?> updateAccountStatus(@RequestBody SetEnabledRequest request){
		System.out.println("in update status request.");
		return ResponseEntity.ok(accountService.setAccountEnabled(request));
	}
	
	@GetMapping("/staff/account/{accountNo}")
	public ResponseEntity<?> getAccountDetails(@PathVariable("accountNo") Long accountNo) {
		return ResponseEntity.ok(accountService.getAccountDetails(accountNo));
	}
	
	@GetMapping("/staff/accounts/approve")
	public ResponseEntity<?> getUnapprovedAccounts() {
		return ResponseEntity.ok(accountService.getUnapprovedAccounts());
	}

	@PutMapping("/staff/accounts/approve")
	public ResponseEntity<?> approveAccount(@RequestBody ApproveAccountRequest request) {
		return ResponseEntity.ok(accountService.approveAccount(request));
	}
}
