package com.learning.service;

import java.util.List;

import com.learning.entity.Staff;
import com.learning.payload.request.CreateStaffRequest;
import com.learning.payload.request.SetEnabledRequest;

public interface AdminService {
	String createStaff(CreateStaffRequest request);
	List<Staff> getAllStaff();
	String setEnabled(SetEnabledRequest request);
}
