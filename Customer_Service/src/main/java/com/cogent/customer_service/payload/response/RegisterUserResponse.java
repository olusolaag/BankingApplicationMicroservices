package com.cogent.customer_service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {
	private long id;
	private String username;
	private String fullname;
	private String password;
}
