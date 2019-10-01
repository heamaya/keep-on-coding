package com.tipuana.csa.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GullyRecovery implements Serializable {
	private Double gulliesSurface;
	private Double recoveredSurface;
	
	public Double getGulliesSurface() {
		return gulliesSurface;
	}
	
	public Double getRecoveredSurface() {
		return recoveredSurface;
	}
	
	public void setGulliesSurface(Double gulliesSurface) {
		this.gulliesSurface = gulliesSurface;
	}
	
	public void setRecoveredSurface(Double recoveredSurface) {
		this.recoveredSurface = recoveredSurface;
	}

	public boolean hasNullValues() {
		
		if(this.getGulliesSurface() == null && 
		   this.getRecoveredSurface() == null) 
		{
			return true;
		}
		
		return false;
	}
}