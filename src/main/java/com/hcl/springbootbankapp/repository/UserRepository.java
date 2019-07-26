package com.hcl.springbootbankapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.springbootbankapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	public Optional<User> findByUsernameAndPassword(String pUserName, String pPassword);
	
	public Optional<User> findByCustomerId(String customerId);

	

}
