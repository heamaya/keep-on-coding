package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.dao.CompanyDAO;
import com.tipuana.csa.dao.LandDAO;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.Land;
import com.tipuana.csa.model.Province;


@SuppressWarnings("serial")
public class QuickLandAction extends LandAction
    implements ServletRequestAware, ServletResponseAware
{
	
    private long companyId;
    private List<Land> lands;
    private HttpServletResponse servletResponse;
    private HttpServletRequest servletRequest;

    public QuickLandAction()
    {
        companyId = 0L;
        lands = new ArrayList<Land>();
    }

    public void prepare()
        throws Exception
    {
        Land land = null;
        if(getRequestId() != 0L)
        {
            land = (Land)getLandDAO().findById(Long.valueOf(getRequestId()), true);
        } else
        {
            land = new Land();
            land.setCompany((Company)getCompanyDAO().findById(Long.valueOf(getCompanyId()), true));
        }
        setModel(land);
    }

    public String list()
    {
        setActionMethod(Constants.LIST);
        if(companyId != 0L)
            setLands(getLandDAO().find(companyId));
        return Constants.LIST;
    }

    public List<Land> getLands()
    {
        return lands;
    }

    public void setLands(List<Land> lands)
    {
        this.lands = lands;
    }

    public Map<String, Object> getRequest()
    {
        return super.getRequest();
    }

    public LandDAO getLandDAO()
    {
        return super.getLandDAO();
    }

    public ProvinceDAO getProvinceDAO()
    {
        return super.getProvinceDAO();
    }

    public CityDAO getCityDAO()
    {
        return super.getCityDAO();
    }

    public Land getModel()
    {
        return super.getModel();
    }

    public CompanyDAO getCompanyDAO()
    {
        return super.getCompanyDAO();
    }

    public List<Company> getCompanies()
    {
        return super.getCompanies();
    }

    public List<Province> getProvinces()
    {
        return super.getProvinces();
    }

    public List<City> getCities()
    {
        return super.getCities();
    }

    public ServletContext getServletContext()
    {
        return super.getServletContext();
    }

    public Boolean getKeepBoundaries()
    {
        return super.getKeepBoundaries();
    }

    public String getPreviousBoundariesFileName()
    {
        return super.getPreviousBoundariesFileName();
    }

    public String getPreviousLandPath()
    {
        return super.getPreviousLandPath();
    }

    public long getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(long companyId)
    {
        this.companyId = companyId;
    }

    public HttpServletResponse getServletResponse()
    {
        return servletResponse;
    }

    public void setServletResponse(HttpServletResponse servletResponse)
    {
        this.servletResponse = servletResponse;
    }

    public HttpServletRequest getServletRequest()
    {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest)
    {
        this.servletRequest = servletRequest;
    }


}
