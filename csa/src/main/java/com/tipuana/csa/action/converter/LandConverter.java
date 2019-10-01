package com.tipuana.csa.action.converter;

import java.util.Map;

import ognl.OgnlContext;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.tipuana.csa.dao.LandDAO;
import com.tipuana.csa.dao.awareness.LandDAOAware;
import com.tipuana.csa.model.Land;


public class LandConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {		
		Land land = null;
		
		try {
			
			Long id = Long.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
			
			if(id != 0) {
				OgnlContext ognlContext = (OgnlContext) context;
				CompoundRoot root = (CompoundRoot) ognlContext.getCurrentObject();
				LandDAO landDAO = null;
				
				try {
					landDAO = ((LandDAOAware) root.get(0)).getLandDAO();
				} catch(Exception exception) {
					landDAO = ((LandDAOAware) root.get(1)).getLandDAO();
				}
					
				land = landDAO.findById(id, false);
			}
			
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return land;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		return ((Land) object).getName();
	}
}