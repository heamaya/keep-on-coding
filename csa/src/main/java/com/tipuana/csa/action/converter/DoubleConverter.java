package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class DoubleConverter extends StrutsTypeConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {		
		Double doubleObject = null;
		
		try {
			String doubleAsString = values[0];
			doubleObject = new Double(DoubleConverterUtil.replaceComma(doubleAsString));
		} catch (Exception exception) {
			throw new TypeConversionException();
		}
		
		return doubleObject;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		return ((Double) object).toString();
	}
}