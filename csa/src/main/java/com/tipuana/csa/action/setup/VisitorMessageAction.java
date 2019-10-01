package com.tipuana.csa.action.setup;

import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.ReadRemoveDeleteAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.VisitorMessageDAO;
import com.tipuana.csa.model.VisitorMessage;


@SuppressWarnings("serial")
public class VisitorMessageAction extends ReadRemoveDeleteAction implements ModelDriven<VisitorMessage>, Preparable {
	private VisitorMessageDAO visitorMessageDAO;
	private VisitorMessage model;
	
	public VisitorMessageAction() {
		setListName("visitorMessages");
	}

	@Override
	public String delete() throws Exception {
		getVisitorMessageDAO().makeTransient(getModel());

		return listAll();
	}

	@Override
	public String list() {
		setActionMethod(Constants.LIST);
		
		getRequest().put(getListName(),getVisitorMessageDAO().findAll(Order.desc("created")));
		
		return Constants.LIST;
	}
	
	public VisitorMessageDAO getVisitorMessageDAO() {
		return visitorMessageDAO;
	}

	public void setVisitorMessageDAO(VisitorMessageDAO visitorMessageDAO) {
		this.visitorMessageDAO = visitorMessageDAO;
	}

	public VisitorMessage getModel() {
		return model;
	}

	public void setModel(VisitorMessage model) {
		this.model = model;
	}

	@Override
	public void prepare() throws Exception {
		
		if(getRequestId() != 0L) {
			setModel(getVisitorMessageDAO().findById(getRequestId(), true));
		}
		
	}
}