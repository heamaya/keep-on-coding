package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.criterion.Order;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.CompanyDAO;
import com.tipuana.csa.dao.GenericCompanyDAO;
import com.tipuana.csa.model.CommercialSociety;
import com.tipuana.csa.model.Company;

@SuppressWarnings("serial")
public abstract class QuickCommercialSocietyAction<M extends CommercialSociety, D extends GenericCompanyDAO<M, Long>> extends CommercialSocietyAction<M, D> {

	private CompanyDAO quickCompanyDAO;
	private List<Company> companies = new ArrayList<Company>();
	
	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		setCompanies(getQuickCompanyDAO().findAll(Order.asc("name")));
		
		return Constants.LIST;
	}

	@JSON(serialize=true, name="companies")
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public CompanyDAO getQuickCompanyDAO() {
		return quickCompanyDAO;
	}

	public void setQuickCompanyDAO(CompanyDAO quickCompanyDAO) {
		this.quickCompanyDAO = quickCompanyDAO;
	}

	@Override
	public String find() {
		return null;
	}
}