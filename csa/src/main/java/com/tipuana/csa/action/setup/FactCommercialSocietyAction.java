package com.tipuana.csa.action.setup;

import com.tipuana.csa.dao.FactCommercialSocietyDAO;
import com.tipuana.csa.model.CompanyType;
import com.tipuana.csa.model.FactCommercialSociety;

@SuppressWarnings("serial")
public class FactCommercialSocietyAction extends CommercialSocietyAction<FactCommercialSociety, FactCommercialSocietyDAO> {

	@Override
	public void prepare() throws Exception {
		
		if(getRequestId() != 0) {
			setModel(getCompanyDAO().findById(getRequestId(), true));
			setPropertiesFromAddress();
		} else {
			setModel(new FactCommercialSociety());
		}
		
	}

	@Override
	public String find() {
		getSession().put(getListName(), getCompanyDAO().find(getModelFromProperties(), CompanyType.FACT, getMatchMode()));
		
		return list();
	}

}
