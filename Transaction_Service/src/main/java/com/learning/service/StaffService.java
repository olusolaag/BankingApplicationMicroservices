package com.learning.service;

import java.util.List;

import com.learning.entity.Beneficiary;
import com.learning.payload.request.ApproveAccountRequest;
import com.learning.payload.request.ApproveBeneficiaryRequest;
import com.learning.payload.request.SetEnabledRequest;
import com.learning.payload.request.TransferRequestStaff;
import com.learning.payload.response.AccountApprovalSummary;
import com.learning.payload.response.AccountDetailsStaffResponse;
import com.learning.payload.response.ApproveAccountResponse;
import com.learning.payload.response.ApproveBeneficiaryResponse;
import com.learning.payload.response.CustomerSummary;

public interface StaffService {
	AccountDetailsStaffResponse getAccountDetails(Long accountNo);

	List<Beneficiary> getUnapprovedBeneficiaries();

	ApproveBeneficiaryResponse approveBeneficiary(ApproveBeneficiaryRequest request);

	List<AccountApprovalSummary> getUnapprovedAccounts();

	ApproveAccountResponse approveAccount(ApproveAccountRequest request);

	List<CustomerSummary> getCustomers();

	String setCustomerEnabled(SetEnabledRequest request);

	CustomerSummary getCustomer(Long customerId);

	String staffTransferFunds(TransferRequestStaff request);
}
