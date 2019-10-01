package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class BooleanTypeConverter extends StrutsTypeConverter {

    public BooleanTypeConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        Boolean booleanValue = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            
            switch(id.intValue()) {
            	case 1:
            		booleanValue = new Boolean(true);
                break;

            	case 2:
            		booleanValue = new Boolean(false);
                break;
            	
            	default:
            		booleanValue = null;
                break;
            }
            
        }
        
        catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return booleanValue;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
        return (((Boolean) object).booleanValue() == true) ? "Si" : "No";
    }
}
