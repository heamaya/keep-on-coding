package com.tipuana.csa.action.setup;

import com.tipuana.csa.dao.AnonymousCommercialSocietyDAO;
import com.tipuana.csa.model.AnonymousCommercialSociety;
import com.tipuana.csa.model.CompanyType;

@SuppressWarnings("serial")
public class AnonymousCommercialSocietyAction extends
	CommercialSocietyAction<AnonymousCommercialSociety, AnonymousCommercialSocietyDAO> 
{

	@Override
	public void prepare() throws Exception {
				
		if(getRequestId() != 0) {
			setModel(getCompanyDAO().findById(getRequestId(), true));
			setPropertiesFromAddress();
		} else {
			setModel(new AnonymousCommercialSociety());
		}
		
	}

	@Override
	public String find() {
		getSession().put(getListName(), getCompanyDAO().find(getModelFromProperties(), CompanyType.ANONYMOUS, getMatchMode()));
		
		return list();
	}
	
	

}