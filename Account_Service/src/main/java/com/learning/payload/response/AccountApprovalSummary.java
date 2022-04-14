package com.learning.payload.response;
//Use Case GET /api/staff/accounts/approve
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountApprovalSummary {
	private String accType;
	private String customerName;
	private long accNo;
	private LocalDate dateCreated;
	private String approved = "no";
}
