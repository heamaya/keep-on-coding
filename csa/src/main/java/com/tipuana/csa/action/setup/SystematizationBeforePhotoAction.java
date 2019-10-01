package com.tipuana.csa.action.setup;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.SystematizationBeforePhotoDAO;
import com.tipuana.csa.model.Systematization;
import com.tipuana.csa.model.SystematizationBeforePhoto;


@SuppressWarnings("serial")
public class SystematizationBeforePhotoAction extends 
	SystematizationPhotoAction<SystematizationBeforePhoto, SystematizationBeforePhotoDAO> implements Preparable {

	@Override
	public void prepare() throws Exception {
		Systematization systematization = getSystematizationDAO().findById(getSystematizationId(), true);
		SystematizationBeforePhoto systematizationPhoto = null;
		
		if(getRequestId() != 0) {
			systematizationPhoto = getSystematizationPhotoDAO().findById(getRequestId(), true); 
			systematizationPhoto.setSystematization(systematization);
			setPhotoFileName(systematizationPhoto.getSystematizationPhotoFileName());
			setPhotoContentType(systematizationPhoto.getSystematizationPhotoContentType());
			setDescriptionSpanish(systematizationPhoto.getDescriptionSpanish());
			setDescriptionPortuguese(systematizationPhoto.getDescriptionPortuguese());
			setDescriptionEnglish(systematizationPhoto.getDescriptionEnglish());
		} else {
			systematizationPhoto = new SystematizationBeforePhoto();
			systematizationPhoto.setSystematization(systematization);
		} 
		
		setModel(systematizationPhoto);
		setSystematizationName(systematization.getLand().getName());
			
	}

	@Override
	public String getPath() {
		return getModel().getSystematization().getBeforePhotoPath();
	}
	
	@Override
	public String list() {
		setMappedRequest(Constants.LIST);
		
		getRequest().put(getListName(), (getSystematizationPhotoDAO()).find(getSystematizationId()));
		
		return Constants.LIST;
	}
}