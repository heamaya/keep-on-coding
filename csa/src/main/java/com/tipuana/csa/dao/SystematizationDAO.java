package com.tipuana.csa.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.tipuana.csa.model.Systematization;


public interface SystematizationDAO extends GenericDAO<Systematization, Long> {
	
	public List<Long> getSystematizationsIds();
	
	public List<Systematization> find(final Systematization systematization, final MatchMode matchMode);

}
