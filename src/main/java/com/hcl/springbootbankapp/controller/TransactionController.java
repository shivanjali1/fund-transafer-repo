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

import com.hcl.springbootbankapp.entity.Account;
import com.hcl.springbootbankapp.model.FundTransferRequest;
import com.hcl.springbootbankapp.service.TransactionService;

/*
 * This is TransactionController class used for fund transfer 
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	/*
	 * This method is used to get all payees
	 * @param accountNO gets account Number
	 * @return return list of all payees
	 */
	@GetMapping("/payee/{accountNO}")
	public ResponseEntity<?> getPayee(@PathVariable Long accountNO) {
		List<Account> payees = transactionService.getPayees(accountNO);
		return new ResponseEntity<List<Account>>(payees, HttpStatus.OK);
	}

	/*
	 * This method is used for fund transfer
	 * @param fundTransferRequest gets own login id, payee account number and transfer amount
	 * @returns fund transfer status
	 */
	@PostMapping("/fundTransfer")
	public ResponseEntity<String> fundTransafer(@RequestBody FundTransferRequest fundTransferRequest) {
		String status;
		try {
			validateRequest(fundTransferRequest);
		} catch (Exception e) {
			return new ResponseEntity<String>("Invalid Request. Error Message : " + e.getMessage(),HttpStatus.BAD_REQUEST);
		}

		try {
			status = transactionService.fundTransfer(fundTransferRequest);
			System.out.println(status);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error Message : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

	private boolean validateRequest(FundTransferRequest lFundTransferRequest) throws Exception {

		if (StringUtils.isEmpty(lFundTransferRequest.getPayeeAccountNo())) {
			throw new Exception("Mandetory element missing: PayeeAccountNo");
		}
		if (StringUtils.isEmpty(lFundTransferRequest.getTrsandferAmt())) {
			throw new Exception("Mandetory element missing: Trsandfer Amount");
		}
		return true;

	}

}
