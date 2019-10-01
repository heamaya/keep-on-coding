package com.tipuana.csa.action.setup;

import org.hibernate.criterion.MatchMode;

import com.tipuana.csa.dao.FarmerDAO;
import com.tipuana.csa.model.Farmer;

@SuppressWarnings("serial")
public class FarmerAction extends PersonAction<Farmer, FarmerDAO> {
	
	public FarmerAction() {
		setListName("farmers");
	}
	
	private MatchMode matchMode;

	@Override
	public void prepare() throws Exception {
		if(getRequestId() != 0) {
			setModel(getPersonDAO().findById(getRequestId(), true));
			setPropertiesFromAddress();
		} else {
			setModel(new Farmer());
		}
		
	}

	@Override
	public String find() {
		getSession().put(getListName(), getPersonDAO().find(getModelFromProperties(), matchMode));
		
		return list();
	}
	
	public MatchMode getMatchMode() {
		return matchMode;
	}

	public void setMatchMode(MatchMode matchMode) {
		this.matchMode = matchMode;
	}

}