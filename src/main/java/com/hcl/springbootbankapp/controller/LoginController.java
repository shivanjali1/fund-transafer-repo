package com.hcl.springbootbankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springbootbankapp.entity.TransactionHistory;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.service.LoginService;


/*
 * This is LoginController class used to provide user login services
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	/*
	 * This method is used by user to login
	 * @param user to get username and password
	 * @return returns list of last 10 transaction history of login user.
	 */
	@PostMapping("/user")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		List<TransactionHistory> transactions=null;
		try {
			 transactions = loginService.loginUser(user);
			System.out.println("User is sucessfully login");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<List<TransactionHistory>>(transactions, HttpStatus.OK);
	}

}
