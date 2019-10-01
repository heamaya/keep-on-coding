package com.tipuana.csa.action.converter;

import java.util.Map;

import ognl.OgnlContext;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.tipuana.csa.dao.SystematizationDAO;
import com.tipuana.csa.dao.awareness.SystematizationDAOAware;
import com.tipuana.csa.model.Systematization;


public class SystematizationConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Systematization systematization = null;
		
		try {
			Long id = Long.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
			
			if(id != 0) {
				OgnlContext ognlContext = (OgnlContext) context;
				CompoundRoot root = (CompoundRoot) ognlContext.getCurrentObject();
				SystematizationDAO systematizationDAO = null;
				
				try {
					systematizationDAO = ((SystematizationDAOAware) root.get(0)).getSystematizationDAO();
				} catch(Exception exception) {
					systematizationDAO = ((SystematizationDAOAware) root.get(1)).getSystematizationDAO();
				}
				
				systematization = systematizationDAO.findById(id, false);
			}
			
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return systematization;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		return ((Systematization) object).getSummary();
	}
}