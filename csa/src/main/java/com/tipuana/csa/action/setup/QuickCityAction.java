package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Province;


@SuppressWarnings("serial")
public class QuickCityAction extends CreateReadUpdateRemoveDeleteAction
		implements Preparable {

	private City model;
	private Long provinceId;
	private Province province;
	private ProvinceDAO provinceDAO;
	private CityDAO cityDAO;
	private List<City> cities;
	private String cityName;

	public QuickCityAction() {
		setCities(new ArrayList<City>());
	}

	public String create() throws Exception {
		getCityDAO().makePersistent(getModelFromProperties());
		
		return list();
	}

	public String delete() throws Exception {
		getCityDAO().makeTransient(getModel());
		
		return list();
	}

	public String list() {
		setActionMethod(Constants.LIST);
		
		setCities(getCityDAO().find(getModel().getProvince()));
		
		return Constants.LIST;
	}

	public String update() throws Exception {
		getCityDAO().makePersistent(getModelFromProperties());
		
		return list();
	}

	public void prepare() throws Exception {
		setProvince((Province) getProvinceDAO().findById(getProvinceId(), true));
		
		if (getRequestId() != 0L) {
			setModel((City) getCityDAO().findById(Long.valueOf(getRequestId()), true));
			setCityName(getModel().getName());
		} else {
			City city = new City();
			city.setProvince(getProvince());
			setModel(city);
		}
		
	}

	@JSON(serialize=false)
	public City getModel() {
		return model;
	}

	public void setModel(City model) {
		this.model = model;
	}

	@JSON(serialize=false)
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	@JSON(serialize=false)
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@JSON(serialize=false)
	public ProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}

	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	@JSON(serialize=false)
	public CityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	@JSON(serialize=true, name="cities")
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public City getModelFromProperties() {
		City model = getModel();
		
		model.setName(getCityName());
	
		return model;
	}
	
}