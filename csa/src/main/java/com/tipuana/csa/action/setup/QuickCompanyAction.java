package com.tipuana.csa.action.setup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.AnonymousCommercialSocietyDAO;
import com.tipuana.csa.dao.CompanyDAO;
import com.tipuana.csa.dao.FactCommercialSocietyDAO;
import com.tipuana.csa.dao.LegalPersonDAO;
import com.tipuana.csa.dao.LimitedResponsibilityCommercialSocietyDAO;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.AnonymousCommercialSociety;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.CompanyType;
import com.tipuana.csa.model.FactCommercialSociety;
import com.tipuana.csa.model.Farmer;
import com.tipuana.csa.model.LegalPerson;
import com.tipuana.csa.model.LimitedResponsibilityCommercialSociety;
import com.tipuana.csa.model.Province;
import com.tipuana.csa.model.TaxCondition;


@SuppressWarnings("serial")
public class QuickCompanyAction extends AddressAction<Company> implements
		ModelDriven<Company>, Preparable, ServletContextAware {
	
	private CompanyDAO companyDAO;
	private LegalPersonDAO legalPersonDAO;
	private FactCommercialSocietyDAO factCommercialSocietyDAO;
	private AnonymousCommercialSocietyDAO anonymousCommercialSocietyDAO;
	private LimitedResponsibilityCommercialSocietyDAO limitedResponsibilityCommercialSocietyDAO;
	private CompanyType companyType;
	private Company company;
	private ServletContext servletContext;
	private String previousPath;
	private List<Company> companies;

	public QuickCompanyAction() {
		companies = new ArrayList<Company>();
	}
	
	public List<Farmer> getFarmers() {
		return null;
	}

	@Override
	public String create() throws Exception {
		
		Company model = getModelFromProperties();
		
		if(getCompanyType() == CompanyType.LEGAL_PERSON) 
		{
			getLegalPersonDAO().makePersistent((LegalPerson) model);
		} else if(getCompanyType() == CompanyType.FACT) 
		{
			getFactCommercialSocietyDAO().makePersistent((FactCommercialSociety) model);
		} else if(getCompanyType() == CompanyType.ANONYMOUS) 
		{
			getAnonymousCommercialSocietyDAO().makePersistent((AnonymousCommercialSociety) model);
		} else if(getCompanyType() == CompanyType.LIMITED_RESPONSIBILITY) 
		{
			getLimitedResponsabilityCommercialSocietyDAO().makePersistent((LimitedResponsibilityCommercialSociety) model);
		}
		
		File currentCompanyRealPath = new File(model.getPath());
		currentCompanyRealPath.mkdir();
		
		return list();
	}
	
	@Override
	public String edit() {
		setActionMethod(Constants.UPDATE);
		
		setPreviousPath(getModel().getPath());
		
		return Constants.SUCCESS;
	}

	@Override
	public String update() throws Exception {
		Company model = getModelFromProperties();
		
		if(getCompanyType() == CompanyType.LEGAL_PERSON) {
			getLegalPersonDAO().makePersistent((LegalPerson) model);
		} else if(getCompanyType() == CompanyType.FACT) {
			getFactCommercialSocietyDAO().makePersistent((FactCommercialSociety) model);
		} else if(getCompanyType() == CompanyType.ANONYMOUS) {
			getAnonymousCommercialSocietyDAO().makePersistent((AnonymousCommercialSociety) model);
		} else if(getCompanyType() == CompanyType.LIMITED_RESPONSIBILITY) {
			getLimitedResponsabilityCommercialSocietyDAO().makePersistent((LimitedResponsibilityCommercialSociety) model);
		}
		
		File previousCompanyRealPath = new File(getPreviousPath());
		File currentCompanyRealPath = new File(model.getPath());
		
		previousCompanyRealPath.renameTo(currentCompanyRealPath);
		
		return list();
	}

	@Override
	public String delete() throws Exception {
		
		Company model = getModel();
		
		if(getCompanyType() == CompanyType.LEGAL_PERSON) {
			getLegalPersonDAO().makeTransient((LegalPerson) model);
		} else if(getCompanyType() == CompanyType.FACT) {
			getFactCommercialSocietyDAO().makeTransient((FactCommercialSociety) model);
		} else if(getCompanyType() == CompanyType.ANONYMOUS) {
			getAnonymousCommercialSocietyDAO().makeTransient((AnonymousCommercialSociety) model);
		} else if(getCompanyType() == CompanyType.LIMITED_RESPONSIBILITY) {
			getLimitedResponsabilityCommercialSocietyDAO().makeTransient((LimitedResponsibilityCommercialSociety) model);
		}
		
		return list();
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		setCompanies(getCompanyDAO().findAll(Order.asc("name")));
		
		return Constants.LIST;
	}


	@Override @JSON(serialize=false)
	public Company getModelFromProperties() {
		Company model = getModel();
		
		if(model == null) {
			
			if(getCompanyType() == CompanyType.LEGAL_PERSON) {
				model = new LegalPerson();
			} else if(getCompanyType() == CompanyType.FACT) {
				model = new FactCommercialSociety();
			} else if(getCompanyType() == CompanyType.ANONYMOUS) {
				model = new AnonymousCommercialSociety();
			} else if(getCompanyType() == CompanyType.LIMITED_RESPONSIBILITY) {
				model = new LimitedResponsibilityCommercialSociety();
			}
			
		}

		if ( getProvince() == null && getCity() == null && 
			(getStreet() == null || getStreet().equalsIgnoreCase("")) && 
			 getStreetNumber() == null && 
			(getDepartment() == null || getDepartment().equalsIgnoreCase("")) && 
			 getFloor() == null) 
		{
			model.setAddress(null);
		} else {
			model.setAddress(getAddressFromProperties(model.getAddress() == null ? new Address() : model.getAddress()));
		}
		
		return model;
	}

	@Override
	public void setPropertiesFromAddress() {
		Address address = getModel().getAddress();
		
		if(address != null) {
			setProvince(address.getProvince());
			setCity(address.getCity());
			setFloor(address.getFloor());
			setDepartment(address.getDepartment());
			setStreetNumber(address.getStreetNumber());
			setStreet(address.getStreet());
		}
		
		getModel().setAddress(address);
	}
	
	@JSON(serialize=false)
	public List<TaxCondition> getTaxConditions() {
		return Arrays.asList(TaxCondition.values());
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@JSON(serialize=false)
	public String getPreviousPath() {
		return previousPath;
	}

	public void setPreviousPath(String previousPath) {
		this.previousPath = previousPath;
	}

	@JSON(serialize=false)
	public LegalPersonDAO getLegalPersonDAO() {
		return legalPersonDAO;
	}

	public void setLegalPersonDAO(LegalPersonDAO legalPersonDAO) {
		this.legalPersonDAO = legalPersonDAO;
	}

	@JSON(serialize=false)
	public FactCommercialSocietyDAO getFactCommercialSocietyDAO() {
		return factCommercialSocietyDAO;
	}

	public void setFactCommercialSocietyDAO(
			FactCommercialSocietyDAO factCommercialSocietyDAO) {
		this.factCommercialSocietyDAO = factCommercialSocietyDAO;
	}

	@JSON(serialize=false) 
	public AnonymousCommercialSocietyDAO getAnonymousCommercialSocietyDAO() {
		return anonymousCommercialSocietyDAO;
	}

	public void setAnonymousCommercialSocietyDAO(
			AnonymousCommercialSocietyDAO anonymousCommercialSocietyDAO) {
		this.anonymousCommercialSocietyDAO = anonymousCommercialSocietyDAO;
	}

	@JSON(serialize=false)
	public LimitedResponsibilityCommercialSocietyDAO getLimitedResponsabilityCommercialSocietyDAO() {
		return limitedResponsibilityCommercialSocietyDAO;
	}

	public void setLimitedResponsabilityCommercialSocietyDAO(
			LimitedResponsibilityCommercialSocietyDAO limitedResponsibilityCommercialSocietyDAO) {
		this.limitedResponsibilityCommercialSocietyDAO = limitedResponsibilityCommercialSocietyDAO;
	}

	@JSON(serialize=false)
	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	@JSON(serialize=false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@JSON(serialize=true, name="companies")
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	@JSON(serialize=false)
	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
	
	@Override @JSON(serialize=false)
	public List<Province> getProvinces() {
		return getProvinceDAO().findAll(Order.asc("name"));
	}

	@Override @JSON(serialize=false)
	public List<City> getCities() {
		
		if (getProvince() != null) {
			return getCityDAO().find(getProvince().getId().longValue());
		}
		
		return new ArrayList<City>();
	}
	
	public List<CompanyType> getCompanyTypes() {
		return Arrays.asList(CompanyType.values());
	}

	public void prepare() throws Exception {
		
		if (getRequestId() != 0L) {
			Company company = getCompanyDAO().findById(getRequestId(), true);
			
			//setName(company.getName());
			
			if(company.getAddress() != null) {
				setStreet(company.getAddress().getStreet());
				setStreetNumber(company.getAddress().getStreetNumber());
				setFloor(company.getAddress().getFloor());
				setDepartment(company.getAddress().getDepartment());
				setProvince(company.getAddress().getProvince());
				setCity(company.getAddress().getCity());
			}
			
			setModel(company);
		}
			
	}

	@Override
	public String find() {
		return null;
	}

}