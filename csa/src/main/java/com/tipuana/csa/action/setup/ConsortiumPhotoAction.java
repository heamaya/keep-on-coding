package com.tipuana.csa.action.setup;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.PathAware;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.ConsortiumDAO;
import com.tipuana.csa.dao.ConsortiumPhotoDAO;
import com.tipuana.csa.model.Consortium;
import com.tipuana.csa.model.ConsortiumPhoto;


@SuppressWarnings("serial")
public class ConsortiumPhotoAction extends 
	CreateReadUpdateRemoveDeleteAction implements RequestAware, ServletContextAware, PathAware, Preparable, ModelDriven<ConsortiumPhoto> {

	private ConsortiumPhoto model;
	private Map<String, Object> request;
	private ConsortiumPhotoDAO consortiumPhotoDAO;
	private ConsortiumDAO consortiumDAO;
	private File photo;
	private String description;
	private ServletContext servletContext;
	private Long consortiumId;
	private String consortiumName;
	
	public ConsortiumPhotoAction() {
		setListName("photos");
	}
	
	@Override
	public void prepare() throws Exception {
		Consortium consortium = getConsortiumDAO().findById(getConsortiumId(), false);
		ConsortiumPhoto consortiumPhoto = null;
		
		if(getRequestId() != 0) {
			consortiumPhoto = getConsortiumPhotoDAO().findById(getRequestId(), true); 
			consortiumPhoto.setConsortium(consortium);
		} else {
			consortiumPhoto = new ConsortiumPhoto();
			consortiumPhoto.setConsortium(consortium);
		} 
		
		setModel(consortiumPhoto);
		setConsortiumName(consortium.getName());
			
	}

	@Override
	public String getPath() {
		return getModel().getConsortium().getPhotosPath();
	}
	
	@Override
	public String list() {
		setMappedRequest(Constants.LIST);
		
		getRequest().put(getListName(), (getConsortiumPhotoDAO()).find(getConsortiumId()));
		
		return Constants.LIST;
	}

	@Override
	public String create() throws Exception {
			
		ConsortiumPhoto model = getModel();
		FileUtil.save(getPhoto(), getPath(),  model.getPhotoFileName());		
		getConsortiumPhotoDAO().makePersistent(model);
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getConsortiumPhotoDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		ConsortiumPhoto model = getModel();
		getConsortiumPhotoDAO().makeTransient(model);
		FileUtil.delete(getPath(), model.getPhotoFileName());
		
		return listAll();
	}
	
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

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public ConsortiumPhoto getModel() {
		return model;
	}

	public ConsortiumPhotoDAO getConsortiumPhotoDAO() {
		return consortiumPhotoDAO;
	}

	public void setConsortiumPhotoDAO(ConsortiumPhotoDAO consortiumPhotoDAO) {
		this.consortiumPhotoDAO = consortiumPhotoDAO;
	}

	public ConsortiumDAO getConsortiumDAO() {
		return consortiumDAO;
	}

	public void setConsortiumDAO(ConsortiumDAO consortiumDAO) {
		this.consortiumDAO = consortiumDAO;
	}

	public Long getConsortiumId() {
		return consortiumId;
	}

	public void setConsortiumId(Long consortiumId) {
		this.consortiumId = consortiumId;
	}

	public String getConsortiumName() {
		return consortiumName;
	}

	public void setConsortiumName(String consortiumName) {
		this.consortiumName = consortiumName;
	}

	public void setModel(ConsortiumPhoto model) {
		this.model = model;
	}
	
}