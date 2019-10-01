package com.tipuana.csa.action.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.Terrace;
import com.tipuana.csa.model.WorkState;
import com.tipuana.csa.model.WorkStateDate;

public class TerraceConverter extends StrutsTypeConverter {

    public TerraceConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        Terrace terrace = null;
        
        try {
        	
        	if((values[0] != null && !values[0].equalsIgnoreCase("")) || 
        	   (values[1] != null && !values[1].equalsIgnoreCase("")) ||
        	   (values[2] != null && !values[2].equalsIgnoreCase(""))) 
        	{
        		terrace = new Terrace();
        		terrace.setGroundVolume(values[0] != null && !values[0].equalsIgnoreCase("") ? new Double(DoubleConverterUtil.replaceComma(values[0])) : null);
        		terrace.setLinearMeters(values[1] != null && !values[1].equalsIgnoreCase("") ? new Double(DoubleConverterUtil.replaceComma(values[1])) : null);
        		
        		if(values[2] != null && !values[2].equalsIgnoreCase("")) {
        			Integer workStateId = Integer.valueOf(values[2]);
        			
        			switch(workStateId.intValue()) {
        				case 1:
        					terrace.setWorkState(WorkState.PROJECTED);
        					break;
        				case 2:
        					terrace.setWorkState(WorkState.IN_PROGRESS);
        					break;
        				case 3:
        					terrace.setWorkState(WorkState.FINISHED);
        					break;
        				default:
        					terrace.setWorkState(null);
        					break;
        			
        			}
        		}
        		
            	if((values[3] != null && !values[3].equalsIgnoreCase("")) || 
                   (values[4] != null && !values[4].equalsIgnoreCase("")) ||
                   (values[5] != null && !values[5].equalsIgnoreCase("")))
            	{
        			WorkStateDate workStateDate = new WorkStateDate();
        			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        			workStateDate.setProjectedDate(values[3] != null && !values[3].equalsIgnoreCase("") ? dateFormat.parse(values[3]) : null );
        			workStateDate.setInProgressDate(values[4] != null && !values[4].equalsIgnoreCase("") ? dateFormat.parse(values[4]) : null );
        			workStateDate.setFinishedDate(values[5] != null && !values[5].equalsIgnoreCase("") ? dateFormat.parse(values[5]) : null );
        			terrace.setWorkStateDate(workStateDate);
        		} 

        	}

        }
        
        catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return terrace;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
    	return ((Terrace) object).toString();
    }
    
}