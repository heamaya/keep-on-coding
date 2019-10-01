package com.tipuana.csa.action.setup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.dao.CompanyDAO;
import com.tipuana.csa.dao.LandDAO;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.dao.awareness.CityDAOAware;
import com.tipuana.csa.dao.awareness.CompanyDAOAware;
import com.tipuana.csa.dao.awareness.ProvinceDAOAware;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.CompanyType;
import com.tipuana.csa.model.Land;
import com.tipuana.csa.model.Province;


@SuppressWarnings("serial")
public class LandAction extends CreateReadUpdateRemoveDeleteSearchAction
		implements Preparable, ModelDriven<Land>, RequestAware, ProvinceDAOAware, CityDAOAware, CompanyDAOAware, ServletContextAware {
	
	private Land model;
	private Map<String, Object> request;
	private LandDAO landDAO;
	private ProvinceDAO provinceDAO;
	private CityDAO cityDAO;
	private CompanyDAO companyDAO;
	private ServletContext servletContext;
	private File boundaries;
	private Boolean keepBoundaries;
	private String previousBoundariesFileName;
	private String previousLandPath;
	
	public LandAction() {
		setListName("lands");
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void prepare() throws Exception {

		if(getRequestId() != 0) {
			setModel(getLandDAO().findById(getRequestId(), true));
		} else {
			setModel(new Land());
		}

	}

	@Override
	public String create() throws Exception {
		Land model = getModel();
		getLandDAO().makePersistent(model);
		
		File currentLandRealPath =  new File(model.getPath());
		currentLandRealPath.mkdir();
		
		File boundaries = getBoundaries();
		
		if(getBoundaries() != null) {
			FileUtil.save(boundaries, currentLandRealPath.getPath(), model.getBoundariesFileName());
		}
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		Land model = getModel();
		getLandDAO().makeTransient(model);
		
		File currentLandRealPath =  new File(model.getPath());
		
		if(model.getBoundariesFileName() != null && !model.getBoundariesFileName().equalsIgnoreCase("")) {
			FileUtil.delete(currentLandRealPath.getPath(), model.getBoundariesFileName());
		}
		
		return listAll();
	}
	
	@Override @SkipValidation
	public String edit() {
		setActionMethod(Constants.UPDATE);
		
		setPreviousBoundariesFileName(getModel().getBoundariesFileName());
		setPreviousLandPath(getModel().getPath());
		
		return Constants.SUCCESS;
	}

	@Override
	public String update() throws Exception {
		Land model = getModel();
		getLandDAO().makePersistent(model);
		
		File previousLandRealPath =  new File(getPreviousLandPath());
		File currentLandRealPath =  new File(model.getPath());
		previousLandRealPath.renameTo(currentLandRealPath);
		
		File boundaries = getBoundaries();
		
		if(boundaries != null) {
			
			if(getPreviousBoundariesFileName() != null && !getPreviousBoundariesFileName().equalsIgnoreCase("")) {
				FileUtil.delete(currentLandRealPath.getPath(), getPreviousBoundariesFileName());
			}
			
			FileUtil.save(boundaries, currentLandRealPath.getPath(), model.getBoundariesFileName());
		}
		
		return listAll();
	}
	
	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.asc("name"));
			
			getRequest().put(getListName(), getLandDAO().findAll(orders));			
		}
		
		return Constants.LIST;
	}

	@Override
	public String find() {
		getSession().put(getListName(), getLandDAO().find(getModel(), getMatchMode()));
		
		return list();
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setModel(Land model) {
		this.model = model;
	}

	public LandDAO getLandDAO() {
		return landDAO;
	}

	@Override
	public ProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}

	@Override
	public CityDAO getCityDAO() {
		return cityDAO;
	}

	public void setLandDAO(LandDAO landDAO) {
		this.landDAO = landDAO;
	}

	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	@Override
	public Land getModel() {
		return model;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
	
	public List<Company> getCompanies() {
		return getCompanyDAO().findAll(Order.asc("name"));
	}
	
	public List<Province> getProvinces() {
		return getProvinceDAO().findAll(Order.asc("name"));
	}

	public List<City> getCities() {
		Province province = getModel().getNearestProvince();
		
		if (province != null) {
			return getCityDAO().find(province.getId().longValue());
		}
		
		return new ArrayList<City>();
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public File getBoundaries() {
		return boundaries;
	}

	public void setBoundaries(File boundaries) {
		this.boundaries = boundaries;
	}

	public Boolean getKeepBoundaries() {
		return keepBoundaries;
	}

	public void setKeepBoundaries(Boolean keepBoundaries) {
		this.keepBoundaries = keepBoundaries;
	}

	public String getPreviousBoundariesFileName() {
		return previousBoundariesFileName;
	}

	public void setPreviousBoundariesFileName(String previousBoundariesFileName) {
		this.previousBoundariesFileName = previousBoundariesFileName;
	}

	public String getPreviousLandPath() {
		return previousLandPath;
	}

	public void setPreviousLandPath(String previousLandPath) {
		this.previousLandPath = previousLandPath;
	}
	
	public List<CompanyType> getCompanyTypes() {
		return Arrays.asList(CompanyType.values());
	}
	
}