package com.tipuana.csa.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Terrace implements Serializable {
	private Double groundVolume;
	private Double linearMeters;
	private Integer designedChannelCount;
	private WorkState workState;
	private WorkStateDate workStateDate;
	
	public Double getGroundVolume() {
		return groundVolume;
	}
	
	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="groundVolume.length", minInclusive="1", maxInclusive="100000")
	public void setGroundVolume(Double groundVolume) {
		this.groundVolume = groundVolume;
	}
	
	public Double getLinearMeters() {
		return linearMeters;
	}

	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="linearMeters.length", minInclusive="1", maxInclusive="1000000")
	public void setLinearMeters(Double linearMeters) {
		this.linearMeters = linearMeters;
	}

	public WorkState getWorkState() {
		return workState;
	}

	public void setWorkState(WorkState workState) {
		this.workState = workState;
	}

	public WorkStateDate getWorkStateDate() {
		return workStateDate;
	}

	public void setWorkStateDate(WorkStateDate workStateDate) {
		this.workStateDate = workStateDate;
	}

	public Integer getDesignedChannelCount() {
		return designedChannelCount;
	}

	public void setDesignedChannelCount(Integer designedChannelCount) {
		this.designedChannelCount = designedChannelCount;
	}

	@Override
	public String toString() {
		return "Terrace [groundVolume=" + groundVolume + ", linearMeters="
				+ linearMeters + ", workState=" + workState
				+ ", workStateDate=" + workStateDate + "]";
	}	
	
	public boolean hasNullValues() {
		
		if(this.getDesignedChannelCount() == null && this.getGroundVolume() == null && this.getLinearMeters() == null && this.getWorkState() == null) {
			
			if(this.getWorkStateDate() == null) {
				return true;
			} else if(this.getWorkStateDate().getProjectedDate() == null && 
					  this.getWorkStateDate().getInProgressDate() == null && 
					  this.getWorkStateDate().getFinishedDate() == null) 
			{
				return true; 
			}
		}
		
		return false;
	}
}