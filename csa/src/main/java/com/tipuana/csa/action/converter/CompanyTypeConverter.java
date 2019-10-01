package com.tipuana.csa.action.converter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.tipuana.csa.model.CompanyType;

import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

public class CompanyTypeConverter extends StrutsTypeConverter
{

    public CompanyTypeConverter()
    {
    }

    @SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String values[], Class toClass)
    {
        CompanyType companyType = null;
        try
        {
            Integer id = Integer.valueOf(values[0].equalsIgnoreCase("") ? "0" : values[0]);
            switch(id.intValue())
            {
            case 1:
                companyType = CompanyType.LEGAL_PERSON;
                break;

            case 2:
                companyType = CompanyType.FACT;
                break;

            case 3:
                companyType = CompanyType.ANONYMOUS;
                break;

            case 4:
                companyType = CompanyType.LIMITED_RESPONSIBILITY;
                break;

            default:
                companyType = null;
                break;
            }
        }
        catch(Exception exception)
        {
            throw new TypeConversionException();
        }
        return companyType;
    }

    @SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object)
    {
        return ((CompanyType)object).getValue();
    }
}
