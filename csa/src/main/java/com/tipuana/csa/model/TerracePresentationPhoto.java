package com.tipuana.csa.model;

@SuppressWarnings("serial")
public class TerracePresentationPhoto extends PresentationPhoto {
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof TerracePresentationPhoto) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (TerracePresentationPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}

}
