package com.tipuana.csa.action.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.PaymentDAO;
import com.tipuana.csa.dao.SystematizationProjectDAO;
import com.tipuana.csa.dao.awareness.SystematizationProjectDAOAware;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.Payment;
import com.tipuana.csa.model.SystematizationProject;

@SuppressWarnings("serial")
public class PaymentAction extends CreateReadUpdateRemoveDeleteSearchAction
		implements ModelDriven<Payment>, Preparable, SystematizationProjectDAOAware {
	
	private Payment model;
	private SystematizationProjectDAO systematizationProjectDAO;
	private PaymentDAO paymentDAO;
	private FromToDateField fromToPaymentDate;
	
	public PaymentAction() {
		setListName("payments");
	}

	@Override
	public SystematizationProjectDAO getSystematizationProjectDAO() {
		return systematizationProjectDAO;
	}

	@Override
	public void prepare() throws Exception {
		long id = getRequestId();
		Payment payment = null;
		
		if(id == 0) {
			payment = new Payment();
		} else {
			payment = getPaymentDAO().findById(id, true);
		}

		payment.setUser(getAuthenticatedUser());
		setModel(payment);
	}

	@Override
	public String create() throws Exception {
		getPaymentDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		getPaymentDAO().makeTransient(getModel());
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getPaymentDAO().makePersistent(getModel());
		
		return listAll();
	}
			
	@Override
	public String list() {
		setActionMethod(Constants.LIST);
			
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {			
			getRequest().put(getListName(), getPaymentDAO().find(getAuthenticatedUser().getId()));			
		}
			
		return Constants.LIST;
	}

	@Override
	public String find() {
		getSession().put(getListName(), getPaymentDAO().find(getModel(), getFromToDateFieldMap()));
		
		return list();
	}

	@Override
	public Payment getModel() {
		return model;
	}

	public PaymentDAO getPaymentDAO() {
		return paymentDAO;
	}

	public void setModel(Payment model) {
		this.model = model;
	}

	public void setSystematizationProjectDAO(
			SystematizationProjectDAO systematizationProjectDAO) {
		this.systematizationProjectDAO = systematizationProjectDAO;
	}

	public void setPaymentDAO(PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	public List<SystematizationProject> getSystematizationProjects() {
		return getSystematizationProjectDAO().find(getAuthenticatedUser().getId());
	}
	
	private Map<String, FromToDateField> getFromToDateFieldMap() {
		Map<String, FromToDateField> fromToDateFieldsMap = new HashMap<String, FromToDateField>();
		fromToDateFieldsMap.put("paymentDate", getFromToPaymentDate());
		
		return fromToDateFieldsMap;
	}

	public FromToDateField getFromToPaymentDate() {
		return fromToPaymentDate;
	}

	public void setFromToPaymentDate(FromToDateField fromToPaymentDate) {
		this.fromToPaymentDate = fromToPaymentDate;
	}
}