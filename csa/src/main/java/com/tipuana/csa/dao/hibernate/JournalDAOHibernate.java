package com.tipuana.csa.dao.hibernate;

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
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.dao.JournalDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.Journal;

public class JournalDAOHibernate extends GenericHibernateDAO<Journal, Long> implements JournalDAO {
	
    @SuppressWarnings("unchecked")
	public List<Journal> find(final Journal journal, final Map<String, FromToDateField> fromToDateFieldMap) {
    	
        return (List<Journal>) getHibernateTemplate().execute(new HibernateCallback<List<Journal>>() {
        	
        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<Journal> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria journalCriteria = session.createCriteria(Journal.class);
                Example journalExample = Example.create(journal);
                
                journalExample.excludeProperty("id");
                journalExample.excludeProperty("version");
                journalExample.excludeProperty("created");
                journalExample.excludeProperty("fromDate");
                journalExample.excludeProperty("toDate");
                journalExample.excludeProperty("incomes");
                journalExample.excludeProperty("outcomes");
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date defaultFromDate = null;
				Date defaultToDate = null;
				
				try {
					defaultFromDate = dateFormat.parse("1999-01-01");
					defaultToDate = dateFormat.parse("2099-01-01");
				} catch(Exception exception) {
					
				}
                
				FromToDateField fromFromToDate = fromToDateFieldMap.get("fromFromToDate");
				
				if(fromFromToDate != null) {

					journalCriteria.add(Restrictions.between(
						"fromDate", 
						fromFromToDate.getFrom() != null ? fromFromToDate.getFrom() : defaultFromDate, 
						fromFromToDate.getTo() != null ? fromFromToDate.getTo() : defaultToDate
					));
					
				}
				
				FromToDateField toFromToDate = fromToDateFieldMap.get("toFromToDate");
				
				if(toFromToDate != null) {

					journalCriteria.add(Restrictions.between(
						"toDate", 
						toFromToDate.getFrom() != null ? toFromToDate.getFrom() : defaultFromDate, 
						toFromToDate.getTo() != null ? toFromToDate.getTo() : defaultToDate
					));
					
				}
                
				journalCriteria.add(journalExample);
				
                return journalCriteria.list();
            }
        });
    }

}