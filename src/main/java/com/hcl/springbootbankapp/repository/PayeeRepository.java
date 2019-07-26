package com.hcl.springbootbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.springbootbankapp.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

}
