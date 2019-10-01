package com.tipuana.csa.action;

@SuppressWarnings("serial")
public abstract class BasePresentationPhotoAction extends BasePresentationAction
		implements PathAware {

	@Override
	public abstract String getPath();
	
	public String execute() {
		return super.execute();
	}
}
