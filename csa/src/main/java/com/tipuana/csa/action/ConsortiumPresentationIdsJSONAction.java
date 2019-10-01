package com.tipuana.csa.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.ConsortiumDAO;


@SuppressWarnings("serial")
public class ConsortiumPresentationIdsJSONAction extends BasePresentationAction {
	
	private List<Long> ids = new ArrayList<Long>();
	private ConsortiumDAO consortiumDAO;
	
	public String execute() {
		
		setIds(getConsortiumDAO().getConsortiumsIds());
		
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
	public ConsortiumDAO getConsortiumDAO() {
		return consortiumDAO;
	}

	public void setConsortiumDAO(ConsortiumDAO consortiumDAO) {
		this.consortiumDAO = consortiumDAO;
	}
}