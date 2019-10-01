package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.DikeSlope;

public class DikeSlopeConverter extends StrutsTypeConverter {

    public DikeSlopeConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        DikeSlope dikeSlope = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            switch(id.intValue())
            {
            case 1:
            	dikeSlope = DikeSlope.ONE_IN_ONE;
                break;

            case 2:
            	dikeSlope = DikeSlope.ONE_AND_A_HALF_IN_ONE;
                break;

            case 3:
            	dikeSlope = DikeSlope.TWO_IN_ONE;
                break;
            case 4:
            	dikeSlope = DikeSlope.TWO_AND_A_HALF_IN_ONE;
                break;

            case 5:
            	dikeSlope = DikeSlope.THREE_IN_ONE;
                break;

            case 6:
            	dikeSlope = DikeSlope.THREE_AND_A_HALF_IN_ONE;
                break;
            case 7:
            	dikeSlope = DikeSlope.FOUR_IN_ONE;
                break;
            default:
            	dikeSlope = null;
                break;
            }
        }
        catch(Exception exception)
        {
            throw new TypeConversionException();
        }
        
        return dikeSlope;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
        
    	return ((DikeSlope) object).toString();
    	
    }

}