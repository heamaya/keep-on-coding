package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.GullyRecovery;

public class GullyRecoveryConverter extends StrutsTypeConverter {

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        GullyRecovery gullyRecovery = null;
        
        try {
        	
        	if((values[0] != null && !values[0].equalsIgnoreCase("")) || (values[0] != null && !values[0].equalsIgnoreCase(""))) {
        		gullyRecovery = new GullyRecovery();
        		gullyRecovery.setGulliesSurface(values[0] != null && !values[0].equalsIgnoreCase("") ? new Double(DoubleConverterUtil.replaceComma(values[0])) : null);
        		gullyRecovery.setRecoveredSurface(values[1] != null && !values[1].equalsIgnoreCase("") ? new Double(DoubleConverterUtil.replaceComma(values[1])) : null);
        	}
        	
        } catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return gullyRecovery;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {      
    	return ((GullyRecovery) object).toString();
    }
	
}
