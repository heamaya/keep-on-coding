package com.tipuana.csa.model;

@SuppressWarnings("serial")
public class HomePresentationPhoto extends PresentationPhoto {
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof HomePresentationPhoto) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (HomePresentationPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}	
}
