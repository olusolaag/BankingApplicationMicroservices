package com.learning.payload.response;
//Use Case GET /api/staff/customer
//Use Case GET /api/staff/customer/:cusomerID
import java.time.LocalDate;

import com.learning.enums.EnabledStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummary {
	private long customerId;
	private String customerName;
	private EnabledStatus status;
	private LocalDate created;
}
