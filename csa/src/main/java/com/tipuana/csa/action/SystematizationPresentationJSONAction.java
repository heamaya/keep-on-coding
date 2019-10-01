
package com.tipuana.csa.action;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.SystematizationDAO;
import com.tipuana.csa.model.Systematization;


@SuppressWarnings("serial")
public class SystematizationPresentationJSONAction extends BasePresentationAction {
	
	private SystematizationDAO systematizationDAO;
	private Systematization systematization;
	private Long systematizationId;
	
	public String execute() {
		SystematizationDAO systematizationDAO = getSystematizationDAO(); 
		Systematization systematization = systematizationDAO.findById(getSystematizationId(), false);
		
		setSystematization(systematization);
		
		return Constants.SUCCESS;
	}

	@JSON(serialize=false)
	public SystematizationDAO getSystematizationDAO() {
		return systematizationDAO;
	}

	public void setSystematizationDAO(SystematizationDAO systematizationDAO) {
		this.systematizationDAO = systematizationDAO;
	}

	@JSON(serialize=true, name="systematization")
	public Systematization getSystematization() {
		return systematization;
	}

	public void setSystematization(Systematization systematization) {
		this.systematization = systematization;
	}

	@JSON(serialize=false)
	public Long getSystematizationId() {
		return systematizationId;
	}

	public void setSystematizationId(Long systematizationId) {
		this.systematizationId = systematizationId;
	}
}