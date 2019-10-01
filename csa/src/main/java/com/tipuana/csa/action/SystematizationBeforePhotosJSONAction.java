
package com.tipuana.csa.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.SystematizationBeforePhotoDAO;
import com.tipuana.csa.model.SystematizationBeforePhoto;


@SuppressWarnings("serial")
public class SystematizationBeforePhotosJSONAction extends BasePresentationAction {
	
	private Long systematizationId;
	private SystematizationBeforePhotoDAO systematizationPhotoDAO;
	private List<SystematizationBeforePhoto> systematizationPhotos;
	
	public String execute() {
		setSystematizationPhotos(getSystematizationPhotoDAO().find(getSystematizationId()));
		return Constants.SUCCESS;
	}

	@JSON(serialize=false)
	public SystematizationBeforePhotoDAO getSystematizationPhotoDAO() {
		return systematizationPhotoDAO;
	}

	public void setSystematizationPhotoDAO(
			SystematizationBeforePhotoDAO systematizationPhotoDAO) {
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
	public List<SystematizationBeforePhoto> getSystematizationPhotos() {
		return systematizationPhotos;
	}

	public void setSystematizationPhotos(
			List<SystematizationBeforePhoto> systematizationPhotos) {
		this.systematizationPhotos = systematizationPhotos;
	}
}