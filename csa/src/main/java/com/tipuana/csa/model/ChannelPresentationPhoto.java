package com.tipuana.csa.model;


@SuppressWarnings("serial")
public class ChannelPresentationPhoto extends PresentationPhoto {

	@Override
	public int compareTo(Object object) {

		if(object instanceof ChannelPresentationPhoto) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (ChannelPresentationPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}	
	
}
