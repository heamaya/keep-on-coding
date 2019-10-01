package com.tipuana.csa.dao;

import java.util.List;
import java.util.Map;

import com.tipuana.csa.model.Dike;
import com.tipuana.csa.model.FromToDateField;

public interface DikeDAO extends GenericDAO<Dike, Long> {
	
	public List<Dike> find(long userId);
	
	public List<Dike> find(final Dike dike, final Map<String, FromToDateField> fromToDateFieldMap);

}
