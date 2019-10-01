package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.tipuana.csa.dao.SystematizationProjectDAO;
import com.tipuana.csa.model.Contract;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.GullyRecovery;
import com.tipuana.csa.model.SystematizationProject;
import com.tipuana.csa.model.Terrace;

public class SystematizationProjectDAOHibernate extends GenericHibernateDAO<SystematizationProject, Long> implements SystematizationProjectDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SystematizationProject> find(final long userId) {
		
		return (List<SystematizationProject>) getHibernateTemplate().executeFind(new HibernateCallback<List<SystematizationProject>>() {
			
			@Override
			public List<SystematizationProject> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery("from SystematizationProject sp where sp.user.id=:userId order by sp.systematization.company.name asc, sp.systematization.land.name asc");
				query.setLong("userId", userId);
			
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SystematizationProject> find(final SystematizationProject systematizationProject, final Map<String, FromToDateField> fromToDateFieldMap) {
		
		return (List<SystematizationProject>) getHibernateTemplate().executeFind(new HibernateCallback<List<SystematizationProject>>() {
			
			@Override
			public List<SystematizationProject> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Criteria systematizationProjectCriteria = session.createCriteria(SystematizationProject.class);

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date defaultFromDate = null;
				Date defaultToDate = null;
				
				try {
					defaultFromDate = dateFormat.parse("1999-01-01");
					defaultToDate = dateFormat.parse("2099-01-01");
				} catch(Exception exception) {
					
				}
				
				List<String> excludeProperties = new ArrayList<String>();
				excludeProperties.add("id");
				excludeProperties.add("version");
				excludeProperties.add("created");
				excludeProperties.add("farmAreaFileName");
				excludeProperties.add("farmAreaContentType");
				excludeProperties.add("dikes");
				excludeProperties.add("headerGullyWorks");
				excludeProperties.add("payments");
				
				if(systematizationProject.getSystematization() == null) {
					excludeProperties.add("systematization");
				} else {
					Example systematizationExample = Example.create(systematizationProject.getSystematization());
					systematizationProjectCriteria.createCriteria("systematization").add(systematizationExample);
				}
				
				if(systematizationProject.getAssessedHectares() == null) {
					excludeProperties.add("assessedHectares");
				}
			
				excludeProperties.add("contract");
				Contract contract = systematizationProject.getContract();
				
				if((contract != null && !contract.hasNullValues()) || fromToDateFieldMap.get("contractDate") != null) {
					FromToDateField contractDate = fromToDateFieldMap.get("contractDate");
					
					if(contractDate != null) {

						systematizationProjectCriteria.add(Restrictions.between(
							"contract.contractDate", 
							contractDate.getFrom() != null ? contractDate.getFrom() : defaultFromDate, 
							contractDate.getTo() != null ? contractDate.getTo() : defaultToDate
						));						
					}
									
					if(contract.getFees() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("contract.fees", contract.getFees()));
					}
					
					if(contract.getHiredHectares() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("contract.hiredHectares", contract.getHiredHectares()));
					}
					
					if(contract.getQuintalsPerFee() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("contract.quintalsPerFee", contract.getQuintalsPerFee()));
					}
					
					if(contract.getQuintalsPerHectare() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("contract.quintalsPerHectare", contract.getQuintalsPerHectare()));
					}
					
					if(contract.getQuintalsToPay() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("contract.quintalsToPay", contract.getQuintalsToPay()));
					}
					
				}
				
				excludeProperties.add("terrace");
				Terrace terrace = systematizationProject.getTerrace();
				
				if( (terrace != null && !terrace.hasNullValues()) ||
					 (fromToDateFieldMap.get("projectedDate") != null || 
					 fromToDateFieldMap.get("inProgressDate") != null || 
					 fromToDateFieldMap.get("finishedDate") != null)) 
				{
					
					if(terrace.getLinearMeters() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("terrace.linearMeters", terrace.getLinearMeters()));
					}
					
					if(terrace.getGroundVolume() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("terrace.groundVolume", terrace.getGroundVolume()));
					}
					
					if(terrace.getDesignedChannelCount() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("terrace.designedChannelCount", terrace.getDesignedChannelCount()));
					}
					
					if(terrace.getWorkState() != null) {
						systematizationProjectCriteria.add(Restrictions.eq("terrace.workState", terrace.getWorkState()));
					}
					
					FromToDateField projectedDate = fromToDateFieldMap.get("projectedDate");
						
					if(projectedDate != null) {

						systematizationProjectCriteria.add(Restrictions.between(
							"terrace.workStateDate.projectedDate", 
							projectedDate.getFrom() != null ? projectedDate.getFrom() : defaultFromDate, 
							projectedDate.getTo() != null ? projectedDate.getTo() : defaultToDate
						));
						
					}
						
					FromToDateField inProgressDate = fromToDateFieldMap.get("projectedDate");
						
					if(inProgressDate != null) {

						systematizationProjectCriteria.add(Restrictions.between(
							"terrace.workStateDate.inProgressDate", 
							inProgressDate.getFrom() != null ? inProgressDate.getFrom() : defaultFromDate, 
							inProgressDate.getTo() != null ? inProgressDate.getTo() : defaultToDate
						));
						
					}
						
					FromToDateField finishedDate = fromToDateFieldMap.get("projectedDate");
					
					if(finishedDate != null) {

						systematizationProjectCriteria.add(Restrictions.between(
							"terrace.workStateDate.finishedDate", 
							finishedDate.getFrom() != null ? finishedDate.getFrom() : defaultFromDate, 
							finishedDate.getTo() != null ? finishedDate.getTo() : defaultToDate
						));						
					}
					
				}
				
				excludeProperties.add("gullyRecovery");
				GullyRecovery gullyRecovery = systematizationProject.getGullyRecovery();
				
				if(gullyRecovery != null && !gullyRecovery.hasNullValues()) {
					
					
					if(gullyRecovery.getGulliesSurface() != null && !gullyRecovery.getGulliesSurface().isNaN()) {
						systematizationProjectCriteria.add(Restrictions.eq("gullyRecovery.gulliesSurface", gullyRecovery.getGulliesSurface()));
					}
					
					if(gullyRecovery.getRecoveredSurface() != null && !gullyRecovery.getRecoveredSurface().isNaN()) {
						systematizationProjectCriteria.add(Restrictions.eq("gullyRecovery.recoveredSurface", gullyRecovery.getRecoveredSurface()));
					}
					
				}
				
				systematizationProjectCriteria.createCriteria("user").add(Example.create(systematizationProject.getUser()));
				Example systematizationProjectExample = Example.create(systematizationProject);
				
				for(String excludeProperty : excludeProperties) {
					systematizationProjectExample.excludeProperty(excludeProperty);
				}
				
				systematizationProjectCriteria.add(systematizationProjectExample);
				
				return systematizationProjectCriteria.list();
			}
		});
	}

}
