package com.lhs.dao;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.entity.SlotHandler;

@Repository
public interface SlotHandle extends JpaRepository<SlotHandler, Integer> {

	List<SlotHandler> findByOperationDate(Date operationDate);

	List<SlotHandler> existsByOperationDate(Date operationDate);

	boolean existsBySlot1(LocalTime operationDate);

	// SlotHandler findByOperationDateOrSlot1OrSlot2OrSlot3(Date
	// operationDate,LocalTime slot1,LocalTime slot2,LocalTime slot3);

}
