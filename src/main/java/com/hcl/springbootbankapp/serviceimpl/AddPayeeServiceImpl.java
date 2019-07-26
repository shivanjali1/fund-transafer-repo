package com.hcl.springbootbankapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.repository.UserRepository;
import com.hcl.springbootbankapp.service.AddPayeeService;
@Service
public class AddPayeeServiceImpl implements AddPayeeService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseDTO addPayee(String loggedUserId, String payeeUserId) {
		// TODO Auto-generated method stub
		
		User payeeUser = userRepository.findByCustomerId(payeeUserId);
		
		User loggedUser = userRepository.findByCustomerId(loggedUserId);
		
		
		ResponseDTO responseObject = new ResponseDTO();
		responseObject.setHttpStatus(HttpStatus.OK);
		responseObject.setData(payeeUser);
		responseObject.setMessage("Success");
		
		
		return responseObject;
	}

}
