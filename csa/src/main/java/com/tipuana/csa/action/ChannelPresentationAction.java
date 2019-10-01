package com.tipuana.csa.action;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.ChannelPresentationPhotoDAO;
import com.tipuana.csa.model.ChannelPresentationPhoto;

@SuppressWarnings("serial")
public class ChannelPresentationAction extends PresentationPhotoAction<ChannelPresentationPhoto, ChannelPresentationPhotoDAO> {
	
	@Override
	public String getPath() {
		return Constants.CHANNEL_PRESENTATION_PATH;
	}

	@Override
	public String getEntityName() {
		return Constants.CHANNEL_PRESENTATION_PHOTO;
	}
	
	
}
