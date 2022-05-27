package com.virtusa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity

public @Data class BenificiaryAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String addBenificiaryBankName;

	private long acNo;
	private long reAcNo;
	private String ifsc;
	private boolean status;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Account account;

}
