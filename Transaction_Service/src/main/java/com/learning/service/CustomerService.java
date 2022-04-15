package com.learning.service;

import java.util.List;

import com.learning.entity.Customer;
import com.learning.payload.request.*;
import com.learning.payload.response.*;

public interface CustomerService {
	public RegisterUserResponse registerCustomer(RegisterRequest request);
	public AccountCreationResponse addAccount(long customerID, CreateAccountRequest request);
	public ApproveAccountResponse approveAccount(long customerID, long accountNo, ApproveAccountRequest request);
	public List<AccountSummaryResponse> getCustomerAccounts(long customerID);
	public GetCustomerResponse getCustomer(long customerID);
	public UpdateCustomerResponse updateCustomer(long customerID, UpdateCustomerRequest customer);
	public AccountDetailsResponse getCustomerAccount(long customerID, long accountID);
	public String addBeneficiary(long customerID, AddBeneficiaryRequest request);
	public List<BeneficiarySummary> getBeneficiaries(long customerID);
	public String deleteBeneficiary(long customerID, long beneficiaryID);
	public String transferFunds(TransferRequest request);
	public String getQuestion(String username);
	public String validateAnswer(String username, SecretAnswerRequest answer);
	public String updatePassword(String username, UpdatePasswordRequest request);
	String setAccountEnabled(SetEnabledRequest request);
	List<BeneficiarySummary> getActiveBeneficiaries(long customerID);
	
}
