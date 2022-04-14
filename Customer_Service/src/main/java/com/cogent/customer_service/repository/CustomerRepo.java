package com.cogent.customer_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cogent.customer_service.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByUsername(String username);
}
