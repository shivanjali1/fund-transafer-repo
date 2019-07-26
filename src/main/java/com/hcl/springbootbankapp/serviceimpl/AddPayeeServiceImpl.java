package com.hcl.springbootbankapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.entity.Payee;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.repository.PayeeRepository;
import com.hcl.springbootbankapp.repository.UserRepository;
import com.hcl.springbootbankapp.service.AddPayeeService;
import com.hcl.springbootbankapp.service.OTPService;
import com.hcl.springbootbankapp.util.PayeeStatusUtil;
@Service
public class AddPayeeServiceImpl implements AddPayeeService {

	private final static String bodyInit = " Dear Custemer, Your OTP for the Adding Payee is ";
	private final static String bodyFinal = "";
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PayeeRepository payeeRepository;
	@Autowired
	OTPService oTPService;
	
	@Override
	public ResponseDTO addPayee(String loggedUserId, String payeeUserId) {
		// TODO Auto-generated method stub
		
		User payeeUser = userRepository.findByCustomerId(payeeUserId);
		User loggedUser = userRepository.findByCustomerId(loggedUserId);
		
		Payee payee = new Payee();
		payee.setStatus(PayeeStatusUtil.ADD_PENDING);
		payee.setPayeeId(payeeUser.getId());
		payee.setCustId(loggedUser.getId());
		payee = payeeRepository.save(payee);
		
		oTPService.generateOTP(loggedUser.getCustomerId(), payee.getId(), loggedUser.getEmail(),bodyInit);
		
		ResponseDTO responseObject = new ResponseDTO();
		responseObject.setHttpStatus(HttpStatus.OK);
		responseObject.setData(payeeUser);
		responseObject.setMessage("Transaction Initiated");
		
		
		return responseObject;
	}

}
