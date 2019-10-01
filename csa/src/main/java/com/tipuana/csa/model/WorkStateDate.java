package com.tipuana.csa.model;

import java.util.Date;

public class WorkStateDate {
	private Date projectedDate;
	private Date inProgressDate;
	private Date finishedDate;
	
	public Date getProjectedDate() {
		return projectedDate;
	}
	
	public Date getInProgressDate() {
		return inProgressDate;
	}
	
	public Date getFinishedDate() {
		return finishedDate;
	}
	
	public void setProjectedDate(Date projectedDate) {
		this.projectedDate = projectedDate;
	}
	
	public void setInProgressDate(Date inProgressDate) {
		this.inProgressDate = inProgressDate;
	}
	
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
}
