package com.tipuana.csa.action.setup;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.SystematizationDAO;
import com.tipuana.csa.dao.SystematizationProjectDAO;
import com.tipuana.csa.dao.awareness.SystematizationDAOAware;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.GullyRecovery;
import com.tipuana.csa.model.Systematization;
import com.tipuana.csa.model.SystematizationProject;
import com.tipuana.csa.model.Terrace;
import com.tipuana.csa.model.WorkState;
import com.tipuana.csa.model.WorkStateDate;

@SuppressWarnings("serial")
public class SystematizationProjectAction extends
		CreateReadUpdateRemoveDeleteSearchAction implements Preparable,
		ModelDriven<SystematizationProject>, RequestAware, SystematizationDAOAware {
	
	private SystematizationProject model;
	private Map<String, Object> request;
	private SystematizationProjectDAO systematizationProjectDAO;
	private SystematizationDAO systematizationDAO;
	private File farmArea;
	private String previousFarmAreaFileName;
	private Boolean keepFarmArea;
	private FromToDateField fromToContractDate;
	private FromToDateField fromToProjectedDate;
	private FromToDateField fromToInProgressDate;
	private FromToDateField fromToFinishedDate;
	
	public SystematizationProjectAction() {
		setListName("systematizationProjects");
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void prepare() throws Exception {
		
		long requestId = getRequestId();
		SystematizationProject systematizationProject = null;
		
		if(requestId != 0) {
			systematizationProject = getSystematizationProjectDAO().findById(requestId, true); 
		} else {
			systematizationProject = new SystematizationProject();
			systematizationProject.setUser(getAuthenticatedUser());
			
		}
		
		if(systematizationProject.getTerrace() == null) {
			systematizationProject.setTerrace(new Terrace());
		} else if(systematizationProject.getTerrace().getWorkStateDate() == null) {
			systematizationProject.getTerrace().setWorkStateDate(new WorkStateDate());
		}
		
		if(systematizationProject.getGullyRecovery() == null) {
			systematizationProject.setGullyRecovery(new GullyRecovery());
		}
		
		setModel(systematizationProject);

	}

	@Override
	public String create() throws Exception {		
		getSystematizationProjectDAO().makePersistent(getModel());
		
		File currentSystematizationProjectRealPath =  new File(model.getPath());
		currentSystematizationProjectRealPath.mkdir();
		File farmArea = getFarmArea();
		
		if(farmArea != null) {
			FileUtil.save(farmArea, currentSystematizationProjectRealPath.getPath(), model.getFarmAreaFileName());
		}
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		File currentSystematizationProjectRealPath =  new File(model.getPath());
		String farmAreaFileName = model.getFarmAreaFileName();
		
		if(farmAreaFileName != null && !farmAreaFileName.equalsIgnoreCase("")) {
			FileUtil.delete(currentSystematizationProjectRealPath.getPath(), farmAreaFileName);
		}
		
		getSystematizationProjectDAO().makeTransient(getModel());
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getSystematizationProjectDAO().makePersistent(getModel());
		
		File currentSystematizationProjectRealPath =  new File(model.getPath());		
		File farmArea = getFarmArea();
		
		if(farmArea != null) {
			
			if(previousFarmAreaFileName != null && !previousFarmAreaFileName.equalsIgnoreCase("")) {
				FileUtil.delete(currentSystematizationProjectRealPath.getPath(), previousFarmAreaFileName);
			}
			
			FileUtil.save(farmArea, currentSystematizationProjectRealPath.getPath(), getModel().getFarmAreaFileName());
		}
		
		return listAll();
	}

	@Override
	public String find() {
		getSession().put(getListName(), getSystematizationProjectDAO().find(getModel(), getFromToDateFieldsMap()));
		
		return list();
	}

	private Map<String, FromToDateField> getFromToDateFieldsMap() {
		Map<String, FromToDateField> fromToDateFieldsMap = new HashMap<String, FromToDateField>();
		fromToDateFieldsMap.put("contractDate", getFromToContractDate());
		fromToDateFieldsMap.put("projectedDate", getFromToProjectedDate());
		fromToDateFieldsMap.put("inProgressDate", getFromToInProgressDate());
		fromToDateFieldsMap.put("finishedDate", getFromToFinishedDate());
		
		return fromToDateFieldsMap;
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
	
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {
			getRequest().put(getListName(), getSystematizationProjectDAO().find(getAuthenticatedUser().getId()));
		}
	
		return Constants.LIST;
	}
	
	@Override
	public SystematizationProject getModel() {
		return model;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public SystematizationProjectDAO getSystematizationProjectDAO() {
		return systematizationProjectDAO;
	}

	public void setModel(SystematizationProject model) {
		this.model = model;
	}

	public void setSystematizationProjectDAO(
			SystematizationProjectDAO systematizationProjectDAO) {
		this.systematizationProjectDAO = systematizationProjectDAO;
	}

	public SystematizationDAO getSystematizationDAO() {
		return systematizationDAO;
	}

	public void setSystematizationDAO(SystematizationDAO systematizationDAO) {
		this.systematizationDAO = systematizationDAO;
	}
	
	public List<Systematization> getSystematizations() {
		return getSystematizationDAO().findAll();
	}
	
	public List<WorkState> getTerraceWorkStates() {
		return Arrays.asList(WorkState.values());
	}

	public File getFarmArea() {
		return farmArea;
	}

	public void setFarmArea(File farmArea) {
		this.farmArea = farmArea;
	}

	public String getPreviousFarmAreaFileName() {
		return previousFarmAreaFileName;
	}

	public void setPreviousFarmAreaFileName(String previousFarmAreaFileName) {
		this.previousFarmAreaFileName = previousFarmAreaFileName;
	}

	public Boolean getKeepFarmArea() {
		return keepFarmArea;
	}

	public void setKeepFarmArea(Boolean keepFarmArea) {
		this.keepFarmArea = keepFarmArea;
	}

	public FromToDateField getFromToContractDate() {
		return fromToContractDate;
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

	public void setFromToContractDate(FromToDateField fromToContractDate) {
		this.fromToContractDate = fromToContractDate;
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
	
}