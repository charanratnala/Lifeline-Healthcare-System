//package com.lhs.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//public class MedicineEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//
//	private List<String> medicineName = new ArrayList<String>();
//	private long amount;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public List<String> getMedicineName() {
//		return medicineName;
//	}
//
//	public void setMedicineName(List<String> medicineName) {
//		this.medicineName = medicineName;
//	}
//
//	public long getAmount() {
//		return amount;
//	}
//
//	public void setAmount(long amount) {
//		this.amount = amount;
//	}
//
//	@Override
//	public String toString() {
//		return "MedicineEntity [id=" + id + ", medicineName=" + medicineName + ", amount=" + amount + "]";
//	}
//
//}
