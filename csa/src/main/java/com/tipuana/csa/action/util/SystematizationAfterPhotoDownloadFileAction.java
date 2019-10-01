package com.tipuana.csa.action.util;

import com.tipuana.csa.model.Systematization;

@SuppressWarnings("serial")
public class SystematizationAfterPhotoDownloadFileAction extends
		SystematizationDownloadFileAction {

	@Override
	public String getFilePath() {
		Systematization systematization = getSystematizationDAO().findById(getSystematizationId(), true);
		
		if(isOnlyStarred() && !systematization.getStarred()) {
			setAllowed(false);
		} else {
			setAllowed(true);
		}
		
		return systematization.getAfterPhotoPath();
	}
	
}