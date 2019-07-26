package com.hcl.springbootbankapp.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.entity.Payee;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;
import com.hcl.springbootbankapp.repository.PayeeRepository;
import com.hcl.springbootbankapp.repository.UserRepository;
import com.hcl.springbootbankapp.service.AddPayeeService;
import com.hcl.springbootbankapp.service.OTPService;
import com.hcl.springbootbankapp.util.PayeeStatusUtil;
@Service
public class AddPayeeServiceImpl implements AddPayeeService {


	private static final String BODYINIT = " Dear Custemer, Your OTP for the Adding Payee ";
	private static final String BODYMIDDLE = "with id ";
	private static final String BODYFINAL = " and name  ";
	private static final String BODYFINALAGAIN = " is: ";
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PayeeRepository payeeRepository;
	
	@Autowired
	OTPService oTPService;
	
	@Autowired
	OTPServiceImpl otpServiceImpl;
	
	/**
	 *@loggedUserId takes logged user id from controller
	 *@payeeUserId takes payee user id from controller
	 *returns returns payee addition request
	 */
	
	
	@Override
	public ResponseDTO addPayee(String loggedUserId, String payeeUserId) {

		ResponseDTO responseObject = new ResponseDTO();

		Optional<User> payeeUser = userRepository.findByCustomerId(payeeUserId);
		Optional<User> loggedUser = userRepository.findByCustomerId(loggedUserId);
		if(payeeUser.isPresent() && loggedUser.isPresent()) {
			Payee payee = new Payee();
			payee.setStatus(PayeeStatusUtil.ADD_PENDING);
			payee.setPayeeId((payeeUser.get()).getId());
			payee.setCustId((loggedUser.get()).getId());
			payee = payeeRepository.save(payee);
			
			oTPService.generateOTP((loggedUser.get()).getCustomerId(), payee.getId(), 
					(loggedUser.get()).getEmail(),BODYINIT+BODYMIDDLE+(payeeUser.get()).getId()+BODYFINAL+(payeeUser.get()).getFirstName()+BODYFINALAGAIN);
			
			responseObject.setHttpStatus(HttpStatus.OK);
			responseObject.setData(payeeUser);
			responseObject.setMessage("Transaction Initiated");
			
			return responseObject;
		}
		

		responseObject.setHttpStatus(HttpStatus.BAD_REQUEST);
		responseObject.setData(null);
		responseObject.setMessage("Initiation Failed");
		
		return responseObject;
		
	}
	
	@Override
	public ResponseDTO validateOtp(ValidateOTP validateOTP) throws ApplicationException {
		
		if(otpServiceImpl.validate(validateOTP.getCustomerId(), validateOTP.getReferenceId(), validateOTP.getOtp())) {
			
			Payee payee = payeeRepository.getPayeeByRefId(validateOTP.getReferenceId());
			payee.setStatus(PayeeStatusUtil.ADD_COMPLETED);
			payeeRepository.save(payee);
			ResponseDTO responseObject = new ResponseDTO();
			responseObject.setHttpStatus(HttpStatus.NO_CONTENT);
			responseObject.setMessage("OTP validated for adding payee");
			return responseObject;
		}
		throw new ApplicationException("OTP is not valid for adding payee ");
	}

}
