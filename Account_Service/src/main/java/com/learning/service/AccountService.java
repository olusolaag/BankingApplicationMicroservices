package com.learning.service;

import java.util.List;

import com.learning.payload.request.ApproveAccountRequest;
import com.learning.payload.request.CreateAccountRequest;
import com.learning.payload.request.SetEnabledRequest;
import com.learning.payload.response.AccountApprovalSummary;
import com.learning.payload.response.AccountCreationResponse;
import com.learning.payload.response.AccountDetailsResponse;
import com.learning.payload.response.AccountDetailsStaffResponse;
import com.learning.payload.response.AccountSummaryResponse;
import com.learning.payload.response.ApproveAccountResponse;

public interface AccountService {
	public AccountCreationResponse addAccount(long customerID, CreateAccountRequest request);
	// To approve the account which is create by customer by Input payload
	public ApproveAccountResponse approveAccount(long customerID, long accountNo, ApproveAccountRequest request);
	public List<AccountSummaryResponse> getCustomerAccounts(long customerID);
	public AccountDetailsResponse getCustomerAccount(long customerID, long accountID);
	String setAccountEnabled(SetEnabledRequest request);
	AccountDetailsStaffResponse getAccountDetails(Long accountNo);
	List<AccountApprovalSummary> getUnapprovedAccounts();
	// Approve the list of accounts added by the customer, so that that the transaction can be done 
	ApproveAccountResponse approveAccount(ApproveAccountRequest request);
}