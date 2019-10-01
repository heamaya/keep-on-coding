package com.tipuana.csa.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.SystematizationDAO;


@SuppressWarnings("serial")
public class SystematizationPresentationIdsJSONAction extends BasePresentationAction {
	
	private List<Long> ids = new ArrayList<Long>();
	private SystematizationDAO systematizationDAO;
	
	public String execute() {
		
		setIds(getSystematizationDAO().getSystematizationsIds());
		
		return Constants.LIST;
	}

	@JSON(serialize=true, name="ids")
	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	@JSON(serialize=false)
	public SystematizationDAO getSystematizationDAO() {
		return systematizationDAO;
	}

	public void setSystematizationDAO(SystematizationDAO systematizationDAO) {
		this.systematizationDAO = systematizationDAO;
	}
}