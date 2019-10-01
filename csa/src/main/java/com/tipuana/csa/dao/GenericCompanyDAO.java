package com.tipuana.csa.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.CompanyType;

public interface GenericCompanyDAO<C extends Company, ID extends Serializable> extends
		GenericDAO<C, ID> {
	
	public List<C> find(final C company, final CompanyType companyType, final MatchMode matchMode);
	
}
