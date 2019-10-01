package com.tipuana.csa.action.util;


@SuppressWarnings("serial")
public class DikePresentationPhotoDownloadFileAction extends PresentationPhotoDownloadFileAction {
	
	@Override
	public String getFilePath() {
		return Constants.DIKE_PRESENTATION_PATH;
	}

}