package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Payment implements Serializable, Comparable<Object> {
	private long id;
	private int version = 0;
	private Date created = new Date();
	private Date paymentDate;
	private Integer feeNumber;
	private Double soyaPriceByQuintal;
	private Double quintalsPaid;
	private SystematizationProject systematizationProject;
	private User user;
	
	public long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public Date getCreated() {
		return created;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public Integer getFeeNumber() {
		return feeNumber;
	}

	public Double getSoyaPriceByQuintal() {
		return soyaPriceByQuintal;
	}

	public Double getQuintalsPaid() {
		return quintalsPaid;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setFeeNumber(Integer feeNumber) {
		this.feeNumber = feeNumber;
	}

	public void setSoyaPriceByQuintal(Double soyaPriceByQuintal) {
		this.soyaPriceByQuintal = soyaPriceByQuintal;
	}

	public void setQuintalsPaid(Double quintalsPaid) {
		this.quintalsPaid = quintalsPaid;
	}
	
	public SystematizationProject getSystematizationProject() {
		return systematizationProject;
	}

	public void setSystematizationProject(
			SystematizationProject systematizationProject) {
		this.systematizationProject = systematizationProject;
	}

	@Override
	public int compareTo(Object object) {

		if(object instanceof Payment) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (Payment) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feeNumber == null) ? 0 : feeNumber.hashCode());
		result = prime * result
				+ ((paymentDate == null) ? 0 : paymentDate.hashCode());
		result = prime * result
				+ ((quintalsPaid == null) ? 0 : quintalsPaid.hashCode());
		result = prime
				* result
				+ ((soyaPriceByQuintal == null) ? 0 : soyaPriceByQuintal
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
		Payment other = (Payment) obj;
		if (feeNumber == null) {
			if (other.feeNumber != null)
				return false;
		} else if (!feeNumber.equals(other.feeNumber))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		if (quintalsPaid == null) {
			if (other.quintalsPaid != null)
				return false;
		} else if (!quintalsPaid.equals(other.quintalsPaid))
			return false;
		if (soyaPriceByQuintal == null) {
			if (other.soyaPriceByQuintal != null)
				return false;
		} else if (!soyaPriceByQuintal.equals(other.soyaPriceByQuintal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [paymentDate=" + paymentDate + ", feeNumber="
				+ feeNumber + ", soyaPriceByQuintal=" + soyaPriceByQuintal
				+ ", quintalsPaid=" + quintalsPaid + "]";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}