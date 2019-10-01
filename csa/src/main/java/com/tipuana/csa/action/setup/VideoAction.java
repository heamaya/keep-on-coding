package com.tipuana.csa.action.setup;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.VideoDAO;
import com.tipuana.csa.model.Video;


@SuppressWarnings("serial")
public class VideoAction extends CreateReadUpdateRemoveDeleteAction implements RequestAware, ModelDriven<Video>, Preparable {
	
	private Video model;
	private VideoDAO videoDAO;
	private Map<String, Object> request;
	
	public VideoAction() {
		setListName("videos");
	}

	@Override
	public String create() throws Exception {
		getVideoDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getVideoDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		getVideoDAO().makeTransient(model);

		return listAll();
	}
	
	@Override
	public String list() {
		setMappedRequest(Constants.LIST);
		
		getRequest().put(getListName(), getVideoDAO().findAll());
		
		return Constants.LIST;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Video getModel() {
		return model;
	}

	public void setModel(Video model) {
		this.model = model;
	}

	public VideoDAO getVideoDAO() {
		return videoDAO;
	}

	public void setVideoDAO(VideoDAO videoDAO) {
		this.videoDAO = videoDAO;
	}

	@Override
	public void prepare() throws Exception {
		
		if(getRequestId() != 0) {
			setModel(getVideoDAO().findById(getRequestId(), true));
		} else {
			setModel(new Video());
		}
		
	}
}