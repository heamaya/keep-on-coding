package com.tipuana.csa.action;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.ConsortiumDAO;
import com.tipuana.csa.model.Consortium;


@SuppressWarnings("serial")
public class ConsortiumPresentationJSONAction extends BasePresentationAction {
	
	private ConsortiumDAO consortiumDAO;
	private Consortium consortium;
	private Long consortiumId;
	
	public String execute() {
		setConsortium(getConsortiumDAO().findById(getConsortiumId(), true));
		
		return Constants.SUCCESS;
	}

	@JSON(serialize=false)
	public ConsortiumDAO getConsortiumDAO() {
		return consortiumDAO;
	}

	public void setConsortiumDAO(ConsortiumDAO consortiumDAO) {
		this.consortiumDAO = consortiumDAO;
	}

	@JSON(serialize=true, name="consortium")
	public Consortium getConsortium() {
		return consortium;
	}

	@JSON(serialize=false)
	public Long getConsortiumId() {
		return consortiumId;
	}

	public void setConsortiumId(Long consortiumId) {
		this.consortiumId = consortiumId;
	}

	public void setConsortium(Consortium consortium) {
		this.consortium = consortium;
	}
}