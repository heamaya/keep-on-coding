package com.tipuana.csa.action;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.ErosionPresentationPhotoDAO;
import com.tipuana.csa.model.ErosionPresentationPhoto;

@SuppressWarnings("serial")
public class WhatDoWeDoPresentationAction extends PresentationPhotoAction<ErosionPresentationPhoto, ErosionPresentationPhotoDAO> {

	@Override
	public String getPath() {
		return Constants.EROSION_PRESENTATION_PATH;
	}

	@Override
	public String getEntityName() {
		return Constants.WHAT_DO_WE_DO_PRESENTATION_PHOTO;
	}
}
