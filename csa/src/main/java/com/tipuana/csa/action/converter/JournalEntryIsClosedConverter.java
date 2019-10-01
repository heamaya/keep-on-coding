package com.tipuana.csa.action.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.JournalEntryIsClosed;

public class JournalEntryIsClosedConverter extends StrutsTypeConverter {

    public JournalEntryIsClosedConverter() {
    
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass)
    {
        JournalEntryIsClosed isClosed = null;
        
        try {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            
            switch(id.intValue()) {
            
            	case 1:
            		isClosed = JournalEntryIsClosed.OPEN;
                break;

            	case 2:
            		isClosed = JournalEntryIsClosed.CLOSED;
                break;
            	default:
                	isClosed = null;
                break;
            }
        }
        
        catch(Exception exception) {
            throw new TypeConversionException();
        }
        
        return isClosed;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
        return ((JournalEntryIsClosed) object).toString();
    }
}
