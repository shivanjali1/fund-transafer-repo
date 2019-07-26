package com.hcl.springbootbankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.springbootbankapp.service.PayeeDeletionService;


@RestController
@RequestMapping("/payee/delete")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class PayeeDeletionController {

	@Autowired
	PayeeDeletionService payeeDeletionService;
	
	@PutMapping("/validate")
	public ResponseEntity<Object> validatePayeeDeletion(){
		
		return new ResponseEntity<>("Payee Deleted successfully", HttpStatus.NO_CONTENT);
	}
}
