package com.tipuana.csa.action;

import java.util.List;

import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ActionContext;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.NewsDAO;
import com.tipuana.csa.model.News;

@SuppressWarnings("serial")
public class NewsPresentationAction extends BasePresentationPhotoAction {
	private NewsDAO newsDAO; 

	@Override
	public String execute() {
		super.execute();
		
		if(getSession().get("news") != null) {
			getRequest().put("news", getSession().get("news")); 
		} else {
			List<News> news = getNewsDAO().findAll(Order.desc("created"));
			getSession().put("news", news);
			getRequest().put("news", news);
		}
		
		String currentActionName = ActionContext.getContext()
												.getActionInvocation()
												.getProxy()
												.getActionName();

		if(currentActionName.equalsIgnoreCase("NextNews")) {
			currentActionName = "News";
		} else if(currentActionName.equalsIgnoreCase("SeguinteNoticia")) {
			currentActionName = "AsNoticias";
		} else if(currentActionName.equalsIgnoreCase("SiguienteNoticia")) {
			currentActionName = "Noticias";
		}
		
		
		getSession().put("currentAction", currentActionName);
		
		return Constants.LIST;
	}

	@Override
	public String getPath() {
		return Constants.NEWS_PRESENTATION_PATH;
	}

	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
}