
package com.tipuana.csa.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.ConsortiumPhotoDAO;
import com.tipuana.csa.model.ConsortiumPhoto;


@SuppressWarnings("serial")
public class ConsortiumPhotosJSONAction extends BasePresentationAction {
	
	private Long consortiumId;
	private ConsortiumPhotoDAO consortiumPhotoDAO;
	private List<ConsortiumPhoto> consortiumPhotos;
	
	public String execute() {
		
		setConsortiumPhotos(getConsortiumPhotoDAO().find(getConsortiumId()));
		
		return Constants.SUCCESS;
	}

	@JSON(serialize=false)
	public Long getConsortiumId() {
		return consortiumId;
	}

	public void setConsortiumId(Long consortiumId) {
		this.consortiumId = consortiumId;
	}

	@JSON(serialize=false)
	public ConsortiumPhotoDAO getConsortiumPhotoDAO() {
		return consortiumPhotoDAO;
	}

	public void setConsortiumPhotoDAO(ConsortiumPhotoDAO consortiumPhotoDAO) {
		this.consortiumPhotoDAO = consortiumPhotoDAO;
	}

	@JSON(serialize=true)
	public List<ConsortiumPhoto> getConsortiumPhotos() {
		return consortiumPhotos;
	}

	public void setConsortiumPhotos(List<ConsortiumPhoto> consortiumPhotos) {
		this.consortiumPhotos = consortiumPhotos;
	}

}