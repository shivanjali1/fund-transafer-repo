package com.hcl.springbootbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.springbootbankapp.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

	@Query(value = "SELECT * FROM Payee where id = :refId", nativeQuery = true)
	Payee getPayeeByRefId(@Param("refId") Long refId);

	@Query(value = "select * from Payee where cust_id = :custId and payee_id = :payeeId", nativeQuery = true)
	Payee getrefIdsByCustIdAndPayeeId(@Param("custId") Long custId, @Param("payeeId") Long payeeId);
}
