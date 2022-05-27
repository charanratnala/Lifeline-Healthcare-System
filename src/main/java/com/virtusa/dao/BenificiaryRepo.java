package com.virtusa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.entity.BenificiaryAccount;

@Repository
public interface BenificiaryRepo extends JpaRepository<BenificiaryAccount, Integer> {

}
