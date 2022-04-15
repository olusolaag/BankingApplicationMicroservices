package com.learning.payload.request;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//Use Case POST /api/customer/:customerID/beneficiary
import com.learning.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBeneficiaryRequest {
	@Min(1)
	private long accountNumber;
	@NotNull
	private AccountType accountType;
	private String approved = "no";
	private String name = "";
}
