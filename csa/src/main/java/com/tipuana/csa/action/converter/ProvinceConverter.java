package com.tipuana.csa.action.converter;

import java.util.Map;

import ognl.OgnlContext;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.dao.awareness.ProvinceDAOAware;
import com.tipuana.csa.model.Province;


public class ProvinceConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Province province = null;
		
		try {
			Long id = Long.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
			
			if(id != 0) {
				OgnlContext ognlContext = (OgnlContext) context;
				CompoundRoot root = (CompoundRoot) ognlContext.getCurrentObject();
				ProvinceDAO provinceDAO = null;
				
				try {
					provinceDAO = ((ProvinceDAOAware) root.get(0)).getProvinceDAO();
				} catch(Exception exception) {
					provinceDAO = ((ProvinceDAOAware) root.get(1)).getProvinceDAO();
				}
				
				province = provinceDAO.findById(id, false);
			}
			
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return province;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		return ((Province) object).getName();
	}
}