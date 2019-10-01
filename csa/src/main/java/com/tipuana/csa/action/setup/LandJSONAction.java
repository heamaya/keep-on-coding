package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.LandDAO;
import com.tipuana.csa.model.Land;


@SuppressWarnings("serial")
public class LandJSONAction extends ActionSupport {
	private List<Land> lands;
	private LandDAO landDAO;
	private Long companyId;

	public LandJSONAction() {
		lands = new ArrayList<Land>();
	}

	public String execute() {
		
		if (companyId != null && companyId.longValue() != 0L) {
			setLands(getLandDAO().find(companyId.longValue()));
		}
		
		return Constants.SUCCESS;
	}

	@JSON(serialize=true, name="lands")
	public List<Land> getLands() {
		return lands;
	}

	public void setLands(List<Land> lands) {
		this.lands = lands;
	}

	@JSON(serialize=false)
	public LandDAO getLandDAO() {
		return landDAO;
	}

	public void setLandDAO(LandDAO landDAO) {
		this.landDAO = landDAO;
	}

	@JSON(serialize=false)
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}