package com.tipuana.csa.dao;

import java.util.List;
import java.util.Map;

import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.SystematizationProject;

public interface SystematizationProjectDAO extends GenericDAO<SystematizationProject, Long> {
	
	public List<SystematizationProject> find(long userId);
	
	public List<SystematizationProject> find(final SystematizationProject systematizationProject, final Map<String, FromToDateField> fromToDateFields);

}
