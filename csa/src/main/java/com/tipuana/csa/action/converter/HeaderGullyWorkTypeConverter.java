package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.HeaderGullyWorkType;

public class HeaderGullyWorkTypeConverter extends StrutsTypeConverter {

    public HeaderGullyWorkTypeConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        HeaderGullyWorkType headerGullyWorkType = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            switch(id.intValue())
            {
            case 1:
            	headerGullyWorkType = HeaderGullyWorkType.DUCKBILL;
                break;
                
            case 2:
            	headerGullyWorkType = HeaderGullyWorkType.JUMP;
                break;

            case 3:
            	headerGullyWorkType = HeaderGullyWorkType.INCLINEDPLAN;
                break;

            default:
            	headerGullyWorkType = null;
                break;
            }
        }
        catch(Exception exception)
        {
            throw new TypeConversionException();
        }
        
        return headerGullyWorkType;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {      
    	return ((HeaderGullyWorkType) object).toString();
    }
}