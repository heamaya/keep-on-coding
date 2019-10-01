package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.JournalIncomeEntryType;

public class JournalIncomeEntryTypeConverter extends StrutsTypeConverter {

    public JournalIncomeEntryTypeConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        JournalIncomeEntryType incomeType = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            
            switch(id.intValue()) {
            case 1:
            	incomeType = JournalIncomeEntryType.PAYMENT;
                break;

            case 2:
            	incomeType = JournalIncomeEntryType.OTHER;
                break;
                
            default:
            	incomeType = null;
                break;
            }
            
        } catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return incomeType;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
        return ((JournalIncomeEntryType) object).getValue();
    }
    
}