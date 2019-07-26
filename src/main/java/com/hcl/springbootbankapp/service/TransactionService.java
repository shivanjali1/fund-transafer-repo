package com.hcl.springbootbankapp.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.entity.Account;
import com.hcl.springbootbankapp.entity.TransactionHistory;
import com.hcl.springbootbankapp.model.FundTransferRequest;
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
	public String fundTransfer(FundTransferRequest fundTransferRequest) throws Exception {
		Account account = accountRepository.findByUserName(fundTransferRequest.getUsername());
		Long ownAccountNo = account.getAccountNo();
		Long payeeAccountNo = fundTransferRequest.getPayeeAccountNo();
		Double transferamt = fundTransferRequest.getTrsandferAmt();
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
			accountRepository.save(ownAccount);

			payeeAccount.setAccountBalance(payeeAccount.getAccountBalance() + transferamt);
			accountRepository.save(payeeAccount);

			TransactionHistory ownTransactionHistory = new TransactionHistory();
			ownTransactionHistory.setAccountNo(ownAccountNo);
			ownTransactionHistory.setFinalBalance(ownAccount.getAccountBalance() - transferamt);
			ownTransactionHistory.setTransactionTime(LocalDateTime.now());
			ownTransactionHistory.setTransactionType("Debit");
			ownTransactionHistory.setTrsansactionAmt(transferamt);

			transactionHistoryRepository.save(ownTransactionHistory);

			TransactionHistory payeeTransactionHistory = new TransactionHistory();
			payeeTransactionHistory.setAccountNo(payeeAccountNo);
			payeeTransactionHistory.setFinalBalance(payeeAccount.getAccountBalance() + transferamt);
			payeeTransactionHistory.setTransactionTime(LocalDateTime.now());
			payeeTransactionHistory.setTransactionType("Credit");
			payeeTransactionHistory.setTrsansactionAmt(transferamt);

			transactionHistoryRepository.save(payeeTransactionHistory);

		}

		return "Fund transfered sucessfully";
	}
}
