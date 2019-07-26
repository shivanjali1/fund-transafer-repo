package com.hcl.springbootbankapp.model;

import lombok.Data;

@Data
public class ValidateOTP {

	private Long customerId;
	
	private Long referenceId;
	
	private String otp;
}
