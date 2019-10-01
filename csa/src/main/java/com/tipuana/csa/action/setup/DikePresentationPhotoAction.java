package com.tipuana.csa.action.setup;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.DikePresentationPhotoDAO;
import com.tipuana.csa.model.DikePresentationPhoto;


@SuppressWarnings("serial")
public class DikePresentationPhotoAction extends 
	PresentationPhotoAction<DikePresentationPhoto, DikePresentationPhotoDAO> implements Preparable {

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
			setModel(new DikePresentationPhoto());
		} 
			
	}

	@Override
	public String getPath() {
		return Constants.DIKE_PRESENTATION_PATH;
	}
}