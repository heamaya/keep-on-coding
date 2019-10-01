package com.tipuana.csa.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.dao.VisitorMessageDAO;
import com.tipuana.csa.dao.awareness.CityDAOAware;
import com.tipuana.csa.dao.awareness.ProvinceDAOAware;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Province;
import com.tipuana.csa.model.VisitorMessage;


@SuppressWarnings("serial")
public class ContactUsPresentationAction extends BasePresentationAction implements ProvinceDAOAware, CityDAOAware {
	private String email;
	private String message;
	private String lastName;
	private String firstName;
	private Province province;
	private City city;
	private VisitorMessageDAO visitorMessageDAO;
	private ProvinceDAO provinceDAO;
	private CityDAO cityDAO;

	@Override
	public String execute() {
		super.execute();
		return Constants.LIST;
	}

	public String getEmail() {
		return email;
	}

	public String getMessage() {
		return message;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String create() {
		VisitorMessage visitorMessage = new VisitorMessage();
		visitorMessage.setEmail(getEmail());
		visitorMessage.setMessage(getMessage());
		visitorMessage.setLastName(getLastName());
		visitorMessage.setFirstName(getFirstName());
		visitorMessage.setProvince(getProvince());
		visitorMessage.setCity(getCity());		
		getVisitorMessageDAO().makePersistent(visitorMessage);
		
		return Constants.LIST;
	}

	public VisitorMessageDAO getVisitorMessageDAO() {
		return visitorMessageDAO;
	}

	public void setVisitorMessageDAO(VisitorMessageDAO visitorMessageDAO) {
		this.visitorMessageDAO = visitorMessageDAO;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public ProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}

	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	@Override
	public CityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	
	public List<Province> getProvinces() {
		return getProvinceDAO().findAll(Order.asc("name"));
	}

	public List<City> getCities() {	
		return new ArrayList<City>();
	}
}