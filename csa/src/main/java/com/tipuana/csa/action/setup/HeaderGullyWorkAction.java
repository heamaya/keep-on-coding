package com.tipuana.csa.action.setup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.dao.HeaderGullyWorkDAO;
import com.tipuana.csa.dao.SystematizationProjectDAO;
import com.tipuana.csa.dao.awareness.SystematizationProjectDAOAware;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.HeaderGullyWork;
import com.tipuana.csa.model.HeaderGullyWorkType;
import com.tipuana.csa.model.SystematizationProject;
import com.tipuana.csa.model.WorkState;

@SuppressWarnings("serial")
public class HeaderGullyWorkAction extends
		CreateReadUpdateRemoveDeleteSearchAction implements
		ModelDriven<HeaderGullyWork>, Preparable, RequestAware,
		SystematizationProjectDAOAware {
	
	private HeaderGullyWork model;
	private HeaderGullyWorkDAO headerGullyWorkDAO;
	private Map<String, Object> request;
	private SystematizationProjectDAO systematizationProjectDAO;
	private FromToDateField fromToProjectedDate;
	private FromToDateField fromToInProgressDate;
	private FromToDateField fromToFinishedDate;
	
	public HeaderGullyWorkAction() {
		setListName("headerGullyWorks");
	}

	@Override
	public SystematizationProjectDAO getSystematizationProjectDAO() {
		return systematizationProjectDAO;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void prepare() throws Exception {
		long id = getRequestId();
		HeaderGullyWork headerGullyWork = null;
		
		if(id == 0) {
			headerGullyWork = new HeaderGullyWork();
		} else {
			headerGullyWork = getHeaderGullyWorkDAO().findById(id, true);
		}
		
		headerGullyWork.setUser(getAuthenticatedUser());
		setModel(headerGullyWork);
	}

	@Override
	public String create() throws Exception {
		getHeaderGullyWorkDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		getHeaderGullyWorkDAO().makeTransient(getModel());
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getHeaderGullyWorkDAO().makePersistent(getModel());
		
		return listAll();
	}
	
	@Override
	public String list() {
		
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {
			getRequest().put(getListName(), getHeaderGullyWorkDAO().find(getAuthenticatedUser().getId()));
		}
		
		return super.list();
	}

	@Override
	public String find() {
		getSession().put(getListName(), getHeaderGullyWorkDAO().find(getModel(), getFromToDateFieldMap()));
		
		return list();
	}

	@Override
	public HeaderGullyWork getModel() {
		return model;
	}

	public HeaderGullyWorkDAO getHeaderGullyWorkDAO() {
		return headerGullyWorkDAO;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setModel(HeaderGullyWork model) {
		this.model = model;
	}

	public void setHeaderGullyWorkDAO(HeaderGullyWorkDAO headerGullyWorkDAO) {
		this.headerGullyWorkDAO = headerGullyWorkDAO;
	}

	public void setSystematizationProjectDAO(SystematizationProjectDAO systematizationProjectDAO) {
		this.systematizationProjectDAO = systematizationProjectDAO;
	}
	
	public List<SystematizationProject> getSystematizationProjects() {
		return getSystematizationProjectDAO().find(getAuthenticatedUser().getId());
	}
	
	public List<WorkState> getWorkStates() {
		return Arrays.asList(WorkState.values());
	}
	
	public List<HeaderGullyWorkType> getHeaderGullyWorkTypes() {
		return Arrays.asList(HeaderGullyWorkType.values());
	}

	public FromToDateField getFromToProjectedDate() {
		return fromToProjectedDate;
	}

	public FromToDateField getFromToInProgressDate() {
		return fromToInProgressDate;
	}

	public FromToDateField getFromToFinishedDate() {
		return fromToFinishedDate;
	}

	public void setFromToProjectedDate(FromToDateField fromToProjectedDate) {
		this.fromToProjectedDate = fromToProjectedDate;
	}

	public void setFromToInProgressDate(FromToDateField fromToInProgressDate) {
		this.fromToInProgressDate = fromToInProgressDate;
	}

	public void setFromToFinishedDate(FromToDateField fromToFinishedDate) {
		this.fromToFinishedDate = fromToFinishedDate;
	}
	
	private Map<String, FromToDateField> getFromToDateFieldMap() {
		Map<String, FromToDateField> fromToDateFieldsMap = new HashMap<String, FromToDateField>();
		fromToDateFieldsMap.put("projectedDate", getFromToProjectedDate());
		fromToDateFieldsMap.put("inProgressDate", getFromToInProgressDate());
		fromToDateFieldsMap.put("finishedDate", getFromToFinishedDate());
		
		return fromToDateFieldsMap;
	}
	
}