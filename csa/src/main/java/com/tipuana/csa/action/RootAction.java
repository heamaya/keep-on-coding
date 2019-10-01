package com.tipuana.csa.action;

import com.opensymphony.xwork2.ActionSupport;
import com.tipuana.csa.action.util.Constants;


@SuppressWarnings("serial")
public class RootAction extends ActionSupport {
	private String startDestination = null;
	private String startNamespace = null;

	@Override
	public String execute() throws Exception {
		String language = getLocale().getLanguage();
		
		if(language.equalsIgnoreCase(Constants.SPANISH)) {
			startDestination = new String(Constants.SPANISH_START_ACTION);
			startNamespace = new String(Constants.SPANISH_START_NAMESPACE);			
		} else if(language.equalsIgnoreCase(Constants.PORTUGUESE)) {
			startDestination = new String(Constants.PORTUGUESE_START_ACTION);
			startNamespace = new String(Constants.PORTUGUESE_START_NAMESPACE);
		} else {
			startDestination = new String(Constants.ENGLISH_START_ACTION);
			startNamespace = new String(Constants.ENGLISH_START_NAMESPACE);
		}
		
		return Constants.SUCCESS;
	}

	public String getStartDestination() {
		return startDestination;
	}

	public String getStartNamespace() {
		return startNamespace;
	}

	public void setStartDestination(String startDestination) {
		this.startDestination = startDestination;
	}

	public void setStartNamespace(String startNamespace) {
		this.startNamespace = startNamespace;
	}
}