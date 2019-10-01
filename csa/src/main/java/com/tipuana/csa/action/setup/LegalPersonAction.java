package com.tipuana.csa.action.setup;

import com.tipuana.csa.dao.LegalPersonDAO;
import com.tipuana.csa.model.CompanyType;
import com.tipuana.csa.model.LegalPerson;

@SuppressWarnings("serial")
public class LegalPersonAction extends AbstractLegalPersonAction<LegalPerson, LegalPersonDAO> {
	
	@Override
	public void prepare() throws Exception {
		if(getRequestId() != 0) {
			setModel(getCompanyDAO().findById(getRequestId(), true));
			setPropertiesFromAddress();
		} else {
			setModel(new LegalPerson());
		}
	}

	@Override
	public String find() {
		getSession().put(getListName(), getCompanyDAO().find(getModelFromProperties(), CompanyType.LEGAL_PERSON, getMatchMode()));
		
		return list();
	}

}
