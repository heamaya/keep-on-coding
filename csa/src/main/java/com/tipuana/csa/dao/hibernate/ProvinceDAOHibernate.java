package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.model.Province;


public class ProvinceDAOHibernate extends GenericHibernateDAO<Province, Long>
		implements ProvinceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Province> find(final Province province) {
		
		return (List<Province>) getHibernateTemplate().executeFind(new HibernateCallback<List<Province>>() {
			
			@Override @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
			public List<Province> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Criteria provinceCriteria = session.createCriteria(Province.class);
				
				List<String> excludeProperties = new ArrayList<String>();
				excludeProperties.add("id");
				excludeProperties.add("created");
				excludeProperties.add("version");
				excludeProperties.add("cities");
				excludeProperties.add("addresses");
				
				Example provinceExample = Example.create(province);
				
				provinceCriteria.add(provinceExample);
				
				if(province.getName() == null || province.getName().equalsIgnoreCase("")) {
					excludeProperties.add("name");
				}
				
				for(String exclude : excludeProperties) {
					provinceExample.excludeProperty(exclude);
				}
				
				provinceCriteria.addOrder(Order.asc("name"));
				
				return provinceCriteria.list();
			}
		});
	}	
}