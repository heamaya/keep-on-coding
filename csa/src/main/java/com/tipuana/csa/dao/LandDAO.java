package com.tipuana.csa.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.tipuana.csa.model.Land;


public interface LandDAO extends GenericDAO<Land, Long> {

	public List<Land> find(final long companyId);
	
	public List<Land> find(final Land land, final MatchMode matchMode);
}
