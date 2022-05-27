package com.virtusa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.dao.AccountRepo;
import com.virtusa.dao.BenificiaryRepo;
import com.virtusa.dao.UserRepo;
import com.virtusa.entity.Account;
import com.virtusa.entity.BenificiaryAccount;
import com.virtusa.entity.User;

@Service
public class AdminService {

	@Autowired
	BenificiaryRepo benificiaryRepo;

	private User s;

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	UserRepo userRepo;

	public void addManager(User user) {
		this.s = user;

		userRepo.save(user);

	}

	public void addAccount(Account acc) {
		acc.setUser(this.s);
		accountRepo.save(acc);

	}

	public void benificiary(BenificiaryAccount benificiaryAccount, Account account) {
		benificiaryAccount.setAccount(account);

		benificiaryRepo.save(benificiaryAccount);

	}

}
