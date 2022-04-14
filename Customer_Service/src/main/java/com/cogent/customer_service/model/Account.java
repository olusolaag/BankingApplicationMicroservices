package com.cogent.customer_service.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transaction;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cogent.customer_service.enums.AccountType;
import com.cogent.customer_service.enums.EnabledStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;
	@NotNull
	private AccountType accountType;

	private double accountBalance;
	@NotBlank
	private String approved;
	@NotNull
	private LocalDate dateOfCreation;
	@NotNull
	private EnabledStatus accountStatus;
	
	/*
	 * @ManyToOne
	 * 
	 * @NotNull
	 * 
	 * @JsonIgnore private Customer customer;
	 */
	@NotNull
	private long customerId;
//	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	
}
