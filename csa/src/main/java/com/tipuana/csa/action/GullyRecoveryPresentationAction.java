package com.tipuana.csa.action;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.GullyRecoveryPresentationPhotoDAO;
import com.tipuana.csa.model.GullyRecoveryPresentationPhoto;

@SuppressWarnings("serial")
public class GullyRecoveryPresentationAction extends PresentationPhotoAction<GullyRecoveryPresentationPhoto, GullyRecoveryPresentationPhotoDAO> {
	
	@Override
	public String getPath() {
		return Constants.GULLY_RECOVERY_PRESENTATION_PATH;
	}

	@Override
	public String getEntityName() {
		return Constants.GULLY_RECOVERY_PRESENTATION_PHOTO;
	}
	
	
}
