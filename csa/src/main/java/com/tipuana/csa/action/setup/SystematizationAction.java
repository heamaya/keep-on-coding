package com.tipuana.csa.action.setup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.CompanyDAO;
import com.tipuana.csa.dao.LandDAO;
import com.tipuana.csa.dao.SystematizationDAO;
import com.tipuana.csa.dao.awareness.CompanyDAOAware;
import com.tipuana.csa.dao.awareness.LandDAOAware;
import com.tipuana.csa.model.BooleanType;
import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.CompanyType;
import com.tipuana.csa.model.Land;
import com.tipuana.csa.model.Systematization;


@SuppressWarnings("serial")
public class SystematizationAction extends
		CreateReadUpdateRemoveDeleteSearchAction implements Preparable,
		ModelDriven<Systematization>, ServletContextAware,
		CompanyDAOAware, LandDAOAware {
	
	private Systematization model;
	private SystematizationDAO systematizationDAO;
	private ServletContext servletContext;
	private CompanyDAO companyDAO;
	private LandDAO landDAO;
	private String previousSystematizationFileName;
	private String previousSystematizationPath;
	private File systematization;
	private Boolean keepSystematization;
	private CompanyType companyType;
	private Boolean findIsStarred;
	
	public SystematizationAction() {
		setListName("systematizations");
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	@Override
	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void prepare() throws Exception {

		if(getRequestId() != 0) {
			setModel(getSystematizationDAO().findById(getRequestId(), true));
		} else {
			setModel(new Systematization());
		}

	}

	@Override
	public String create() throws Exception {
		Systematization model = getModel();
		getSystematizationDAO().makePersistent(model);
		
		File currentSystematizationRealPath =  new File(model.getPath());
		File currentSystematizationBeforePhotoRealPath =  new File(model.getBeforePhotoPath());
		File currentSystematizationAfterPhotoRealPath =  new File(model.getAfterPhotoPath());
		
		currentSystematizationRealPath.mkdir();
		currentSystematizationBeforePhotoRealPath.mkdir();
		currentSystematizationAfterPhotoRealPath.mkdir();
		
		File systematization = getSystematization();
		
		if(systematization != null) {
			FileUtil.save(systematization, currentSystematizationRealPath.getPath(), model.getSystematizationFileName());
		}
		
		return listAll();
	}
	
	@Override
	public String edit() {
		setActionMethod(Constants.UPDATE);
		
		setPreviousSystematizationFileName(getModel().getSystematizationFileName());
		setPreviousSystematizationPath(getModel().getPath());
		
		return Constants.SUCCESS;
	}

	@Override
	public String update() throws Exception {
		
		Systematization model = getModel();
		getSystematizationDAO().makePersistent(model);
		
		File currentSystematizationRealPath =  new File(model.getPath());
		File previousSystematizationRealPath = new File(getPreviousSystematizationPath());
		previousSystematizationRealPath.renameTo(currentSystematizationRealPath);
		
		File systematization = getSystematization();
		
		if(systematization != null) {
			String previousSystematizationFileName = getPreviousSystematizationFileName();
			
			if(previousSystematizationFileName != null && !previousSystematizationFileName.equalsIgnoreCase("")) {
				FileUtil.delete(currentSystematizationRealPath.getPath(), previousSystematizationFileName);
			}
			
			FileUtil.save(systematization, currentSystematizationRealPath.getPath(), getModel().getSystematizationFileName());
		}
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		Systematization model = getModel();

		File currentSystematizationRealPath =  new File(model.getPath());
		String systematizationFileName = model.getSystematizationFileName();
		
		if(systematizationFileName != null && !systematizationFileName.equalsIgnoreCase("")) {
			FileUtil.delete(currentSystematizationRealPath.getPath(), systematizationFileName);
		}
		
		File currentBeforePhotosRealPath = new File(model.getBeforePhotoPath());
		File currentAfterPhotosRealPath = new File(model.getAfterPhotoPath());
		currentBeforePhotosRealPath.delete();
		currentAfterPhotosRealPath.delete();
		currentSystematizationRealPath.delete();
		
		getSystematizationDAO().makeTransient(model);
		
		return listAll();
	}
	
	@Override
	public String find() {
		Systematization systematization = getModel();
		systematization.setStarred(getFindIsStarred());
		
		getSession().put(getListName(), getSystematizationDAO().find(systematization, getMatchMode()));
		
		return list();
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {
			getRequest().put(getListName(), getSystematizationDAO().findAll());			
		}
		
		return Constants.LIST;
	}

	public Systematization getModel() {
		return model;
	}

	public SystematizationDAO getSystematizationDAO() {
		return systematizationDAO;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public LandDAO getLandDAO() {
		return landDAO;
	}

	public void setModel(Systematization model) {
		this.model = model;
	}

	public void setSystematizationDAO(SystematizationDAO systematizationDAO) {
		this.systematizationDAO = systematizationDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void setLandDAO(LandDAO landDAO) {
		this.landDAO = landDAO;
	}

	public String getPreviousSystematizationFileName() {
		return previousSystematizationFileName;
	}

	public void setPreviousSystematizationFileName(String previousSystematizationFileName) {
		this.previousSystematizationFileName = previousSystematizationFileName;
	}

	public File getSystematization() {
		return systematization;
	}

	public void setSystematization(File systematization) {
		this.systematization = systematization;
	}

	public Boolean getKeepSystematization() {
		return keepSystematization;
	}

	public void setKeepSystematization(Boolean keepSystematization) {
		this.keepSystematization = keepSystematization;
	}
	
	public List<Company> getCompanies() {
		return getCompanyDAO().findAll(Order.asc("name"));
	}

	public List<Land> getLands() {
		Company company = getModel().getCompany();
		
		if (company != null) {
			return getLandDAO().find(company.getId().longValue());
		}
		
		return new ArrayList<Land>();
	}

	public String getPreviousSystematizationPath() {
		return previousSystematizationPath;
	}

	public void setPreviousSystematizationPath(String previousSystematizationPath) {
		this.previousSystematizationPath = previousSystematizationPath;
	}
	
	public List<CompanyType> getCompanyTypes() {
		return Arrays.asList(CompanyType.values());
	}

	public Boolean getFindIsStarred() {
		return findIsStarred;
	}

	public void setFindIsStarred(Boolean findIsStarred) {
		this.findIsStarred = findIsStarred;
	}

	public List<BooleanType> getBooleanTypes() {
		return Arrays.asList(BooleanType.values());
	}
}