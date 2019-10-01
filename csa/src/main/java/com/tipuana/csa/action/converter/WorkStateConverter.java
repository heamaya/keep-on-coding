package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.WorkState;

public class WorkStateConverter extends StrutsTypeConverter {

    public WorkStateConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        WorkState workState = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            switch(id.intValue())
            {
            case 1:
            	workState = WorkState.PROJECTED;
                break;

            case 2:
            	workState = WorkState.IN_PROGRESS;
                break;

            case 3:
            	workState = WorkState.FINISHED;
                break;

            default:
            	workState = null;
                break;
            }
        }
        catch(Exception exception)
        {
            throw new TypeConversionException();
        }
        
        return workState;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
        
    	return ((WorkState) object).getValue();
    	
    }

}