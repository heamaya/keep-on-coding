package com.tipuana.csa.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;


public interface GenericDAO<T, ID extends Serializable> {

    T findById(ID id, boolean lock);

    List<T> findAll();
    
    List<T> findAll(Order order);
    
    List<T> findAll(List<Order> order);
	
    List<T> findByExample(T exampleInstance, String... excludeProperty);
    
    List<T> findByExample(T exampleInstance, List<String> excludeProperties);
    
    List<T> findByExample(T exampleInstance, List<String> excludeProperties, Order... orderBy);

    T makePersistent(T entity);
    
    ID save(T entity);
    
    Collection<T> makePersistent(Collection<T> entities);
    
    void makeTransient(T entity);
    
    void initialize(Object object);

    void flush();

    void clear();
    
    public int getCount();
    
	List<T> findByCriteria(Criterion... criterion);	
	
	List<T> findByCriteria(Order order, final Criterion... criterion);	
	
	List<T> findByCriteria(List<Order> orders, final Criterion... criterion);	
	
	List<T> findByCriteria(List<Order> orders, int first, int max, Criterion... criterion);
	
	void setHasFilters(boolean hasFilters);
	
	void setFilters(Map<String, Map<String, String>> filters);
}
