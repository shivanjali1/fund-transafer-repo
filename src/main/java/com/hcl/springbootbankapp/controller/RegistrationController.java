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
import com.hcl.springbootbankapp.service.RegistrationService;

/*
 * This is RegistrationController class used user registration
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {

	private static final String ERROR_MSG = "Mandetory Element missing : ";

	@Autowired
	RegistrationService registrationService;

	/*
	 * This method is for user registration
	 * 
	 * @param user gets username and password
	 * 
	 * @return returns registered user
	 */
	@PostMapping("/user")
	public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO) throws ApplicationException {
		validateUser(userDTO);
		return new ResponseEntity<>(registrationService.registerUser(userDTO), HttpStatus.OK);
	}

	private void validateUser(UserDTO userDTO) throws ApplicationException {

		if (StringUtils.isEmpty(userDTO.getAddress())) {
			throw new ApplicationException(ERROR_MSG + "Address");
		}
		if (StringUtils.isEmpty(userDTO.getDOB())) {
			throw new ApplicationException(ERROR_MSG + "Date of birth");
		}
		if (StringUtils.isEmpty(userDTO.getEmail())) {
			throw new ApplicationException(ERROR_MSG + "Email");
		}
		if (StringUtils.isEmpty(userDTO.getFirstName())) {
			throw new ApplicationException(ERROR_MSG + "FirstName");
		}
		if (StringUtils.isEmpty(userDTO.getGender())) {
			throw new ApplicationException(ERROR_MSG + "Gender");
		}
		if (StringUtils.isEmpty(userDTO.getLastName())) {
			throw new ApplicationException(ERROR_MSG + "LastName");
		}
		if (StringUtils.isEmpty(userDTO.getUsername())) {
			throw new ApplicationException(ERROR_MSG + "Username");
		}
		if (StringUtils.isEmpty(userDTO.getPassword())) {
			throw new ApplicationException(ERROR_MSG + "Password");
		}
		if (StringUtils.isEmpty(userDTO.getPhoneNumber())) {
			throw new ApplicationException(ERROR_MSG + "PhoneNumber");
		}

	}

}
