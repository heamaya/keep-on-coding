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

import com.tipuana.csa.dao.JournalOutcomeEntryDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.JournalOutcomeEntry;

public class JournalOutcomeEntryDAOHibernate extends GenericJournalEntryDAOHibernate<JournalOutcomeEntry, Long> implements JournalOutcomeEntryDAO {

	@SuppressWarnings("unchecked")
	public List<Object []> getOutcomesReport(final FromToDateField fromToDateField) {
    	
        return (List<Object []>) getHibernateTemplate().execute(new HibernateCallback<List<Object []>>() {
        	
        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<Object []> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("select joe.user, sum(joe.amount) from JournalOutcomeEntry joe where joe.entryDate >= :from and joe.entryDate <= :to group by joe.user order by joe.user.username asc");
                query.setDate("from", fromToDateField.getFrom());
                query.setDate("to", fromToDateField.getTo());
        		
                return (List<Object []>) query.list();
            }
        });
    }
	
    @SuppressWarnings("unchecked")
	public List<JournalOutcomeEntry> find(final FromToDateField fromToDateField) {
    	
        return (List<JournalOutcomeEntry>) getHibernateTemplate().execute(new HibernateCallback<List<JournalOutcomeEntry>>() {
        	
        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<JournalOutcomeEntry> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from JournalOutcomeEntry joe where joe.entryDate between :from and :to");
                query.setDate("from", fromToDateField.getFrom());
                query.setDate("to", fromToDateField.getTo());
        		
                return query.list();
            }
        });
    }

}