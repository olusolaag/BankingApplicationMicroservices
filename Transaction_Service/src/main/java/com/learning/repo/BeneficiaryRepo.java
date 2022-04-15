package com.learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Beneficiary;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Long> {
	List<Beneficiary> findAllByApproved(String approved);
}
