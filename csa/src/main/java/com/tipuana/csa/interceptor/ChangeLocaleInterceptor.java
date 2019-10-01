package com.tipuana.csa.interceptor;

import java.util.Locale;
import java.util.Map;

import org.apache.tiles.locale.impl.DefaultLocaleResolver;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tipuana.csa.action.InternationalizedAction;


@SuppressWarnings("serial")
public class ChangeLocaleInterceptor extends AbstractInterceptor {
	

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		String requestLocale = ((InternationalizedAction) actionInvocation.getAction()).getRequestLocale();
		
		if(requestLocale == null || requestLocale.equalsIgnoreCase("")) {
			requestLocale = actionInvocation.getInvocationContext().getLocale().getLanguage();	
		}
		
		Locale currentLocale = new Locale(requestLocale);
		
		actionInvocation.getInvocationContext().setLocale(currentLocale);
		Map<String,Object> session = actionInvocation.getInvocationContext().getSession();	
		session.put("request_locale", requestLocale); 
		session.put(DefaultLocaleResolver.LOCALE_KEY, currentLocale);
		
				
		return actionInvocation.invoke();
	}
}