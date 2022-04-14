package com.learning.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Account;
@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
	List<Account> findAllByApproved(String approved);
	
	Set<Account> findAllByCustomerId(long customerID);
}
