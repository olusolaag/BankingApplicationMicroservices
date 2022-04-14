package com.cogent.customer_service.service;

import com.cogent.customer_service.payload.request.RegisterRequest;
import com.cogent.customer_service.payload.request.SecretAnswerRequest;
import com.cogent.customer_service.payload.request.UpdateCustomerRequest;
import com.cogent.customer_service.payload.request.UpdatePasswordRequest;
import com.cogent.customer_service.payload.response.GetCustomerResponse;
import com.cogent.customer_service.payload.response.RegisterUserResponse;
import com.cogent.customer_service.payload.response.UpdateCustomerResponse;


public interface CustomerService {
	public RegisterUserResponse registerCustomer(RegisterRequest request);
	public GetCustomerResponse getCustomer(long customerID);
	public UpdateCustomerResponse updateCustomer(long customerID, UpdateCustomerRequest customer);
	public String getQuestion(String username);
	public String validateAnswer(String username, SecretAnswerRequest answer);
	public String updatePassword(String username, UpdatePasswordRequest request);
}
