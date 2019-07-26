
package com.hcl.springbootbankapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.DTO.FundTransferRequest;
import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.entity.Account;
import com.hcl.springbootbankapp.entity.TransactionHistory;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.repository.AccountRepository;
import com.hcl.springbootbankapp.repository.TransactionHistoryRepository;
import com.hcl.springbootbankapp.repository.UserRepository;

@Service
public class TransactionService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;

	/*
	 * This method is used to get all payees
	 * 
	 * @param accountNO gets account Number
	 * 
	 * @return return list of all payees
	 */
	public List<Account> getPayees(Long pAccountNo) {
		return accountRepository.findByAccountNoNotIn(pAccountNo);
	}

	/*
	 * This method is used for fund transfer
	 * @param fundTransferRequest gets own login id, payee account number and
	 * transfer amount
	 * @returns fund transfer status
	 */
	@Transactional
	public ResponseDTO fundTransfer(FundTransferRequest fundTransferRequest) throws ApplicationException {
		
		ResponseDTO responseDTO = new ResponseDTO();
		Optional<User> optionalCustomerId = userRepository.findByCustomerId(fundTransferRequest.getCustomerId());
		User customerId;
		if(optionalCustomerId.isPresent()) {
			customerId = optionalCustomerId.get();
		}else {
			throw new ApplicationException("Incorrect customer id");
		}
		
		Optional<User> optionalPayeeCustomerId = userRepository.findByCustomerId(fundTransferRequest.getPayeeCustomerId());
		User payeecustomerId;
		if(optionalPayeeCustomerId.isPresent()) {
			payeecustomerId = optionalPayeeCustomerId.get();
		}else {
			throw new ApplicationException("Incorrect payee customer id");
		}
		
		Account account = accountRepository.findByUserId(customerId.getId());
		Account payeeaccount = accountRepository.findByUserId(payeecustomerId.getId());
		
		Long ownAccountNo = account.getAccountNo();
		Long payeeAccountNo = payeeaccount.getAccountNo();
		
		Double transferamt = fundTransferRequest.getTransferAmount();
		List<Account> payees = accountRepository.findByAccountNoNotIn(ownAccountNo);

		boolean isValidPayee = false;
		for (Account accountlist : payees) {
			if (!accountlist.getAccountNo().equals(ownAccountNo)) {
				isValidPayee = true;
			}
		}

		if (isValidPayee) {
			Account ownAccount = accountRepository.findByAccountNo(ownAccountNo);
			Account payeeAccount = accountRepository.findByAccountNo(payeeAccountNo);

			ownAccount.setAccountBalance(ownAccount.getAccountBalance() - transferamt);
			Account saveOwnAccount = accountRepository.save(ownAccount);

			payeeAccount.setAccountBalance(payeeAccount.getAccountBalance() + transferamt);
			Account savePayeeAccount = accountRepository.save(payeeAccount);

			TransactionHistory ownTransactionHistory = new TransactionHistory();
			ownTransactionHistory.setAccountNo(ownAccountNo);
			ownTransactionHistory.setFinalBalance(saveOwnAccount.getAccountBalance());
			ownTransactionHistory.setTransactionTime(LocalDateTime.now());
			ownTransactionHistory.setTransactionType("Debit");
			ownTransactionHistory.setTrsansactionAmt(transferamt);

			transactionHistoryRepository.save(ownTransactionHistory);

			TransactionHistory payeeTransactionHistory = new TransactionHistory();
			payeeTransactionHistory.setAccountNo(payeeAccountNo);
			payeeTransactionHistory.setFinalBalance(savePayeeAccount.getAccountBalance());
			payeeTransactionHistory.setTransactionTime(LocalDateTime.now());
			payeeTransactionHistory.setTransactionType("Credit");
			payeeTransactionHistory.setTrsansactionAmt(transferamt);

			transactionHistoryRepository.save(payeeTransactionHistory);
			responseDTO.setHttpStatus(HttpStatus.OK);
			responseDTO.setMessage("Fund transfered sucessfully");
		}else {
			throw new ApplicationException("Invalid payee");
		}

		return responseDTO;
	}
}

