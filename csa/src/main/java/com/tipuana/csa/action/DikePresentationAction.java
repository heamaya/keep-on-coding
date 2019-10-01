package com.tipuana.csa.action;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.DikePresentationPhotoDAO;
import com.tipuana.csa.model.DikePresentationPhoto;

@SuppressWarnings("serial")
public class DikePresentationAction extends PresentationPhotoAction<DikePresentationPhoto, DikePresentationPhotoDAO> {
	
	@Override
	public String getPath() {
		return Constants.DIKE_PRESENTATION_PATH;
	}

	@Override
	public String getEntityName() {
		return Constants.DIKE_PRESENTATION_PHOTO;
	}
	
	
}
