package com.cogent.customer_service.payload.response;
//Use Case GET /api/cusomter/:customerID/account/:accountID
//Use Case GET /api/staf/account/:accountNo
import java.util.List;

import com.cogent.customer_service.enums.AccountType;
import com.cogent.customer_service.enums.EnabledStatus;
import com.cogent.customer_service.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsResponse {
	private long accountNumber;
	private AccountType accountType;
	private double balance;
	private EnabledStatus status;
	private List<Transaction> transactions;
}
