package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.enums.EnabledStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Column(unique=true)
	private String username;
	@NotBlank
	private String fullname;
	@NotBlank
	@JsonIgnore
	private String password;

	private String phone;
	
	private String pan;
	
	private String panImage;
	
	private String aadhar;
	
	private String aadharImage;
	
	private String secretQuestion;

	private String secretAnswer;
	
	private LocalDate createdDate;
	
	private EnabledStatus status = EnabledStatus.ENABLED;

//	private Set<Account> accounts = new HashSet<>();
	
//	private Set<Beneficiary> beneficiaries = new HashSet<>();
}
