package com.hcl.springbootbankapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This is TransactionHistory entity
 */
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

	@Id
	@Column(name = "transaction_history_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transaction_time", nullable = false)
	private LocalDateTime transactionTime;

	@Column(name = "account_no", nullable = false)
	private Long accountNo;

	@Column(name = "transaction_type", nullable = false)
	private String transactionType;

	@Column(name = "final_balance", nullable = false)
	private Double finalBalance;

	@Column(name = "trsansaction_amt", nullable = false)
	private Double trsansactionAmt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(Double finalBalance) {
		this.finalBalance = finalBalance;
	}

	public Double getTrsansactionAmt() {
		return trsansactionAmt;
	}

	public void setTrsansactionAmt(Double trsansactionAmt) {
		this.trsansactionAmt = trsansactionAmt;
	}

	@Override
	public String toString() {
		return "TransactionHistory [id=" + id + ", transactionTime=" + transactionTime + ", accountNo=" + accountNo
				+ ", transactionType=" + transactionType + ", finalBalance=" + finalBalance + ", trsansactionAmt="
				+ trsansactionAmt + "]";
	}

}
