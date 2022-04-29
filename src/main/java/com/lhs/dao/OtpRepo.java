package com.lhs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.entity.OtpEntity;
@Repository
public interface OtpRepo extends JpaRepository<OtpEntity, Integer> {

}
