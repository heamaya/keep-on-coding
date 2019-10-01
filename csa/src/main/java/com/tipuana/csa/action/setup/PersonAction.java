package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.GenericDAO;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Person;
import com.tipuana.csa.model.Province;
import com.tipuana.csa.model.TaxCondition;

@SuppressWarnings("serial")
public abstract class PersonAction<M extends Person, D extends GenericDAO<M, Long>> extends AddressAction<M>
		implements ModelDriven<M>, Preparable {
	
	private D personDAO;
	
	@Override
	public String create() throws Exception {
		M model = getModelFromProperties();
		
		getPersonDAO().makePersistent(model);
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		M model = getModelFromProperties();
		
		getPersonDAO().makePersistent(model);
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		getPersonDAO().makeTransient(getModel());
		
		return listAll();
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.asc("lastName"));
			orders.add(Order.asc("firstName"));
			
			getRequest().put(getListName(), getPersonDAO().findAll(orders));			
		}
		
		return Constants.LIST;
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

	public D getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(D personDAO) {
		this.personDAO = personDAO;
	}
}