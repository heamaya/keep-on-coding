package com.tipuana.csa.action.setup;

import com.tipuana.csa.dao.LimitedResponsibilityCommercialSocietyDAO;
import com.tipuana.csa.model.CompanyType;
import com.tipuana.csa.model.LimitedResponsibilityCommercialSociety;

@SuppressWarnings("serial")
public class LimitedResponsibilityCommercialSocietyAction extends CommercialSocietyAction<LimitedResponsibilityCommercialSociety, LimitedResponsibilityCommercialSocietyDAO> {

	@Override
	public void prepare() throws Exception {
		if(getRequestId() != 0) {
			setModel(getCompanyDAO().findById(getRequestId(), true));
			setPropertiesFromAddress();
		} else {
			setModel(new LimitedResponsibilityCommercialSociety());
		}
		
	}

	@Override
	public String find() {
		getSession().put(getListName(), getCompanyDAO().find(getModelFromProperties(), CompanyType.LIMITED_RESPONSIBILITY, getMatchMode()));
		
		return list();
	}
}