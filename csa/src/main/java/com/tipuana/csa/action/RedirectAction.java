package com.tipuana.csa.action;

import com.tipuana.csa.action.base.BaseAction;
import com.tipuana.csa.action.util.RedirectConstants;
import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;

@SuppressWarnings("serial")
public class RedirectAction extends BaseAction {

	@Override
	public String execute() throws Exception {

		User authenticatedUser = getAuthenticatedUser();
		
		if(authenticatedUser != null) {

	       if(authenticatedUser.getRole().getValue().equals(Role.ADMIN.getValue())) {
	    	   return RedirectConstants.ADMIN;
	       } else if(authenticatedUser.getRole().getValue().equals(Role.SUPERADMIN.getValue())) {
	    	   return RedirectConstants.SUPERADMIN;
	       } else if(authenticatedUser.getRole().getValue().equals(Role.BASICADMIN.getValue())) {
	    	   return RedirectConstants.BASICADMIN;
	       } else if(authenticatedUser.getRole().getValue().equals(Role.CUSTOMER.getValue())) {
	    	   return RedirectConstants.CUSTOMER;
	       } else if(authenticatedUser.getRole().getValue().equals(Role.ROOT.getValue())) {
	    	   return RedirectConstants.ROOT;
	       }
			
		}
		
		return RedirectConstants.INPUT;
	}
}