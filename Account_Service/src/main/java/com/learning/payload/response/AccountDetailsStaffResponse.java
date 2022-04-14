package com.learning.payload.response;

import java.util.List;

import com.learning.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsStaffResponse {
	private long accountNumber;
	private String customerName;
	private double balance;
	private List<Transaction> transactions;
}
