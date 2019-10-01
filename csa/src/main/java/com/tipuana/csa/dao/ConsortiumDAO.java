package com.tipuana.csa.dao;

import java.util.List;

import com.tipuana.csa.model.Consortium;


public interface ConsortiumDAO extends GenericDAO<Consortium, Long> {

	public List<Long> getConsortiumsIds();
}
