package com.hcl.springbootbankapp.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.entity.OtpDetails;
import com.hcl.springbootbankapp.entity.Payee;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;
import com.hcl.springbootbankapp.repository.PayeeRepository;
import com.hcl.springbootbankapp.repository.UserRepository;
import com.hcl.springbootbankapp.service.OTPService;
import com.hcl.springbootbankapp.service.PayeeDeletionService;
import com.hcl.springbootbankapp.util.PayeeStatusUtil;

@Service
public class PayeeDeletionServiceImpl implements PayeeDeletionService {
	
	private static final Logger logger = LoggerFactory.getLogger(PayeeDeletionServiceImpl.class);
	private final static String BODYINIT = " Dear Custemer, Your OTP for the transaction is ";
	private final static String BODYFINAL = "";

	@Autowired
	PayeeRepository payeeRepository;
	
	@Autowired
	OTPServiceImpl otpServiceImpl;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OTPService OTPService;
	
	@Override
	public String validateOtp(ValidateOTP validateOTP) throws ApplicationException {
		
		if(otpServiceImpl.validate(validateOTP.getCustomerId(), validateOTP.getReferenceId(), validateOTP.getOtp())) {
			payeeRepository.updatePayeeStatus(validateOTP.getReferenceId(), PayeeStatusUtil.DELETE_COMPLETED);
			return "OTP validated for deleting payee";
		}
		throw new ApplicationException("OTP is not valid for deleting payee ");
	}
	
	
	public ResponseDTO deletePayee(String customerId, String payeeId) throws ApplicationException {
		logger.info("Delete request intitiated");

		ResponseDTO response = new ResponseDTO();
		User savedUser = null;
		User savedPayee = null;
		OtpDetails otpDetails=null;
		Payee payee= null;

		Optional<User> findByCustomerId = userRepository.findByCustomerId(customerId);
		Optional<User> findByPayeeId = userRepository.findByCustomerId(payeeId);

		if (findByCustomerId.isPresent() && findByPayeeId.isPresent()) {
			savedUser = findByCustomerId.get();
			savedPayee = findByPayeeId.get();
			 
			

			payeeRepository.updatePayeeStatus(payeeRepository.getrefIdsByCustIdAndPayeeId(savedUser.getId(), savedPayee.getId()), PayeeStatusUtil.DELETE_PENDING);
	
			OTPService.generateOTP(customerId, savedPayee.getId(), savedUser.getEmail(),BODYINIT);
		
		//otpDetails=payeeRepository.getOtpDetailsByCustIdAndPayeeId(savedUser.getId(), savedPayee.getId());
		
		response.setMessage("Delete transaction request intitiated for" + "" + customerId + "sucessfully.");
		response.setHttpStatus(HttpStatus.OK);
		response.setData("");
		logger.info("Payment transaction intitiated");
		logger.info("" + response);

		return response;

		}
		throw new ApplicationException("Invalid Customerid or PayeeId");
	}

}
