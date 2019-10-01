package com.tipuana.csa.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import com.tipuana.csa.action.util.Constants;

@SuppressWarnings("serial")
public abstract class BasePresentationAction extends ActionSupport implements DestinationAware, RequestAware, SessionAware, InternationalizedAction {
	
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected String requestLocale;
	
	public String execute() {
		
		Map<String, Object> session = getSession();
		ActionProxy actionProxy = ActionContext.getContext().getActionInvocation().getProxy();
		session.put("currentAction", actionProxy.getActionName());
		session.put("currentNamespace", actionProxy.getNamespace());
		session.put("currentLanguage", getLocale().getLanguage());

		return Constants.SUCCESS;
	}

	@Override
	public String getDestination() {
		return getClass().getSimpleName();
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String getRequestLocale() {
		return requestLocale;
	}

	public void setRequestLocale(String requestLocale) {
		this.requestLocale = requestLocale;
	}
}