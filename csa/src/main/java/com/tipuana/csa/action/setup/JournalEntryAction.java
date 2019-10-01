package com.tipuana.csa.action.setup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.dao.GenericJournalEntryDAO;
import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.dao.awareness.UserDAOAware;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.JournalEntry;
import com.tipuana.csa.model.JournalEntryIsClosed;
import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;

@SuppressWarnings("serial")
public abstract class JournalEntryAction<JE extends JournalEntry, JEDAO extends GenericJournalEntryDAO<JE, Long>> extends
		CreateReadUpdateRemoveDeleteSearchAction implements ModelDriven<JE>, Preparable, UserDAOAware {

	private JE model;
	private JEDAO journalEntryDAO;
	private FromToDateField fromToEntryDate;
	private UserDAO userDAO;
	private JournalEntryIsClosed journalEntryIsClosed;
	
	public JournalEntryAction() {
		setListName("journalEntries");
	}

	@Override
	public JE getModel() {
		return model;
	}

	@Override
	public String create() throws Exception {
		getJournalEntryDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		getJournalEntryDAO().makeTransient(getModel());
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		getJournalEntryDAO().makePersistent(getModel());
		
		return listAll();
	}

	@Override
	public String find() {
		JE model = getModel();
		JournalEntryIsClosed isClosed = getJournalEntryIsClosed();
		
		if(isClosed == JournalEntryIsClosed.OPEN) {
			model.setIsClosed(false);
		} else if(isClosed == JournalEntryIsClosed.CLOSED) {
			model.setIsClosed(true);
		} else {
			model.setIsClosed(null);
		}
		
		getSession().put(getListName(), getJournalEntryDAO().find(model, getFromToDateFieldMap(), getMatchMode()));
		
		return list();
	}
	
	protected Map<String, FromToDateField> getFromToDateFieldMap() {
		Map<String, FromToDateField> fromToDateFieldsMap = new HashMap<String, FromToDateField>();
		fromToDateFieldsMap.put("fromToEntryDate", getFromToEntryDate());
		
		return fromToDateFieldsMap;
	}
	
	@Override @SkipValidation
	public String list() {
	
		if(getSession().get(getListName()) != null) {
			getRequest().put(getListName(), getSession().get(getListName()));
		} else {
			getRequest().put(getListName(), getJournalEntryDAO().findAll(Order.desc("entryDate")));
		}
		
		return super.list();
	}

	public void setModel(JE model) {
		this.model = model;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public List<User> getSuperAdministrators() {
		User user = new User();
		user.setRole(Role.SUPERADMIN);
		
		return getUserDAO().find(user);
	}
	
	public FromToDateField getFromToEntryDate() {
		return fromToEntryDate;
	}

	public void setFromToEntryDate(FromToDateField fromToEntryDate) {
		this.fromToEntryDate = fromToEntryDate;
	}

	public JEDAO getJournalEntryDAO() {
		return journalEntryDAO;
	}

	public void setJournalEntryDAO(JEDAO journalEntryDAO) {
		this.journalEntryDAO = journalEntryDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void prepare() throws Exception {
		
		long id = getRequestId(); 
		
		if(id == 0) {
			setModel((JE) new JournalEntry());
		}
	}

	public JournalEntryIsClosed getJournalEntryIsClosed() {
		return journalEntryIsClosed;
	}

	public void setJournalEntryIsClosed(JournalEntryIsClosed journalEntryIsClosed) {
		this.journalEntryIsClosed = journalEntryIsClosed;
	}
	
	public List<JournalEntryIsClosed> getJournalEntryIsClosedOptions() {
		return Arrays.asList(JournalEntryIsClosed.values());
	}
}