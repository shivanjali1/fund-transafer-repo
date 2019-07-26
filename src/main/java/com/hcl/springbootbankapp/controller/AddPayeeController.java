package com.hcl.springbootbankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.service.AddPayeeService;

@RestController
@RequestMapping(value = "/addPayee")
public class AddPayeeController {
	
	@Autowired
	AddPayeeService addPayeeService;
	
	@PostMapping
	public ResponseEntity<ResponseDTO> addPayee(@RequestParam String loggedUserId, @RequestParam String payeeUserId){
		
		ResponseDTO returneResponse = addPayeeService.addPayee(loggedUserId,payeeUserId);
		
		return null;
		
	}
	
}

