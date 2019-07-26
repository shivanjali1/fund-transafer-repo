package com.hcl.springbootbankapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "optId")
public class OtpDetails implements Serializable {
	
	private static final long serialVersionUID = -8985679445647579333L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "otp_id")
	private  Long otpId;
	
	private String customerId;
	
	@Column(name = "time")
	private LocalDateTime time;
	
	@Column(name = "otp_code")
	private String otpCode;
	
	@Column(name = "ref_id")
	private Long refId;

}
