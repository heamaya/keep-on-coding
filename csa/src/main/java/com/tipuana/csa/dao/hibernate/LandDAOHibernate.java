package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.dao.LandDAO;
import com.tipuana.csa.model.Land;


public class LandDAOHibernate extends GenericHibernateDAO<Land, Long> implements LandDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Land> find(final long companyId) {
		
		return (List<Land>) getHibernateTemplate().executeFind(new HibernateCallback<List<Land>>() {
			
			@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED, readOnly=true)
			public List<Land> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Query query = session.createQuery("from Land land where land.company.id=:companyId order by land.name asc");
				query.setLong("companyId", companyId);
				
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Land> find(final Land land, final MatchMode matchMode) {
		
		return (List<Land>) getHibernateTemplate().executeFind(new HibernateCallback<List<Land>>() {
			
			@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
			public List<Land> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Criteria landCriteria = session.createCriteria(Land.class);
				Example landExample = Example.create(land);
				
				List<String> excludeProperties = new ArrayList<String>();
                excludeProperties.add("id");
                excludeProperties.add("version");
                excludeProperties.add("created");
                excludeProperties.add("boundariesFileName");
                excludeProperties.add("boundariesContentType");
                
                if(land.getCompany() == null) {
                	excludeProperties.add("company");
                } else {
                	Example companyExample = Example.create(land.getCompany());
                	landCriteria.createCriteria("company").add(companyExample).addOrder(Order.asc("name"));
                }
                
                if(land.getName() == null || land.getName().equalsIgnoreCase("")) {
                	excludeProperties.add("name");
                }
                
                if(land.getDescription() == null || land.getDescription().equalsIgnoreCase("")) {
                	excludeProperties.add("description");
                }
                
                if(land.getNearestProvince() == null) {
                    excludeProperties.add("nearestProvince");
                } else {
                    Example nearestProvinceExample = Example.create(land.getNearestProvince());
                    landCriteria.createCriteria("nearestProvince").add(nearestProvinceExample);
                }
                    
                if(land.getNearestCity() == null) {
                    excludeProperties.add("nearestCity");
                } else {
                    Example nearestCityExample = Example.create(land.getNearestCity());
                    landCriteria.createCriteria("nearestCity").add(nearestCityExample);
                }
                
                if(land.getNearestCityDistance() == null || land.getNearestCityDistance().isNaN()) {
                	excludeProperties.add("nearestCityDistance");
                }
                
                if(land.getSurfaceArea() == null || land.getSurfaceArea().isNaN()) {
                	excludeProperties.add("surfaceArea");
                }
                
                for(String excludeProperty : excludeProperties) {
                	landExample.excludeProperty(excludeProperty);
                }
                
				landExample.enableLike(matchMode);
                landCriteria.add(landExample);
                landCriteria.addOrder(Order.asc("name"));

                
                return landCriteria.list();
				
			}
		});
	}
}
