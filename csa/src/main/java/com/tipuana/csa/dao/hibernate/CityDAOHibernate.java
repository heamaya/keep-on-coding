package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Province;


public class CityDAOHibernate extends GenericHibernateDAO<City, Long> implements
		CityDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<City> find(final City city) {
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<City>>() {
			
			@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
			public List<City> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Criteria cityCriteria = session.createCriteria(City.class);
				
				List<String> excludeProperties = new ArrayList<String>();
				excludeProperties.add("id");
				excludeProperties.add("created");
				excludeProperties.add("version");
				excludeProperties.add("addresses");
				
				Example cityExample = Example.create(city);
				
				cityCriteria.add(cityExample);
				
				if(city.getName() == null || city.getName().equalsIgnoreCase("")) {
					excludeProperties.add("name");
				}
				
				if(city.getProvince() == null) {
					excludeProperties.add("province");
				} else {
					Example provinceExample = Example.create(city.getProvince());
					cityCriteria.createCriteria("province").add(provinceExample);
				}
				
				for(String exclude : excludeProperties) {
					cityExample.excludeProperty(exclude);
				}
				
				cityCriteria.addOrder(Order.asc("name"));
				
				return cityCriteria.list();
			}
		});
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<City> find(final Province province) {
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<City>>() {
			
			@Override
			public List<City> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Query query = session.createQuery("from City c where c.province=:province order by c.name asc");
				query.setEntity("province", province);
				
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<City> find(final long provinceId) {
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<City>>() {
			
			@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED, readOnly=true)
			public List<City> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Query query = session.createQuery("from City c where c.province.id=:provinceId order by c.name asc");
				query.setLong("provinceId", provinceId);
				
				return query.list();
			}
		});
	}
}