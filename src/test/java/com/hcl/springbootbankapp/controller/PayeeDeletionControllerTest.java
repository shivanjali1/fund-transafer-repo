package com.hcl.springbootbankapp.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;
import com.hcl.springbootbankapp.service.PayeeDeletionService;

@RunWith(MockitoJUnitRunner.class)
public class PayeeDeletionControllerTest {

	@InjectMocks
	PayeeDeletionController payeeDeletionController;
	
	@Mock
	PayeeDeletionService payeeDeletionService;
	
	ValidateOTP validateOTP;
	
	@Before
	public void setUp() {
		validateOTP = new ValidateOTP();
	}
	
	@Test
	public void testValidatePayeeDeletionIfOTPIsValid() throws ApplicationException {
		assertNotNull(payeeDeletionController.validatePayeeDeletion(validateOTP));
	}
	
}
