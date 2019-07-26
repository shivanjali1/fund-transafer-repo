package com.hcl.springbootbankapp.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.springbootbankapp.exception.ApplicationException;
import com.hcl.springbootbankapp.model.ValidateOTP;
import com.hcl.springbootbankapp.repository.PayeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class PayeeDeletionServiceImplTest {

	@InjectMocks
	PayeeDeletionServiceImpl payeeDeletionServiceImpl; 
	
	@Mock
	PayeeRepository payeeRepository;
	
	@Mock
	OTPServiceImpl otpServiceImpl;
	
	ValidateOTP validateOTP;
	
	@Before
	public void setUp() {
		validateOTP = new ValidateOTP();
		validateOTP.setCustomerId("Cust32652663");
		validateOTP.setOtp("Dgyu34262");
		validateOTP.setReferenceId(5L);
	}
	
	@Test
	public void testValidateOTPIfOTPIsValid() throws ApplicationException {
		
		when(otpServiceImpl.validate(validateOTP.getCustomerId(), validateOTP.getReferenceId(), validateOTP.getOtp())).thenReturn(true);
		assertEquals("OTP validated for deleting payee", payeeDeletionServiceImpl.validateOtp(validateOTP));
	}
	
	@Test(expected = ApplicationException.class)
	public void testValidateOTPIfOTPIsInvalid() throws ApplicationException {
		
		when(otpServiceImpl.validate(validateOTP.getCustomerId(), validateOTP.getReferenceId(), validateOTP.getOtp())).thenReturn(false);
		assertNotEquals("OTP validated for deleting payee", payeeDeletionServiceImpl.validateOtp(validateOTP));
	}
}
