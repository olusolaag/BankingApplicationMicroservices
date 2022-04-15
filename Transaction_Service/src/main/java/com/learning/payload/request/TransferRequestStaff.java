package com.learning.payload.request;
//Use Case PUT /api/customer/transfer
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestStaff {
	@Min(0)	
	private long fromAccount;
	@Min(0)
	private long toAccount;
	@DecimalMin("0.01")
	private double amount;
	
	private String reason;
	@NotNull
	private String byStaff;
}
