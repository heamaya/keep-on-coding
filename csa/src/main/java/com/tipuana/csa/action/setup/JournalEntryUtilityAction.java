package com.tipuana.csa.action.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.action.util.FileUtil;
import com.tipuana.csa.dao.JournalEntryUtilityDAO;
import com.tipuana.csa.dao.JournalIncomeEntryDAO;
import com.tipuana.csa.dao.JournalOutcomeEntryDAO;
import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.model.JournalEntryUtility;
import com.tipuana.csa.model.JournalIncomeEntry;
import com.tipuana.csa.model.JournalIncomeEntryType;
import com.tipuana.csa.model.JournalOutcomeEntry;
import com.tipuana.csa.model.JournalOutcomeEntryType;
import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;

@SuppressWarnings("serial")
public class JournalEntryUtilityAction extends CreateAction
		implements ModelDriven<JournalEntryUtility>, Preparable {
	
	private JournalEntryUtility model;
	private JournalIncomeEntryDAO journalIncomeEntryDAO;
	private JournalOutcomeEntryDAO journalOutcomeEntryDAO;
	private JournalEntryUtilityDAO journalEntryUtilityDAO;
	private UserDAO userDAO;
	private File journalEntries;
	
	public JournalEntryUtilityAction() {
		setListName("journalEntryUtilityList");
	}
	
	@Override
	public void prepare() throws Exception {
		
		long id = getRequestId();
		
		if(id == 0) {
			setModel(new JournalEntryUtility());
		} else {
			setModel(getJournalEntryUtilityDAO().findById(id, true));
		}

	}

	@Override
	public String create() throws Exception {		
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    
	    List<User> users = getUserDAO().find(Role.SUPERADMIN);
	    User amaya = null;
	    User crusta = null;
	    	    	
	    if(users.get(0).getUsername().equalsIgnoreCase("heamaya@yahoo.com.ar")) {
	    	amaya = users.get(0);
	    	crusta = users.get(1);
	    } else {
	    	crusta = users.get(0);
	    	amaya = users.get(1);
	    }
	    
	    CSVReader journalEntriesReader = new CSVReader(new InputStreamReader(new FileInputStream(getJournalEntries()), "ISO-8859-1"));	    
	    String [] journalEntriesLine;
	    List<JournalIncomeEntry> incomes = new ArrayList<JournalIncomeEntry>();
	    List<JournalOutcomeEntry> outcomes = new ArrayList<JournalOutcomeEntry>();
	     
	    while ((journalEntriesLine = journalEntriesReader.readNext()) != null) {
	    		    	
	    	if((journalEntriesLine[2] != null && !journalEntriesLine[2].equals("")) ||
	    	   (journalEntriesLine[3] != null && !journalEntriesLine[3].equals(""))) 
	    	{
	    		JournalIncomeEntry income = new JournalIncomeEntry();
		    	income.setEntryDate(dateFormat.parse(journalEntriesLine[0]));
		    	income.setDescription(journalEntriesLine[1]);
	    		
	    		if(journalEntriesLine[1].contains("Cobranza") || 
	    		   journalEntriesLine[1].contains("COBRANZA") ||
	    		   journalEntriesLine[1].contains("Cobranza")
	    		) {
	    			income.setIncomeType(JournalIncomeEntryType.PAYMENT);
	    		} else {
	    			income.setIncomeType(JournalIncomeEntryType.OTHER);
	    		}
	    		
	    		if(journalEntriesLine[2] != null && !journalEntriesLine[2].equals("")) {
	    			income.setUser(amaya);
	    			income.setAmount(new Double(journalEntriesLine[2]));	
	    			income.setIsClosed(false);
	    		} else {
	    			income.setUser(crusta);
	    			income.setAmount(new Double(journalEntriesLine[3]));
	    			income.setIsClosed(false);
	    		}
	    		
	    		incomes.add(income);
	    		
	    	} else if((journalEntriesLine[4] != null && !journalEntriesLine[4].equals("")) ||
	    	   (journalEntriesLine[5] != null && !journalEntriesLine[5].equals(""))) 
	    	{
	    		JournalOutcomeEntry outcome = new JournalOutcomeEntry();
	    		outcome.setEntryDate(dateFormat.parse(journalEntriesLine[0]));
	    		outcome.setDescription(journalEntriesLine[1]);
	    		
	    		if(journalEntriesLine[1].contains("LUZ") || 
	    		   journalEntriesLine[1].contains("Luz") ||
	    		   journalEntriesLine[1].contains("luz") ||
	    		   journalEntriesLine[1].contains("INTERNET") || 
	    		   journalEntriesLine[1].contains("Internet") ||
	    		   journalEntriesLine[1].contains("internet") ||
	    		   journalEntriesLine[1].contains("TELEFONO") || 
	    		   journalEntriesLine[1].contains("Telefono") ||
	    		   journalEntriesLine[1].contains("telefono") ||
	    		   journalEntriesLine[1].contains("TELÉFONO") || 
	    		   journalEntriesLine[1].contains("Teléfono") ||
	    		   journalEntriesLine[1].contains("teléfono") ||
	    		   journalEntriesLine[1].contains("TELEFONICO") || 
	    		   journalEntriesLine[1].contains("Telefonico") ||
	    		   journalEntriesLine[1].contains("telefonico") ||
	    		   journalEntriesLine[1].contains("TELEFÓNICO") || 
	    		   journalEntriesLine[1].contains("Telefónico") ||
	    		   journalEntriesLine[1].contains("telefónico") ||
	    		   journalEntriesLine[1].contains("TELECOM") || 
	    		   journalEntriesLine[1].contains("Telecom") ||
	    		   journalEntriesLine[1].contains("telecom")
	    		) {
	    			outcome.setOutcomeType(JournalOutcomeEntryType.SERVICES);
	    		} else if(
	    			journalEntriesLine[1].contains("LIMPIEZA") || 
	    			journalEntriesLine[1].contains("Limpieza") ||
	    			journalEntriesLine[1].contains("limpieza")) 
	    		{
	    			outcome.setOutcomeType(JournalOutcomeEntryType.CLEANING);
	    		} else if(
	    			journalEntriesLine[1].contains("SUELDO") || 
	    			journalEntriesLine[1].contains("Sueldo") ||
	    			journalEntriesLine[1].contains("sueldo") ||
	    			journalEntriesLine[1].contains("SALARIO") ||
	    			journalEntriesLine[1].contains("Salario") ||
	    			journalEntriesLine[1].contains("salario")) 
	    		{
	    			outcome.setOutcomeType(JournalOutcomeEntryType.SALARY);
	    		} else if(
	    			journalEntriesLine[1].contains("HONORARIOS") || 
		    		journalEntriesLine[1].contains("Honorarios") ||
		    		journalEntriesLine[1].contains("honorarios")
		    	) {
		    		outcome.setOutcomeType(JournalOutcomeEntryType.HONORARIA);
		    	} else if(
		    		journalEntriesLine[1].contains("ALQUILER") || 
	    			journalEntriesLine[1].contains("Alquiler") ||
	    			journalEntriesLine[1].contains("alquiler")
		    	) {
		    		outcome.setOutcomeType(JournalOutcomeEntryType.RENT);
		    	} else if(
		    		journalEntriesLine[1].contains("MONOTRIBUTO") || 
		    		journalEntriesLine[1].contains("Monotributo") ||
		    		journalEntriesLine[1].contains("monotributo") ||
		    		journalEntriesLine[1].contains("BRUTOS") || 
	    			journalEntriesLine[1].contains("Brutos") ||
	    			journalEntriesLine[1].contains("brutos")
			    ) {
			    	outcome.setOutcomeType(JournalOutcomeEntryType.TAXES);
			    } else if(
			    	journalEntriesLine[1].contains("SUPERMERCADO") || 
			    	journalEntriesLine[1].contains("Supermercado") ||
			    	journalEntriesLine[1].contains("supermercado") ||
			    	journalEntriesLine[1].contains("AGUA") || 
			    	journalEntriesLine[1].contains("Agua") ||
			    	journalEntriesLine[1].contains("agua") ||
			    	journalEntriesLine[1].contains("SUP") || 
		    		journalEntriesLine[1].contains("Sup") ||
		    		journalEntriesLine[1].contains("sup")
				) {
			    	outcome.setOutcomeType(JournalOutcomeEntryType.VICTUALS);
				} else if(
					journalEntriesLine[1].contains("TONER") || 
				    journalEntriesLine[1].contains("Toner") ||
				    journalEntriesLine[1].contains("toner") ||
				    journalEntriesLine[1].contains("PLOT") || 
				    journalEntriesLine[1].contains("Plot") ||
				    journalEntriesLine[1].contains("plot") ||
				    journalEntriesLine[1].contains("PLOTEAR") || 
				    journalEntriesLine[1].contains("Plotear") ||
				    journalEntriesLine[1].contains("plotear") ||
				    journalEntriesLine[1].contains("PLOTEO") || 
				    journalEntriesLine[1].contains("Ploteo") ||
				    journalEntriesLine[1].contains("ploteo") ||
				    journalEntriesLine[1].contains("LIBRERIA") || 
				    journalEntriesLine[1].contains("Libreria") ||
				    journalEntriesLine[1].contains("libreria") ||
				    journalEntriesLine[1].contains("LIBRERÍA") || 
				    journalEntriesLine[1].contains("Librería") ||
				    journalEntriesLine[1].contains("librería") ||
				    journalEntriesLine[1].contains("BOLSAS") || 
				    journalEntriesLine[1].contains("Bolsas") ||
				    journalEntriesLine[1].contains("bolsas") ||
				    journalEntriesLine[1].contains("BOLSA") || 
				    journalEntriesLine[1].contains("Bolsa") ||
				    journalEntriesLine[1].contains("bolsa") ||
				    journalEntriesLine[1].contains("CENTER DRAW") || 
				    journalEntriesLine[1].contains("Center Draw") ||
				    journalEntriesLine[1].contains("center draw") ||
				    journalEntriesLine[1].contains("bolsa") ||
				    journalEntriesLine[1].contains("CARTUCHOS") || 
			    	journalEntriesLine[1].contains("Cartuchos") ||
			    	journalEntriesLine[1].contains("cartuchos")
				) {
				   	outcome.setOutcomeType(JournalOutcomeEntryType.OFFICE);
				} else if(
					journalEntriesLine[1].contains("SEGURO") || 
			    	journalEntriesLine[1].contains("Seguro") ||
			    	journalEntriesLine[1].contains("seguro")) 
			    {
			    	outcome.setOutcomeType(JournalOutcomeEntryType.INSURANCE);
			    } else if(
					journalEntriesLine[1].contains("COBRANZA") || 
				    journalEntriesLine[1].contains("Cobranza") ||
				    journalEntriesLine[1].contains("cobranza")) 
			    {
			    	outcome.setOutcomeType(JournalOutcomeEntryType.COMISSION);
			    } else if(
			    	journalEntriesLine[1].contains("RELEVAMIENTO") || 
				    journalEntriesLine[1].contains("Relevamiento") ||
				    journalEntriesLine[1].contains("relevamiento") ||
				    journalEntriesLine[1].contains("MENSURA") || 
				    journalEntriesLine[1].contains("Mensura") ||
				    journalEntriesLine[1].contains("mensura")) 
			    {
			    	outcome.setOutcomeType(JournalOutcomeEntryType.MENSURATION);
			    } else if(
				    journalEntriesLine[1].contains("BCO.") || 
					journalEntriesLine[1].contains("Bco.") ||
					journalEntriesLine[1].contains("BCO") ||
					journalEntriesLine[1].contains("Bco") || 
					journalEntriesLine[1].contains("BANCARIO") ||
					journalEntriesLine[1].contains("Bancario") || 
					journalEntriesLine[1].contains("BANCO") ||
					journalEntriesLine[1].contains("banco")
				) {
			    	outcome.setOutcomeType(JournalOutcomeEntryType.BANK);
				} else {
					outcome.setOutcomeType(JournalOutcomeEntryType.OTHER);
				}
	    		
	    		if(journalEntriesLine[4] != null && !journalEntriesLine[4].equals("")) {
	    			outcome.setUser(amaya);
	    			outcome.setAmount(new Double(journalEntriesLine[4]));
	    			outcome.setIsClosed(false);
	    		} else {
	    			outcome.setUser(crusta);
	    			outcome.setAmount(new Double(journalEntriesLine[5]));
	    			outcome.setIsClosed(false);
	    		}
	    		
	    		outcomes.add(outcome);
	    	}

	    }
	    /*
    	System.out.println("Ingresos:");
    	
    	for(JournalIncomeEntry i : incomes) {
    		System.out.println(i);
    	}

    	System.out.println("Egresos:");
    	
    	for(JournalOutcomeEntry o : outcomes) {
    		System.out.println(o);
    	}
	    */
		getJournalIncomeEntryDAO().makePersistent(incomes);
		getJournalOutcomeEntryDAO().makePersistent(outcomes);
		getJournalEntryUtilityDAO().makePersistent(getModel());
		
		FileUtil.save(getJournalEntries(), Constants.JOURNAL_ENTRIES_PATH, getModel().getJournalEntriesFileName());
		
		return listAll();
	}
	
	@Override
	public String list() {
		getRequest().put(getListName(), getJournalEntryUtilityDAO().findAll());
		
		return super.list();
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

	public File getJournalEntries() {
		return journalEntries;
	}

	public void setJournalEntries(File journalEntries) {
		this.journalEntries = journalEntries;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public JournalEntryUtility getModel() {
		return model;
	}

	public void setModel(JournalEntryUtility model) {
		this.model = model;
	}

	public JournalEntryUtilityDAO getJournalEntryUtilityDAO() {
		return journalEntryUtilityDAO;
	}

	public void setJournalEntryUtilityDAO(
			JournalEntryUtilityDAO journalEntryUtilityDAO) {
		this.journalEntryUtilityDAO = journalEntryUtilityDAO;
	}
	
}