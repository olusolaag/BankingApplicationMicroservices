package com.learning.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.learning.enums.EnabledStatus;
import com.learning.enums.RoleName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity

public class Staff extends User {

	public Staff(long id, String username, String fullname, String password, Set<Role> roles) {
		super(id, username, fullname,  password, EnabledStatus.ENABLED, roles);
	}
	
	
}
