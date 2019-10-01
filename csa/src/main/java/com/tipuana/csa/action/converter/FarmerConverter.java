package com.tipuana.csa.action.converter;

import java.util.Map;

import ognl.OgnlContext;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.tipuana.csa.dao.FarmerDAO;
import com.tipuana.csa.dao.awareness.FarmerDAOAware;
import com.tipuana.csa.model.Farmer;


public class FarmerConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {		
		Farmer farmer = null;
		
		try {
			
			Long id = Long.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
			
			if(id != 0) {
				OgnlContext ognlContext = (OgnlContext) context;
				CompoundRoot root = (CompoundRoot) ognlContext.getCurrentObject();
				FarmerDAO farmerDAO = null;
				
				try {
					farmerDAO = ((FarmerDAOAware) root.get(0)).getFarmerDAO();
				} catch(Exception exception) {
					farmerDAO = ((FarmerDAOAware) root.get(1)).getFarmerDAO();
				}
					
				farmer = farmerDAO.findById(id, false);
			}
			
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return farmer;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		Farmer farmer = (Farmer) object; 
		
		return new StringBuilder(farmer.getLastName()).append(" ").append(farmer.getFirstName()).toString();
	}
}