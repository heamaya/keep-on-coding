package com.tipuana.csa.action;

import java.util.List;

import org.hibernate.criterion.Order;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.GenericDAO;
import com.tipuana.csa.model.PresentationPhoto;


@SuppressWarnings("serial")
public abstract class PresentationPhotoAction<M extends PresentationPhoto, D extends GenericDAO<M, Long>> extends BasePresentationPhotoAction {

	private D presentationPhotoDAO;

	@Override
	public String execute() {
		super.execute();
		/*
		Map<String, String> filterParameters = new HashMap<String, String>();
		filterParameters.put("entityName", getEntityName());
		String descriptionColumn = new StringBuilder("description").append(getLocale().getDisplayLanguage()).toString();
		filterParameters.put("descriptionColumn", descriptionColumn);
		Map<String, Map<String,String>> filters = new HashMap<String, Map<String,String>>();
		filters.put("InternationalizedPresentationPhoto", filterParameters);
		D presentationPhotoDAO = getPresentationPhotoDAO();
		presentationPhotoDAO.setFilters(filters);
		presentationPhotoDAO.setHasFilters(true);
		*/
		List<M> presentationPhotos = getPresentationPhotoDAO().findAll(Order.asc("created"));
		
		getRequest().put("presentationPhotos", presentationPhotos);
		
		return Constants.LIST;
	}

	public D getPresentationPhotoDAO() {
		return presentationPhotoDAO;
	}

	public void setPresentationPhotoDAO(D presentationPhotoDAO) {
		this.presentationPhotoDAO = presentationPhotoDAO;
	}
	
	@Override
	public abstract String getPath();
	
	public abstract String getEntityName();
}