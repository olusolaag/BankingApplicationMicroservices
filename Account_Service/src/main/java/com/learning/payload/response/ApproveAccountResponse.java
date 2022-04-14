package com.learning.payload.response;
//Use Case PUT /api/customer/:customerID/account/:accountNo

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApproveAccountResponse {
	private long accountNumber;
	private String approval = "yes";
}
