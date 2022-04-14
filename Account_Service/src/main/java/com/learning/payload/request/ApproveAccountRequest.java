package com.learning.payload.request;
//Use Case PUT /api/customer/:customerID/account/:accountNo
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApproveAccountRequest {
	@Min(1)
	private long accountNumber;
	@NotBlank
	private String approved = "yes";
}
