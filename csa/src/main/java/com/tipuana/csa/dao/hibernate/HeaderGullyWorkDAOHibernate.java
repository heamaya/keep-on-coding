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

import com.tipuana.csa.dao.HeaderGullyWorkDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.HeaderGullyWork;

public class HeaderGullyWorkDAOHibernate extends GenericHibernateDAO<HeaderGullyWork, Long> implements HeaderGullyWorkDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HeaderGullyWork> find(final long userId) {
		
		return (List<HeaderGullyWork>) getHibernateTemplate().executeFind(new HibernateCallback<List<HeaderGullyWork>>() {
			
			@Override
			public List<HeaderGullyWork> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery("from HeaderGullyWork h where h.user.id=:userId order by h.created asc");
				query.setLong("userId", userId);
			
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HeaderGullyWork> find(final HeaderGullyWork hgw, final Map<String, FromToDateField> fromToDateFieldMap) {
		
		return (List<HeaderGullyWork>) getHibernateTemplate().executeFind(new HibernateCallback<List<HeaderGullyWork>>() {
			
			@Override
			public List<HeaderGullyWork> doInHibernate(Session session) throws HibernateException,
					SQLException {
							
				Criteria hgwCriteria = session.createCriteria(HeaderGullyWork.class);
				Example hgwExample = Example.create(hgw);
				hgwExample.excludeProperty("id");
				hgwExample.excludeProperty("version");
				hgwExample.excludeProperty("created");
				
				if(hgw.getWorkState() == null) {
					hgwExample.excludeProperty("workState");
				}
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date defaultFromDate = null;
				Date defaultToDate = null;
				
				try {
					defaultFromDate = dateFormat.parse("1999-01-01");
					defaultToDate = dateFormat.parse("2099-01-01");
				} catch(Exception exception) {
					
				}
									
				if(fromToDateFieldMap.get("projectedDate") != null ||
				   fromToDateFieldMap.get("inProgressDate") != null || 
				   fromToDateFieldMap.get("finishedDate") != null) 
				{
														
					FromToDateField projectedDate = fromToDateFieldMap.get("projectedDate");
										
					if(projectedDate != null) {

						hgwCriteria.add(Restrictions.between(
							"workStateDate.projectedDate", 
							projectedDate.getFrom() != null ? projectedDate.getFrom() : defaultFromDate, 
							projectedDate.getTo() != null ? projectedDate.getTo() : defaultToDate
						));
										
					}
										
					FromToDateField inProgressDate = fromToDateFieldMap.get("inProgressDate");
										
					if(inProgressDate != null) {

						hgwCriteria.add(Restrictions.between(
							"workStateDate.inProgressDate", 
							inProgressDate.getFrom() != null ? inProgressDate.getFrom() : defaultFromDate, 
							inProgressDate.getTo() != null ? inProgressDate.getTo() : defaultToDate
						));
									
					}
										
					FromToDateField finishedDate = fromToDateFieldMap.get("finishedDate");
									
					if(finishedDate != null) {

						hgwCriteria.add(Restrictions.between(
							"workStateDate.finishedDate", 
							finishedDate.getFrom() != null ? finishedDate.getFrom() : defaultFromDate, 
							finishedDate.getTo() != null ? finishedDate.getTo() : defaultToDate
						));						
					}
									
				}
				
				if(hgw.getType() == null) {
					hgwExample.excludeProperty("type");
				}
				
				if(hgw.getName() == null || !hgw.getName().equalsIgnoreCase("")) {
					hgwExample.excludeProperty("name");
				}
				
				if(hgw.getBrickCount() == null) {
					hgwExample.excludeProperty("name");
				}
				
				if(hgw.getBlockCount() == null) {
					hgwExample.excludeProperty("blockCount");
				}
				
				if(hgw.getConcrete() == null) {
					hgwExample.excludeProperty("concrete");
				}
			
				if(hgw.getIron() == null) {
					hgwExample.excludeProperty("iron");
				}
				
				if(hgw.getIronThickness() == null || hgw.getIronThickness().isNaN()) {
					hgwExample.excludeProperty("ironThickness");
				}
				
				if(hgw.getGabions() == null || hgw.getGabions().isNaN()) {
					hgwExample.excludeProperty("gabions");
				}
				
				if(hgw.getSystematizationProject() == null) {
					hgwExample.excludeProperty("systematizationProject");
				} else {
					Example systematizationProjectExample = Example.create(hgw.getSystematizationProject());
					hgwCriteria.createCriteria("systematizationProject").add(systematizationProjectExample);
				}
				
				hgwCriteria.createCriteria("user").add(Example.create(hgw.getUser()));
				hgwCriteria.add(hgwExample);
				
				return hgwCriteria.list();
			}
		});	
	}

}
