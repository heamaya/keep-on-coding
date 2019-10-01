package com.tipuana.csa.action.setup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.criterion.Order;
import org.springframework.web.context.ServletContextAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.GenericCompanyDAO;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.Province;
import com.tipuana.csa.model.TaxCondition;


@SuppressWarnings("serial")
public abstract class CompanyAction<M extends Company, D extends GenericCompanyDAO<M, Long>> extends AddressAction<M>
		implements ModelDriven<M>, Preparable, ServletContextAware {
	
	private D companyDAO;
	private ServletContext servletContext;
	private String previousPath;
	
	public CompanyAction() {
		setListName("companies");
	}

	@Override
	public String create() throws Exception {
		M model = getModelFromProperties();
		
		getCompanyDAO().makePersistent(model);
		
		File currentCompanyRealPath = new File(model.getPath());
		currentCompanyRealPath.mkdir();
		
		return listAll();
	}
	
	@Override
	public String edit() {
		setActionMethod(Constants.UPDATE);
		
		setPreviousPath(getModel().getPath());
		
		return Constants.SUCCESS;
	}

	@Override
	public String update() throws Exception {
		M model = getModelFromProperties();
		
		getCompanyDAO().makePersistent(model);
		
		File previousCompanyRealPath = new File(getPreviousPath());
		File currentCompanyRealPath = new File(model.getPath());
		
		previousCompanyRealPath.renameTo(currentCompanyRealPath);
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		getCompanyDAO().makeTransient(getModel());
		
		return listAll();
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.asc("name"));
			
			getRequest().put(getListName(), getCompanyDAO().findAll(orders));			
		}
		
		return Constants.LIST;
	}

	public D getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(D companyDAO) {
		this.companyDAO = companyDAO;
	}

	@Override
	public M getModelFromProperties() {
		M model = getModel();

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

	public List<TaxCondition> getTaxConditions() {
		return Arrays.asList(TaxCondition.values());
	}
	
	public List<Province> getProvinces() {
		return getProvinceDAO().findAll(Order.asc("name"));
	}

	public List<City> getCities() {
		
		if (getProvince() != null) {
			return getCityDAO().find(getProvince().getId().longValue());
		}
		
		return new ArrayList<City>();
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String getPreviousPath() {
		return previousPath;
	}

	public void setPreviousPath(String previousPath) {
		this.previousPath = previousPath;
	}
}