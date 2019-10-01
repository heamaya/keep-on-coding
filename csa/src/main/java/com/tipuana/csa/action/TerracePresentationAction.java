package com.tipuana.csa.action;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.TerracePresentationPhotoDAO;
import com.tipuana.csa.model.TerracePresentationPhoto;

@SuppressWarnings("serial")
public class TerracePresentationAction extends PresentationPhotoAction<TerracePresentationPhoto, TerracePresentationPhotoDAO> {
	
	@Override
	public String getPath() {
		return Constants.TERRACE_PRESENTATION_PATH;
	}

	@Override
	public String getEntityName() {
		return Constants.TERRACE_PRESENTATION_PHOTO;
	}
	
}