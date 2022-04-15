package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Staff;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {

	Optional<Staff> findByUsername(String username);

}
