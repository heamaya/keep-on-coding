package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.dao.JournalIncomeEntryDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.JournalIncomeEntry;

public class JournalIncomeEntryDAOHibernate extends GenericJournalEntryDAOHibernate<JournalIncomeEntry, Long> implements JournalIncomeEntryDAO {
	
    @SuppressWarnings("unchecked")
	public List<Object []> getIncomesReport(final FromToDateField fromToDateField) {
    	
        return (List<Object []>) getHibernateTemplate().execute(new HibernateCallback<List<Object []>>() {
        	
        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<Object []> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("select jie.user, sum(jie.amount) from JournalIncomeEntry jie where jie.entryDate >= :from and jie.entryDate <= :to group by jie.user order by jie.user.username asc");
                query.setDate("from", fromToDateField.getFrom());
                query.setDate("to", fromToDateField.getTo());
        		
                return (List<Object []>) query.list();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
	public List<JournalIncomeEntry> find(final FromToDateField fromToDateField) {
    	
        return (List<JournalIncomeEntry>) getHibernateTemplate().execute(new HibernateCallback<List<JournalIncomeEntry>>() {
        	
        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<JournalIncomeEntry> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from JournalIncomeEntry jie where jie.entryDate between :from and :to");
                query.setDate("from", fromToDateField.getFrom());
                query.setDate("to", fromToDateField.getTo());
        		
                return query.list();
            }
        });
    }

}