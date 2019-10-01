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

import com.tipuana.csa.dao.ConsortiumPhotoDAO;
import com.tipuana.csa.model.ConsortiumPhoto;


public class ConsortiumPhotoDAOHibernate extends
		GenericHibernateDAO<ConsortiumPhoto, Long> implements ConsortiumPhotoDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsortiumPhoto> find(final long consortiumId) {
		
		return (List<ConsortiumPhoto>) getHibernateTemplate().executeFind(new HibernateCallback<List<ConsortiumPhoto>>() {
			
			@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED, readOnly=true)
			public List<ConsortiumPhoto> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Query query = session.createQuery("from ConsortiumPhoto cf where cf.consortium.id=:consortiumId order by cf.created asc");
				query.setLong("consortiumId", consortiumId);
				
				return query.list();
			}
		});
	}
}
