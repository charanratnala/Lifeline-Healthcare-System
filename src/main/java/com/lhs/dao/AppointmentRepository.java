package com.lhs.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lhs.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query("select a from Appointment a where a.doctorAvailableDate > :upcoming")
	List<Appointment> findAllDateAfter(@Param("upcoming") LocalDate datetime1);

	public List<Appointment> findByDoctorAvailableDate(LocalDate currentDate);

	public Appointment findByDocId(int id);

}
