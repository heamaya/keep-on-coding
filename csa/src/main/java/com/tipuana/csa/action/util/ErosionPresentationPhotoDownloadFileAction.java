package com.tipuana.csa.action.util;


@SuppressWarnings("serial")
public class ErosionPresentationPhotoDownloadFileAction extends PresentationPhotoDownloadFileAction {
	
	@Override
	public String getFilePath() {
		return Constants.EROSION_PRESENTATION_PATH;
	}

}