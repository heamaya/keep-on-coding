package com.tipuana.csa.model;

import java.io.Serializable;

import com.tipuana.csa.action.converter.DoubleConverterUtil;

/**
 * @author henry
 *
 */
@SuppressWarnings("serial")
public class Spillway implements Serializable {
	private Integer spillwayNumber;
	private Double length;
	private Double height;
	
	public Double getLength() {
		return length;
	}
	
	//@RequiredFieldValidator(type=ValidatorType.FIELD, key="length.required")
	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="length.length", minInclusive="0.1", maxInclusive="500")
	public void setLength(Double length) {
		this.length = length;
	}
	
	public Double getHeight() {
		return height;
	}
	
	//@RequiredFieldValidator(type=ValidatorType.FIELD, key="height.required")
	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="height.length", minInclusive="0.05", maxInclusive="5")
	public void setHeight(Double height) {
		this.height = height;
	}
	
	public Integer getSpillwayNumber() {
		return spillwayNumber;
	}

	public void setSpillwayNumber(Integer spillwayNumber) {
		this.spillwayNumber = spillwayNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result
				+ ((spillwayNumber == null) ? 0 : spillwayNumber.hashCode());
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
		Spillway other = (Spillway) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (spillwayNumber == null) {
			if (other.spillwayNumber != null)
				return false;
		} else if (!spillwayNumber.equals(other.spillwayNumber))
			return false;
		return true;
	}

	public String getSummary() {
		StringBuilder sb = new StringBuilder().
		append(" Longitud: ").
		append(getLength()).
		append(" (m) | Altura: ").
		append(getHeight()).
		append(" (m)");
		
		return DoubleConverterUtil.replaceDot(sb.toString());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder().
		append(getSpillwayNumber()).
		append(";").
		append(getLength()).
		append(";").
		append(getHeight());
		
		return DoubleConverterUtil.replaceDot(sb.toString());
	}
}