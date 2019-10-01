package com.tipuana.csa.model;


@SuppressWarnings("serial")
public class ErosionPresentationPhoto extends PresentationPhoto {

	@Override
	public int compareTo(Object object) {

		if(object instanceof ErosionPresentationPhoto) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (ErosionPresentationPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}	
	
}
