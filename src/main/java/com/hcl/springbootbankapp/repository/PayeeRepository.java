package com.hcl.springbootbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.springbootbankapp.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

	@Query(value="update Payee set status = :status where id = :referenceId ",nativeQuery = true)
	public void updatePayeeStatus(Long referenceId, String status);
}
