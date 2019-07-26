package com.hcl.springbootbankapp.service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;

public interface AddPayeeService {

	public ResponseDTO addPayee(String loggedUserId, String payeeUserId);

}
