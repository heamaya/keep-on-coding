package com.tipuana.csa.dao;

import java.util.List;

import com.tipuana.csa.model.SystematizationAfterPhoto;


public interface SystematizationAfterPhotoDAO extends GenericDAO<SystematizationAfterPhoto, Long>{

	public List<SystematizationAfterPhoto> find(final long systematizationId);
}
