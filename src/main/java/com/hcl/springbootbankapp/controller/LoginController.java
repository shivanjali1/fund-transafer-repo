package com.hcl.springbootbankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springbootbankapp.DTO.UserDTO;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.service.LoginService;

/*
 * This is LoginController class used to provide user login services
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	private static final String ERROR_MSG = "Mandetory Element missing : ";

	@Autowired
	LoginService loginService;

	/*
	 * This method is used by user to login
	 * 
	 * @param user to get username and password
	 * 
	 * @return returns list of last 10 transaction history of login user.
	 */
	@PostMapping("/user")
	public ResponseEntity<Object> loginUser(@RequestBody UserDTO userDTO) throws ApplicationException{

		validateUser(userDTO);
		return new ResponseEntity<>(loginService.loginUser(userDTO), HttpStatus.OK);
	}

	private void validateUser(UserDTO userDTO) throws ApplicationException {

		if (StringUtils.isEmpty(userDTO.getUsername())) {
			throw new ApplicationException(ERROR_MSG + "Username");
		}
		if (StringUtils.isEmpty(userDTO.getPassword())) {
			throw new ApplicationException(ERROR_MSG + "Password");
		}

	}

}
