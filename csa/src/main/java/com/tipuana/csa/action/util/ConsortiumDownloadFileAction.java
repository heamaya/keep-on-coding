package com.tipuana.csa.action.util;

import com.tipuana.csa.dao.ConsortiumDAO;
import com.tipuana.csa.model.Consortium;

@SuppressWarnings("serial")
public class ConsortiumDownloadFileAction extends DownloadFileAction {
	private Long consortiumId;
	private ConsortiumDAO consortiumDAO;

	@Override
	public String getFilePath() {
		Consortium consortium = getConsortiumDAO().findById(getConsortiumId(), true);
		
		if(isOnlyStarred() && !consortium.getStarred()) {
			setAllowed(false);
		} else {
			setAllowed(true);
		}
		
		return consortium.getPath();
	}

	public Long getConsortiumId() {
		return consortiumId;
	}

	public void setConsortiumId(Long consortiumId) {
		this.consortiumId = consortiumId;
	}

	public ConsortiumDAO getConsortiumDAO() {
		return consortiumDAO;
	}

	public void setConsortiumDAO(ConsortiumDAO consortiumDAO) {
		this.consortiumDAO = consortiumDAO;
	}

}