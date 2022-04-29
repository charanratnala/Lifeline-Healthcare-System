package com.lhs.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lhs.entity.AppointmentDetails;

@Repository
public interface BillMakingRepo extends PagingAndSortingRepository<AppointmentDetails, Long> {

}
