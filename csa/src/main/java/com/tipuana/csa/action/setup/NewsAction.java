package com.tipuana.csa.action.setup;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.criterion.Order;
import org.springframework.web.context.ServletContextAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.PathAware;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.NewsDAO;
import com.tipuana.csa.model.News;


@SuppressWarnings("serial")
public class NewsAction extends CreateReadUpdateRemoveDeleteSearchAction 
		implements Preparable, ModelDriven<News>, PathAware, ServletContextAware 
{
	private News model;
	private NewsDAO newsDAO;
	private File image;
	private ServletContext servletContext;
	private Boolean keepImage;
	private String previousImageFileName;
	
	public NewsAction() {
		setListName("news");
	}

	@Override
	public String delete() throws Exception {
		getNewsDAO().makeTransient(getModel());
		
		if(getModel().getImageFileName() != null && !getModel().getImageFileName().equalsIgnoreCase("")) {
			FileUtil.delete(getPath(), getModel().getImageFileName());
		}

		return listAll();
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {
			getRequest().put(getListName(), getNewsDAO().findAll(Order.desc("created")));
		}
		
		
		return Constants.LIST;
	}

	@Override
	public void prepare() throws Exception {
		
		if(getRequestId() != 0L) {
			setModel(getNewsDAO().findById(getRequestId(), true));
		} else {
			setModel(new News());
		}
		
	}

	@Override
	public String create() throws Exception {
		
		getNewsDAO().makePersistent(getModel());
		
		if(getImage() != null) {
			FileUtil.save(getImage(), getPath(),  model.getImageFileName());	
		}
		
		return listAll();
	}
	
	@Override @SkipValidation
	public String edit() {
		setActionMethod(Constants.UPDATE);
		
		setPreviousImageFileName(getModel().getImageFileName());
		
		return Constants.SUCCESS;
	}

	@Override
	public String update() throws Exception {
		getNewsDAO().makePersistent(getModel());
		
		if(getImage() != null) {
			
			if(getPreviousImageFileName() != null && !getPreviousImageFileName().equalsIgnoreCase("")) {
				FileUtil.delete(getPath(), getPreviousImageFileName());
			}
			
			FileUtil.save(getImage(), getPath(),  model.getImageFileName());
		}

		return listAll();
	}

	@Override
	public String find() {
		getSession().put(getListName(), getNewsDAO().find(getModel(), getMatchMode()));
		
		return list();
	}

	public News getModel() {
		return model;
	}

	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setModel(News model) {
		this.model = model;
	}

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	@Override
	public String getPath() {
		return Constants.NEWS_PRESENTATION_PATH;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public Boolean getKeepImage() {
		return keepImage;
	}

	public void setKeepImage(Boolean keepImage) {
		this.keepImage = keepImage;
	}

	public String getPreviousImageFileName() {
		return previousImageFileName;
	}

	public void setPreviousImageFileName(String previousImageFileName) {
		this.previousImageFileName = previousImageFileName;
	}
}