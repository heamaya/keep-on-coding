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

import com.tipuana.csa.dao.SystematizationBeforePhotoDAO;
import com.tipuana.csa.model.SystematizationBeforePhoto;


public class SystematizationBeforePhotoDAOHibernate extends
		GenericHibernateDAO<SystematizationBeforePhoto, Long> implements
		SystematizationBeforePhotoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SystematizationBeforePhoto> find(final long systematizationId) {
		
		return (List<SystematizationBeforePhoto>) getHibernateTemplate().executeFind(new HibernateCallback<List<SystematizationBeforePhoto>>() {
			
			
			@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED, readOnly=true)
			public List<SystematizationBeforePhoto> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Query query = session.createQuery("from SystematizationBeforePhoto sbf where sbf.systematization.id=:systematizationId order by sbf.created asc");
				query.setLong("systematizationId", systematizationId);
				
				return query.list();
			}
		});
	}

}
