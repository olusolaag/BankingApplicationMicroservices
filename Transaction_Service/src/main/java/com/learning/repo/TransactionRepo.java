package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Account;

@Repository
public interface TransactionRepo extends JpaRepository<Account, Long>{

}
