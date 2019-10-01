package com.tipuana.csa.action.setup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.BaseAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.JournalDAO;
import com.tipuana.csa.dao.JournalIncomeEntryDAO;
import com.tipuana.csa.dao.JournalOutcomeEntryDAO;
import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.Journal;
import com.tipuana.csa.model.JournalEntry;
import com.tipuana.csa.model.JournalIncomeEntry;
import com.tipuana.csa.model.JournalOutcomeEntry;
import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;
import com.tipuana.csa.model.UserJournal;

@SuppressWarnings("serial")
public class JournalSummaryAction extends BaseAction implements RequestAware, Preparable, SessionAware {
	private UserDAO userDAO;
	private JournalDAO journalDAO;
	private JournalIncomeEntryDAO journalIncomeEntryDAO;
	private JournalOutcomeEntryDAO journalOutcomeEntryDAO;
	private Long journalId;
	private Journal journal;
	private List<Journal> journals;
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	@Override
	public void prepare() throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("fromDate"));
		orderList.add(Order.desc("toDate"));
		
		setJournals(getJournalDAO().findAll(orderList));
	}
	
	public String listAll() {
		return goTo();	
	}
	
	@SuppressWarnings("unchecked")
	public String list() {
		
		if(session.get("entries") != null && session.get("journal") != null) {
			setActionMethod(Constants.LIST);
			
			getRequest().put("entries", (List<JournalEntry>) session.get("entries"));
			this.setJournal((Journal) session.get("journal"));
				
			return Constants.LIST;	
		} else {
			return goTo();
		}
			
	}
	
	public String goTo() {
		setActionMethod(Constants.LIST);
		List<Journal> journals = getJournals();
		Journal currentJournal = null;
		
		if(journals.size() > 0) {
			
			if(getJournalId() == null || getJournalId().doubleValue() == 0) {
				
				Journal lastClosedJournal = journals.get(0);
				Calendar calendar = Calendar.getInstance();
			    calendar.setTime(lastClosedJournal.getToDate());
			    calendar.add(Calendar.DATE, 1);
			    Date openEntriesFromDate = calendar.getTime();// The day after last journal toDate.
			    Date now = new Date();
			    Date openEntriesToDate =  null;
			    
			    if(openEntriesFromDate.getTime() > now.getTime()) {
			    	calendar.setTime(openEntriesFromDate);
			    	calendar.add(Calendar.DATE, 30);
			    	openEntriesToDate = calendar.getTime();
			    } else {
			    	openEntriesToDate = now;
			    }
			    
			    FromToDateField openEntriesFromToDate = new FromToDateField(openEntriesFromDate, openEntriesToDate);
				List<JournalIncomeEntry> openIncomes = getJournalIncomeEntryDAO().find(openEntriesFromToDate);
				List<JournalOutcomeEntry> openOutcomes = getJournalOutcomeEntryDAO().find(openEntriesFromToDate);
					
				if(openIncomes.size() > 0 || openOutcomes.size() > 0) {
					currentJournal = generateOpenJournal(openEntriesFromToDate, openIncomes, openOutcomes);
				} else {
					currentJournal = generateOpenJournal();
				}
				
			} else {
				currentJournal = getJournalDAO().findById(getJournalId(), true);
			}
			
		} else {
			List<JournalIncomeEntry> openIncomes = getJournalIncomeEntryDAO().findAll(Order.desc("entryDate"));
			List<JournalOutcomeEntry> openOutcomes = getJournalOutcomeEntryDAO().findAll(Order.desc("entryDate"));
			
			if(openIncomes.size() > 0 || openOutcomes.size() > 0) {
				currentJournal = generateOpenJournal(openIncomes, openOutcomes);
			} else {
				currentJournal = generateOpenJournal();
			}
		}
		
		setJournal(currentJournal);
		List<JournalEntry> entries = getEntries(currentJournal);		
		getRequest().put("entries", entries);
		getSession().put("entries", entries);
		getSession().put("journal", currentJournal);
		
		return Constants.LIST;
	}
	
	private Journal generateOpenJournal() {
		Journal openJournal = new Journal();
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date defaultFromDate = null;
		Date defaultToDate = null;
		
		try {
			defaultFromDate = dateFormat.parse("1999-01-01");
			defaultToDate = dateFormat.parse("2099-01-01");
		} catch(Exception exception) {
			
		}
		
		openJournal.setFromDate(defaultFromDate);
		openJournal.setToDate(defaultToDate);
		openJournal.setIncomes(new HashSet<JournalIncomeEntry>());
		openJournal.setOutcomes(new HashSet<JournalOutcomeEntry>());
		openJournal.setRevenue(0.0d);
		openJournal.setIncomesAmount(0.0d);
		openJournal.setOutcomesAmount(0.0d);
		openJournal.setUsersJournal(new HashSet<UserJournal>());
		
		return openJournal;
	}

	private Journal generateOpenJournal(List<JournalIncomeEntry> openIncomes, List<JournalOutcomeEntry> openOutcomes) {
		FromToDateField fromToDateField = new FromToDateField();
		Date lastIncomeDate = openIncomes.get(0).getEntryDate();
		Date lastOutcomeDate = openOutcomes.get(0).getEntryDate();
		Date firstIncomeDate = openIncomes.get(openIncomes.size() -1).getEntryDate();
		Date firstOutcomeDate = openOutcomes.get(openOutcomes.size() -1).getEntryDate();
		
		if(lastIncomeDate.after(lastOutcomeDate)) {
			fromToDateField.setTo(lastIncomeDate);	
		} else {
			fromToDateField.setTo(lastOutcomeDate);
		}
		
		if(firstIncomeDate.before(firstOutcomeDate)) {
			fromToDateField.setFrom(firstIncomeDate);
		} else {
			fromToDateField.setFrom(firstOutcomeDate);
		}
		
		return generateOpenJournal(fromToDateField, openIncomes, openOutcomes);
	}
	
	private Journal generateOpenJournal(FromToDateField fromToEntryDate, List<JournalIncomeEntry> openIncomes, List<JournalOutcomeEntry> openOutcomes) {
		Journal openJournal = new Journal();
		openJournal.setFromDate(fromToEntryDate.getFrom());
		openJournal.setToDate(fromToEntryDate.getTo());
		openJournal.setIncomes(new HashSet<JournalIncomeEntry>(openIncomes));
		openJournal.setOutcomes(new HashSet<JournalOutcomeEntry>(openOutcomes));
		List<Object []> incomesReport = getJournalIncomeEntryDAO().getIncomesReport(fromToEntryDate);
		List<Object []> outcomesReport = getJournalOutcomeEntryDAO().getOutcomesReport(fromToEntryDate);
		ListIterator<Object []> incomeReportsIterator = incomesReport.listIterator();
		ListIterator<Object []> outcomeReportsIterator = outcomesReport.listIterator();		
		double totalIncomesAmount = 0d;
		double totalOutcomesAmount = 0d;
		
		for(Object[] ioa : incomesReport ) {
			totalIncomesAmount += Double.parseDouble(ioa[1].toString());	
		}
		
		for(Object[] ooa : outcomesReport ) {
			totalOutcomesAmount += Double.parseDouble(ooa[1].toString());	
		}
		
		openJournal.setIncomesAmount(totalIncomesAmount);
		openJournal.setOutcomesAmount(totalOutcomesAmount);
		openJournal.setRevenue(totalIncomesAmount - totalOutcomesAmount);
		List<UserJournal> usersJournal = new ArrayList<UserJournal>();
		
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
					userJournalRealRevenue = (openJournal.getRevenue() / userCount) - userJournalRevenue;
					usersJournal.add(new UserJournal(openJournal, incomeUser, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
				} else {
					userJournalRevenue = incomesAmount - 0d;
					userJournalRealRevenue = (openJournal.getRevenue() / userCount) - userJournalRevenue;
					usersJournal.add(new UserJournal(openJournal, incomeUser, incomesAmount, 0d, userJournalRevenue , userJournalRealRevenue));
					userJournalRevenue = 0d - outcomesAmount;
					userJournalRealRevenue = (openJournal.getRevenue() / userCount) - userJournalRevenue;
					usersJournal.add(new UserJournal(openJournal, outcomeUser, 0d, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
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
				double userJournalRealRevenue = (openJournal.getRevenue() / userCount) - userJournalRevenue;
			
				usersJournal.add(new UserJournal(openJournal, user, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
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
				double userJournalRealRevenue = (openJournal.getRevenue() / userCount) - userJournalRevenue;
			
				usersJournal.add(new UserJournal(openJournal, user, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
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
				double userJournalRealRevenue = (openJournal.getRevenue() / userCount) - userJournalRevenue;
			
				usersJournal.add(new UserJournal(openJournal, user, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
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
					double userJournalRealRevenue = (openJournal.getRevenue() / userCount) - userJournalRevenue;
					
					usersJournal.add(new UserJournal(openJournal, superAdminUser, incomesAmount, outcomesAmount, userJournalRevenue , userJournalRealRevenue));
				}
				
			}
			
		}
		
		
		
		openJournal.setUsersJournal(new HashSet<UserJournal>(usersJournal));
			
		return openJournal;
	}

	private List<JournalEntry> getEntries(Journal journal) {
		
		Comparator<JournalEntry> entryComparator = new Comparator<JournalEntry>() {
			@Override
			public int compare(JournalEntry je1, JournalEntry je2) {
		        long n1 = je1.getEntryDate().getTime();
		        long n2 = je2.getEntryDate().getTime();
		        
		        if (n1 < n2) {
		        	return -1;
		        } else if(n1 > n2) {
		        	return 1;
		        } else {
		        	
		        	long jec1 = je1.getCreated().getTime();
		        	long jec2 = je2.getCreated().getTime();
		        	
			        if (jec1 < jec2) {
			        	return -1;
			        } else if(jec1 > jec2) {
			        	return 1;
			        }
			        
			        return 0;
		        }

			}
			
		};
		
		List<JournalEntry> entries = new ArrayList<JournalEntry>(); 
		entries.addAll(new ArrayList<JournalIncomeEntry>(journal.getIncomes()));
		entries.addAll(new ArrayList<JournalOutcomeEntry>(journal.getOutcomes()));
		
		Collections.sort(entries, entryComparator);
		
		return entries;
	}
	


	public JournalDAO getJournalDAO() {
		return journalDAO;
	}
	
	public void setJournalDAO(JournalDAO journalDAO) {
		this.journalDAO = journalDAO;
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

	public Long getJournalId() {
		return journalId;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setJournalId(Long journalId) {
		this.journalId = journalId;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	
	public List<Journal> getJournals() {
		return this.journals;
	}

	public void setJournals(List<Journal> journals) {
		this.journals = journals;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
}