package com.tipuana.csa.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.criterion.Order;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.VideoDAO;
import com.tipuana.csa.model.Video;



@SuppressWarnings("serial")
public class VideoPresentationAction extends BasePresentationAction implements RequestAware {
	
	private Map<String, Object> request;
	private VideoDAO videoDAO;
	
	@Override
	public String execute() {
		super.execute();
		
		List<Video> videos = getVideoDAO().findAll(Order.asc("created"));
		
		getRequest().put("videos", videos);
		
		return Constants.LIST;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public VideoDAO getVideoDAO() {
		return videoDAO;
	}

	public void setVideoDAO(VideoDAO videoDAO) {
		this.videoDAO = videoDAO;
	}

}