package com.learning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.payload.request.AddBeneficiaryRequest;
import com.learning.payload.request.ApproveAccountRequest;
import com.learning.payload.request.CreateAccountRequest;
import com.learning.payload.request.SecretAnswerRequest;
import com.learning.payload.request.SetEnabledRequest;
import com.learning.payload.request.TransferRequest;
import com.learning.payload.request.UpdateCustomerRequest;
import com.learning.payload.request.UpdatePasswordRequest;
import com.learning.payload.response.AccountCreationResponse;
import com.learning.service.CustomerService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/{customerID}/account")
	public ResponseEntity<?> createAccount(@PathVariable("customerID") Long customerId,
			@Valid @RequestBody CreateAccountRequest request) {
		//System.out.println("Reached create account path.");
		return ResponseEntity.ok(customerService.addAccount(customerId, request));
	}

	@PreAuthorize("hasRole('STAFF')")
	@PutMapping("/{customerID}/account/{accountNo}")
	public ResponseEntity<?> approveAccount(@PathVariable("customerID") Long customerId,
			@PathVariable("accountNo") long accountNo, @Valid @RequestBody ApproveAccountRequest request) {
		return ResponseEntity.ok(customerService.approveAccount(customerId, accountNo, request));
	}
	
	//@CrossOrigin
	@GetMapping("/{customerID}/account")
	public ResponseEntity<?> getAccounts(@PathVariable("customerID") Long customerId) {
		return ResponseEntity.ok(customerService.getCustomerAccounts(customerId));
	}
	@PutMapping("/accountStatus")
	public ResponseEntity<?> updateAccountStatus(@RequestBody SetEnabledRequest request){
		System.out.println("in update status request.");
		return ResponseEntity.ok(customerService.setAccountEnabled(request));
	}

	@GetMapping("/{customerID}")
	public ResponseEntity<?> getCustomer(@PathVariable("customerID") Long customerId) {
		return ResponseEntity.ok(customerService.getCustomer(customerId));
	}

	@GetMapping("/{customerID}/account/{accountID}")
	public ResponseEntity<?> getCustomerAccount(@PathVariable("customerID") Long customerId,
			@PathVariable("accountID") Long accountId) {
		return ResponseEntity.ok(customerService.getCustomerAccount(customerId, accountId));
	}

	@PutMapping("/{customerID}")
	public ResponseEntity<?> updateCustomer(@Valid @PathVariable("customerID") Long customerId,
			@RequestBody UpdateCustomerRequest request) {
		return ResponseEntity.ok(customerService.updateCustomer(customerId, request));
	}

	@PostMapping("/{customerID}/beneficiary")
	public ResponseEntity<?> addBeneficiary(@Valid @PathVariable("customerID") Long customerId,
			@RequestBody AddBeneficiaryRequest request) {
		//Need to deal with beneficiary name.
		//System.out.println("Adding beneficiary");
		return ResponseEntity.ok(customerService.addBeneficiary(customerId, request));
	}

	@GetMapping("/{customerID}/beneficiary")
	public ResponseEntity<?> getBeneficiaries(@PathVariable("customerID") Long customerId) {
		return ResponseEntity.ok(customerService.getBeneficiaries(customerId));
	}
	@GetMapping("/{customerID}/activeBeneficiary")
	public ResponseEntity<?> getActiveBeneficiaries(@PathVariable("customerID") Long customerId) {
		return ResponseEntity.ok(customerService.getActiveBeneficiaries(customerId));
	}
	@DeleteMapping("/{customerID}/beneficiary/{beneficiaryID}")
	public ResponseEntity<?> deleteBeneficiary(@PathVariable("customerID") Long customerId,
			@PathVariable("beneficiaryID") Long beneficiaryId) {
		return ResponseEntity.ok(customerService.deleteBeneficiary(customerId, beneficiaryId));
	}

	@PutMapping("/transfer")
	public ResponseEntity<?> transferFunds(@Valid @RequestBody TransferRequest request) {
		return ResponseEntity.status(200).body(customerService.transferFunds(request));
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
