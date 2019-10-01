package com.tipuana.csa.action.setup;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.TerracePresentationPhotoDAO;
import com.tipuana.csa.model.TerracePresentationPhoto;


@SuppressWarnings("serial")
public class TerracePresentationPhotoAction extends 
	PresentationPhotoAction<TerracePresentationPhoto, TerracePresentationPhotoDAO> implements Preparable {

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
			setModel(new TerracePresentationPhoto());
		} 
			
	}

	@Override
	public String getPath() {
		return Constants.TERRACE_PRESENTATION_PATH;
	}
}