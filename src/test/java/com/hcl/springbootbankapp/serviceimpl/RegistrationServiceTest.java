package com.hcl.springbootbankapp.serviceimpl;

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
import com.hcl.springbootbankapp.entity.Account;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.repository.AccountRepository;
import com.hcl.springbootbankapp.repository.TransactionHistoryRepository;
import com.hcl.springbootbankapp.repository.UserRepository;
import com.hcl.springbootbankapp.service.RegistrationService;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
	
	
	@InjectMocks
	RegistrationService registrationService;
	
	@Mock
	UserRepository userRepositoryMock;
	
	@Mock
	AccountRepository accountRepositoryMock;
	
	@Mock
	TransactionHistoryRepository transactionHistoryMock;
	
	UserDTO userDTO = new UserDTO();
	ResponseDTO responseDTO = new ResponseDTO();
	User user = new User();
	
	Account account = new Account();
	@Before
	public void setUp() {
		userDTO = createUserDTO();
	}
	
	@Test
	public void testregisterUser() throws ApplicationException {
		user.setId(1L);
		Mockito.when(userRepositoryMock.save(Mockito.any(User.class))).thenReturn(user);
		account.setId(1L);
		Mockito.when(accountRepositoryMock.save(Mockito.any(Account.class))).thenReturn(account);
		assertNotNull(registrationService.registerUser(userDTO));
		
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
