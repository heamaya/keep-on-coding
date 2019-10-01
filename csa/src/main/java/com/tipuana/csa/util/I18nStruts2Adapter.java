package com.tipuana.csa.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.views.jsp.TagUtils;
import org.displaytag.localization.I18nResourceProvider;
import org.displaytag.localization.LocaleResolver;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.util.ValueStack;


public class I18nStruts2Adapter implements LocaleResolver, I18nResourceProvider {
    public static final String UNDEFINED_KEY = "???";

    public Locale resolveLocale(HttpServletRequest request) {

        Locale locale = null;
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        
        for(Object object:valueStack.getRoot()) {
        	
        	if(object instanceof LocaleProvider) {
        		locale = ((LocaleProvider) object).getLocale();
        		
        		break;
        	}
        	
        }
        
        if (locale == null) {
            locale = Locale.getDefault();
        }

        return locale;
    }

    public String getResource(String resourceKey, String defaultValue, Tag tag, PageContext pageContext) {
        String key = (resourceKey != null) ? resourceKey : defaultValue;
        String text = null;
        ValueStack valueStack = TagUtils.getStack(pageContext);
        
        for(Object object: valueStack.getRoot()) {
        	
        	if(object instanceof TextProvider) {
        		text = ((TextProvider) object).getText(key);
        		
        		break;
        	}
        }

        if (text == null && resourceKey != null) {
            text = UNDEFINED_KEY + resourceKey + UNDEFINED_KEY;
        }

        return text;
    }

}