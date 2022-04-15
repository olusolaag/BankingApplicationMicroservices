package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByUsername(String username);
}
