package com.tipuana.csa.action.util;

import com.tipuana.csa.model.Consortium;


@SuppressWarnings("serial")
public class ConsortiumPhotoDownloadFileAction extends
		ConsortiumDownloadFileAction {
	
	@Override
	public String getFilePath() {
		Consortium consortium = getConsortiumDAO().findById(getConsortiumId(), true);
		
		if(isOnlyStarred() && !consortium.getStarred()) {
			setAllowed(false);
		} else {
			setAllowed(true);
		}
		
		return consortium.getPhotosPath();
	}

}