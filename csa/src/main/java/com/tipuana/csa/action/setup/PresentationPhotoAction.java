package com.tipuana.csa.action.setup;

import java.io.File;

import com.tipuana.csa.action.PathAware;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.GenericDAO;
import com.tipuana.csa.model.PresentationPhoto;


@SuppressWarnings("serial")
public abstract class PresentationPhotoAction<M extends PresentationPhoto, D extends GenericDAO<M, Long>> extends 
	CreateReadUpdateRemoveDeleteAction implements PathAware {
	
	private M model;
	private D presentationPhotoDAO;
	private File photo;
	private String photoFileName;
	private String photoContentType;
	private String descriptionSpanish;
	private String descriptionEnglish;
	private String descriptionPortuguese;
	private String downloadFileAction;
	
	public PresentationPhotoAction() {
		setListName("presentationPhotos");
	}

	@Override
	public String create() throws Exception {
			
		M model = getModelFromProperties();
		FileUtil.save(getPhoto(), getPath(),  model.getName());		
		getPresentationPhotoDAO().makePersistent(model);
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getPresentationPhotoDAO().makePersistent(getModelFromProperties());
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		M model = getModelFromProperties();
		getPresentationPhotoDAO().makeTransient(model);
		FileUtil.delete(getPath(), model.getName());
		
		return listAll();
	}
	
	@Override
	public String list() {
		setMappedRequest(Constants.LIST);
		
		getRequest().put(getListName(), getPresentationPhotoDAO().findAll());
		
		return Constants.LIST;
	}

	public File getPhoto() {
		return photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	
	public String getDescriptionSpanish() {
		return descriptionSpanish;
	}

	public String getDescriptionEnglish() {
		return descriptionEnglish;
	}

	public String getDescriptionPortuguese() {
		return descriptionPortuguese;
	}

	public void setDescriptionSpanish(String descriptionSpanish) {
		this.descriptionSpanish = descriptionSpanish;
	}

	public void setDescriptionEnglish(String descriptionEnglish) {
		this.descriptionEnglish = descriptionEnglish;
	}

	public void setDescriptionPortuguese(String descriptionPortuguese) {
		this.descriptionPortuguese = descriptionPortuguese;
	}

	public M getModelFromProperties() {
		M model = getModel(); 
		
		model.setName(getPhotoFileName());
		model.setContentType(getPhotoContentType());
		
		model.setDescriptionEnglish(getDescriptionEnglish());
		model.setDescriptionSpanish(getDescriptionSpanish());
		model.setDescriptionPortuguese(getDescriptionPortuguese());
		
		setModel(model);
		
		return model;
	}

	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
	}

	public D getPresentationPhotoDAO() {
		return presentationPhotoDAO;
	}

	public void setPresentationPhotoDAO(D presentationPhotoDAO) {
		this.presentationPhotoDAO = presentationPhotoDAO;
	}
	
	public String getDownloadFileAction() {
		return downloadFileAction;
	}

	public void setDownloadFileAction(String downloadFileAction) {
		this.downloadFileAction = downloadFileAction;
	}

	public abstract String getPath();
}