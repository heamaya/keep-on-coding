package com.tipuana.csa.action.setup;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.GullyRecoveryPresentationPhotoDAO;
import com.tipuana.csa.model.GullyRecoveryPresentationPhoto;


@SuppressWarnings("serial")
public class GullyRecoveryPresentationPhotoAction extends 
	PresentationPhotoAction<GullyRecoveryPresentationPhoto, GullyRecoveryPresentationPhotoDAO> implements Preparable {

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
			setModel(new GullyRecoveryPresentationPhoto());
		} 
			
	}

	@Override
	public String getPath() {
		return Constants.GULLY_RECOVERY_PRESENTATION_PATH;
	}
}