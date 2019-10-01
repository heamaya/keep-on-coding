package com.tipuana.csa.action.util;

import com.tipuana.csa.dao.LandDAO;

@SuppressWarnings("serial")
public class LandBoundariesDownloadFileAction extends DownloadFileAction {
	private Long landId;
	private LandDAO landDAO;

	@Override
	public String getFilePath() {	
		return getLandDAO().findById(getLandId(), true).getPath();
	}

	public Long getLandId() {
		return landId;
	}

	public void setLandId(Long landId) {
		this.landId = landId;
	}

	public LandDAO getLandDAO() {
		return landDAO;
	}

	public void setLandDAO(LandDAO landDAO) {
		this.landDAO = landDAO;
	}
}