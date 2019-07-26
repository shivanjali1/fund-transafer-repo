package com.hcl.springbootbankapp.service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;

public interface AddPayeeService {

	public ResponseDTO addPayee(String loggedUserId, String payeeUserId);
	
	public ResponseDTO validateOtp(ValidateOTP validateOTP) throws ApplicationException;

}
