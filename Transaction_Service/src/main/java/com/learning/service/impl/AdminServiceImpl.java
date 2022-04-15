package com.learning.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Role;
import com.learning.entity.Staff;
import com.learning.enums.RoleName;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.CreateStaffRequest;
import com.learning.payload.request.SetEnabledRequest;
import com.learning.repo.RoleRepo;
import com.learning.repo.StaffRepo;
import com.learning.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	StaffRepo staffRepo;
	@Autowired
	RoleRepo roleRepo;

	@Override
	public String createStaff(CreateStaffRequest request) {
		// Create a new staff and set attributes by request.
		Staff staff = new Staff();
		staff.setFullname(request.getStaffFullName());
		staff.setPassword(request.getStaffPassword());
		staff.setUsername(request.getStaffUserName());
		staff.setRoles(new HashSet<Role>());
		staff.getRoles().add(roleRepo.findByRoleName(RoleName.ROLE_STAFF)
				.orElseThrow(() -> new NoDataFoundException("Staff Role Not Found")));
		staffRepo.save(staff);
		return "Staff created";
	}

	@Override
	public List<Staff> getAllStaff() {
		List<Staff> staff = staffRepo.findAll();
		return staff;
	}

	@Override
	public String setEnabled(SetEnabledRequest request) {
		Staff staff = staffRepo.findById(request.getId())
				.orElseThrow(() -> new NoDataFoundException("Staff not found"));
		staff.setStatus(request.getStatus());
		staffRepo.save(staff);
		return "Staff status updated";
	}

}
