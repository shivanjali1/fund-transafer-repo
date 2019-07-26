package com.hcl.springbootbankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public User registerUser(User lUser) {
		User savedUser = userRepository.save(lUser);
		Long accountNo = (long) (Math.random() * 100000 + 3333300000L);

		Account account = new Account();
		account.setAccountBalance(10000.0);
		account.setAccountNo(accountNo);
		account.setUserName(savedUser.getUsername());

		accountRepository.save(account);
		return savedUser;
	}
	
	/*
	 * This method is to get all users
	 * @return returns list of all users
	 */
	public List<User> getUser() {
		return userRepository.findAll();
	}


}
