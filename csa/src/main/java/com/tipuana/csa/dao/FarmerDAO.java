package com.tipuana.csa.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.tipuana.csa.model.Farmer;

public interface FarmerDAO extends GenericDAO<Farmer, Long> {
	
	public abstract List<Farmer> findNotInCommercialSociety(long commercialSocietyId);
	
	public abstract List<Farmer> findIn(List<Long> farmerIds);
	
	public abstract List<Farmer> find(Farmer farmer, MatchMode matchMode);

}
