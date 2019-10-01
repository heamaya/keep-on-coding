package com.tipuana.csa.action.base;

import java.util.Arrays;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.criterion.MatchMode;

import com.tipuana.csa.action.util.Constants;


@SuppressWarnings("serial")
public abstract class CreateReadUpdateRemoveDeleteSearchAction extends CreateReadUpdateRemoveDeleteAction implements Searchable {
	private MatchMode matchMode;
	
	@Override
	public abstract String create() throws Exception;

	@Override @SkipValidation
	public abstract String delete() throws Exception;

	@Override
	public abstract String update() throws Exception;

	@Override @SkipValidation
	public String search() {
		setActionMethod(Constants.FIND);
		
		return Constants.SUCCESS;
	}
	
	@Override @SkipValidation
	public abstract String find();

	public MatchMode getMatchMode() {
		return matchMode;
	}

	public void setMatchMode(MatchMode matchMode) {
		this.matchMode = matchMode;
	}
	
	public List<com.tipuana.csa.model.MatchMode> getMatchModes() {
		return Arrays.asList(com.tipuana.csa.model.MatchMode.values());
	}
}