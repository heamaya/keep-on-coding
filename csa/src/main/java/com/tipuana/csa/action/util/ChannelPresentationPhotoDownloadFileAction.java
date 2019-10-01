package com.tipuana.csa.action.util;


@SuppressWarnings("serial")
public class ChannelPresentationPhotoDownloadFileAction extends PresentationPhotoDownloadFileAction {
	
	@Override
	public String getFilePath() {
		return Constants.CHANNEL_PRESENTATION_PATH;
	}

}