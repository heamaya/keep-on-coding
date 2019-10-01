package com.tipuana.csa.dao;

import java.util.List;
import java.util.Map;

import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.HeaderGullyWork;

public interface HeaderGullyWorkDAO extends GenericDAO<HeaderGullyWork, Long> {
	
	public List<HeaderGullyWork> find(long userId);
	
	public List<HeaderGullyWork> find(final HeaderGullyWork headerGullyWork, final Map<String, FromToDateField> fromToDateFieldMap);

}
