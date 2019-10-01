package com.tipuana.csa.action.setup;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.ConsortiumDAO;
import com.tipuana.csa.model.Consortium;


@SuppressWarnings("serial")
public class ConsortiumAction extends
		CreateReadUpdateRemoveDeleteSearchAction implements Preparable,
		ModelDriven<Consortium>, RequestAware, ServletContextAware {
	
	private Consortium model;
	private ConsortiumDAO consortiumDAO;
	private Map<String, Object> request;
	private ServletContext servletContext;
	private String previousLimitFileName;
	private String previousPath;
	private Boolean keepLimit;
	private File limit;

	public ConsortiumAction() {
		setListName("consortiums");
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void prepare() throws Exception {

		if(getRequestId() != 0) {
			setModel(getConsortiumDAO().findById(getRequestId(), true));
		} else {
			setModel(new Consortium());
		}

	}

	@Override
	public String create() throws Exception {
		Consortium model = getModel();
		getConsortiumDAO().makePersistent(model);
		
		File currentRealPath =  new File(model.getPath());
		File currentPhotosRealPath =  new File(model.getPhotosPath());
		
		currentRealPath.mkdir();
		currentPhotosRealPath.mkdir();
		
		File limit = getLimit();
		
		if(limit != null) {
			FileUtil.save(limit, currentRealPath.getPath(), model.getLimitFileName());
		}
		
		return listAll();
	}
	
	@Override
	public String edit() {
		setActionMethod(Constants.UPDATE);
		
		setPreviousLimitFileName(getModel().getLimitFileName());
		setPreviousPath(getModel().getPath());
		
		return Constants.SUCCESS;
	}

	@Override
	public String update() throws Exception {
		
		Consortium model = getModel();
		getConsortiumDAO().makePersistent(model);
		
		File currentRealPath =  new File(model.getPath());
		File previousRealPath = new File(getPreviousPath());
		
		previousRealPath.renameTo(currentRealPath);
		
		File limit = getLimit();
		
		if(limit != null) {
			
			String previousLimitFileName = getPreviousLimitFileName();
			
			if(previousLimitFileName != null && !previousLimitFileName.equalsIgnoreCase("")) {
				FileUtil.delete(currentRealPath.getPath(), previousLimitFileName);
			}
			
			FileUtil.save(limit, currentRealPath.getPath(), model.getLimitFileName());
		}
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		Consortium model = getModel();
		getConsortiumDAO().makeTransient(model);
		
		File currentRealPath =  new File(model.getPath());
		String limitFileName = model.getLimitFileName();
		
		if(limitFileName != null && !limitFileName.equalsIgnoreCase("")) {
			FileUtil.delete(currentRealPath.getPath(), limitFileName);
		}
		
		File currentPhotosRealPath =  new File(model.getPhotosPath());
		currentPhotosRealPath.delete();
		currentRealPath.delete();
		
		return listAll();
	}
	
	@Override
	public String find() {
	
		return list();
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		getRequest().put(getListName(), getConsortiumDAO().findAll());
		
		return Constants.LIST;
	}


	public Map<String, Object> getRequest() {
		return request;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public Consortium getModel() {
		return model;
	}

	public void setModel(Consortium model) {
		this.model = model;
	}

	public ConsortiumDAO getConsortiumDAO() {
		return consortiumDAO;
	}

	public void setConsortiumDAO(ConsortiumDAO consortiumDAO) {
		this.consortiumDAO = consortiumDAO;
	}

	public String getPreviousLimitFileName() {
		return previousLimitFileName;
	}

	public void setPreviousLimitFileName(String previousLimitFileName) {
		this.previousLimitFileName = previousLimitFileName;
	}

	public String getPreviousPath() {
		return previousPath;
	}

	public void setPreviousPath(String previousPath) {
		this.previousPath = previousPath;
	}

	public Boolean getKeepLimit() {
		return keepLimit;
	}

	public void setKeepLimit(Boolean keepLimit) {
		this.keepLimit = keepLimit;
	}

	public File getLimit() {
		return limit;
	}

	public void setLimit(File limit) {
		this.limit = limit;
	}
}