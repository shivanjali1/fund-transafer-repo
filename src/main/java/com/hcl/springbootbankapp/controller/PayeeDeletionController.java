package com.hcl.springbootbankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;
import com.hcl.springbootbankapp.service.PayeeDeletionService;
import com.hcl.springbootbankapp.serviceimpl.PayeeDeletionServiceImpl;


@RestController
@RequestMapping("/payee/delete")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class PayeeDeletionController {


	@Autowired
	PayeeDeletionService payeeDeletionService;
	
	
	@DeleteMapping("")
	public ResponseEntity<Object> deletePayee(@RequestParam String customerId,@RequestParam String payeeId) throws ApplicationException{
		
		if (StringUtils.isEmpty(payeeId) || StringUtils.isEmpty(customerId)) {
			return new ResponseEntity<>("Please enter valid customer id and payee id..", HttpStatus.BAD_REQUEST);
		}
		
		else {
			ResponseDTO response = new ResponseDTO();
		   response= payeeDeletionService.deletePayee(customerId, payeeId);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		
	}
	
	@PutMapping("/validate")
	public ResponseEntity<Object> validatePayeeDeletion(@RequestBody ValidateOTP validateOTP) throws ApplicationException{
		return new ResponseEntity<>(payeeDeletionService.validateOtp(validateOTP), HttpStatus.NO_CONTENT);
	}
}
