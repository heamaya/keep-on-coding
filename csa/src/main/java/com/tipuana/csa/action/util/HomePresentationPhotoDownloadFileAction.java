package com.tipuana.csa.action.util;


@SuppressWarnings("serial")
public class HomePresentationPhotoDownloadFileAction extends PresentationPhotoDownloadFileAction {
	
	@Override
	public String getFilePath() {
		return Constants.HOME_PRESENTATION_PATH;
	}

}
