package com.tipuana.csa.action.base;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.action.util.Constants;


@SuppressWarnings("serial")
public abstract class ReadRemoveDeleteAction extends BaseCreateReadUpdateRemoveDeleteAction implements Showable, Listable, Removable, Deletable {

	@Override @SkipValidation
	public String list() {
		setActionMethod(Constants.LIST);
		
		return Constants.LIST;
	}
	
	@Override @SkipValidation 
	public String show() {
		setReadOnly(true);
		
		
		return Constants.SUCCESS;
	}
	
	@Override @SkipValidation
	public String remove() {
		setReadOnly(true);
		
		setActionMethod(Constants.DELETE);
		
		return Constants.SUCCESS;
	}

	@Override @SkipValidation @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=false)
	public abstract String delete() throws Exception;
	
	@Override
	public String listAll() {
		getSession().remove(getListName());
		
		return list();
	}
	
}