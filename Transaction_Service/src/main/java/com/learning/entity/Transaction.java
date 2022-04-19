package com.learning.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
	private LocalDateTime dateTime;
	private String reference;
	private double amount;
	//private TransactionType type;
	private long fromAccountNum;
	private long toAccountNum;

	@NotNull
	@JsonIgnore
	private Account fromAccount;
	
	@NotNull
	@JsonIgnore
	private Account toAccount;
}
