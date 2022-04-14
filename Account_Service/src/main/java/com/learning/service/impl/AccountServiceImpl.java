package com.learning.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Customer;
import com.learning.entity.Transaction;
import com.learning.enums.EnabledStatus;
import com.learning.exception.AccountCreationException;
import com.learning.exception.NoDataFoundException;
import com.learning.facade.CustomerServiceProxy;
import com.learning.payload.request.ApproveAccountRequest;
import com.learning.payload.request.CreateAccountRequest;
import com.learning.payload.request.SetEnabledRequest;
import com.learning.payload.response.AccountApprovalSummary;
import com.learning.payload.response.AccountCreationResponse;
import com.learning.payload.response.AccountDetailsResponse;
import com.learning.payload.response.AccountDetailsStaffResponse;
import com.learning.payload.response.AccountSummaryResponse;
import com.learning.payload.response.ApproveAccountResponse;
import com.learning.payload.response.GetCustomerResponse;
import com.learning.repo.AccountRepo;
import com.learning.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	private CustomerServiceProxy proxy;

	@Override
	public AccountCreationResponse addAccount(long customerID, CreateAccountRequest request) {
		Account newAccount = new Account();
		// Retrieve customer based on URL
		// change to proxy
		GetCustomerResponse currentCustomer = proxy.retrieveCustomer(customerID);
		// Store list of customer's current accounts, so we can tell which one is new,
		// later.
		//Set<Account> oldAccounts = accountRepo.findAllByCustomerId(customerID);
		
		// Set all attributes of new account, based on defaults, and on request.
		newAccount.setAccountBalance(request.getAccountBalance());
		newAccount.setAccountType(request.getAccountType());
		newAccount.setApproved(request.getApproved());
		newAccount.setAccountStatus(EnabledStatus.ENABLED);
		newAccount.setCustomerId(customerID);
		newAccount.setDateOfCreation(LocalDate.now());
		newAccount.setTransactions(new ArrayList<Transaction>());
		newAccount = accountRepo.save(newAccount);
		
		// System.out.println(newAccount.getCustomerId());
//
//		// Add created account to customer.
//		// newAccount = accountRepo.save(newAccount);
//		currentCustomer.getAccounts().add(newAccount);
//		// Save customer to update list of accounts. The returned Customer should have
//		// the
//		// newly created Account, with its generated Account Number.
//		Customer updatedCustomer = customerRepo.save(currentCustomer);
//		Set<Account> newAccounts = updatedCustomer.getAccounts();
//		// Compare the sets to find the newly created Account.
//		newAccounts.removeAll(oldAccounts);
//		// newAccounts should have exactly 1 account in it. If it doesn't, something is
//		// wrong.
//		if (newAccounts.isEmpty() || newAccounts.size() > 1) {
//			throw new AccountCreationException("Account Creation Failed");
//		}
//		// Account createdAccount = ((Account[]) newAccounts.toArray())[0];
//		Account createdAccount = null;
//		for (Account account : newAccounts) {
//			createdAccount = account;
//		}

//		// Populate the response with the details of the new Account.
//		AccountCreationResponse result = new AccountCreationResponse();
//		result.setAccountType(createdAccount.getAccountType());
//		result.setAccountBalance(createdAccount.getAccountBalance());
//		result.setApproved(createdAccount.getApproved());
//		result.setAccountNumber(createdAccount.getAccountNumber());
//		result.setDateOfCreation(createdAccount.getDateOfCreation());
//		result.setCustomerId(createdAccount.getCustomerId());

		// Populate the response with the details of the new Account.
		AccountCreationResponse result = new AccountCreationResponse();
		result.setAccountType(newAccount.getAccountType());
		result.setAccountBalance(newAccount.getAccountBalance());
		result.setApproved(newAccount.getApproved());
		result.setAccountNumber(newAccount.getAccountNumber());
		result.setDateOfCreation(newAccount.getDateOfCreation());
		result.setCustomerId(newAccount.getCustomerId());
		return result;
	}

	@Override
	public ApproveAccountResponse approveAccount(long customerID, long accountNo, ApproveAccountRequest request) {
		// find the current customer
		GetCustomerResponse currentCustomer = proxy.retrieveCustomer(customerID);
		// get all the accounts
		Set<Account> accounts = accountRepo.findAllByCustomerId(customerID);
		ApproveAccountResponse accountResponse = new ApproveAccountResponse();
		// for each loop to iterate the accounts
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNo) {
				account.setApproved(request.getApproved());

				// want the response to the user
				accountResponse.setAccountNumber(accountNo);
				accountResponse.setApproval(request.getApproved());

				return accountResponse;
			}
		}

		// if we couldn't find the account number, we will throw the exception
		throw new NoDataFoundException("Please check Account Number");
	}

	@Override
	public List<AccountSummaryResponse> getCustomerAccounts(long customerID) {
		// Get the customer
		GetCustomerResponse currentCustomer = proxy.retrieveCustomer(customerID);
		// Get the accounts
		Set<Account> accounts = accountRepo.findAllByCustomerId(customerID);
		// create a list for the summaries.
		List<AccountSummaryResponse> results = new ArrayList<>();
		// generate the summaries from the accounts.
		for (Account x : accounts) {
			AccountSummaryResponse summary = new AccountSummaryResponse();
			summary.setAccountNumber(x.getAccountNumber());
			summary.setAccountType(x.getAccountType());
			summary.setBalance(x.getAccountBalance());
			summary.setStatus(x.getAccountStatus());
			results.add(summary);
		}
		return results;
	}

	@Override
	public AccountDetailsResponse getCustomerAccount(long customerID, long accountID) {
		GetCustomerResponse currentCustomer = proxy.retrieveCustomer(customerID);
		Set<Account> accounts = accountRepo.findAllByCustomerId(customerID);
		for (Account x : accounts) {
			if (x.getAccountNumber() == accountID) {
				AccountDetailsResponse response = new AccountDetailsResponse();
				response.setAccountNumber(x.getAccountNumber());
				response.setAccountType(x.getAccountType());
				response.setBalance(x.getAccountBalance());
				response.setStatus(x.getAccountStatus());
				response.setTransactions(x.getTransactions());
				return response;
			}
		}
		throw new NoDataFoundException("Account with ID: " + accountID + " not found");
	}

	@Override
	public String setAccountEnabled(SetEnabledRequest request) {
		// find the current customer
		System.out.println(request);
		System.out.println("Line 1");
		Account currentAccount = accountRepo.findById(request.getId())
				.orElseThrow(() -> new NoDataFoundException("Account Not Found"));
		// get all the accounts
		// for each loop to iterate the accounts
		System.out.println("Line 2");
		currentAccount.setAccountStatus(request.getStatus());
		System.out.println("Line 3");
		// System.out.println(currentAccount);
		System.out.println("Line 4");
		accountRepo.save(currentAccount);
		System.out.println("Done");
		return "Account status updated";
	}
	
	@Override
	public AccountDetailsStaffResponse getAccountDetails(Long accountNo) {
		Account account = accountRepo.findById(accountNo)
				.orElseThrow(() -> new NoDataFoundException("Account not found"));
		AccountDetailsStaffResponse response = new AccountDetailsStaffResponse();
		response.setAccountNumber(account.getAccountNumber());
		response.setBalance(account.getAccountBalance());
		GetCustomerResponse currentCustomer = proxy.retrieveCustomer(account.getCustomerId());
		response.setCustomerName(currentCustomer.getFullName());
		response.setTransactions(account.getTransactions());
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
			GetCustomerResponse currentCustomer = proxy.retrieveCustomer(account.getCustomerId());
			summary.setCustomerName(currentCustomer.getFullName());
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
	
	
}
