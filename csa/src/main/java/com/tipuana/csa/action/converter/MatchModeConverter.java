package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.hibernate.criterion.MatchMode;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class MatchModeConverter extends StrutsTypeConverter {

    public MatchModeConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass)
    {
        MatchMode matchMode = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            
            switch(id.intValue()) {
            
            	case 1:
            		matchMode = MatchMode.EXACT;
                break;

            	case 2:
            		matchMode = MatchMode.START;
                break;

            	case 3:
            		matchMode = MatchMode.ANYWHERE;
                break;

            	case 4:
            		matchMode = MatchMode.END;
                break;

            	default:
                	matchMode = MatchMode.EXACT;
                break;
            }
        }
        
        catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return matchMode;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
        return ((MatchMode) object).toString();
    }
}
