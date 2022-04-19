package com.learning.service;

import com.learning.payload.request.TransferRequest;
import com.learning.payload.request.TransferRequestStaff;


public interface TransactionService {
	String staffTransferFunds(TransferRequestStaff request);
	String transferFunds(TransferRequest request);
}
