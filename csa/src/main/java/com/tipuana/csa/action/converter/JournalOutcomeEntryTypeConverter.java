package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.JournalOutcomeEntryType;

public class JournalOutcomeEntryTypeConverter extends StrutsTypeConverter {

    public JournalOutcomeEntryTypeConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass) {
        JournalOutcomeEntryType outcomeType = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            
            switch(id.intValue()) {
            case 1:
            	outcomeType = JournalOutcomeEntryType.SERVICES;
                break;
            case 2:
            	outcomeType = JournalOutcomeEntryType.TAXES;
                break;
            case 3:
            	outcomeType = JournalOutcomeEntryType.SALARY;
                break;
            case 4:
            	outcomeType = JournalOutcomeEntryType.HONORARIA;
                break;
            case 5:
            	outcomeType = JournalOutcomeEntryType.RENT;
                break;
            case 6:
            	outcomeType = JournalOutcomeEntryType.OFFICE;
                break;
            case 7:
            	outcomeType = JournalOutcomeEntryType.VICTUALS;
                break;
            case 8:
            	outcomeType = JournalOutcomeEntryType.BUY;
                break;
            case 9:
            	outcomeType = JournalOutcomeEntryType.MAINTENANCE;
                break;
            case 10:
            	outcomeType = JournalOutcomeEntryType.CLEANING;
                break;
            case 11:
            	outcomeType = JournalOutcomeEntryType.INSURANCE;
                break;
            case 12:
            	outcomeType = JournalOutcomeEntryType.MENSURATION;
            	break;
            case 13:
            	outcomeType = JournalOutcomeEntryType.BANK;
            	break;
            case 14:
            	outcomeType = JournalOutcomeEntryType.COMISSION;
            	break;
            case 15:
            	outcomeType = JournalOutcomeEntryType.OTHER;
            	break;
            default:
            	outcomeType = null;
                break;
            }
            
        } catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return outcomeType;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
        return ((JournalOutcomeEntryType) object).getValue();
    }
    
}