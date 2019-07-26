package com.hcl.springbootbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.springbootbankapp.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

	@Query(value = "update payee set status = :status where id = :referenceId ", nativeQuery = true)
	public void updatePayeeStatus(Long referenceId, String status);

	@Query(value = "select id from Payee where custId = :custId and payeeId = :payeeId", nativeQuery = true)
	Long getrefIdsByCustIdAndPayeeId(@Param("custId") Long custId, @Param("payeeId") Long payeeId);
}
