package com.hcl.springbootbankapp.serviceimpl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.entity.OtpDetails;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.repository.OTPRepository;
import com.hcl.springbootbankapp.service.OTPService;

@Service
public class OTPServiceImpl implements OTPService {

	Random rndmMethod = new Random();
	private static final int OTPLENGTH = 6; 
	private static final String SUB = "OTP for transaction";
	@Autowired
	OTPRepository oTPRepository;
	
	@Autowired
	EmailServiceImpl emailServiceImpl;

	
	@Override
	public OtpDetails generateOTP(String custId, Long tranId, String emailId,String message) {

		OtpDetails otpDetails = oTPRepository.getOtpDetailsByTransIdAndCustId(custId, tranId);
		if(otpDetails != null) {
			oTPRepository.delete(otpDetails);
		}
		String otpCode = getRanOTP(OTPLENGTH);
		OtpDetails otp = new OtpDetails();
		otp.setCustomerId(custId);
		otp.setOtpCode(otpCode);
		otp.setTime(LocalDateTime.now());
		otp.setRefId(tranId);
		oTPRepository.save(otp);
		emailServiceImpl.sendSimpleMessage(SUB,message+otpCode, emailId);
		return otp;
	}

	@Override
	public boolean validate(String custId, Long tranId,String otpToValidate) throws ApplicationException {

		OtpDetails otp = oTPRepository.getOtpDetailsByTransIdAndCustId(custId, tranId);
		if(otp != null) {
			if(otp.getOtpCode().equals(otpToValidate)) {
				oTPRepository.delete(otp);
				return true;
				
			}
		}
		
		return false;
	}

	private String getRanOTP(int len) {
		// Using numeric values
		String numbers = "0123456789";
		// Using random method
		char[] otp = new char[len];
		for (int i = 0; i < len; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			otp[i] = numbers.charAt(rndmMethod.nextInt(numbers.length()));
		}
		return new String(otp);
	}
}
