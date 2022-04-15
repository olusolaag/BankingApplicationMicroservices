package com.learning.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Staff;
import com.learning.enums.RoleName;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.CreateStaffRequest;
import com.learning.payload.request.RegisterRequest;
import com.learning.payload.request.SignInRequest;
import com.learning.payload.request.SignUpRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.repo.RoleRepo;
import com.learning.repo.StaffRepo;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.AdminService;
import com.learning.service.CustomerService;
import com.learning.service.StaffService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {
	@Autowired
	StaffService staffService;
	@Autowired
	CustomerService customerService;
	@Autowired
	AdminService adminService;
	@Autowired
	StaffRepo staffRepo;
	
	@Autowired
	RoleRepo roleRepo;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;

	private void debug() {
		Staff admin = new Staff();
		admin.setFullname("Administrator Joe");
		admin.setPassword(passwordEncoder.encode("secret@123"));
		admin.setUsername("admin@admin.com");
		admin.getRoles().add(roleRepo.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(()->new NoDataFoundException("Admin Role not found")));
		admin.getRoles().add(roleRepo.findByRoleName(RoleName.ROLE_STAFF).orElseThrow(()->new NoDataFoundException("Staff Role not found")));
		staffRepo.save(admin);
		System.out.println("Admin Created");
	}


	private ResponseEntity<?> signin(@Valid @RequestBody SignInRequest signInRequest) {
		System.out.println(signInRequest.getUsername()+ " : " + signInRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
				signInRequest.getUsername(), signInRequest.getPassword())); 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
		List<String> roles = userDetailsImpl.getAuthorities()
				.stream()
				.map(e->e.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,userDetailsImpl.getId(),
				userDetailsImpl.getUsername(), 
				roles));
	}
	
	@PostMapping("/api/customer/authenticate")
	public ResponseEntity<?> customerSignin(@Valid @RequestBody SignInRequest signInRequest) {
		return signin(signInRequest);
	}
	
	@PostMapping("/api/staff/authenticate")
	public ResponseEntity<?> staffSignin(@Valid @RequestBody SignInRequest signInRequest) {
		return signin(signInRequest);
	}
	@PostMapping("/api/admin/authenticate")
	public ResponseEntity<?> adminSignin(@Valid @RequestBody SignInRequest signInRequest) {
		return signin(signInRequest);
	}
	@PostMapping("/api/customer/register")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody SignUpRequest request) {
		//debug();
		RegisterRequest customer = new RegisterRequest();
		customer.setUsername(request.getUsername());
		customer.setFullname(request.getFullname());
		customer.setPassword(passwordEncoder.encode(request.getPassword()));
		return ResponseEntity.status(201).body(customerService.registerCustomer(customer));
	}
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/admin/staff")
	public ResponseEntity<?> createStaff(@RequestBody CreateStaffRequest request) {
		request.setStaffPassword(passwordEncoder.encode(request.getStaffPassword()));
		return ResponseEntity.ok(adminService.createStaff(request));
	}

}
