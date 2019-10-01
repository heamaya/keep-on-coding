package com.tipuana.csa.model;

import java.util.Date;

public class FromToDateField {
	private Date from;
	private Date to;
	
	public FromToDateField() {
		super();
	}

	public FromToDateField(Date from, Date to) {
		super();
		this.from = from;
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public void setTo(Date to) {
		this.to = to;
	}
}