package com.hcl.springbootbankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.springbootbankapp.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	

	public Account findByUserId(Long userId);

	
	public List<Account> findByAccountNoNotIn(Long accountNo);

	public Account findByAccountNo(Long accountNo);
	
}
