package com.hcl.springbootbankapp.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.DTO.UserDTO;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.service.RegistrationService;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {
	
	@InjectMocks
	RegistrationController registrationController;
	
	@Mock
	RegistrationService registrationServiceMock;
	
	UserDTO userDTO = new UserDTO();
	ResponseDTO responseDTO = new ResponseDTO();
	
	@Before
	public void setUp() {
		userDTO = createUserDTO();
	}
	
	@Test
	public void testregisterUser() throws ApplicationException {
		Mockito.when(registrationServiceMock.registerUser(userDTO)).thenReturn(responseDTO);
		assertNotNull(registrationController.registerUser(userDTO));
	}
	
	@Test(expected = ApplicationException.class)
	public void testregisterUserPhoneNoMissing() throws ApplicationException {
		userDTO.setPhoneNumber(null);
		assertNotNull(registrationController.registerUser(userDTO));
	}
	
	
	
	private UserDTO createUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setAddress("pune");
		userDTO.setDOB(LocalDate.of(1992, 05, 22));
		userDTO.setEmail("email");
		userDTO.setGender("male");
		userDTO.setLastName("kanase");
		userDTO.setPassword("password");
		userDTO.setPhoneNumber("3647668");
		userDTO.setUsername("shiv");
		userDTO.setFirstName("shiv");
		return userDTO;
	}

}
