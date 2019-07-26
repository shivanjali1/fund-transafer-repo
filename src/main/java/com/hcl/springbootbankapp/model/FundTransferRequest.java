package com.hcl.springbootbankapp.model;

public class FundTransferRequest {

	String username;

	Long payeeAccountNo;

	Double trsandferAmt;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(Long payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public Double getTrsandferAmt() {
		return trsandferAmt;
	}

	public void setTrsandferAmt(Double trsandferAmt) {
		this.trsandferAmt = trsandferAmt;
	}

}
