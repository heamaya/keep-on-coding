package com.tipuana.csa.dao;

import java.util.List;

import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Province;


public interface CityDAO extends GenericDAO<City, Long> {

	public List<City> find(final Province province);
	
	public List<City> find(final City city);
	
	public List<City> find(final long provinceId);
}
