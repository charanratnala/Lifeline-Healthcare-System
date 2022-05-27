package com.virtusa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

}
