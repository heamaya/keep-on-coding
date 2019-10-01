package com.tipuana.csa.action.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.dispatcher.StreamResult;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Result;

@SuppressWarnings("serial")
public abstract class DownloadFileAction extends ActionSupport implements ServletContextAware {
 
	private String fileName;
	private String contentType;
	private ServletContext servletContext;
	private boolean onlyStarred;
	private boolean isAllowed;
	
	public DownloadFileAction() {
		super();
		setOnlyStarred(false);
		setAllowed(true);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}	

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public InputStream getFile() throws Exception {
		
		try {
			
			File file = new File(getFilePath(), getFileName());
		
			if(file.exists() && isAllowed()) {
			
				Result result = ActionContext.getContext().getActionInvocation().getResult();
			
				if(result != null && result instanceof StreamResult) {
					StreamResult streamResult = (StreamResult) result;
					streamResult.setContentType(getContentType());
					streamResult.setContentDisposition("filename=\"" + getFileName() + "\"");
				}
			
				return new FileInputStream(file);
			}
			
		} catch(Exception exception) {
			throw exception;
		}
		
		return null;
		
	}
	
	public boolean isOnlyStarred() {
		return onlyStarred;
	}

	public void setOnlyStarred(boolean onlyStarred) {
		this.onlyStarred = onlyStarred;
	}
	
	public boolean isAllowed() {
		return isAllowed;
	}

	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	public abstract String getFilePath();
}