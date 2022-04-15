package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.payload.request.SetEnabledRequest;
import com.learning.service.AdminService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@GetMapping("/staff")
	public ResponseEntity<?> listStaff() {
		return ResponseEntity.ok(adminService.getAllStaff());
	}
	
	@PutMapping("/staff")
	public ResponseEntity<?> setStaffEnabled(@RequestBody SetEnabledRequest request) {
		return ResponseEntity.ok(adminService.setEnabled(request));
	}
}
