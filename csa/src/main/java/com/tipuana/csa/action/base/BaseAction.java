package com.tipuana.csa.action.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.opensymphony.xwork2.ActionSupport;
import com.tipuana.csa.interceptor.SpringSecurityPrincipal;
import com.tipuana.csa.model.User;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	protected User authenticatedUser;
	protected static Log log = LogFactory.getLog(BaseCreateReadUpdateRemoveDeleteAction.class);
	private String mappedRequest;
	private String actionMethod;

	public String getActionClass() {
		return getClass().getSimpleName();
	}
	
	public String getDestination() {
		return getClass().getSimpleName();
	}
	
	public String getMappedRequest() {
		return mappedRequest;
	}

	public String getActionMethod() {
		return actionMethod;
	}
	
	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
		setMappedRequest(getActionClass() + "_" + actionMethod);
	}
	
	public void setMappedRequest(String mappedRequest) {
		this.mappedRequest = mappedRequest;
		log.debug("Setting mappedRequest to " + this.mappedRequest);
	}

	public User getAuthenticatedUser() {
		return authenticatedUser;
	}

	@SpringSecurityPrincipal
	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

}