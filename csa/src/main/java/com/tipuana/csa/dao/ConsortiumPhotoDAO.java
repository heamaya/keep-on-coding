package com.tipuana.csa.dao;

import java.util.List;

import com.tipuana.csa.model.ConsortiumPhoto;


public interface ConsortiumPhotoDAO extends GenericDAO<ConsortiumPhoto, Long> {
	
	public List<ConsortiumPhoto> find(final long consortiumId);
	
}