package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Contract implements Serializable {
	private Date contractDate;	
	private Double hiredHectares;
	private Integer fees;
	private Double quintalsPerHectare;

	public Double getHiredHectares() {
		return hiredHectares;
	}

	public void setHiredHectares(Double hiredHectares) {
		this.hiredHectares = hiredHectares;
	}

	public Integer getFees() {
		return fees;
	}

	public void setFees(Integer fees) {
		this.fees = fees;
	}

	public Double getQuintalsPerHectare() {
		return quintalsPerHectare;
	}

	public void setQuintalsPerHectare(Double quintalsPerHectare) {
		this.quintalsPerHectare = quintalsPerHectare;
	}

	public Double getQuintalsToPay() {
		
		if(getHiredHectares () == null || getQuintalsPerHectare() == null) {
			return null;
		}
		
		return new Double(getHiredHectares().doubleValue() * getQuintalsPerHectare().doubleValue());
	}
	
	public Double getQuintalsPerFee() {
		
		if(getQuintalsToPay() == null || getFees() == null || getFees().doubleValue() == 0) {
			return null;
		}
		
		return getQuintalsToPay().doubleValue() / ((double) getFees());
	}
	
	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contractDate == null) ? 0 : contractDate.hashCode());
		result = prime * result + ((fees == null) ? 0 : fees.hashCode());
		result = prime * result
				+ ((hiredHectares == null) ? 0 : hiredHectares.hashCode());
		result = prime
				* result
				+ ((quintalsPerHectare == null) ? 0 : quintalsPerHectare
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		if (contractDate == null) {
			if (other.contractDate != null)
				return false;
		} else if (!contractDate.equals(other.contractDate))
			return false;
		if (fees == null) {
			if (other.fees != null)
				return false;
		} else if (!fees.equals(other.fees))
			return false;
		if (hiredHectares == null) {
			if (other.hiredHectares != null)
				return false;
		} else if (!hiredHectares.equals(other.hiredHectares))
			return false;
		if (quintalsPerHectare == null) {
			if (other.quintalsPerHectare != null)
				return false;
		} else if (!quintalsPerHectare.equals(other.quintalsPerHectare))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contract [contractDate=" + contractDate + ", hiredHectares="
				+ hiredHectares + ", fees=" + fees + ", quintalsPerHectare="
				+ quintalsPerHectare + "]";
	}

	public boolean hasNullValues() {
		
		if(this.getContractDate() == null && 
		   this.getFees() == null && 
		   this.getHiredHectares() == null &&  
		   this.getQuintalsPerFee() == null &&
		   this.getQuintalsPerHectare() == null &&
		   this.getQuintalsToPay() == null)
		{
			return true;
		}
		
		return false;
	}
	
}