package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.dao.JournalDAO;
import com.tipuana.csa.dao.JournalIncomeEntryDAO;
import com.tipuana.csa.dao.JournalOutcomeEntryDAO;
import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.dao.UserJournalDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.Journal;
import com.tipuana.csa.model.JournalIncomeEntry;
import com.tipuana.csa.model.JournalOutcomeEntry;
import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;
import com.tipuana.csa.model.UserJournal;

@SuppressWarnings("serial")
public class JournalAction extends CreateReadUpdateRemoveDeleteSearchAction
		implements ModelDriven<Journal>, Preparable {
	
	private Journal model;
	private JournalDAO journalDAO;
	private JournalIncomeEntryDAO journalIncomeEntryDAO;
	private JournalOutcomeEntryDAO journalOutcomeEntryDAO;
	private UserJournalDAO userJournalDAO;
	private FromToDateField fromFromToDate;
	private FromToDateField toFromToDate;
	private UserDAO userDAO;
	
	public JournalAction() {
		setListName("journals");
	}
	
	@Override
	public void prepare() throws Exception {
		
		long id = getRequestId();
		
		if(id == 0) {
			setModel(new Journal());
		} else {
			setModel(getJournalDAO().findById(id, true));
		}

	}

	@Override
	public String create() throws Exception {		
		Journal journal = getModelFromProperties();
		journal = getJournalDAO().makePersistent(journal);
		getUserJournalDAO().makePersistent(getUsersJournal(journal));
		
		return listAll();
	}

	@Override
	public String delete() throws Exception {
		Journal journal = getModel();
		
		Set<JournalIncomeEntry> incomes = journal.getIncomes();
		Set<JournalOutcomeEntry> outcomes = journal.getOutcomes();
				
		
		for(JournalIncomeEntry i: incomes) {
			i.setIsClosed(false);	
			i.setJournal(null);
		}
		
		for(JournalOutcomeEntry o: outcomes) {
			o.setIsClosed(false);
			o.setJournal(null);
		}
		
		getJournalDAO().makeTransient(getModel());
		getJournalIncomeEntryDAO().makePersistent(incomes);
		getJournalOutcomeEntryDAO().makePersistent(outcomes);
		
		return listAll();
	}

	@Override
	public String update() throws Exception {
		Journal journal = getModelFromProperties();
		journal = getJournalDAO().makePersistent(journal);
		getUserJournalDAO().makePersistent(getUsersJournal(journal));
		
		return listAll();
	}

	@Override
	public String find() {
		getSession().put(getListName(), getJournalDAO().find(getModel(), getFromToDateFieldMap()));
		
		return list();
	}
	
	@Override
	public String list() {
		
		if (getSession().get(getListName()) == null) {
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.desc("fromDate"));
			orders.add(Order.desc("toDate"));
			
			getRequest().put(getListName(), getJournalDAO().findAll(orders));
		} else {
			getRequest().put(getListName(), getSession().get(getListName()));
		}
		
		return super.list();
	}	
	
	private Map<String, FromToDateField> getFromToDateFieldMap() {
		Map<String, FromToDateField> fromToDateFieldsMap = new HashMap<String, FromToDateField>();
		fromToDateFieldsMap.put("fromFromToDate", getFromFromToDate());
		fromToDateFieldsMap.put("toFromToDate", getToFromToDate());
		
		return fromToDateFieldsMap;
	}

	@Override
	public Journal getModel() {
		return model;
	}

	public JournalDAO getJournalDAO() {
		return journalDAO;
	}
	
	public void setJournalDAO(JournalDAO journalDAO) {
		this.journalDAO = journalDAO;
	}

	public void setModel(Journal model) {
		this.model = model;
	}

	public FromToDateField getFromFromToDate() {
		return fromFromToDate;
	}

	public FromToDateField getToFromToDate() {
		return toFromToDate;
	}

	public void setFromFromToDate(FromToDateField fromFromToDate) {
		this.fromFromToDate = fromFromToDate;
	}

	public void setToFromToDate(FromToDateField toFromToDate) {
		this.toFromToDate = toFromToDate;
	}
	
	public JournalIncomeEntryDAO getJournalIncomeEntryDAO() {
		return journalIncomeEntryDAO;
	}

	public JournalOutcomeEntryDAO getJournalOutcomeEntryDAO() {
		return journalOutcomeEntryDAO;
	}

	public void setJournalIncomeEntryDAO(JournalIncomeEntryDAO journalIncomeEntryDAO) {
		this.journalIncomeEntryDAO = journalIncomeEntryDAO;
	}

	public void setJournalOutcomeEntryDAO(
			JournalOutcomeEntryDAO journalOutcomeEntryDAO) {
		this.journalOutcomeEntryDAO = journalOutcomeEntryDAO;
	}
	
	public UserJournalDAO getUserJournalDAO() {
		return userJournalDAO;
	}

	public void setUserJournalDAO(UserJournalDAO userJournalDAO) {
		this.userJournalDAO = userJournalDAO;
	}

	private Journal getModelFromProperties() {
		Journal model = getModel();
		FromToDateField fromToEntryDate = new FromToDateField(model.getFromDate(), model.getToDate());
		
		List<JournalIncomeEntry> incomes = getJournalIncomeEntryDAO().find(fromToEntryDate);
		List<JournalOutcomeEntry> outcomes = getJournalOutcomeEntryDAO().find(fromToEntryDate);
		model.setIncomes(new HashSet<JournalIncomeEntry>(incomes));
		model.setOutcomes(new HashSet<JournalOutcomeEntry>(outcomes));		
		double totalIncomesAmount = 0d;
		double totalOutcomesAmount = 0d;		
		
		for(JournalIncomeEntry i: incomes) {
			i.setIsClosed(true);
			totalIncomesAmount += i.getAmount();	
		}
		
		for(JournalOutcomeEntry o: outcomes) {
			o.setIsClosed(true);
			totalOutcomesAmount += o.getAmount();	
		}
		
		model.setIncomesAmount(totalIncomesAmount);
		model.setOutcomesAmount(totalOutcomesAmount);
		model.setRevenue(totalIncomesAmount - totalOutcomesAmount);
		
		
		return model;	
	}
	
	private List<UserJournal> getUsersJournal(Journal journal) {
		List<UserJournal> usersJournal = new ArrayList<UserJournal>();
		
		FromToDateField fromToEntryDate = new FromToDateField(journal.getFromDate(), journal.getToDate());
		List<Object []> incomesReport = getJournalIncomeEntryDAO().getIncomesReport(fromToEntryDate);
		List<Object []> outcomesReport = getJournalOutcomeEntryDAO().getOutcomesReport(fromToEntryDate);
		ListIterator<Object []> incomeReportsIterator = incomesReport.listIterator();
		ListIterator<Object []> outcomeReportsIterator = outcomesReport.listIterator();		
	
		int incomesReportSize = incomesReport.size();
		int outcomesReportSize = outcomesReport.size();

		List<User> superAdmins = getUserDAO().find(Role.SUPERADMIN);
		int userCount = superAdmins.size();
			
		if(incomesReportSize == outcomesReportSize) {
			
			while(incomeReportsIterator.hasNext() && outcomeReportsIterator.hasNext()) {
				User incomeUser = null;
				User outcomeUser = null;
				double incomesAmount = 0d;
				double outcomesAmount = 0d;
				Object [] incomeReport = incomeReportsIterator.next();
				incomeUser = (User) incomeReport[0];
				incomesAmount = Double.parseDouble(incomeReport[1].toString()); 
				Object [] outcomeReport = outcomeReportsIterator.next();
				outcomeUser = (User) outcomeReport[0];
				outcomesAmount = Double.parseDouble(outcomeReport[1].toString());
				
				double userJournalRevenue = 0d;
				double userJournalRealRevenue = 0d;
				
				if(incomeUser == outcomeUser) {
					userJournalRevenue = incomesAmount - outcomesAmount;
					userJournalRealRevenue = (journal.getRevenue() / userCount) - userJournalRevenue;
					usersJournal.add(new UserJournal(journal, incomeUser, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
				} else {
					userJournalRevenue = incomesAmount - 0d;
					userJournalRealRevenue = (journal.getRevenue() / userCount) - userJournalRevenue;
					usersJournal.add(new UserJournal(journal, incomeUser, incomesAmount, 0d, userJournalRevenue , userJournalRealRevenue));
					userJournalRevenue = 0d - outcomesAmount;
					userJournalRealRevenue = (journal.getRevenue() / userCount) - userJournalRevenue;
					usersJournal.add(new UserJournal(journal, outcomeUser, 0d, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
				}
			}
				
		} else if(incomesReportSize > outcomesReportSize) {
			
			User currentOutcomesUser = null;
			double currentOutcomesAmount = 0d;
			
			while(incomeReportsIterator.hasNext()) {
				User user = null;
				double incomesAmount = 0d;
				double outcomesAmount = 0d;
				
				Object [] incomeReport = incomeReportsIterator.next();
				user = (User) incomeReport[0];
				incomesAmount = Double.parseDouble(incomeReport[1].toString());
				
				if(outcomeReportsIterator.hasNext() && currentOutcomesUser == null) {
					Object [] outcomeReport = outcomeReportsIterator.next();
					currentOutcomesUser = (User) outcomeReport[0];
					currentOutcomesAmount = Double.parseDouble(outcomeReport[1].toString());
				}	
					
				if(user == currentOutcomesUser) {
					outcomesAmount = currentOutcomesAmount;
					currentOutcomesUser = null;
					currentOutcomesAmount = 0d;
				} 
				
				double userJournalRevenue = incomesAmount - outcomesAmount;
				double userJournalRealRevenue = (journal.getRevenue() / userCount) - userJournalRevenue;
			
				usersJournal.add(new UserJournal(journal, user, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
			}
			
		}  else if(incomesReportSize > outcomesReportSize) {
			
			User currentOutcomesUser = null;
			double currentOutcomesAmount = 0d;
			
			while(incomeReportsIterator.hasNext()) {
				User user = null;
				double incomesAmount = 0d;
				double outcomesAmount = 0d;
				
				Object [] incomeReport = incomeReportsIterator.next();
				user = (User) incomeReport[0];
				incomesAmount = Double.parseDouble(incomeReport[1].toString());
				
				if(outcomeReportsIterator.hasNext() && currentOutcomesUser == null) {
					Object [] outcomeReport = outcomeReportsIterator.next();
					currentOutcomesUser = (User) outcomeReport[0];
					currentOutcomesAmount = Double.parseDouble(outcomeReport[1].toString());
				}	
					
				if(user == currentOutcomesUser) {
					outcomesAmount = currentOutcomesAmount;
					currentOutcomesUser = null;
					currentOutcomesAmount = 0d;
				} 
				
				double userJournalRevenue = incomesAmount - outcomesAmount;
				double userJournalRealRevenue = (journal.getRevenue() / userCount) - userJournalRevenue;
			
				usersJournal.add(new UserJournal(journal, user, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
			}
			
		} else if(incomesReportSize < outcomesReportSize) {
			
			User currentIncomesUser = null;
			double currentIncomesAmount = 0d;
			
			while(outcomeReportsIterator.hasNext()) {
				User user = null;
				double incomesAmount = 0d;
				double outcomesAmount = 0d;
				
				Object [] outcomeReport = outcomeReportsIterator.next();
				user = (User) outcomeReport[0];
				outcomesAmount = Double.parseDouble(outcomeReport[1].toString());
				
				if(incomeReportsIterator.hasNext() && currentIncomesUser == null) {
					Object [] incomeReport = incomeReportsIterator.next();
					currentIncomesUser = (User) incomeReport[0];
					currentIncomesAmount = Double.parseDouble(incomeReport[1].toString());
				}	
					
				if(user == currentIncomesUser) {
					incomesAmount = currentIncomesAmount;
					currentIncomesUser = null;
					currentIncomesAmount = 0d;
				} 
				
				double userJournalRevenue = incomesAmount - outcomesAmount;
				double userJournalRealRevenue = (journal.getRevenue() / userCount) - userJournalRevenue;
			
				usersJournal.add(new UserJournal(journal, user, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
			}
			
		}
		
		if(superAdmins.size() > usersJournal.size()) {
			
			for(User superAdminUser : superAdmins) {
				
				boolean hasJournal = false;
				
				for(UserJournal userJournal: usersJournal) {
					
					if(userJournal.getUser() == superAdminUser) {
						hasJournal = true;
					}
				}
				
				if(!hasJournal) {
					double incomesAmount = 0d;
					double outcomesAmount = 0d;
					double userJournalRevenue = incomesAmount - outcomesAmount;
					double userJournalRealRevenue = (journal.getRevenue() / userCount) - userJournalRevenue;
					
					usersJournal.add(new UserJournal(journal, superAdminUser, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
				}
				
			}
			
		}
	
		return usersJournal;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}	
	
	
}