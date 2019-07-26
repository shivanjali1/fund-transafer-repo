package com.hcl.springbootbankapp.DTO;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO  {

	private String message;
	private HttpStatus httpStatus;
	private Object data;

}
