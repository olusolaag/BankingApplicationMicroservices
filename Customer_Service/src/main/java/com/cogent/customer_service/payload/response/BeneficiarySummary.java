package com.cogent.customer_service.payload.response;
import com.cogent.customer_service.enums.IsActive;

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
