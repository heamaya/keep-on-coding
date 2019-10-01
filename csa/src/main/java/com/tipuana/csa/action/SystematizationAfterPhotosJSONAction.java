
package com.tipuana.csa.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.SystematizationAfterPhotoDAO;
import com.tipuana.csa.model.SystematizationAfterPhoto;


@SuppressWarnings("serial")
public class SystematizationAfterPhotosJSONAction extends BasePresentationAction {
	
	private Long systematizationId;
	private SystematizationAfterPhotoDAO systematizationPhotoDAO;
	private List<SystematizationAfterPhoto> systematizationPhotos;
	
	public String execute() {
		setSystematizationPhotos(getSystematizationPhotoDAO().find(getSystematizationId()));
		return Constants.SUCCESS;
	}

	@JSON(serialize=false)
	public SystematizationAfterPhotoDAO getSystematizationPhotoDAO() {
		return systematizationPhotoDAO;
	}

	public void setSystematizationPhotoDAO(
			SystematizationAfterPhotoDAO systematizationPhotoDAO) {
		this.systematizationPhotoDAO = systematizationPhotoDAO;
	}
	
	@JSON(serialize=false)
	public Long getSystematizationId() {
		return systematizationId;
	}

	public void setSystematizationId(Long systematizationId) {
		this.systematizationId = systematizationId;
	}

	@JSON(serialize=true, name="systematizationPhotos")
	public List<SystematizationAfterPhoto> getSystematizationPhotos() {
		return systematizationPhotos;
	}

	public void setSystematizationPhotos(
			List<SystematizationAfterPhoto> systematizationPhotos) {
		this.systematizationPhotos = systematizationPhotos;
	}
}