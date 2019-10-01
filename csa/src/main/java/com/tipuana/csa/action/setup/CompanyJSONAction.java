package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ActionSupport;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.CompanyDAO;
import com.tipuana.csa.model.Company;


@SuppressWarnings("serial")
public class CompanyJSONAction extends ActionSupport {
	private CompanyDAO companyDAO;
	private List<Company> companies;
	

	public CompanyJSONAction() {
		companies = new ArrayList<Company>();
	}

	public String execute() {
		
		setCompanies(getCompanyDAO().findAll(Order.asc("name")));
	
		
		return Constants.SUCCESS;
	}

	@JSON(serialize=false)
	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}


	@JSON(serialize=true, name="companies")
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
}