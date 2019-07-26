package com.hcl.springbootbankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.DTO.UserDTO;
import com.hcl.springbootbankapp.entity.Account;
import com.hcl.springbootbankapp.entity.TransactionHistory;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.repository.AccountRepository;
import com.hcl.springbootbankapp.repository.TransactionHistoryRepository;
import com.hcl.springbootbankapp.repository.UserRepository;

/*
 * This is LoginService class used to provide user login services
 */
@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;

	/*
	 * This method is used by user to login
	 * @param user to get username and password
	 * @return returns list of last 10 transaction history of login user.
	 */
	public ResponseDTO loginUser(UserDTO userDTO) throws ApplicationException {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		Optional<User> OptionalUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		boolean isPresent = OptionalUser.isPresent();
		if (isPresent) {
			Account lAccount = accountRepository.findByUserId(OptionalUser.get().getId());

			Pageable sortedByTransactionTime = PageRequest.of(0, 10, Sort.by("transactionTime").descending());
			List<TransactionHistory> lTenTransactionByAccountNo = transactionHistoryRepository
					.findByAccountNo(lAccount.getAccountNo(), sortedByTransactionTime);

			responseDTO.setData(lTenTransactionByAccountNo);
			responseDTO.setMessage("User loged in sucessfully");
			responseDTO.setHttpStatus(HttpStatus.OK);
			return responseDTO;
		} else {
			throw new ApplicationException("User is not authentited.");
		}
	}
	
}
