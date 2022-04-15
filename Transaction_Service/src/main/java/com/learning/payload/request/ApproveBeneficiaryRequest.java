package com.learning.payload.request;
//Use Case PUT /api/staff/beneficiary
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveBeneficiaryRequest {
	private long beneficiaryId;
	private long beneficiaryAccount;
	@NotNull
	private LocalDate beneficiaryAddedDate;
	private String approved="Yes";
}
