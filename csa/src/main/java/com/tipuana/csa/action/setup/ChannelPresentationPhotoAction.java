package com.tipuana.csa.action.setup;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.ChannelPresentationPhotoDAO;
import com.tipuana.csa.model.ChannelPresentationPhoto;


@SuppressWarnings("serial")
public class ChannelPresentationPhotoAction extends 
	PresentationPhotoAction<ChannelPresentationPhoto, ChannelPresentationPhotoDAO> implements Preparable {

	@Override
	public void prepare() throws Exception {
		
		if(getRequestId() != 0) {
			setModel(getPresentationPhotoDAO().findById(getRequestId(), true));
			setPhotoFileName(getModel().getName());
			setPhotoContentType(getModel().getContentType());
			setDescriptionSpanish(getModel().getDescriptionSpanish());
			setDescriptionPortuguese(getModel().getDescriptionPortuguese());
			setDescriptionEnglish(getModel().getDescriptionEnglish());
		} else {
			setModel(new ChannelPresentationPhoto());
		} 
			
	}

	@Override
	public String getPath() {
		
		return Constants.CHANNEL_PRESENTATION_PATH;
	}
}