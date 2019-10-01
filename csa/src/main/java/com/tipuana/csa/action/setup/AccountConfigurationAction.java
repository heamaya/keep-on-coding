package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.PersonDAO;
import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Province;
import com.tipuana.csa.model.User;


@SuppressWarnings("serial")
public class AccountConfigurationAction extends ReadUpdateAddressAction<User> {
	private UserDAO userDAO;
	private PersonDAO personDAO;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String primaryTelephoneNumber;
	private String secondaryTelephoneNumber;
	private String primaryCellPhoneNumber;
	private String secondaryCellPhoneNumber;
	private String secondaryEmail;
	private String comment;
	private String password;
	private String repeatPassword;
	private Boolean keepPassword;
	
	@Override
	public User getModelFromProperties() {
		User model = getModel();
		model.setUsername(getUsername());
		model.getPerson().setFirstName(getFirstName());
		model.getPerson().setLastName(getLastName());
		model.getPerson().setEmail(getUsername());
		model.getPerson().setPrimaryTelephoneNumber(getPrimaryTelephoneNumber());
		model.getPerson().setPrimaryCellPhoneNumber(getPrimaryCellPhoneNumber());
		model.getPerson().setSecondaryEmail(getSecondaryEmail());
		model.getPerson().setSecondaryTelephoneNumber(getSecondaryTelephoneNumber());
		model.getPerson().setSecondaryCellPhoneNumber(getSecondaryCellPhoneNumber());
		model.getPerson().setComment(getComment());
		
		if ( getProvince() == null && getCity() == null && 
			(getStreet() == null || getStreet().equalsIgnoreCase("")) && 
			 getStreetNumber() == null && 
			(getDepartment() == null || getDepartment().equalsIgnoreCase("")) && 
			 getFloor() == null) 
		{
			model.getPerson().setAddress(null);
		} else {
			model.getPerson().setAddress(getAddressFromProperties(model.getPerson().getAddress() == null ? new Address() : model.getPerson().getAddress()));
		}
		
		return model;
	}
	
	public void setPropertiesFromModel() {
		User model = getModel();
		
		setUsername(model.getUsername());
		setFirstName(model.getPerson().getFirstName());
		setLastName(model.getPerson().getLastName());
		setEmail(model.getPerson().getEmail());
		setPrimaryTelephoneNumber(model.getPerson().getPrimaryTelephoneNumber());
		setSecondaryTelephoneNumber(model.getPerson().getSecondaryTelephoneNumber());
		setPrimaryCellPhoneNumber(model.getPerson().getPrimaryCellPhoneNumber());
		setSecondaryCellPhoneNumber(model.getPerson().getSecondaryCellPhoneNumber());
		setSecondaryEmail(model.getPerson().getSecondaryEmail());
		setComment(model.getPerson().getComment());
		
		setPropertiesFromAddress();
	}

	@Override
	public void setPropertiesFromAddress() {
		Address address = getModel().getPerson().getAddress();
		
		if(address != null) {
			setProvince(address.getProvince());
			setCity(address.getCity());
			setFloor(address.getFloor());
			setDepartment(address.getDepartment());
			setStreetNumber(address.getStreetNumber());
			setStreet(address.getStreet());
		}
		
		getModel().getPerson().setAddress(address);
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

	@Override
	public String update() throws Exception {
		User model = getModelFromProperties();
		
		if(getKeepPassword().booleanValue() == false && getPassword() != null && !getPassword().equalsIgnoreCase("")) {
			model.setPassword(getPassword());
			getUserDAO().makePersistent(model);	
		} else {
			getPersonDAO().makePersistent(model.getPerson());
		}
		
		return list();
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		setPropertiesFromModel();
		
		return Constants.LIST;
	}

	@Override
	public User getModel() {
		return this.getAuthenticatedUser();
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public String edit() {
		setActionMethod(Constants.UPDATE);
		setPropertiesFromModel();	
		return Constants.SUCCESS;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrimaryTelephoneNumber() {
		return primaryTelephoneNumber;
	}

	public void setPrimaryTelephoneNumber(String primaryTelephoneNumber) {
		this.primaryTelephoneNumber = primaryTelephoneNumber;
	}

	public String getSecondaryTelephoneNumber() {
		return secondaryTelephoneNumber;
	}

	public void setSecondaryTelephoneNumber(String secondaryTelephoneNumber) {
		this.secondaryTelephoneNumber = secondaryTelephoneNumber;
	}

	public String getPrimaryCellPhoneNumber() {
		return primaryCellPhoneNumber;
	}

	public void setPrimaryCellPhoneNumber(String primaryCellPhoneNumber) {
		this.primaryCellPhoneNumber = primaryCellPhoneNumber;
	}

	public String getSecondaryCellPhoneNumber() {
		return secondaryCellPhoneNumber;
	}

	public void setSecondaryCellPhoneNumber(String secondaryCellPhoneNumber) {
		this.secondaryCellPhoneNumber = secondaryCellPhoneNumber;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public Boolean getKeepPassword() {
		return keepPassword;
	}

	public void setKeepPassword(Boolean keepPassword) {
		this.keepPassword = keepPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}