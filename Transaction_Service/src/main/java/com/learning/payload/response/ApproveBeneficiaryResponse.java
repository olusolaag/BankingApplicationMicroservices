package com.learning.payload.response;
//Use Case GET /api/staff/beneficiary
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveBeneficiaryResponse {
	private long fromCustomer;
	private long beneficiaryAccount;
	private LocalDate beneficiaryAddedDate;
	private String approved ="Yes";
}
