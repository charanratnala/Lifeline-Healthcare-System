package com.virtusa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.entity.Account;
import com.virtusa.entity.BenificiaryAccount;
import com.virtusa.entity.User;
import com.virtusa.service.AdminService;

@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {

		adminService.addManager(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@PostMapping("/addAccount")
	public ResponseEntity<Account> addAccount(@RequestBody Account account) {

		adminService.addAccount(account);
		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}

	@PostMapping("/addBenificiary/{id}")
	public ResponseEntity<BenificiaryAccount> benificiary(@RequestBody BenificiaryAccount benificiaryAccount,
			@PathVariable("id") Account account) {
		System.out.println(account);
		adminService.benificiary(benificiaryAccount, account);
		return new ResponseEntity<BenificiaryAccount>(benificiaryAccount, HttpStatus.OK);
	}
}
