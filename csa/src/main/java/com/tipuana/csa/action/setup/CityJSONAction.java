package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.model.City;


@SuppressWarnings("serial")
public class CityJSONAction extends ActionSupport {
	private List<City> cities;
	private CityDAO cityDAO;
	private Long provinceId;

	public CityJSONAction() {
		cities = new ArrayList<City>();
	}

	public String execute() {
		
		if (provinceId != null && provinceId.longValue() != 0L) {
			setCities(getCityDAO().find(provinceId.longValue()));
		}
		
		return Constants.SUCCESS;
	}

	@JSON(serialize=true, name="cities")
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@JSON(serialize=false)
	public CityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	@JSON(serialize=false)
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

}