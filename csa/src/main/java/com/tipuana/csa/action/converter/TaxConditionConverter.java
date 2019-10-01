package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.TaxCondition;


public class TaxConditionConverter extends StrutsTypeConverter
{

    public TaxConditionConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        TaxCondition taxCondition = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            
            switch(id.intValue()) {
            
            	case 1:
            		taxCondition = TaxCondition.REGISTERED_RESPONSIBLE;
            		break;

            	case 2:
            		taxCondition = TaxCondition.MONOTRIBUTE;
            		break;
            		
            	case 3:
            		taxCondition = TaxCondition.FREE;
            		break;

            	default:
            		taxCondition = null;
            		break;
            }
            
        } catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return taxCondition;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
        return ((TaxCondition) object).getValue();
    }
}
