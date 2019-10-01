package com.tipuana.csa.action;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.HomePresentationPhotoDAO;
import com.tipuana.csa.model.HomePresentationPhoto;

@SuppressWarnings("serial")
public class HomePresentationAction extends PresentationPhotoAction<HomePresentationPhoto, HomePresentationPhotoDAO> {
	
	@Override
	public String getPath() {
		return Constants.HOME_PRESENTATION_PATH;
	}

	@Override
	public String getEntityName() {
		return Constants.HOME_PRESENTATION_PHOTO;
	}
	
	
}
