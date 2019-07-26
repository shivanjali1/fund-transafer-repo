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
import com.hcl.springbootbankapp.util.PayeeStatusUtil;
@Service
public class AddPayeeServiceImpl implements AddPayeeService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PayeeRepository payeeRepository;
	
	@Override
	public ResponseDTO addPayee(String loggedUserId, String payeeUserId) {
		// TODO Auto-generated method stub
		
		User payeeUser = userRepository.findByCustomerId(payeeUserId);
		User loggedUser = userRepository.findByCustomerId(loggedUserId);
		
		Payee payee = new Payee();
		payee.setStatus(PayeeStatusUtil.ADD_PENDING);
		payee.setPayeeId(payeeUser.getId());
		payee.setCustId(loggedUser.getId());
		payeeRepository.save(payee);
		
		ResponseDTO responseObject = new ResponseDTO();
		responseObject.setHttpStatus(HttpStatus.OK);
		responseObject.setData(payeeUser);
		responseObject.setMessage("Transaction Initiated");
		
		
		return responseObject;
	}

}
