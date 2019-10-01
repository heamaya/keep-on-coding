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

import com.tipuana.csa.dao.SystematizationDAO;
import com.tipuana.csa.model.Systematization;


public class SystematizationDAOHibernate extends
		GenericHibernateDAO<Systematization, Long> implements SystematizationDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getSystematizationsIds() {
		
		return (List<Long>) getHibernateTemplate().executeFind(new HibernateCallback<List<Long>>() {
			
			@Override
			public List<Long> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				boolean isStarred = true;
				Query query = session.createQuery("select s.id from Systematization s where s.starred=:isStarred order by s.company.name asc, s.land.name asc");
				query.setBoolean("isStarred", isStarred);
			
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Systematization> find(final Systematization systematization, final MatchMode matchMode) {
		
		return (List<Systematization>) getHibernateTemplate().executeFind(new HibernateCallback<List<Systematization>>() {
			
			@Override
			public List<Systematization> doInHibernate(Session session) 
				throws HibernateException, SQLException 
			{
				Criteria systematizationCriteria = session.createCriteria(Systematization.class);
				Example systematizationExample = Example.create(systematization);
				
				List<String> excludeProperties = new ArrayList<String>();
				excludeProperties.add("id");
				excludeProperties.add("created");
				excludeProperties.add("version");
				excludeProperties.add("systematizationFileName");
				excludeProperties.add("systematizationContentType");
				excludeProperties.add("beforePhotos");
				excludeProperties.add("afterPhotos");
				excludeProperties.add("systematizationProjects");
				
				if(systematization.getCompany() != null) {
					Example companyExample = Example.create(systematization.getCompany());
					systematizationCriteria.createCriteria("company")
										   .add(companyExample)
										   .addOrder(Order.asc("name"));
				} else {
					excludeProperties.add("company");
				}
				
				if(systematization.getLand() != null) {
					Example landExample = Example.create(systematization.getLand());
					systematizationCriteria.createCriteria("land")
					                       .add(landExample)
					                       .addOrder(Order.asc("name"));
				} else {
					excludeProperties.add("land");
				}
				
				if(systematization.getDescriptionSpanish() == null || 
				   systematization.getDescriptionSpanish().equalsIgnoreCase("")) 
				{
					excludeProperties.add("descriptionSpanish");
				}
				
				if(systematization.getDescriptionEnglish() == null || 
				   systematization.getDescriptionEnglish().equalsIgnoreCase("")) 
				{
					excludeProperties.add("descriptionEnglish");
				}
				
				if(systematization.getDescriptionPortuguese() == null || 
				   systematization.getDescriptionPortuguese().equalsIgnoreCase("")) 
				{
					excludeProperties.add("descriptionPortuguese");
				}
				
				if(systematization.getStarred() == null) {
					excludeProperties.add("starred");
				}
				
				for(String excludeProperty : excludeProperties) {
					systematizationExample.excludeProperty(excludeProperty);
				}
			
				systematizationExample.enableLike(matchMode);
				systematizationCriteria.add(systematizationExample);
				
				return systematizationCriteria.list();
			}
		});
	}
}
