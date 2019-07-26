package com.hcl.springbootbankapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.springbootbankapp.entity.TransactionHistory;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
	
	@Query(value = "select * from transaction_history t where t.account_no = :accountNo limit 10", nativeQuery = true)
	public List<TransactionHistory> findLastTenTransactionByAccountNo(@Param("accountNo")Long accountNo);
	
	public List<TransactionHistory> findByAccountNo(Long accountNo, Pageable page);

}
