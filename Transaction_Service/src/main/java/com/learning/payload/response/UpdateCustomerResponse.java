package com.learning.payload.response;
//Use Case PUT /api/customer/:customerID
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerResponse {
	private long customerId;
	private String fullname;
	private String phone;
	private String pan;
	private String panImage;
	private String aadhar;
	private String aadharImage;
	private String secretQuestion;
	private String secretAnswer;
}
