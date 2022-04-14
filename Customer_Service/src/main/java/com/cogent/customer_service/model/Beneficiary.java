package com.cogent.customer_service.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cogent.customer_service.enums.AccountType;
import com.cogent.customer_service.enums.IsActive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long beneficiaryId;
	private long accountNo;
	private AccountType accountType;
	private String name;
	private IsActive isActive = IsActive.YES;
	private String approved = "no";
	private LocalDate addedDate;
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name="id", nullable = false)
//	private Customer customer;
}
