package com.learning.payload.response;
//Use Case GET /api/customer/:customerID
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerResponse {
	private String username;
	private String fullName;
	private String phone;
	private String pan;
	private String aadhar;
}
