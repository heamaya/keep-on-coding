package com.tipuana.csa.action.setup;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.ErosionPresentationPhotoDAO;
import com.tipuana.csa.model.ErosionPresentationPhoto;


@SuppressWarnings("serial")
public class ErosionPresentationPhotoAction extends 
	PresentationPhotoAction<ErosionPresentationPhoto, ErosionPresentationPhotoDAO> implements Preparable {

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
			setModel(new ErosionPresentationPhoto());
		} 
			
	}

	@Override
	public String getPath() {
		return Constants.EROSION_PRESENTATION_PATH;
	}
}