package com.learning.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.StaffTransaction;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.ApproveAccountRequest;
import com.learning.payload.request.ApproveBeneficiaryRequest;
import com.learning.payload.request.SetEnabledRequest;
import com.learning.payload.request.TransferRequestStaff;
import com.learning.payload.response.AccountApprovalSummary;
import com.learning.payload.response.AccountDetailsStaffResponse;
import com.learning.payload.response.ApproveAccountResponse;
import com.learning.payload.response.ApproveBeneficiaryResponse;
import com.learning.payload.response.CustomerSummary;
import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.CustomerRepo;
import com.learning.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	BeneficiaryRepo beneficiaryRepo;

	@Override
	public AccountDetailsStaffResponse getAccountDetails(Long accountNo) {
		Account account = accountRepo.findById(accountNo)
				.orElseThrow(() -> new NoDataFoundException("Account not found"));
		AccountDetailsStaffResponse response = new AccountDetailsStaffResponse();
		response.setAccountNumber(account.getAccountNumber());
		response.setBalance(account.getAccountBalance());
		response.setCustomerName(customerRepo.findById(account.getCustomerId())
				.orElseThrow(() -> new NoDataFoundException("Customer not found")).getFullname());
		response.setTransactions(account.getTransactions());
		return response;
	}

	@Override
	public List<Beneficiary> getUnapprovedBeneficiaries() {
		List<Beneficiary> beneficiaries = beneficiaryRepo.findAllByApproved("no");
		return beneficiaries;
	}

	@Override
	public ApproveBeneficiaryResponse approveBeneficiary(ApproveBeneficiaryRequest request) {
//		// Retrieve the customer
//		System.out.println(request.getFromCustomer());
//		Customer customer = customerRepo.findById(request.getFromCustomer())
//				.orElseThrow(() -> new NoDataFoundException("Customer not found"));
//		Beneficiary toBeApproved = null;
//		System.out.println(customer);
//		// Match the beneficiary account no to the customer's beneficiary list.
//		for (Beneficiary beneficiary : customer.getBeneficiaries()) {
//			System.out.println(beneficiary.getAccountNo());
//			if (beneficiary.getAccountNo() == request.getBeneficiaryAccount()) {
//				toBeApproved = beneficiary;
//			}
//		}
		Beneficiary toBeApproved = beneficiaryRepo.findById(request.getBeneficiaryId())
				.orElseThrow(()->new NoDataFoundException("Beneficiary Not Found"));
		// Set approved to desired status.
		toBeApproved.setApproved(request.getApproved());
		// Save changes to customer(and hence to list of beneficiaries)
		beneficiaryRepo.save(toBeApproved);

		// Create and set response payload.
		ApproveBeneficiaryResponse response = new ApproveBeneficiaryResponse();
		response.setApproved(toBeApproved.getApproved());
		response.setBeneficiaryAccount(toBeApproved.getAccountNo());
		response.setBeneficiaryAddedDate(toBeApproved.getAddedDate());
		return response;
	}

	@Override
	public List<AccountApprovalSummary> getUnapprovedAccounts() {
		List<Account> accounts = accountRepo.findAllByApproved("no");
		List<AccountApprovalSummary> summaries = new ArrayList<>();
		for (Account account : accounts) {
			AccountApprovalSummary summary = new AccountApprovalSummary();
			summary.setAccNo(account.getAccountNumber());
			summary.setAccType(account.getAccountType().toString());
			summary.setApproved(account.getApproved());
			summary.setCustomerName(customerRepo.findById(account.getCustomerId())
					.orElseThrow(() -> new NoDataFoundException("Customer not found")).getFullname());
			summary.setDateCreated(account.getDateOfCreation());
			summaries.add(summary);
		}
		return summaries;
	}

	@Override
	public ApproveAccountResponse approveAccount(ApproveAccountRequest request) {
		// Get the account
		Account account = accountRepo.findById(request.getAccountNumber()).orElseThrow(()-> new NoDataFoundException("Account not found"));
		// Set the approval
		account.setApproved(request.getApproved());
		// save to the DB
		accountRepo.save(account);
		ApproveAccountResponse response = new ApproveAccountResponse();
		response.setAccountNumber(account.getAccountNumber());
		response.setApproval(account.getApproved());
		return response;
	}

	@Override
	public List<CustomerSummary> getCustomers() {
		List<Customer> customers = customerRepo.findAll();
		List<CustomerSummary> response = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerSummary summary = new CustomerSummary();
			summary.setCreated(customer.getCreatedDate());
			summary.setCustomerId(customer.getId());
			summary.setCustomerName(customer.getFullname());
			summary.setStatus(customer.getStatus());
			System.out.println(customer.getStatus());
			response.add(summary);
		}
		return response;
	}

	@Override
	public String setCustomerEnabled(SetEnabledRequest request) {
		// Get the customer
		Customer customer = customerRepo.findById(request.getId())
				.orElseThrow(() -> new NoDataFoundException("Customer not found"));
		// Update the status.
		customer.setStatus(request.getStatus());
		// Save the update.
		customerRepo.save(customer);
		return "Customer Status Updated";
	}

	@Override
	public CustomerSummary getCustomer(Long customerId) {
		// Get the customer
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NoDataFoundException("Customer not found"));
		CustomerSummary summary = new CustomerSummary();
		summary.setCreated(customer.getCreatedDate());
		summary.setCustomerId(customer.getId());
		summary.setCustomerName(customer.getFullname());
		summary.setStatus(customer.getStatus());
		return summary;
	}

	@Override
	public String staffTransferFunds(TransferRequestStaff request) {
		StaffTransaction transaction = new StaffTransaction();
		// First, retrieve the appropriate accounts.
		Account fromAccount = accountRepo.findById(request.getFromAccount())
				.orElseThrow(() -> new NoDataFoundException("From Account Not Valid"));
		transaction.setFromAccount(fromAccount);
		transaction.setFromAccountNum(fromAccount.getAccountNumber());
		Account toAccount = accountRepo.findById(request.getToAccount())
				.orElseThrow(() -> new NoDataFoundException("To Account Not Valid"));
		transaction.setToAccountNum(toAccount.getAccountNumber());
		transaction.setToAccount(toAccount);
		// if we want to do validity checking, here would be good. From Account should
		// be part of request.customer's Account Set. To Account should be part of
		// Customer's beneficiary list. next, input details about the transaction.
		transaction.setAmount(request.getAmount());
		transaction.setDateTime(LocalDateTime.now());
		transaction.setReference(request.getReason());
		transaction.setByStaff(request.getByStaff());
		// Add the transaction to the affected accounts.
		fromAccount.getTransactions().add(transaction);
		toAccount.getTransactions().add(transaction);
		// Temp variable stores balance and calculates new balance.
		double temp = fromAccount.getAccountBalance();
		// subtract amount from fromAccount's balance.
		temp -= transaction.getAmount();
		// Might check to ensure temp is still positive, throw NSF if not?
		fromAccount.setAccountBalance(temp);

		temp = toAccount.getAccountBalance();
		// Add amount to toAccount's balance.
		temp += transaction.getAmount();
		toAccount.setAccountBalance(temp);
		// Save the affected accounts.
		accountRepo.save(fromAccount);
		accountRepo.save(toAccount);
		return "Transfer Complete";
	}

}
