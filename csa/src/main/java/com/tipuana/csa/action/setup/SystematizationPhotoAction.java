package com.tipuana.csa.action.setup;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.tipuana.csa.action.PathAware;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteAction;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.GenericDAO;
import com.tipuana.csa.dao.SystematizationDAO;
import com.tipuana.csa.model.SystematizationPhoto;


@SuppressWarnings("serial")
public abstract class SystematizationPhotoAction<M extends SystematizationPhoto, D extends GenericDAO<M, Long>> extends 
	CreateReadUpdateRemoveDeleteAction implements RequestAware, ServletContextAware, PathAware {

	private M model;
	private Map<String, Object> request;
	private D systematizationPhotoDAO;
	private SystematizationDAO systematizationDAO;
	private File photo;
	private String photoFileName;
	private String photoContentType;
	private String descriptionSpanish;
	private String descriptionEnglish;
	private String descriptionPortuguese;
	private ServletContext servletContext;
	private Long systematizationId;
	private String systematizationName;
	
	public SystematizationPhotoAction() {
		setListName("photos");
	}

	@Override
	public String create() throws Exception {
			
		M model = getModelFromProperties();
		FileUtil.save(getPhoto(), getPath(),  model.getSystematizationPhotoFileName());		
		getSystematizationPhotoDAO().makePersistent(model);
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getSystematizationPhotoDAO().makePersistent(getModelFromProperties());
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		M model = getModelFromProperties();
		getSystematizationPhotoDAO().makeTransient(model);
		FileUtil.delete(getPath(), model.getSystematizationPhotoFileName());
		
		return listAll();
	}
	
	@Override
	public abstract String list();

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getRequest() {
		return request;
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
		
		model.setSystematizationPhotoFileName(getPhotoFileName());
		model.setSystematizationPhotoContentType(getPhotoContentType());
		model.setDescriptionSpanish(getDescriptionSpanish());
		model.setDescriptionEnglish(getDescriptionEnglish());
		model.setDescriptionPortuguese(getDescriptionPortuguese());
		
		setModel(model);
		
		return model;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
	}

	public D getSystematizationPhotoDAO() {
		return systematizationPhotoDAO;
	}

	public void setSystematizationPhotoDAO(D systematizationPhotoDAO) {
		this.systematizationPhotoDAO = systematizationPhotoDAO;
	}
	
	public Long getSystematizationId() {
		return systematizationId;
	}

	public void setSystematizationId(Long systematizationId) {
		this.systematizationId = systematizationId;
	}
	
	public SystematizationDAO getSystematizationDAO() {
		return systematizationDAO;
	}

	public void setSystematizationDAO(SystematizationDAO systematizationDAO) {
		this.systematizationDAO = systematizationDAO;
	}

	public abstract String getPath();

	public String getSystematizationName() {
		return systematizationName;
	}

	public void setSystematizationName(String systematizationName) {
		this.systematizationName = systematizationName;
	}
	
	
}