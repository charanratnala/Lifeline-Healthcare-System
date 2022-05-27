package com.lhs.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SlotHandler {

	@Override
	public String toString() {
		return "SlotHandler [id=" + id + ", operationDate=" + operationDate + ", status=" + status + ", slot1=" + slot1
				+ ", slot2=" + slot2 + ", slot3=" + slot3 + ", dc=" + dc + ", slot4=" + slot4 + "]";
	}

	private boolean slot1status;
	private boolean slot2status;
	private boolean slot3status;

	public boolean isSlot1status() {
		return slot1status;
	}

	public void setSlot1status(boolean slot1status) {
		this.slot1status = slot1status;
	}

	public boolean isSlot2status() {
		return slot2status;
	}

	public void setSlot2status(boolean slot2status) {
		this.slot2status = slot2status;
	}

	public boolean isSlot3status() {
		return slot3status;
	}

	public void setSlot3status(boolean slot3status) {
		this.slot3status = slot3status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date operationDate;

	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	private LocalTime slot1;
	private LocalTime slot2;
	private LocalTime slot3;

	public SlotHandler(int id, Date operationDate, AppointmentStatus status, LocalTime slot1, LocalTime slot2,
			LocalTime slot3, Doctor dc, LocalTime slot4) {
		super();
		this.id = id;
		this.operationDate = operationDate;
		this.status = status;
		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
		this.dc = dc;
		this.slot4 = slot4;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	@OneToOne
	Doctor dc;

	public Doctor getDc() {
		return dc;
	}

	public void setDc(Doctor dc) {
		this.dc = dc;
	}

	private LocalTime slot4;
	// private LocalTime M1230;

	// private boolean slotStatus;

//	public boolean isSlotStatus() {
//		return slotStatus;
//	}
//
//
//
//	public void setSlotStatus(boolean slotStatus) {
//		this.slotStatus = slotStatus;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public LocalTime getSlot1() {
		return slot1;
	}

	public void setSlot1(LocalTime slot1) {
		this.slot1 = slot1;
	}

	public LocalTime getSlot2() {
		return slot2;
	}

	public void setSlot2(LocalTime slot2) {
		this.slot2 = slot2;
	}

	public LocalTime getSlot3() {
		return slot3;
	}

	public void setSlot3(LocalTime slot3) {
		this.slot3 = slot3;
	}

	public LocalTime getSlot4() {
		return slot4;
	}

	public void setSlot4(LocalTime slot4) {
		this.slot4 = slot4;
	}

	public SlotHandler() {
		super();
	}

	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
