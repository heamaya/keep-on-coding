package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.Spillway;

public class SpillwayConverter extends StrutsTypeConverter {

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        Spillway spillway = null;
        
        try {
        	
        	if((values[0] != null && !values[0].equalsIgnoreCase(""))) {
        		values = values[0].split(";");
        		
        		spillway = new Spillway();
        		spillway.setSpillwayNumber(new Integer(values[0]) );
        		spillway.setLength(new Double(DoubleConverterUtil.replaceComma(values[1])));
        		spillway.setHeight(new Double(DoubleConverterUtil.replaceComma(values[2])));
        		
        	}
        	
        } catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return spillway;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {      
    	return ((Spillway) object).getSummary();
    }
	
}
