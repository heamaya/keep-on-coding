package com.tipuana.csa.action.setup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.DikeDAO;
import com.tipuana.csa.dao.SystematizationProjectDAO;
import com.tipuana.csa.dao.awareness.SystematizationProjectDAOAware;
import com.tipuana.csa.model.Dike;
import com.tipuana.csa.model.DikeSlope;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.SystematizationProject;
import com.tipuana.csa.model.WorkState;
import com.tipuana.csa.model.WorkStateDate;

@SuppressWarnings("serial")
public class DikeAction extends CreateReadUpdateRemoveDeleteSearchAction
		implements ModelDriven<Dike>, Preparable, RequestAware, SystematizationProjectDAOAware {
	
	private Dike model;
	private DikeDAO dikeDAO;
	private SystematizationProjectDAO systematizationProjectDAO;
	private Map<String, Object> request;
	private FromToDateField fromToProjectedDate;
	private FromToDateField fromToInProgressDate;
	private FromToDateField fromToFinishedDate;
	
	public DikeAction() {
		setListName("dikes");
	}

	@Override
	public void prepare() throws Exception {
		
		long id = getRequestId();
		Dike dike = null;
		
		if(id == 0) {
			dike = new Dike();
			dike.setUser(getAuthenticatedUser());
		} else {
			dike = getDikeDAO().findById(id, true);
		}
		
		if(dike.getWorkStateDate() == null) {
			dike.setWorkStateDate(new WorkStateDate());
		}
		
		setModel(dike);

	}
	
	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {			
			getRequest().put(getListName(), getDikeDAO().find(getAuthenticatedUser().getId()));			
		}
		
		return Constants.LIST;
	}

	@Override
	public String create() throws Exception {
		getDikeDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		getDikeDAO().makeTransient(getModel());
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getDikeDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String find() {
		getSession().put(getListName(), getDikeDAO().find(getModel(), getFromToDateFieldMap()));
		
		return list();
	}

	@Override
	public Dike getModel() {
		return model;
	}

	public DikeDAO getDikeDAO() {
		return dikeDAO;
	}

	public void setModel(Dike model) {
		this.model = model;
	}

	public void setDikeDAO(DikeDAO dikeDAO) {
		this.dikeDAO = dikeDAO;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public SystematizationProjectDAO getSystematizationProjectDAO() {
		return systematizationProjectDAO;
	}

	public void setSystematizationProjectDAO(
			SystematizationProjectDAO systematizationProjectDAO) {
		this.systematizationProjectDAO = systematizationProjectDAO;
	}
	
	public List<SystematizationProject> getSystematizationProjects() {
		return getSystematizationProjectDAO().find(getAuthenticatedUser().getId());
	}
	
	public List<DikeSlope> getDikeSlopes() {
		return Arrays.asList(DikeSlope.values());
	}
	
	public List<WorkState> getDikeWorkStates() {
		return Arrays.asList(WorkState.values());
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