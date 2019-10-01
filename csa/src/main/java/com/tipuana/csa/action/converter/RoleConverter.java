package com.tipuana.csa.action.converter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.Role;

import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

public class RoleConverter extends StrutsTypeConverter
{

    public RoleConverter()
    {
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass)
    {
        Role role = null;
        try
        {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            switch(id.intValue())
            {
            case 1: 
                role = Role.ROOT;
                break;

            case 2: 
                role = Role.SUPERADMIN;
                break;

            case 3: 
                role = Role.ADMIN;
                break;

            case 4: 
                role = Role.BASICADMIN;
                break;

            default:
                role = null;
                break;
            }
        }
        catch(Exception exception)
        {
            throw new TypeConversionException();
        }
        return role;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object)
    {
        return ((Role)object).getValue();
    }
}
