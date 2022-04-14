package com.learning.payload.response;
//Use Case GET /api/customer/:customerID/account
import com.learning.enums.EnabledStatus;
import com.learning.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSummaryResponse {
	private long accountNumber;
	private AccountType accountType;
	private double balance;
	private EnabledStatus status;
}
