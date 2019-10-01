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

import com.tipuana.csa.dao.SystematizationAfterPhotoDAO;
import com.tipuana.csa.model.SystematizationAfterPhoto;


public class SystematizationAfterPhotoDAOHibernate extends
		GenericHibernateDAO<SystematizationAfterPhoto, Long> implements SystematizationAfterPhotoDAO 
{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SystematizationAfterPhoto> find(final long systematizationId) {
		
		return (List<SystematizationAfterPhoto>) getHibernateTemplate().executeFind(new HibernateCallback<List<SystematizationAfterPhoto>>() {
			
			@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED, readOnly=true)
			public List<SystematizationAfterPhoto> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Query query = session.createQuery("from SystematizationAfterPhoto saf where saf.systematization.id=:systematizationId order by saf.created asc");
				query.setLong("systematizationId", systematizationId);
				
				return query.list();
			}
		});
	}

}
