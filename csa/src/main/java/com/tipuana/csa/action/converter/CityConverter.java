package com.tipuana.csa.action.converter;

import java.util.Map;

import ognl.OgnlContext;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.dao.awareness.CityDAOAware;
import com.tipuana.csa.model.City;


public class CityConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {		
		City city = null;
		
		try {
			
			Long id = Long.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
			
			if(id != 0) {
				OgnlContext ognlContext = (OgnlContext) context;
				CompoundRoot root = (CompoundRoot) ognlContext.getCurrentObject();
				CityDAO cityDAO = null;
				
				try {
					cityDAO = ((CityDAOAware) root.get(0)).getCityDAO();
				} catch(Exception exception) {
					cityDAO = ((CityDAOAware) root.get(1)).getCityDAO();
				}
					
				city = cityDAO.findById(id, false);
			}
			
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return city;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		return ((City) object).getName();
	}
}