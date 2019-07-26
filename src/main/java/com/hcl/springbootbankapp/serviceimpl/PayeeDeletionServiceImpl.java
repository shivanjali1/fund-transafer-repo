package com.hcl.springbootbankapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;
import com.hcl.springbootbankapp.repository.PayeeRepository;
import com.hcl.springbootbankapp.service.PayeeDeletionService;

@Service
public class PayeeDeletionServiceImpl implements PayeeDeletionService {

	@Autowired
	PayeeRepository payeeRepository;
	
	@Autowired
	OTPServiceImpl otpServiceImpl;
	
	@Override
	public String validateOtp(ValidateOTP validateOTP) throws ApplicationException {
		
		if(otpServiceImpl.validate(validateOTP.getCustomerId(), validateOTP.getReferenceId(), validateOTP.getOtp())) {
			payeeRepository.deletePayee(validateOTP.getReferenceId(), status);
			return "OTP validated for deleting payee ";
		}
		//return "OTP is not valid for deleting payee ";
		throw new ApplicationException("OTP is not valid for deleting payee ");
	}

}
