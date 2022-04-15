package com.learning.payload.response;
//Use Case GET /api/cusomter/:customerID/account/:accountID
//Use Case GET /api/staf/account/:accountNo
import java.util.List;

import com.learning.entity.Transaction;
import com.learning.enums.EnabledStatus;
import com.learning.enums.AccountType;

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
