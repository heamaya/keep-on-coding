package com.tipuana.csa.action.converter;

import java.util.Map;

import ognl.OgnlContext;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.tipuana.csa.dao.SystematizationProjectDAO;
import com.tipuana.csa.dao.awareness.SystematizationProjectDAOAware;
import com.tipuana.csa.model.SystematizationProject;


public class SystematizationProjectConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		SystematizationProject systematizationProject = null;
		
		try {
			Long id = Long.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
			
			if(id != 0) {
				OgnlContext ognlContext = (OgnlContext) context;
				CompoundRoot root = (CompoundRoot) ognlContext.getCurrentObject();
				SystematizationProjectDAO systematizationProjectDAO = null;
				
				try {
					systematizationProjectDAO = ((SystematizationProjectDAOAware) root.get(0)).getSystematizationProjectDAO();
				} catch(Exception exception) {
					systematizationProjectDAO = ((SystematizationProjectDAOAware) root.get(1)).getSystematizationProjectDAO();
				}
				
				systematizationProject = systematizationProjectDAO.findById(id, false);
			}
			
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return systematizationProject;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		return ((SystematizationProject) object).getSummary();
	}
}