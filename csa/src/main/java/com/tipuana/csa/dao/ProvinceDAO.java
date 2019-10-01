package com.tipuana.csa.dao;

import java.util.List;

import com.tipuana.csa.model.Province;


public interface ProvinceDAO extends GenericDAO<Province, Long> {

	public List<Province> find(final Province state);
	
}
