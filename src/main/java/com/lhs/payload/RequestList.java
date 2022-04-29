package com.lhs.payload;



import java.sql.Date;

public class RequestList {
	
	Date from;
	Date to;
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public RequestList(Date from, Date to) {
		super();
		this.from = from;
		this.to = to;
	}
	public RequestList() {
		super();
	}

}
