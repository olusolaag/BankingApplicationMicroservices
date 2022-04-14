package com.cogent.customer_service.payload.request;
// Use Case POST /api/customer/:customerID/account
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cogent.customer_service.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateAccountRequest {
	@NotNull
	private AccountType accountType;
	
	private double accountBalance;
	@NotBlank
	private String approved = "no";
}
