package com.hcl.springbootbankapp.model;

import lombok.Data;

@Data
public class ValidateOTP {

	private String customerId;
	
	private String payeeId;
	
	private Long referenceId;
	
	private String otp;
}
