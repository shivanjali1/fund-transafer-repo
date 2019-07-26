package com.hcl.springbootbankapp.DTO;

import lombok.Data;

@Data
public class FundTransferRequest {

	String customerId;

	String payeeCustomerId;

	Double transferAmount;

}
