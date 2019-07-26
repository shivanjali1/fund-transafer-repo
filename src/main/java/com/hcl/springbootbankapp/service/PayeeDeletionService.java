package com.hcl.springbootbankapp.service;


import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;

public interface PayeeDeletionService {

	public ResponseDTO validateOtp(ValidateOTP validateOTP) throws ApplicationException;
	public ResponseDTO deletePayee(String customerId, String payeeId) throws ApplicationException;
}
