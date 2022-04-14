package com.cogent.customer_service.payload.response;
//Use Case POST /api/customer/:customerID/account

import java.time.LocalDate;

import com.cogent.customer_service.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationResponse {
	private AccountType accountType;
	private double accountBalance;
	private String approved = "no";
	private Long accountNumber;
	private LocalDate dateOfCreation;
	private Long customerId;
}
