package com.tipuana.csa.action.setup;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.SystematizationAfterPhotoDAO;
import com.tipuana.csa.model.Systematization;
import com.tipuana.csa.model.SystematizationAfterPhoto;


@SuppressWarnings("serial")
public class SystematizationAfterPhotoAction extends 
	SystematizationPhotoAction<SystematizationAfterPhoto, SystematizationAfterPhotoDAO> implements Preparable {

	@Override
	public void prepare() throws Exception {
		Systematization systematization = getSystematizationDAO().findById(getSystematizationId(), true);
		SystematizationAfterPhoto systematizationPhoto = null;
		
		if(getRequestId() != 0) {
			systematizationPhoto = getSystematizationPhotoDAO().findById(getRequestId(), true); 
			systematizationPhoto.setSystematization(systematization);
			setPhotoFileName(systematizationPhoto.getSystematizationPhotoFileName());
			setPhotoContentType(systematizationPhoto.getSystematizationPhotoContentType());
			setDescriptionSpanish(systematizationPhoto.getDescriptionSpanish());
			setDescriptionPortuguese(systematizationPhoto.getDescriptionPortuguese());
			setDescriptionEnglish(systematizationPhoto.getDescriptionEnglish());
		} else {
			systematizationPhoto = new SystematizationAfterPhoto();
			systematizationPhoto.setSystematization(systematization);
		} 
		
		setModel(systematizationPhoto);
		setSystematizationName(systematization.getLand().getName());
	}

	@Override
	public String getPath() {
		return getModel().getSystematization().getAfterPhotoPath();
	}
	
	@Override
	public String list() {
		setMappedRequest(Constants.LIST);
		
		getRequest().put(getListName(), (getSystematizationPhotoDAO()).find(getSystematizationId()));
		
		return Constants.LIST;
	}
}