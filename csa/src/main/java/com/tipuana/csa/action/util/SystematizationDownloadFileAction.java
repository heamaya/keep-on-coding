package com.tipuana.csa.action.util;

import com.tipuana.csa.dao.SystematizationDAO;
import com.tipuana.csa.model.Systematization;

@SuppressWarnings("serial")
public class SystematizationDownloadFileAction extends DownloadFileAction {
	private Long systematizationId;
	private SystematizationDAO systematizationDAO;
	
	@Override
	public String getFilePath() {
		
		Systematization systematization = getSystematizationDAO().findById(getSystematizationId(), true);
		
		if(isOnlyStarred() && !systematization.getStarred()) {
			setAllowed(false);
		} else {
			setAllowed(true);
		}
		
		return systematization.getPath();
	}

	public Long getSystematizationId() {
		return systematizationId;
	}

	public void setSystematizationId(Long systematizationId) {
		this.systematizationId = systematizationId;
	}

	public SystematizationDAO getSystematizationDAO() {
		return systematizationDAO;
	}

	public void setSystematizationDAO(SystematizationDAO systematizationDAO) {
		this.systematizationDAO = systematizationDAO;
	}

}