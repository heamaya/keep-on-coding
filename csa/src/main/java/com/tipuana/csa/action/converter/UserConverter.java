package com.tipuana.csa.action.converter;

import java.util.Map;

import ognl.OgnlContext;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.dao.awareness.UserDAOAware;
import com.tipuana.csa.model.User;


public class UserConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {		
		User user = null;
		
		try {
			
			Long id = Long.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
			
			if(id != 0) {
				OgnlContext ognlContext = (OgnlContext) context;
				CompoundRoot root = (CompoundRoot) ognlContext.getCurrentObject();
				UserDAO userDAO = null;
				
				try {
					userDAO = ((UserDAOAware) root.get(0)).getUserDAO();
				} catch(Exception exception) {
					userDAO = ((UserDAOAware) root.get(1)).getUserDAO();
				}
					
				user = userDAO.findById(id, false);
			}
			
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return user;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		return ((User) object).getSummary();
	}
}