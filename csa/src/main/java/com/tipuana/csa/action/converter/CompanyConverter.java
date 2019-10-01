package com.tipuana.csa.action.converter;

import java.util.Map;

import ognl.OgnlContext;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.tipuana.csa.dao.CompanyDAO;
import com.tipuana.csa.dao.awareness.CompanyDAOAware;
import com.tipuana.csa.model.Company;


public class CompanyConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Company company = null;
		
		try {
			Long id = Long.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
			
			if(id != 0) {
				OgnlContext ognlContext = (OgnlContext) context;
				CompoundRoot root = (CompoundRoot) ognlContext.getCurrentObject();
				CompanyDAO companyDAO = null;
				
				try {
					companyDAO = ((CompanyDAOAware) root.get(0)).getCompanyDAO();
				} catch(Exception exception) {
					companyDAO = ((CompanyDAOAware) root.get(1)).getCompanyDAO();
				}
				
				company = companyDAO.findById(id, false);
			}
			
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return company;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		return ((Company) object).getName();
	}
}