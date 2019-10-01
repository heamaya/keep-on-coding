package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.criterion.Order;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.CompanyDAO;
import com.tipuana.csa.dao.LegalPersonDAO;
import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.LegalPerson;

@SuppressWarnings("serial")
public class QuickLegalPersonAction extends AbstractLegalPersonAction<LegalPerson, LegalPersonDAO> {
	
	private List<Company> companies = new ArrayList<Company>();
	private CompanyDAO quickCompanyDAO;

	public CompanyDAO getQuickCompanyDAO() {
		return quickCompanyDAO;
	}

	public void setQuickCompanyDAO(CompanyDAO quickCompanyDAO) {
		this.quickCompanyDAO = quickCompanyDAO;
	}
	
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

	@Override
	public String find() {
		return null;
	}
}