
package com.hcl.springbootbankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springbootbankapp.DTO.FundTransferRequest;
import com.hcl.springbootbankapp.entity.Account;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.service.TransactionService;

/*
 * This is TransactionController class used for fund transfer 
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	private static final String ERROR_MSG = "Mandetory Element missing : ";

	/*
	 * This method is used to get all payees
	 * 
	 * @param accountNO gets account Number
	 * 
	 * @return return list of all payees
	 */
	@GetMapping("/payee/{accountNO}")
	public ResponseEntity<Object> getPayee(@PathVariable Long accountNO) {
		List<Account> payees = transactionService.getPayees(accountNO);
		return new ResponseEntity<>(payees, HttpStatus.OK);
	}

	/*
	 * This method is used for fund transfer
	 * 
	 * @param fundTransferRequest gets own login id, payee account number and
	 * transfer amount
	 * 
	 * @returns fund transfer status
	 */
	@PostMapping("/fundTransfer")
	public ResponseEntity<Object> fundTransafer(@RequestBody FundTransferRequest fundTransferRequest) throws ApplicationException {

		validateRequest(fundTransferRequest);
		return new ResponseEntity<>(transactionService.fundTransfer(fundTransferRequest), HttpStatus.OK);

	}

	private void validateRequest(FundTransferRequest lFundTransferRequest) throws ApplicationException {

		if (StringUtils.isEmpty(lFundTransferRequest.getCustomerId())) {
			throw new ApplicationException(ERROR_MSG + "CustomerId");
		}
		if (StringUtils.isEmpty(lFundTransferRequest.getTransferAmount())) {
			throw new ApplicationException(ERROR_MSG + "transfer Amount");
		}
		if (StringUtils.isEmpty(lFundTransferRequest.getPayeeCustomerId())) {
			throw new ApplicationException(ERROR_MSG + "PayeeCustomerId");
		}

	}

}

