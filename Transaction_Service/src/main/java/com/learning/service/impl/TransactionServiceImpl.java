package com.learning.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.StaffTransaction;
import com.learning.entity.Transaction;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.TransferRequest;
import com.learning.payload.request.TransferRequestStaff;
import com.learning.repo.TransactionRepo;
import com.learning.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepo transactionRepo;
	
	@Override
	public String staffTransferFunds(TransferRequestStaff request) {
		StaffTransaction transaction = new StaffTransaction();
		// First, retrieve the appropriate accounts.
		Account fromAccount = transactionRepo.findById(request.getFromAccount())
				.orElseThrow(() -> new NoDataFoundException("From Account Not Valid"));
		transaction.setFromAccount(fromAccount);
		transaction.setFromAccountNum(fromAccount.getAccountNumber());
		Account toAccount = transactionRepo.findById(request.getToAccount())
				.orElseThrow(() -> new NoDataFoundException("To Account Not Valid"));
		transaction.setToAccountNum(toAccount.getAccountNumber());
		transaction.setToAccount(toAccount);
		// if we want to do validity checking, here would be good. From Account should
		// be part of request.customer's Account Set. To Account should be part of
		// Customer's beneficiary list. next, input details about the transaction.
		transaction.setAmount(request.getAmount());
		transaction.setDateTime(LocalDateTime.now());
		transaction.setReference(request.getReason());
		transaction.setByStaff(request.getByStaff());
		// Add the transaction to the affected accounts.
		fromAccount.getTransactions().add(transaction);
		toAccount.getTransactions().add(transaction);
		// Temp variable stores balance and calculates new balance.
		double temp = fromAccount.getAccountBalance();
		// subtract amount from fromAccount's balance.
		temp -= transaction.getAmount();
		// Might check to ensure temp is still positive, throw NSF if not?
		fromAccount.setAccountBalance(temp);

		temp = toAccount.getAccountBalance();
		// Add amount to toAccount's balance.
		temp += transaction.getAmount();
		toAccount.setAccountBalance(temp);
		// Save the affected accounts.
		transactionRepo.save(fromAccount);
		transactionRepo.save(toAccount);
		return "Transfer Complete";
	}

	@Override
	public String transferFunds(TransferRequest request) {
		Transaction transaction = new Transaction();
		// First, retrieve the appropriate accounts.
		Account fromAccount = transactionRepo.findById(request.getFromAccount())
				.orElseThrow(() -> new NoDataFoundException("From Account Not Valid"));
		transaction.setFromAccount(fromAccount);
		Account toAccount = transactionRepo.findById(request.getToAccount())
				.orElseThrow(() -> new NoDataFoundException("To Account Not Valid"));
		transaction.setToAccount(toAccount);
		// if we want to do validity checking, here would be good. From Account should
		// be part of request.customer's Account Set. To Account should be part of
		// Customer's beneficiary list. next, input details about the transaction.
		transaction.setAmount(request.getAmount());
		transaction.setDateTime(LocalDateTime.now());
		transaction.setReference(request.getReason());
		transaction.setFromAccountNum(request.getFromAccount());
		transaction.setToAccountNum(request.getToAccount());
		// Add the transaction to the affected accounts.
		fromAccount.getTransactions().add(transaction);
		toAccount.getTransactions().add(transaction);
		// Temp variable stores balance and calculates new balance.
		double temp = fromAccount.getAccountBalance();
		// subtract amount from fromAccount's balance.
		temp -= transaction.getAmount();
		// Might check to ensure temp is still positive, throw NSF if not?
		fromAccount.setAccountBalance(temp);

		temp = toAccount.getAccountBalance();
		// Add amount to toAccount's balance.
		temp += transaction.getAmount();
		toAccount.setAccountBalance(temp);
		// Save the affected accounts.
		transactionRepo.save(fromAccount);
		transactionRepo.save(toAccount);
		return "Transfer complete";
	}

}
