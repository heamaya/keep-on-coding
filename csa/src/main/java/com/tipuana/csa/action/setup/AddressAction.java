package com.tipuana.csa.action.setup;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.dao.awareness.CityDAOAware;
import com.tipuana.csa.dao.awareness.ProvinceDAOAware;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Province;


@SuppressWarnings("serial")
public abstract class AddressAction<M> extends CreateReadUpdateRemoveDeleteSearchAction implements ProvinceDAOAware, CityDAOAware {
	private M model;
	private Province province;
	private City city;
	private String street;
	private Integer streetNumber;
	private String department;
	private Integer floor;
	private ProvinceDAO provinceDAO;
	private CityDAO cityDAO;
	
	@JSON(serialize=false)
	public Province getProvince() {
		return province;
	}
	
	public void setProvince(Province province) {
		this.province = province;
	}
	
	@JSON(serialize=false)
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	@JSON(serialize=false)
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	@JSON(serialize=false)
	public Integer getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	@JSON(serialize=false)
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@JSON(serialize=false)
	public Integer getFloor() {
		return floor;
	}
	
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	
	@Override
	public ProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}
	
	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}
	
	@Override @JSON(serialize=false)
	public CityDAO getCityDAO() {
		return cityDAO;
	}
	
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	
	public abstract M getModelFromProperties();
	
	public abstract void setPropertiesFromAddress();

	@JSON(serialize=false)
	public Address getAddressFromProperties(Address address) {
		address.setProvince(getProvince());
		address.setCity(getCity());
		address.setDepartment(getDepartment());
		address.setFloor(getFloor());
		address.setStreet(getStreet());
		address.setStreetNumber(getStreetNumber());
		
		return address;
	}
		
	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
	}
	
	
	public abstract List<Province> getProvinces();
	
	public abstract List<City> getCities();
}