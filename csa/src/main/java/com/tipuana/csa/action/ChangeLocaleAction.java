package com.tipuana.csa.action;

import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.tipuana.csa.action.util.Constants;

@SuppressWarnings("serial")
public class ChangeLocaleAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private String redirectToAction;
	private String redirectToNamespace;
	private String requestLocale;
	
	public String execute() {
		String requestLocale = getRequestLocale();
		Map<String, Object> session = getSession();
		//session.put("request_locale", requestLocale); 
		//session.put(DefaultLocaleResolver.LOCALE_KEY, new Locale(requestLocale));
		
		String currentAction = session.get("currentAction").toString();
		String currentNamespace = session.get("currentNamespace").toString();
		String currentLanguage = session.get("currentLanguage").toString();
		
		ResourceBundle currentResourceBundle = 
			getTexts(
				new StringBuilder("com/tipuana/csa/action/package_")
					.append(currentLanguage)
					.toString()
			);
		
		ResourceBundle nextResourceBundle = 
			getTexts(
				new StringBuilder("com/tipuana/csa/action/package_")
					.append(requestLocale)
					.toString());
		
		Iterator<String> keySetIterator = currentResourceBundle.keySet().iterator();
		
		boolean namespaceWasFound = false;
		
		if(currentNamespace.equalsIgnoreCase("/")) {
			setRedirectToNamespace(currentNamespace);
			namespaceWasFound = true;
		}
		
		while(!namespaceWasFound) {
			
			String key = keySetIterator.next();
			
			if(key.endsWith("Namespace") && currentResourceBundle.getString(key).equalsIgnoreCase(currentNamespace)) {
				setRedirectToNamespace(nextResourceBundle.getString(key));
				namespaceWasFound = true;
			}
			
		}
		
		if(currentAction.equalsIgnoreCase("")) {			
			currentAction = getText("homeAction");
		}
		
		keySetIterator = currentResourceBundle.keySet().iterator();
		
		boolean actionWasFound = false;
				
		while(!actionWasFound) {
			String key = keySetIterator.next();
			
			if(key.endsWith("Action") && currentResourceBundle.getString(key).equalsIgnoreCase(currentAction)) {
				setRedirectToAction(nextResourceBundle.getString(key));
				actionWasFound = true;
			}
		}
		
		return Constants.SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getRequestLocale() {
		return this.requestLocale;
	}
	
	public void setRequestLocale(String requestLocale) {
		this.requestLocale = requestLocale;
	}

	public String getRedirectToAction() {
		return redirectToAction;
	}

	public String getRedirectToNamespace() {
		return redirectToNamespace;
	}

	public void setRedirectToAction(String redirectToAction) {
		this.redirectToAction = redirectToAction;
	}

	public void setRedirectToNamespace(String redirectToNamespace) {
		this.redirectToNamespace = redirectToNamespace;
	}
}