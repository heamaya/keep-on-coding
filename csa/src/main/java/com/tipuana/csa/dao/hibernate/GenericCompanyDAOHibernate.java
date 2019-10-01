package com.tipuana.csa.dao.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tipuana.csa.dao.GenericCompanyDAO;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.AnonymousCommercialSociety;
import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.CompanyType;
import com.tipuana.csa.model.FactCommercialSociety;
import com.tipuana.csa.model.LegalPerson;
import com.tipuana.csa.model.LimitedResponsibilityCommercialSociety;

public abstract class GenericCompanyDAOHibernate<C extends Company, ID extends Serializable> extends
	GenericHibernateDAO<C, ID> implements GenericCompanyDAO<C, ID> 
{

	@SuppressWarnings("unchecked")
	@Override
	public List<C> find(final C company, final CompanyType companyType, final MatchMode matchMode) {

		return (List<C>) getHibernateTemplate().executeFind(new HibernateCallback<List<C>>() {
			
			@Override
			public List<C> doInHibernate(Session session) throws HibernateException,
					SQLException {

				Criteria companyCriteria = null;
				
				Example companyExample = Example.create(company);
				companyExample.excludeProperty("id");
				companyExample.excludeProperty("version");
				companyExample.excludeProperty("created");
				companyExample.excludeProperty("lands");
				
				if(companyType == CompanyType.ANONYMOUS) {
					companyCriteria = session.createCriteria(AnonymousCommercialSociety.class);
				} else if(companyType == CompanyType.FACT) {
					companyCriteria = session.createCriteria(FactCommercialSociety.class);
				} else if(companyType == CompanyType.LEGAL_PERSON) {
					companyCriteria = session.createCriteria(LegalPerson.class);
				} else if(companyType == CompanyType.LIMITED_RESPONSIBILITY) {
					companyCriteria = session.createCriteria(LimitedResponsibilityCommercialSociety.class);
				} else {
					companyCriteria = session.createCriteria(Company.class);
					companyExample.excludeProperty("companyType");
				}
				
				if(company.getName() == null || company.getName().equalsIgnoreCase("")) {
					companyExample.excludeProperty("name");
				}
				
				if(company.getTributeKey() == null || company.getTributeKey().equalsIgnoreCase("")) {
					companyExample.excludeProperty("tributeKey");
				}
				
				if(company.getAddress() == null) {
					companyExample.excludeProperty("address");
				} else {
					Address address = company.getAddress();
	                Example addressExample = Example.create(address);
	                addressExample.excludeProperty("id");
	                addressExample.excludeProperty("created");
	                addressExample.excludeProperty("version");
	                Criteria addressCriteria = companyCriteria.createCriteria("address");
	                        
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

				if(company.getTaxCondition() == null) {
					companyExample.excludeProperty("taxCondition");
				} 
				
				companyExample.enableLike(matchMode);
				companyCriteria.add(companyExample);
			
				return companyCriteria.list();
			}
		});

	}
	
}