package com.tipuana.csa.action.base;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("serial")
public class BaseCreateReadUpdateRemoveDeleteAction extends BaseAction implements RequestAware, SessionAware {
	protected static Log log = LogFactory.getLog(BaseCreateReadUpdateRemoveDeleteAction.class);
	private long requestId;
	private boolean readOnly = false;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private String listName;
	
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		log.debug("Setting readOnly to " + readOnly);
	}
	
	public long getRequestId() {
		return requestId;
	}
	
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	
	public boolean isReadOnly() {
		return readOnly;
	}

	public static Log getLog() {
		return log;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public static void setLog(Log log) {
		BaseCreateReadUpdateRemoveDeleteAction.log = log;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}
}