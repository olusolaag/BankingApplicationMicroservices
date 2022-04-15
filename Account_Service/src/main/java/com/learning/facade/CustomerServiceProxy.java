package com.learning.facade;

//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.payload.response.GetCustomerResponse;

@FeignClient(name ="banking-customer-microservice")
//@RibbonClient(name ="Customer_Service")
public interface CustomerServiceProxy {
	
	@GetMapping("/api/customer/{customerID}")
	public GetCustomerResponse retrieveCustomer(@PathVariable("customerID") Long customerId);
}
