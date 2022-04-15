package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Role;
import com.learning.enums.RoleName;
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	public Optional<Role> findByRoleName(RoleName roleName);

}
