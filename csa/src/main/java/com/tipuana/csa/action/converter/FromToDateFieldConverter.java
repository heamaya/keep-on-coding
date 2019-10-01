package com.tipuana.csa.action.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.FromToDateField;

public class FromToDateFieldConverter extends StrutsTypeConverter {

    public FromToDateFieldConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        FromToDateField fromToDateField = null;
        try {
        	
        	if((values[0] != null && !values[0].equalsIgnoreCase("")) || 
        	   (values[1] != null && !values[1].equalsIgnoreCase(""))) 
        	{
        		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		fromToDateField = new FromToDateField();
    			fromToDateField.setFrom(values[0] != null && !values[0].equalsIgnoreCase("") ? dateFormat.parse(values[0]) : null);
    			fromToDateField.setTo(values[1] != null && !values[1].equalsIgnoreCase("") ? dateFormat.parse(values[1]) : null);

        	}

        }
        
        catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return fromToDateField;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
    	return ((FromToDateField) object).toString();
    }
    
}