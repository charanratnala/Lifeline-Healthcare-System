package com.lhs.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lhs.entity.SlotEntity;

@Repository
public interface Slot extends PagingAndSortingRepository<SlotEntity, Integer> {
	public boolean existsByOperationDate(Date operationDate);
	// public boolean existsBySlot1OrSlot2(LocalTime localtime);

	public List<SlotEntity> findByOperationDate(Date operationDate);

}
