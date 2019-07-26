package com.hcl.springbootbankapp.service;


import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;

public interface PayeeDeletionService {

	public String validateOtp(ValidateOTP validateOTP) throws ApplicationException;
}
