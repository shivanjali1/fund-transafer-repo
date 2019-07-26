package com.hcl.springbootbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.springbootbankapp.entity.OtpDetails;

@Repository
public interface OTPRepository extends JpaRepository<OtpDetails, Long> {

	@Query("from OtpDetails where customerId = :custId and refId = :transId")
	OtpDetails getOtpDetailsByTransIdAndCustId(@Param("custId") String custId, @Param("transId") Long tranId);

}
