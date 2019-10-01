package com.tipuana.csa.action.base;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.action.util.Constants;


@SuppressWarnings("serial")
public abstract class CreateReadUpdateAction extends BaseCreateReadUpdateRemoveDeleteAction implements Creatable, Listable, Updatable {

	@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=false)
	public abstract String create() throws Exception;

	@Override @SkipValidation
	public String add() {
		setActionMethod(Constants.CREATE);
		
		return Constants.SUCCESS;
	}
	
	@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=false)
	public abstract String update() throws Exception;

	@Override @SkipValidation
	public String edit() {
		setActionMethod(Constants.UPDATE);
		
		return Constants.SUCCESS;
	}

	@Override @SkipValidation
	public String list() {		
		setActionMethod(Constants.LIST);

		
		return Constants.LIST;
	}
	
	@Override
	public String listAll() {
		getSession().remove(getListName());
		
		return list();
	}

}