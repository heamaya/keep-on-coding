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

import com.tipuana.csa.dao.FarmerDAO;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.Farmer;

public class FarmerDAOHibernate extends GenericHibernateDAO<Farmer, Long>
		implements FarmerDAO {
	
	@SuppressWarnings("unchecked")
	public List<Farmer> findNotInCommercialSociety(final long commercialSocietyId) {

		return (List<Farmer>) getHibernateTemplate().executeFind(new HibernateCallback<List<Farmer>>() {
			
			@Override
			public List<Farmer> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery("from Farmer f where f not in (select fs from CommercialSociety cs join cs.farmers fs where cs.id=:commercialSocietyId)");
				query.setLong("commercialSocietyId", commercialSocietyId);
			
				List<Farmer> farmers = query.list();
				
				return farmers;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Farmer> findIn(final List<Long> farmerIds) {

		return (List<Farmer>) getHibernateTemplate().executeFind(new HibernateCallback<List<Farmer>>() {
			
			@Override
			public List<Farmer> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				if(farmerIds != null && farmerIds.size() > 0) {
					Query query = session.createQuery("from Farmer f where f.id in (:farmerIds)");
					query.setParameterList("farmerIds", farmerIds);
			
					return query.list();
				}
				
				return new ArrayList<Farmer>();
			}
		});
	}

    @SuppressWarnings("unchecked")
	public List<Farmer> find(final Farmer farmer, final MatchMode matchMode) {
    	
        return (List<Farmer>) getHibernateTemplate().execute(new HibernateCallback<List<Farmer>>() {
        	
        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<Farmer> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria farmerCriteria = session.createCriteria(Farmer.class);
                
                Example farmerExample = Example.create(farmer);
                List<String> excludeProperties = new ArrayList<String>();
                excludeProperties.add("id");
                excludeProperties.add("version");
                excludeProperties.add("created");
                excludeProperties.add("commercialSocieties");
                excludeProperties.add("legalPerson");
                
                if(farmer.getFirstName() == null || farmer.getFirstName().equalsIgnoreCase("")) {
                	excludeProperties.add("firstName");
                } 
                    
                if(farmer.getLastName() == null || farmer.getLastName().equalsIgnoreCase("")) {
                    excludeProperties.add("lastName");
                }
                    
                if(farmer.getEmail() == null || farmer.getEmail().equalsIgnoreCase("")) {
                    excludeProperties.add("email");
                }
                    
                if(farmer.getSecondaryEmail() == null || farmer.getSecondaryEmail().equalsIgnoreCase("")) {
                    excludeProperties.add("secondaryEmail");
                }
                    
                if(farmer.getPrimaryTelephoneNumber() == null || farmer.getPrimaryTelephoneNumber().equalsIgnoreCase("")) {
                    excludeProperties.add("primaryTelephoneNumber");
                }
                    
                if(farmer.getSecondaryTelephoneNumber() == null || farmer.getSecondaryTelephoneNumber().equalsIgnoreCase("")) {
                    excludeProperties.add("secondaryTelephoneNumber");
                }
                    
                if(farmer.getPrimaryCellPhoneNumber() == null || farmer.getPrimaryCellPhoneNumber().equalsIgnoreCase("")) {
                    excludeProperties.add("primaryCellPhoneNumber");
                }
                    
                if(farmer.getSecondaryCellPhoneNumber() == null || farmer.getSecondaryCellPhoneNumber().equalsIgnoreCase("")) {
                    excludeProperties.add("secondaryCellPhoneNumber");
                }
                    
                if(farmer.getComment() == null || farmer.getComment().equalsIgnoreCase("")) {
                    excludeProperties.add("comment");
                }
                    
                if(farmer.getAddress() == null) {
                    excludeProperties.add("address");
                } else {
                    Address address = farmer.getAddress();
                    Example addressExample = Example.create(address);
                    addressExample.excludeProperty("id");
                    addressExample.excludeProperty("created");
                    addressExample.excludeProperty("version");
                    Criteria addressCriteria = farmerCriteria.createCriteria("address");
                        
                    if(address.getProvince() == null) {
                        addressExample.excludeProperty("province");
                    } else {
                        Example provinceExample = Example.create(address.getProvince());
                        addressCriteria.createCriteria("province").add(provinceExample);
                    }
                        
                    if(address.getCity() == null) {
                        addressExample.excludeProperty("city");
                    } else {
                        Example cityExample = Example.create(address.getCity());
                        addressCriteria.createCriteria("city").add(cityExample);
                    }
                        
                    if(address.getStreet() == null || address.getStreet().equalsIgnoreCase("")) {
                        addressExample.excludeProperty("street");
                    }
                       
                    if(address.getStreetNumber() == null) {
                        addressExample.excludeProperty("streetNumber");
                    }
                        
                    if(address.getFloor() == null) {
                        addressExample.excludeProperty("floor");
                    }
                        
                    if(address.getDepartment() == null || address.getDepartment().equalsIgnoreCase("")) {
                        addressExample.excludeProperty("department");
                    }
                    
                    addressExample.enableLike(matchMode);
                    addressCriteria.add(addressExample);
                }

                for(String exclude : excludeProperties) {
                	farmerExample.excludeProperty(exclude);
                }
                
                farmerExample.enableLike(matchMode);
                farmerCriteria.add(farmerExample);
                farmerCriteria.addOrder(Order.asc("email"));
                farmerCriteria.addOrder(Order.asc("lastName"));
                farmerCriteria.addOrder(Order.asc("firstName"));
                
                return farmerCriteria.list();
            }
        });
    }

}