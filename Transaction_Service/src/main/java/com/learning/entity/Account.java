package com.learning.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.learning.enums.EnabledStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
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
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	
}
