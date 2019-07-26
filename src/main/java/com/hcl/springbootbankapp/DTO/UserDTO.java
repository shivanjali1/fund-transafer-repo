package com.hcl.springbootbankapp.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
	
	private String firstName;

	private String lastName;

	private String address;

	private String phoneNumber;

	private String username;

	private String password;

	private String email;

	private String gender;

	private LocalDate dOB;

}
