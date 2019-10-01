package com.tipuana.csa.action.util;


@SuppressWarnings("serial")
public class TerracePresentationPhotoDownloadFileAction extends PresentationPhotoDownloadFileAction {
	
	@Override
	public String getFilePath() {
		return Constants.TERRACE_PRESENTATION_PATH;
	}

}