package com.tipuana.csa.action.setup;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.HomePresentationPhotoDAO;
import com.tipuana.csa.model.HomePresentationPhoto;


@SuppressWarnings("serial")
public class HomePresentationPhotoAction extends 
	PresentationPhotoAction<HomePresentationPhoto, HomePresentationPhotoDAO> implements Preparable {

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
			setModel(new HomePresentationPhoto());
		} 
			
	}

	@Override
	public String getPath() {
		return Constants.HOME_PRESENTATION_PATH;
	}
}