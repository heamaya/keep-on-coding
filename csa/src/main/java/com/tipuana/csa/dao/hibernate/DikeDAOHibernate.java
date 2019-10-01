package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tipuana.csa.dao.DikeDAO;
import com.tipuana.csa.model.Dike;
import com.tipuana.csa.model.FromToDateField;

public class DikeDAOHibernate extends GenericHibernateDAO<Dike, Long> implements
		DikeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Dike> find(final long userId) {
		
		return (List<Dike>) getHibernateTemplate().executeFind(new HibernateCallback<List<Dike>>() {
			
			@Override
			public List<Dike> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery("from Dike d where d.user.id=:userId order by d.created asc");
				query.setLong("userId", userId);
			
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dike> find(final Dike dike, final Map<String, FromToDateField> fromToDateFieldMap) {
		
		return (List<Dike>) getHibernateTemplate().executeFind(new HibernateCallback<List<Dike>>() {
			
			@Override
			public List<Dike> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Criteria dikeCriteria = session.createCriteria(Dike.class);
				
				Example dikeExample = Example.create(dike);
				dikeExample.excludeProperty("id");
				dikeExample.excludeProperty("version");
				dikeExample.excludeProperty("created");
				dikeExample.excludeProperty("outlets");
				dikeExample.excludeProperty("spillways");
				dikeExample.excludeProperty("workStateDate");

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date defaultFromDate = null;
				Date defaultToDate = null;
				
				try {
					defaultFromDate = dateFormat.parse("1999-01-01");
					defaultToDate = dateFormat.parse("2099-01-01");
				} catch(Exception exception) {
					
				}
				
				if(dike.getSystematizationProject() == null) {
					dikeExample.excludeProperty("systematizationProject");
				} else {
					Example systematizationProjectExample = Example.create(dike.getSystematizationProject());
					dikeCriteria.createCriteria("systematizationProject").add(systematizationProjectExample);
				}
				
				if(dike.getName() == null || dike.getName().equalsIgnoreCase("")) {
					dikeExample.excludeProperty("name");
				}
				
				if(dike.getWorkState() == null) {
					dikeExample.excludeProperty("workState");
				}
				
				if(dike.getDikeSlope() == null) {
					dikeExample.excludeProperty("dikeSlope");
				}
				
				if(dike.getMaximumHeight() == null) {
					dikeExample.excludeProperty("maximumHeight");
				}
				
				if(dike.getGroundVolume() == null) {
					dikeExample.excludeProperty("groundVolume");
				}
				
				if(dike.getSlopeSurface() == null) {
					dikeExample.excludeProperty("slopeSurface");
				}
				
				if(dike.getBaseSurface() == null) {
					dikeExample.excludeProperty("baseSurface");
				}
				
				if(dike.getLength() == null) {
					dikeExample.excludeProperty("length");
				}
				
				if(dike.getMaximumWidth() == null) {
					dikeExample.excludeProperty("maximumWidth");
				}
				
				if(fromToDateFieldMap.get("projectedDate") != null ||
				   fromToDateFieldMap.get("inProgressDate") != null || 
				   fromToDateFieldMap.get("finishedDate") != null) 
				{
												
					FromToDateField projectedDate = fromToDateFieldMap.get("projectedDate");
							
					if(projectedDate != null) {

						dikeCriteria.add(Restrictions.between(
							"workStateDate.projectedDate", 
							projectedDate.getFrom() != null ? projectedDate.getFrom() : defaultFromDate, 
							projectedDate.getTo() != null ? projectedDate.getTo() : defaultToDate
						));
							
					}
							
					FromToDateField inProgressDate = fromToDateFieldMap.get("inProgressDate");
							
					if(inProgressDate != null) {

						dikeCriteria.add(Restrictions.between(
							"workStateDate.inProgressDate", 
							inProgressDate.getFrom() != null ? inProgressDate.getFrom() : defaultFromDate, 
							inProgressDate.getTo() != null ? inProgressDate.getTo() : defaultToDate
						));
						
					}
							
					FromToDateField finishedDate = fromToDateFieldMap.get("finishedDate");
						
					if(finishedDate != null) {

						dikeCriteria.add(Restrictions.between(
							"workStateDate.finishedDate", 
							finishedDate.getFrom() != null ? finishedDate.getFrom() : defaultFromDate, 
							finishedDate.getTo() != null ? finishedDate.getTo() : defaultToDate
						));						
					}
						
				}
				
				dikeCriteria.createCriteria("user").add(Example.create(dike.getUser()));
				dikeCriteria.add(dikeExample);
			
				return dikeCriteria.list();
			}
		});
	}

}