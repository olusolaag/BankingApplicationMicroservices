package com.cogent.customer_service.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cogent.customer_service.exception.NoDataFoundException;
import com.cogent.customer_service.model.Customer;
import com.cogent.customer_service.payload.request.RegisterRequest;
import com.cogent.customer_service.payload.request.SecretAnswerRequest;
import com.cogent.customer_service.payload.request.UpdateCustomerRequest;
import com.cogent.customer_service.payload.request.UpdatePasswordRequest;
import com.cogent.customer_service.payload.response.GetCustomerResponse;
import com.cogent.customer_service.payload.response.RegisterUserResponse;
import com.cogent.customer_service.payload.response.UpdateCustomerResponse;
import com.cogent.customer_service.repository.CustomerRepo;
import com.cogent.customer_service.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;
//	@Autowired
//	AccountRepo accountRepo;
//	@Autowired
//	RoleRepo roleRepo;
//	@Autowired
	PasswordEncoder passwordEncoder;
//	@Autowired
//	StaffRepo staffRepo;
//	@Autowired
//	BeneficiaryRepo beneficiaryRepo;

	@Override
	public RegisterUserResponse registerCustomer(RegisterRequest request) {
		// create a customer from the request
		Customer customer = new Customer();
		customer.setFullname(request.getFullname());
		customer.setUsername(request.getUsername());
		customer.setPassword(passwordEncoder.encode(request.getPassword())); // should encrypt password here.
		// customer creation date is now.
		customer.setCreatedDate(LocalDate.now());
		// save customer to DB
		Customer temp = customerRepo.save(customer);
		// Create response from the DB returned customer.
		RegisterUserResponse response = new RegisterUserResponse();
		response.setFullname(temp.getFullname());
		response.setId(temp.getId());
		response.setPassword(temp.getPassword());// should not be encrypted password.
		response.setUsername(temp.getUsername());
		return response;
	}

	/*@Override
	public AccountCreationResponse addAccount(long customerID, CreateAccountRequest request) {
		Account newAccount = new Account();
		// Retrieve customer based on URL
		Customer currentCustomer = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Customer Not Found"));
		// Store list of customer's current accounts, so we can tell which one is new,
		// later.
		Set<Account> oldAccounts = currentCustomer.getAccounts();
		// Set all attributes of new account, based on defaults, and on request.
		newAccount.setAccountBalance(request.getAccountBalance());
		newAccount.setAccountType(request.getAccountType());
		newAccount.setApproved(request.getApproved());
		newAccount.setAccountStatus(EnabledStatus.ENABLED);
		newAccount.setCustomerId(currentCustomer.getId());
		newAccount.setDateOfCreation(LocalDate.now());
		newAccount.setTransactions(new ArrayList<Transaction>());
		// System.out.println(newAccount.getCustomerId());

		// Add created account to customer.
		// newAccount = accountRepo.save(newAccount);
		currentCustomer.getAccounts().add(newAccount);
		// Save customer to update list of accounts. The returned Customer should have
		// the
		// newly created Account, with its generated Account Number.
		Customer updatedCustomer = customerRepo.save(currentCustomer);
		Set<Account> newAccounts = updatedCustomer.getAccounts();
		// Compare the sets to find the newly created Account.
		newAccounts.removeAll(oldAccounts);
		// newAccounts should have exactly 1 account in it. If it doesn't, something is
		// wrong.
		if (newAccounts.isEmpty() || newAccounts.size() > 1) {
			throw new AccountCreationException("Account Creation Failed");
		}
		// Account createdAccount = ((Account[]) newAccounts.toArray())[0];
		Account createdAccount = null;
		for (Account account : newAccounts) {
			createdAccount = account;
		}

		// Populate the response with the details of the new Account.
		AccountCreationResponse result = new AccountCreationResponse();
		result.setAccountType(createdAccount.getAccountType());
		result.setAccountBalance(createdAccount.getAccountBalance());
		result.setApproved(createdAccount.getApproved());
		result.setAccountNumber(createdAccount.getAccountNumber());
		result.setDateOfCreation(createdAccount.getDateOfCreation());
		result.setCustomerId(createdAccount.getCustomerId());

		return result;
	}*/

	/*@Override
	public ApproveAccountResponse approveAccount(long customerID, long accountNo, ApproveAccountRequest request) {
		// find the current customer
		Customer currentCustomer = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Customer Not Found"));
		// get all the accounts
		Set<Account> accounts = currentCustomer.getAccounts();
		ApproveAccountResponse accountResponse = new ApproveAccountResponse();
		// for each loop to iterate the accounts
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNo) {
				account.setApproved(request.getApproved());

				// want the response to the user
				accountResponse.setAccountNumber(accountNo);
				accountResponse.setApproval(request.getApproved());

				customerRepo.save(currentCustomer);
				return accountResponse;
			}
		}

		// if we couldn't find the account number, we will throw the exception
		throw new NoDataFoundException("Please check Account Number");
	}*/

	/*@Override
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
	}*/

	@Override
	public GetCustomerResponse getCustomer(long customerID) {
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Sorry, Customer with ID:" + customerID + " Not Found"));
		GetCustomerResponse response = new GetCustomerResponse();
		response.setAadhar(customer.getAadhar());
		response.setFullName(customer.getFullname());
		response.setPan(customer.getPan());
		response.setPhone(customer.getPhone());
		response.setUsername(customer.getUsername());
		return response;
	}

	@Override
	public UpdateCustomerResponse updateCustomer(long customerID, UpdateCustomerRequest customer) {
		// Retrieve customer to be updated.
		Customer fromRepo = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Customer ID not found"));
		// Check each field of the request to see if it contains an update.
		// and then apply the update to the retrieved customer
		System.out.println("In updateCustomer");
		System.out.println(customer);
		if (customer.getAadhar() != null && !customer.getAadhar().isBlank())
			fromRepo.setAadhar(customer.getAadhar());
		if (customer.getAadharImage() != null && !customer.getAadharImage().isBlank())
			fromRepo.setAadharImage(customer.getAadharImage());
		if (customer.getFullname() != null && !customer.getFullname().isBlank())
			fromRepo.setFullname(customer.getFullname());
		if (customer.getPan() != null && !customer.getPan().isBlank())
			fromRepo.setPan(customer.getPan());
		if (customer.getPanImage() != null && !customer.getPanImage().isBlank())
			fromRepo.setPanImage(customer.getPanImage());
		if (customer.getPhone() != null && !customer.getPhone().isBlank())
			fromRepo.setPhone(customer.getPhone());
		if (customer.getSecretQuestion() != null && !customer.getSecretQuestion().isBlank())
			fromRepo.setSecretQuestion(customer.getSecretQuestion());
		if (customer.getSecretAnswer() != null && !customer.getSecretAnswer().isBlank())
			fromRepo.setSecretAnswer(customer.getSecretAnswer());
		// Save the customer back to the database
		Customer updated = customerRepo.save(fromRepo);
		// Generate the response payload from the updated customer.
		UpdateCustomerResponse response = new UpdateCustomerResponse();
		response.setCustomerId(customerID);
		response.setFullname(updated.getFullname());
		response.setPhone(updated.getPhone());
		response.setPan(updated.getPan());
		response.setAadhar(updated.getAadhar());
		response.setSecretQuestion(updated.getSecretQuestion());
		response.setSecretAnswer(updated.getSecretAnswer());
		response.setPanImage(updated.getPanImage());
		response.setAadharImage(updated.getAadharImage());
		return response;
	}

	/*@Override
	public AccountDetailsResponse getCustomerAccount(long customerID, long accountID) {
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Sorry, Customer with ID:" + customerID + " Not Found"));
		Set<Account> accounts = customer.getAccounts();
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
	}*/

	/*@Override
	public String addBeneficiary(long customerID, AddBeneficiaryRequest request) {
		// Retrieve the customer
		Customer customer = customerRepo.findById(customerID).orElseThrow(
				() -> new NoDataFoundException("Sorry Beneficiary With " + request.getAccountNumber() + " not added"));
		// Retrieve the set of beneficiaries
		Set<Beneficiary> beneficiaries = customer.getBeneficiaries();
		// Create a new beneficiary to add
		Beneficiary toBeAdded = new Beneficiary();
		toBeAdded.setAccountNo(request.getAccountNumber());
		toBeAdded.setAddedDate(LocalDate.now());
		toBeAdded.setAccountType(request.getAccountType());
		toBeAdded.setIsActive(IsActive.YES);
		toBeAdded.setApproved(request.getApproved());
		toBeAdded.setName(request.getName());
		// toBeAdded.setCustomer(customer);
		// add the beneficiary and save the customer to the repo.
		beneficiaries.add(toBeAdded);
		System.out.println(toBeAdded);
		customerRepo.save(customer);
		return "Beneficiary with " + request.getAccountNumber() + " added";
	}*/

	/*@Override
	public List<BeneficiarySummary> getBeneficiaries(long customerID) {
		// retrieve the customer
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Customer not found"));
		// retrieve the beneficiaries
		Set<Beneficiary> beneficiaries = customer.getBeneficiaries();
		// create the list for summaries
		List<BeneficiarySummary> summaries = new ArrayList<>();
		// create the summaries
		for (Beneficiary beneficiary : beneficiaries) {
			BeneficiarySummary summary = new BeneficiarySummary();
			summary.setActive(beneficiary.getIsActive());
			summary.setBeneficiaryAccountNo(beneficiary.getAccountNo());
			// TODO: Fix name to lookup by account no.
			summary.setBeneficiaryName(beneficiary.getName());
			summary.setBeneficiaryId(beneficiary.getBeneficiaryId());
			summaries.add(summary);

		}
		return summaries;
	}*/
	/*@Override
	public List<BeneficiarySummary> getActiveBeneficiaries(long customerID) {
		// retrieve the customer
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Customer not found"));
		// retrieve the beneficiaries
		Set<Beneficiary> beneficiaries = customer.getBeneficiaries();
		// create the list for summaries
		List<BeneficiarySummary> summaries = new ArrayList<>();
		// create the summaries
		for (Beneficiary beneficiary : beneficiaries) {
			if (beneficiary.getApproved().equals("yes")) {
				BeneficiarySummary summary = new BeneficiarySummary();
				summary.setActive(beneficiary.getIsActive());
				summary.setBeneficiaryAccountNo(beneficiary.getAccountNo());
				// TODO: Fix name to lookup by account no.
				summary.setBeneficiaryName(beneficiary.getName());
				summary.setBeneficiaryId(beneficiary.getBeneficiaryId());
				summaries.add(summary);
			}

		}
		return summaries;
	}*/

	/*@Override
	public String deleteBeneficiary(long customerID, long beneficiaryID) {
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Customer ID not found"));
		Set<Beneficiary> beneficiaries = customer.getBeneficiaries();
		Iterator<Beneficiary> iterator = beneficiaries.iterator();
		while (iterator.hasNext()) {
			Beneficiary element = iterator.next();
			if (element.getBeneficiaryId() == beneficiaryID) {
				iterator.remove();
				customerRepo.save(customer);
				beneficiaryRepo.delete(element);
				return "Beneficiary Deleted Successfully";
			}
		}
		return "Beneficiary Not Delete";
	}*/

	/*@Override
	public String transferFunds(TransferRequest request) {
		Transaction transaction = new Transaction();
		// First, retrieve the appropriate accounts.
		Account fromAccount = accountRepo.findById(request.getFromAccount())
				.orElseThrow(() -> new NoDataFoundException("From Account Not Valid"));
		transaction.setFromAccount(fromAccount);
		Account toAccount = accountRepo.findById(request.getToAccount())
				.orElseThrow(() -> new NoDataFoundException("To Account Not Valid"));
		transaction.setToAccount(toAccount);
		// if we want to do validity checking, here would be good. From Account should
		// be part of request.customer's Account Set. To Account should be part of
		// Customer's beneficiary list. next, input details about the transaction.
		transaction.setAmount(request.getAmount());
		transaction.setDateTime(LocalDateTime.now());
		transaction.setReference(request.getReason());
		transaction.setFromAccountNum(request.getFromAccount());
		transaction.setToAccountNum(request.getToAccount());
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
		return "Transfer complete";
	}*/

	@Override
	public String getQuestion(String username) {
		// Get the customer
		Customer customer = customerRepo.findByUsername(username)
				.orElseThrow(() -> new NoDataFoundException("User not found"));
		// Return the customer's secret question.
		return customer.getSecretQuestion();
	}

	@Override
	public String validateAnswer(String username, SecretAnswerRequest request) {
		// Get the customer
		Customer customer = customerRepo.findByUsername(username)
				.orElseThrow(() -> new NoDataFoundException("User not found"));
		// compare the provided answer with the stored answer
		if (customer.getSecretAnswer().equalsIgnoreCase(request.getAnswer())) {
			// if they match, good.
			return "Details validated";
		}
		// if the don't match.
		return "Sorry your secret details are not matching";
	}

	@Override
	public String updatePassword(String username, UpdatePasswordRequest request) {
		Customer customer = customerRepo.findByUsername(username)
				.orElseThrow(() -> new NoDataFoundException("Sorry password not updated"));
		customer.setPassword(passwordEncoder.encode(request.getNewPassword()));
		customerRepo.save(customer);
		return "new password updated";
	}

	/*@Override
	public List<AccountSummaryResponse> getCustomerAccounts(long customerID) {
		// Get the customer
		Customer currentCustomer = customerRepo.findById(customerID)
				.orElseThrow(() -> new NoDataFoundException("Customer not found"));
		// Get the accounts
		Set<Account> accounts = currentCustomer.getAccounts();
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
	}*/

}
