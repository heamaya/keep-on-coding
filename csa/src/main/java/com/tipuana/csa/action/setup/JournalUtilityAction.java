package com.tipuana.csa.action.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;

import au.com.bytecode.opencsv.CSVReader;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.JournalDAO;
import com.tipuana.csa.dao.JournalIncomeEntryDAO;
import com.tipuana.csa.dao.JournalOutcomeEntryDAO;
import com.tipuana.csa.dao.JournalUtilityDAO;
import com.tipuana.csa.dao.UserJournalDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.Journal;
import com.tipuana.csa.model.JournalIncomeEntry;
import com.tipuana.csa.model.JournalOutcomeEntry;
import com.tipuana.csa.model.JournalUtility;
import com.tipuana.csa.model.User;
import com.tipuana.csa.model.UserJournal;

@SuppressWarnings("serial")
public class JournalUtilityAction extends CreateAction implements ModelDriven<JournalUtility>, Preparable {
	private JournalUtility model;
	private JournalUtilityDAO journalUtilityDAO;
	private JournalIncomeEntryDAO journalIncomeEntryDAO;
	private JournalOutcomeEntryDAO journalOutcomeEntryDAO;
	private JournalDAO journalDAO;
	private UserJournalDAO userJournalDAO;
	private File journal;
	
	public JournalUtilityAction() {
		setListName("journalUtilityList");
	}
	
	@Override
	public void prepare() throws Exception {
		
		long id = getRequestId();
		
		if(id == 0) {
			setModel(new JournalUtility());
		} else {
			setModel(getJournalUtilityDAO().findById(id, true));
		}

	}

	@Override
	public String create() throws Exception {		
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    
	    CSVReader journalsReader = new CSVReader(new InputStreamReader(new FileInputStream(getJournal()), "ISO-8859-1"));
	    String [] journalsLine;
	    //List<Journal> journals = new ArrayList<Journal>();
	    List<UserJournal> usersJournal = new ArrayList<UserJournal>();
	    
	    while ((journalsLine = journalsReader.readNext()) != null) {
	    	Journal journal = new Journal();
	    	journal.setFromDate(dateFormat.parse(journalsLine[0]));
	    	journal.setToDate(dateFormat.parse(journalsLine[1]));
	    	FromToDateField fromToEntryDate = new FromToDateField(journal.getFromDate(), journal.getToDate());
	    	List<JournalIncomeEntry> incomes = getJournalIncomeEntryDAO().find(fromToEntryDate);
	    	List<JournalOutcomeEntry> outcomes = getJournalOutcomeEntryDAO().find(fromToEntryDate);
	    	
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

			for(JournalIncomeEntry i : incomes) {
				i.setIsClosed(true);
				journal.getIncomes().add(i);
			}
			
			for(JournalOutcomeEntry o : outcomes) {
				journal.getOutcomes().add(o);
				o.setIsClosed(true);
			}
			
			journal.setIncomesAmount(totalIncomesAmount);
			journal.setOutcomesAmount(totalOutcomesAmount);
			journal.setRevenue(totalIncomesAmount - totalOutcomesAmount);
			journal.setIncomes(new HashSet<JournalIncomeEntry>(incomes));
			journal.setOutcomes(new HashSet<JournalOutcomeEntry>(outcomes));		
			journal = getJournalDAO().makePersistent(journal);

			int incomesReportSize = incomesReport.size();
			int outcomesReportSize = outcomesReport.size();
			int userCount = Math.max(incomesReportSize, outcomesReportSize);

				
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
		}

			getUserJournalDAO().makePersistent(usersJournal);
	    
	    /*
	    System.out.println("Cierres:");
	    
	    for(Journal j : journals) {
	    	System.out.println(j);	
	    }
	    */
		getJournalUtilityDAO().makePersistent(getModel());
		
		FileUtil.save(getJournal(), Constants.JOURNAL_MONTHS_PATH, getModel().getJournalFileName());
		
		return listAll();
	}
	
	@Override
	public String list() {
		getRequest().put(getListName(), getJournalUtilityDAO().findAll());
		
		return super.list();
	}	

	@Override
	public JournalUtility getModel() {
		return model;
	}

	public JournalUtilityDAO getJournalUtilityDAO() {
		return journalUtilityDAO;
	}
	
	public void setJournalUtilityDAO(JournalUtilityDAO journalUtilityDAO) {
		this.journalUtilityDAO = journalUtilityDAO;
	}

	public void setModel(JournalUtility model) {
		this.model = model;
	}

	public JournalDAO getJournalDAO() {
		return journalDAO;
	}

	public void setJournalDAO(JournalDAO journalDAO) {
		this.journalDAO = journalDAO;
	}

	public File getJournal() {
		return journal;
	}

	public void setJournal(File journal) {
		this.journal = journal;
	}

	public UserJournalDAO getUserJournalDAO() {
		return userJournalDAO;
	}

	public void setUserJournalDAO(UserJournalDAO userJournalDAO) {
		this.userJournalDAO = userJournalDAO;
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
}