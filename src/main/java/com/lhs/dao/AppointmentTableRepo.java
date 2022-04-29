package com.lhs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.entity.AppointmentTable;
@Repository
public interface AppointmentTableRepo extends JpaRepository<AppointmentTable, Integer> {

}
