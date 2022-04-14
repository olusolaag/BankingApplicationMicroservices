package com.cogent.customer_service.payload.response;
import com.cogent.customer_service.enums.AccountType;
import com.cogent.customer_service.enums.EnabledStatus;

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
