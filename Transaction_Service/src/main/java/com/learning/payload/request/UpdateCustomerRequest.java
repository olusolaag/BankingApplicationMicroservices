package com.learning.payload.request;
//Use Case PUT /api/customer/:customerID

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {
	private String fullname;
	private String phone;
	private String pan;
	private String aadhar;
	private String secretQuestion;
	private String secretAnswer;
	private String panImage;
	private String aadharImage;
}
