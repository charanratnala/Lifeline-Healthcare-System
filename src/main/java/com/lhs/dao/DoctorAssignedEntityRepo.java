package com.lhs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.entity.DoctorsGuide;

@Repository
public interface DoctorAssignedEntityRepo extends JpaRepository<DoctorsGuide, Integer> {

}
