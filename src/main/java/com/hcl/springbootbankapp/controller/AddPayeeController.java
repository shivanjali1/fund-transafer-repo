package com.hcl.springbootbankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;
import com.hcl.springbootbankapp.service.AddPayeeService;


@RestController
@RequestMapping(value = "/addPayee")
public class AddPayeeController {
	
	private static final String ERROR_MSG = "Mandetory Element missing : ";

	
	@Autowired
	AddPayeeService addPayeeService;
	
	/**
	 * this method is used to make a request to add a new payee for the customer
	 * @param loggedUserId takes logged user id
	 * @param payeeUserId takes payee user id as input
	 * @return returns response object with response data, message,http status
	 * @throws ApplicationException throws custom exception if error occurred
	 */
	@PostMapping
	public ResponseEntity<ResponseDTO> addPayee(@RequestParam String loggedUserId, @RequestParam String payeeUserId) throws ApplicationException{
		validateRequest(loggedUserId, payeeUserId);
		ResponseDTO returneResponse = addPayeeService.addPayee(loggedUserId,payeeUserId);
		if("Initiation Failed".equals(returneResponse.getMessage())) {
			throw new ApplicationException("Adding Benificiary Failed");
		}
		return new ResponseEntity<>(returneResponse, HttpStatus.OK);
	}
	
	@PutMapping("/validate")
	public ResponseEntity<ResponseDTO> validatePayeeDeletion(@RequestBody ValidateOTP validateOTP) throws ApplicationException{
		
		ResponseDTO returnResponse = addPayeeService.validateOtp(validateOTP);
		return new ResponseEntity<>(returnResponse, HttpStatus.NO_CONTENT);
	}
	
	
	private void validateRequest(String loggedUserId, String payeeUserId) throws ApplicationException {
		
		if (StringUtils.isEmpty(loggedUserId)) {
			throw new ApplicationException(ERROR_MSG + "loggedUserId");
		}
		if (StringUtils.isEmpty(payeeUserId)) {
			throw new ApplicationException(ERROR_MSG + "payeeUserId Id");
		}
		if (payeeUserId.equals(loggedUserId)) {
			throw new ApplicationException(ERROR_MSG + "You Cannot add yourself as payee");
		}
	}
	
}

