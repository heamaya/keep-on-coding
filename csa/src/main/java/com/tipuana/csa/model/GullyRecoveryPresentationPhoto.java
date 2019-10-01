package com.tipuana.csa.model;


@SuppressWarnings("serial")
public class GullyRecoveryPresentationPhoto extends PresentationPhoto {

	@Override
	public int compareTo(Object object) {

		if(object instanceof GullyRecoveryPresentationPhoto) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (GullyRecoveryPresentationPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}	
	
}
