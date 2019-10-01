package com.tipuana.csa.dao;

import java.util.List;

import com.tipuana.csa.model.SystematizationBeforePhoto;


public interface SystematizationBeforePhotoDAO extends GenericDAO<SystematizationBeforePhoto, Long> {
	
	public List<SystematizationBeforePhoto> find(final long systematizationId);
	
}
