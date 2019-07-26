package com.hcl.springbootbankapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.model.ValidateOTP;
import com.hcl.springbootbankapp.repository.PayeeRepository;
import com.hcl.springbootbankapp.service.PayeeDeletionService;

@Service
public class PayeeDeletionServiceImpl implements PayeeDeletionService {

	@Autowired
	PayeeRepository payeeRepository;
	
	@Override
	public String validateOtp(ValidateOTP validateOTP) {
		return null;
	}

}
