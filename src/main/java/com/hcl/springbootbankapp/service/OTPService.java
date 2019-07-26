package com.hcl.springbootbankapp.service;

import com.hcl.springbootbankapp.entity.OtpDetails;
import com.hcl.springbootbankapp.exception.ApplicationException;

public interface OTPService {
	
	OtpDetails generateOTP(String custId,Long tranId,String emailId,String message);
	boolean validate(String custId, Long tranId,String otpToValidate) throws ApplicationException;

}
