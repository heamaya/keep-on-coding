package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.Person;
import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;

public class UserDAOHibernate extends GenericHibernateDAO<User, Long> implements UserDAO {
	
	private StrongPasswordEncryptor passwordEncryptor; 

    public UserDAOHibernate() {
    
    }

    @SuppressWarnings("unchecked")
	public List<User> find(final User user) {
    	
        return getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
        	
        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<User> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria userCriteria = session.createCriteria(User.class);
                
                Example userExample = Example.create(user);
                
                List<String> excludeProperties = new ArrayList<String>();
                excludeProperties.add("id");
                excludeProperties.add("version");
                excludeProperties.add("created");
                excludeProperties.add("password");
                excludeProperties.add("enabled");
                excludeProperties.add("messages");
                
                if(user.getUsername() == null || user.getUsername().equalsIgnoreCase("")) {
                	excludeProperties.add("username");
                }
                
                for(String exclude : excludeProperties) {
                	userExample.excludeProperty(exclude);
                }

                if(user.getPerson() != null) {
                    Criteria personCriteria = userCriteria.createCriteria("person");
                    Person person = user.getPerson();
                    
                    excludeProperties = new ArrayList<String>();
                    excludeProperties.add("id");
                    excludeProperties.add("created");
                    excludeProperties.add("version");
                    
                    if(person.getFirstName() == null || person.getFirstName().equalsIgnoreCase("")) {
                        excludeProperties.add("firstName");
                    }
                    
                    if(person.getLastName() == null || person.getLastName().equalsIgnoreCase("")) {
                        excludeProperties.add("lastName");
                    }
                    
                    if(person.getEmail() == null || person.getEmail().equalsIgnoreCase("")) {
                        excludeProperties.add("email");
                    }
                    
                    if(person.getSecondaryEmail() == null || person.getSecondaryEmail().equalsIgnoreCase("")) {
                        excludeProperties.add("secondaryEmail");
                    }
                    
                    if(person.getPrimaryTelephoneNumber() == null || person.getPrimaryTelephoneNumber().equalsIgnoreCase("")) {
                        excludeProperties.add("primaryTelephoneNumber");
                    }
                    
                    if(person.getSecondaryTelephoneNumber() == null || person.getSecondaryTelephoneNumber().equalsIgnoreCase("")) {
                        excludeProperties.add("secondaryTelephoneNumber");
                    }
                    
                    if(person.getPrimaryCellPhoneNumber() == null || person.getPrimaryCellPhoneNumber().equalsIgnoreCase("")) {
                        excludeProperties.add("primaryCellPhoneNumber");
                    }
                    
                    if(person.getSecondaryCellPhoneNumber() == null || person.getSecondaryCellPhoneNumber().equalsIgnoreCase("")) {
                        excludeProperties.add("secondaryCellPhoneNumber");
                    }
                    
                    if(person.getComment() == null || person.getComment().equalsIgnoreCase("")) {
                        excludeProperties.add("comment");
                    }
                    
                    if(person.getAddress() == null) {
                        excludeProperties.add("address");
                    } else {
                        Address address = person.getAddress();
                        Example addressExample = Example.create(address);
                        addressExample.excludeProperty("id");
                        addressExample.excludeProperty("created");
                        addressExample.excludeProperty("version");
                        Criteria addressCriteria = personCriteria.createCriteria("address");
                        addressCriteria.add(addressExample);
                        
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
                        
                    }

                    Example personExample = Example.create(person); 
                    
                    for(String exclude : excludeProperties) {
                    	personExample.excludeProperty(exclude);
                    }

                    personCriteria.add(personExample);
                    personCriteria.addOrder(Order.asc("email"));
                    personCriteria.addOrder(Order.asc("lastName"));
                    personCriteria.addOrder(Order.asc("firstName"));
                } else {
                    userExample.excludeProperty("person");
                }
                
                userCriteria.add(userExample);
                
                return userCriteria.list();
            }
        });
    }

    @SuppressWarnings("unchecked")
	public List<User> findAllOrderByUser()
    {
        return getHibernateTemplate().execute(new HibernateCallback<List<User>>() {

        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<User> doInHibernate(Session session)
                throws HibernateException, SQLException
            {
                Criteria userCriteria = session.createCriteria(User.class);
                userCriteria.createAlias("person", "p");
                userCriteria.addOrder(Order.asc("username"));
                userCriteria.addOrder(Order.asc("p.lastName"));
                userCriteria.addOrder(Order.asc("p.firstName"));
               
                return userCriteria.list();
            }
        });
    }

    @SuppressWarnings("unchecked")
	public List<User> find(final Role role) {
        return getHibernateTemplate().execute(new HibernateCallback<List<User>>() {

        	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public List<User> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria userCriteria = session.createCriteria(User.class);
                userCriteria.add(Restrictions.eq("role", role));
                userCriteria.createAlias("person", "p");
                userCriteria.addOrder(Order.asc("username"));
                userCriteria.addOrder(Order.asc("p.lastName"));
                userCriteria.addOrder(Order.asc("p.firstName"));
                
                return userCriteria.list();
            }
        });
    }



	@Override
	public User makePersistent(User user) {
		user.setPassword(getPasswordEncryptor().encryptPassword(user.getPassword()));
		
        getHibernateTemplate().saveOrUpdate(user);
        
        return user;
	}
	
	

	@Override
	public Collection<User> makePersistent(Collection<User> users) {

		for(User user: users) {
			user.setPassword(getPasswordEncryptor().encryptPassword(user.getPassword()));
		}
		
		return super.makePersistent(users);
	}

	public StrongPasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public void setPasswordEncryptor(StrongPasswordEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}


	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, DataAccessException {
        
    	return getHibernateTemplate().execute(new HibernateCallback<UserDetails>() {

    		@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly=true)
            public UserDetails doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from User u where u.username = :username");
                query.setString("username", username);
                
                return (User) query.uniqueResult();
            }
        });
    }


    
}
