package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.payload.request.ApproveAccountRequest;
import com.learning.payload.request.ApproveBeneficiaryRequest;
import com.learning.payload.request.SetEnabledRequest;
import com.learning.payload.request.TransferRequestStaff;
import com.learning.service.StaffService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/staff")
public class StaffController {
	@Autowired
	StaffService staffService;

	@GetMapping("/account/{accountNo}")
	public ResponseEntity<?> getAccountDetails(@PathVariable("accountNo") Long accountNo) {
		return ResponseEntity.ok(staffService.getAccountDetails(accountNo));
	}

	@GetMapping("/beneficiary")
	public ResponseEntity<?> getUnapprovedBeneficiaries() {
		return ResponseEntity.ok(staffService.getUnapprovedBeneficiaries());
	}

	@PutMapping("/beneficiary")
	public ResponseEntity<?> approveBeneficiary(@RequestBody ApproveBeneficiaryRequest request) {
		return ResponseEntity.ok(staffService.approveBeneficiary(request));
	}

	@GetMapping("/accounts/approve")
	public ResponseEntity<?> getUnapprovedAccounts() {
		return ResponseEntity.ok(staffService.getUnapprovedAccounts());
	}

	@PutMapping("/accounts/approve")
	public ResponseEntity<?> approveAccount(@RequestBody ApproveAccountRequest request) {
		return ResponseEntity.ok(staffService.approveAccount(request));
	}

	@GetMapping("/customer")
	public ResponseEntity<?> listCustomers() {
		return ResponseEntity.ok(staffService.getCustomers());
	}

	@PutMapping("/customer")
	public ResponseEntity<?> setCustomerEnabled(@RequestBody SetEnabledRequest request) {
		return ResponseEntity.ok(staffService.setCustomerEnabled(request));
	}

	@GetMapping("/customer/{customerID}")
	public ResponseEntity<?> getCustomer(@PathVariable("customerID") Long customerId) {
		return ResponseEntity.ok(staffService.getCustomer(customerId));
	}

	@PutMapping("/transfer")
	public ResponseEntity<?> staffTransfer(@RequestBody TransferRequestStaff request) {
		return ResponseEntity.ok(staffService.staffTransferFunds(request));
	}

}
