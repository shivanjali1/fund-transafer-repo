package com.hcl.springbootbankapp.model;

import lombok.Data;

@Data
public class ValidateOTP {

	private String customerId;
	
	private Long referenceId;
	
	private String otp;
}
