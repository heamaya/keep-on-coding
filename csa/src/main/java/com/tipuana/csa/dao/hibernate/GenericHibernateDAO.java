package com.tipuana.csa.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tipuana.csa.dao.GenericDAO;


@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly=false)
public abstract class GenericHibernateDAO<T, ID extends Serializable> extends
		HibernateDaoSupport implements GenericDAO<T, ID> {

	private Class<T> persistentClass;
	private Map<String, Map<String,String>> filters;
	private boolean hasFilters;

	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, readOnly = false)
	public T findById(ID id, boolean lock) {
		T entity;
		Session session = getSession();

		if (isHasFilters()) {
			enableFiltering(session);
		}

		if (lock) {
			entity = (T) getHibernateTemplate().load(getPersistentClass(), id,  
					LockMode.UPGRADE_NOWAIT);
		} else {
			entity = (T) session.load(getPersistentClass(), id);
		}

		return entity;
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	public List<T> findAll(Order order) {
		return findByCriteria(order);
	}

	public List<T> findAll(List<Order> orders) {
		return findByCriteria(orders);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(final T exampleInstance,
			final String... excludeProperty) {

		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@Override
					@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session
								.createCriteria(getPersistentClass());
						Example example = Example.create(exampleInstance);

						for (String exclude : excludeProperty) {
							example.excludeProperty(exclude);
						}

						criteria.add(example);
						List<T> list = criteria.list();

						return list;
					}
				});
	}

	public T makePersistent(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);

		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public ID save(T entity) {
		return (ID) getHibernateTemplate().save(entity);
	}

	public Collection<T> makePersistent(Collection<T> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);

		return entities;
	}

	public void makeTransient(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final Criterion... criterion) {

		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@Override
					@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						if (isHasFilters()) {
							enableFiltering(session);
						}

						Criteria criteria = session
								.createCriteria(getPersistentClass());

						for (Criterion c : criterion) {
							criteria.add(c);
						}

						return criteria.list();

					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final Order order,
			final Criterion... criterion) {

		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@Override
					@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						if (isHasFilters()) {
							enableFiltering(session);
						}

						Criteria criteria = session
								.createCriteria(getPersistentClass());

						criteria.addOrder(order);

						for (Criterion c : criterion) {
							criteria.add(c);
						}

						return criteria.list();

					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final List<Order> orders,
			final Criterion... criterion) {

		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@Override
					@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						if (isHasFilters()) {
							enableFiltering(session);
						}

						Criteria criteria = session
								.createCriteria(getPersistentClass());

						for (Order o : orders) {
							criteria.addOrder(o);
						}

						for (Criterion c : criterion) {
							criteria.add(c);
						}

						return criteria.list();

					}
				});
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final List<Order> orders, final int first, final int max,
			final Criterion... criterion) {

		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@Override
					@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						if (isHasFilters()) {
							enableFiltering(session);
						}

						Criteria criteria = session
								.createCriteria(getPersistentClass());
						
						criteria.setFirstResult(first);
					    criteria.setMaxResults(max);

						for (Order o : orders) {
							criteria.addOrder(o);
						}

						for (Criterion c : criterion) {
							criteria.add(c);
						}

						return criteria.list();

					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(final T exampleInstance,
			final List<String> excludeProperties) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@Override
					@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						if (isHasFilters()) {
							enableFiltering(session);
						}
						
						Criteria criteria = session
								.createCriteria(getPersistentClass());
						Example example = Example.create(exampleInstance);

						for (String exclude : excludeProperties) {
							example.excludeProperty(exclude);
						}

						criteria.add(example);
						List<T> list = criteria.list();

						return list;
					}
				});
	}
	

	


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(final T exampleInstance,
			final List<String> excludeProperties, final Order... order) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@Override
					@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						if (isHasFilters()) {
							enableFiltering(session);
						}
						
						Criteria criteria = session
								.createCriteria(getPersistentClass());
						Example example = Example.create(exampleInstance);

						for (String exclude : excludeProperties) {
							example.excludeProperty(exclude);
						}

						criteria.add(example);

						for (Order o : order) {
							criteria.addOrder(o);
						}

						List<T> list = criteria.list();

						return list;

					}
				});
	}

	public void initialize(Object object) {
		getHibernateTemplate().initialize(object);
	}
	
	public Map<String, Map<String, String>> getFilters() {
		return filters;
	}

	public boolean isHasFilters() {
		return hasFilters;
	}

	public void setFilters(Map<String, Map<String, String>> filters) {
		this.filters = filters;
	}

	public void setHasFilters(boolean hasFilters) {
		this.hasFilters = hasFilters;
	}

	public void enableFiltering(Session session) {
		
		Map<String, Map<String, String>> filters = getFilters();

		for(String filterName: filters.keySet()) {
			Filter filter = session.enableFilter(filterName);
			Map<String,String> filterParameters = filters.get(filterName);
			
			for (String filterParameterName : filterParameters.keySet()) {
				filter.setParameter(filterParameterName, filterParameters.get(filterParameterName));
			}			
		}
	}
	
	public int getCount() throws DataAccessException {
		
		StringBuilder countQuery = new StringBuilder("select count(*) from ");
		countQuery.append(getPersistentClass().getName());
		
		return DataAccessUtils.intResult(getHibernateTemplate().find(countQuery.toString()));
	}
}