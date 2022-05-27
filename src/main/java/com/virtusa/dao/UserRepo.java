package com.virtusa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
