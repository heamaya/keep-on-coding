package com.tipuana.csa.action.util;


@SuppressWarnings("serial")
public class NewsPresentationPhotoDownloadFileAction extends PresentationPhotoDownloadFileAction {
	
	@Override
	public String getFilePath() {
		return Constants.NEWS_PRESENTATION_PATH;
	}

}