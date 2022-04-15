package com.learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
	List<Account> findAllByApproved(String approved);
}
