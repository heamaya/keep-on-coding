package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.Outlet;

public class OutletConverter extends StrutsTypeConverter {

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        Outlet outlet = null;
        
        try {
        	
        	if((values[0] != null && !values[0].equalsIgnoreCase(""))) {
        		values = values[0].split(";");
        		
        		outlet = new Outlet();
        		outlet.setOutletNumber(new Integer(values[0]) );
        		outlet.setDiameter(new Double(DoubleConverterUtil.replaceComma(values[1])));
        		outlet.setInletHeight(new Double(DoubleConverterUtil.replaceComma(values[2])));
        		outlet.setOutletHeight(new Double(DoubleConverterUtil.replaceComma(values[3])));
        	}
        	
        } catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return outlet;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {      
    	return ((Outlet) object).getSummary();
    }
	
}