package com.lhs.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lhs.entity.Prescription;
import com.lhs.entity.Combo;

@Repository
public interface BillMakingRepo extends PagingAndSortingRepository<Prescription, Long> {

	Prescription findByPreid(String preid);

	Optional<Combo> findById(int id);

}
