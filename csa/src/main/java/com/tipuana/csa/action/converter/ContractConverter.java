package com.tipuana.csa.action.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.Contract;

public class ContractConverter extends StrutsTypeConverter {

    public ContractConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        Contract contract = null;
        try {
        	
        	if((values[0] != null && !values[0].equalsIgnoreCase("")) || 
        	   (values[1] != null && !values[1].equalsIgnoreCase("")) ||
        	   (values[2] != null && !values[2].equalsIgnoreCase("")) ||
        	   (values[3] != null && !values[3].equalsIgnoreCase(""))) 
        	{
        		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		contract = new Contract();
    			contract.setContractDate(values[0] != null && !values[0].equalsIgnoreCase("") ? dateFormat.parse(values[0]) : null );
        		contract.setHiredHectares(values[1] != null && !values[1].equalsIgnoreCase("") ? new Double(DoubleConverterUtil.replaceComma(values[1])) : null);
        		contract.setFees(values[2] != null && !values[2].equalsIgnoreCase("") ? new Integer(values[2]) : null);
        		contract.setQuintalsPerHectare(values[3] != null && !values[3].equalsIgnoreCase("") ? new Double(DoubleConverterUtil.replaceComma(values[3])) : null);
        	}

        }
        
        catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return contract;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
    	return ((Contract) object).toString();
    }
    
}