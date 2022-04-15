package com.learning.payload.response;
//Use Case GET /api/customer/:customerID/beneficiary
import com.learning.enums.IsActive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarySummary {
	private Long beneficiaryId;
	private Long beneficiaryAccountNo;
	private String beneficiaryName;
	private IsActive active;
}
