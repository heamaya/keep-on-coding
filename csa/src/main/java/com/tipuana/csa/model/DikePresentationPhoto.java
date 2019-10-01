package com.tipuana.csa.model;


@SuppressWarnings("serial")
public class DikePresentationPhoto extends PresentationPhoto {

	@Override
	public int compareTo(Object object) {

		if(object instanceof DikePresentationPhoto) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (DikePresentationPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}	
	
}
