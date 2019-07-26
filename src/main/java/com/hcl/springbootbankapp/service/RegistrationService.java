package com.hcl.springbootbankapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.DTO.UserDTO;
import com.hcl.springbootbankapp.entity.Account;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.repository.AccountRepository;
import com.hcl.springbootbankapp.repository.TransactionHistoryRepository;
import com.hcl.springbootbankapp.repository.UserRepository;

/*
 * This is RegistrationService class used user registration service
 */
@Service
public class RegistrationService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;
	
	/*
	 * This method is for user registration
	 * @param user gets username and password
	 * @return returns registered user 
	 */
	public ResponseDTO registerUser(UserDTO userDTO) {
		
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		ResponseDTO responseDTO = new ResponseDTO();
		String customerId =  user.getFirstName().substring(0,3)+user.getPhoneNumber().substring(0,3).substring(0,3) + System.currentTimeMillis()/3600;
		user.setCustomerId(customerId);
		User savedUser = userRepository.save(user);
		
		Long accountNo = (long) (Math.random() * 100000 + 3333300000L);
		Account account = new Account();
		account.setAccountBalance(10000.0);
		account.setAccountNo(accountNo);
		account.setUserId(savedUser.getId());

		accountRepository.save(account);
		
		responseDTO.setHttpStatus(HttpStatus.OK);
		responseDTO.setMessage("User sucessfully registered");
		responseDTO.setData(savedUser);
		return responseDTO;
	}
	
}
