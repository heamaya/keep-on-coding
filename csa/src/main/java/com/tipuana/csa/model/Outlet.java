package com.tipuana.csa.model;

import java.io.Serializable;

import com.tipuana.csa.action.converter.DoubleConverterUtil;

@SuppressWarnings("serial")
public class Outlet implements Serializable {
	private Integer outletNumber;
	private Double diameter;
	private Double inletHeight;
	private Double outletHeight;
	
	public Double getDiameter() {
		return diameter;
	}
	
	//@RequiredFieldValidator(type=ValidatorType.FIELD, key="diameter.required", shortCircuit=true)
	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="diameter.length", minInclusive="0.25", maxInclusive="2")
	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}

	public Double getInletHeight() {
		return inletHeight;
	}

	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="inletHeight.length", minInclusive="0", maxInclusive="5")
	public void setInletHeight(Double inletHeight) {
		this.inletHeight = inletHeight;
	}

	public Double getOutletHeight() {
		return outletHeight;
	}

	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="outletHeight.length", minInclusive="0", maxInclusive="5")
	public void setOutletHeight(Double outletHeight) {
		this.outletHeight = outletHeight;
	}
	
	public Integer getOutletNumber() {
		return outletNumber;
	}

	public void setOutletNumber(Integer outletNumber) {
		this.outletNumber = outletNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((diameter == null) ? 0 : diameter.hashCode());
		result = prime * result
				+ ((inletHeight == null) ? 0 : inletHeight.hashCode());
		result = prime * result
				+ ((outletHeight == null) ? 0 : outletHeight.hashCode());
		result = prime * result
				+ ((outletNumber == null) ? 0 : outletNumber.hashCode());
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
		Outlet other = (Outlet) obj;
		if (diameter == null) {
			if (other.diameter != null)
				return false;
		} else if (!diameter.equals(other.diameter))
			return false;
		if (inletHeight == null) {
			if (other.inletHeight != null)
				return false;
		} else if (!inletHeight.equals(other.inletHeight))
			return false;
		if (outletHeight == null) {
			if (other.outletHeight != null)
				return false;
		} else if (!outletHeight.equals(other.outletHeight))
			return false;
		if (outletNumber == null) {
			if (other.outletNumber != null)
				return false;
		} else if (!outletNumber.equals(other.outletNumber))
			return false;
		return true;
	}

	public String getSummary() {
		StringBuilder sb = new StringBuilder("Diámetro: ").
		append(getDiameter()).
		append(" (m) | Altura de Entrada: ").
		append(getInletHeight()).
		append(" (m) | Altura de Salida: ").
		append(getOutletHeight()).
		append(" (m)");
		
		return DoubleConverterUtil.replaceDot(sb.toString());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder().
		append(getOutletNumber()).
		append(";").
		append(getDiameter()).
		append(";").
		append(getInletHeight()).
		append(";").
		append(getOutletHeight());
		
		return DoubleConverterUtil.replaceDot(sb.toString());
	}
}