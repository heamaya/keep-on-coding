package com.tipuana.csa.dao.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.dao.GenericJournalEntryDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.JournalEntry;
import com.tipuana.csa.model.JournalIncomeEntry;
import com.tipuana.csa.model.JournalOutcomeEntry;

public abstract class GenericJournalEntryDAOHibernate<JE extends JournalEntry, ID extends Serializable> extends GenericHibernateDAO<JE, ID> implements GenericJournalEntryDAO<JE, ID> {
	
    @SuppressWarnings("unchecked")
	public List<JE> find(final JE journalEntry, final Map<String, FromToDateField> fromToDateFieldMap, final MatchMode matchMode) {
    	
        return (List<JE>) getHibernateTemplate().execute(new HibernateCallback<List<JE>>() {
        	
        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<JE> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria journalEntryCriteria = null;
                Example journalEntryExample = null;
                
                if(journalEntry instanceof JournalIncomeEntry) {
                	journalEntryCriteria = session.createCriteria(JournalIncomeEntry.class);
                	JournalIncomeEntry journalIncomeEntry = (JournalIncomeEntry) journalEntry;
                	journalEntryExample = Example.create(journalIncomeEntry);
                	
                	if(journalIncomeEntry.getIncomeType() == null) {
                		journalEntryExample.excludeProperty("incomeType");
                	}
                	
                } else if(journalEntry instanceof JournalOutcomeEntry) {
                	journalEntryCriteria = session.createCriteria(JournalOutcomeEntry.class);
                	JournalOutcomeEntry journalOutcomeEntry = (JournalOutcomeEntry) journalEntry;
                	journalEntryExample = Example.create(journalOutcomeEntry);
                	
                	if(journalOutcomeEntry.getOutcomeType() == null) {
                		journalEntryExample.excludeProperty("outcomeType");
                	}
                	
                } else {
                	journalEntryCriteria = session.createCriteria(JournalEntry.class);
                	journalEntryExample = Example.create((JournalEntry) journalEntry);
                }
                
                journalEntryExample.excludeProperty("id");
                journalEntryExample.excludeProperty("version");
                journalEntryExample.excludeProperty("created");
                journalEntryExample.excludeProperty("entryDate");
                
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date defaultFromDate = null;
				Date defaultToDate = null;
				
				try {
					defaultFromDate = dateFormat.parse("1999-01-01");
					defaultToDate = dateFormat.parse("2099-01-01");
				} catch(Exception exception) {
					
				}
                
				FromToDateField fromToEntryDate = fromToDateFieldMap.get("fromToEntryDate");
				
				if(fromToEntryDate != null) {

					journalEntryCriteria.add(Restrictions.between(
						"entryDate", 
						fromToEntryDate.getFrom() != null ? fromToEntryDate.getFrom() : defaultFromDate, 
								fromToEntryDate.getTo() != null ? fromToEntryDate.getTo() : defaultToDate
					));
					
				}
				
				if(journalEntry.getAmount() == null) {
					journalEntryExample.excludeProperty("amount");
				}
				
				if(journalEntry.getDescription() == null || journalEntry.getDescription().equalsIgnoreCase("")) {
					journalEntryExample.excludeProperty("description");
				}
				
				if(journalEntry.getIsClosed() == null) {
					journalEntryExample.excludeProperty("isClosed");
				}
				
				if(journalEntry.getUser() == null) {
					journalEntryExample.excludeProperty("user");
				} else {
					journalEntryCriteria.createCriteria("user").add(Example.create(journalEntry.getUser()));
				}
				
				journalEntryExample.enableLike(matchMode);
			
                journalEntryCriteria.add(journalEntryExample);
                
                return journalEntryCriteria.list();
            }
        });
    }

}