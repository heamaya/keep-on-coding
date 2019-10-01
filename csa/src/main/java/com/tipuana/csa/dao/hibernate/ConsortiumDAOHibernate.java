package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tipuana.csa.dao.ConsortiumDAO;
import com.tipuana.csa.model.Consortium;


public class ConsortiumDAOHibernate extends
		GenericHibernateDAO<Consortium, Long> implements ConsortiumDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getConsortiumsIds() {
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Long>>() {
			
			@Override
			public List<Long> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				boolean isStarred = true;
				Query query = session.createQuery("select c.id from Consortium c where c.starred=:isStarred order by c.creationDate asc");
				query.setBoolean("isStarred", isStarred);
			
				return query.list();
			}
		});
	}
}
