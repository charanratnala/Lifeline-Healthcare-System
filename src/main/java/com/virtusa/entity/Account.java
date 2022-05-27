package com.virtusa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String accountNo;

	private String customerName;
	private String ifsc;
	private String phoneNo;
	private String bankName;
	private String email;

	private String branch;

	@OneToOne
	private User user;

	@OneToMany()
	List<BenificiaryAccount> acc = new ArrayList<BenificiaryAccount>();

}
