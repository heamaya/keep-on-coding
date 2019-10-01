package com.tipuana.csa.action.setup;

import com.tipuana.csa.dao.AnonymousCommercialSocietyDAO;
import com.tipuana.csa.model.AnonymousCommercialSociety;

@SuppressWarnings("serial")
public class QuickAnonymousCommercialSocietyAction extends QuickCommercialSocietyAction<AnonymousCommercialSociety, AnonymousCommercialSocietyDAO> {
	
	@Override
	public void prepare() throws Exception {
		
		if(getRequestId() != 0) {
			setModel(getCompanyDAO().findById(getRequestId(), true));
			setPropertiesFromAddress();
		} else {
			setModel(new AnonymousCommercialSociety());
		}
		
	}

}