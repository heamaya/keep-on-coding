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

import com.tipuana.csa.dao.PaymentDAO;
import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.Payment;

public class PaymentDAOHibernate extends GenericHibernateDAO<Payment, Long> implements PaymentDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> find(final long userId) {
		
		return (List<Payment>) getHibernateTemplate().executeFind(new HibernateCallback<List<Payment>>() {
			
			@Override
			public List<Payment> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery("from Payment p where p.user.id=:userId order by p.created asc");
				query.setLong("userId", userId);
			
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> find(final Payment payment, final Map<String, FromToDateField> fromToDateFieldMap) {
		
		return (List<Payment>) getHibernateTemplate().executeFind(new HibernateCallback<List<Payment>>() {
			
			@Override
			public List<Payment> doInHibernate(Session session) throws HibernateException,
					SQLException 
			{
				Criteria paymentCriteria = session.createCriteria(Payment.class);
				Example paymentExample = Example.create(payment);
				paymentExample.excludeProperty("id");
				paymentExample.excludeProperty("version");
				paymentExample.excludeProperty("created");
				paymentExample.excludeProperty("paymentDate");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date defaultFromDate = null;
				Date defaultToDate = null;
				
				try {
					defaultFromDate = dateFormat.parse("1999-01-01");
					defaultToDate = dateFormat.parse("2099-01-01");
				} catch(Exception exception) {
					
				}
									
				if(fromToDateFieldMap.get("paymentDate") != null) {
														
					FromToDateField paymentDate = fromToDateFieldMap.get("paymentDate");
										
					if(paymentDate != null) {

						paymentCriteria.add(Restrictions.between(
							"paymentDate", 
							paymentDate.getFrom() != null ? paymentDate.getFrom() : defaultFromDate, 
							paymentDate.getTo() != null ? paymentDate.getTo() : defaultToDate
						));
										
					}
				}
				
				if(payment.getFeeNumber() == null) {
					paymentExample.excludeProperty("feeNumber");
				}
				
				if(payment.getSoyaPriceByQuintal() == null || payment.getSoyaPriceByQuintal().isNaN()) {
					paymentExample.excludeProperty("soyaPriceByQuintal");
				}

				if(payment.getQuintalsPaid() == null || payment.getQuintalsPaid().isNaN()) {
					paymentExample.excludeProperty("quintalsPaid");
				}
				
				if(payment.getSystematizationProject() == null) {
					paymentExample.excludeProperty("systematizationProject");
				} else {
					Example systematizationProjectExample = Example.create(payment.getSystematizationProject());
					paymentCriteria.createCriteria("systematizationProject").add(systematizationProjectExample);
				}
				
				paymentCriteria.createCriteria("user").add(Example.create(payment.getUser()));
				
				paymentCriteria.add(paymentExample);
				
				return paymentCriteria.list();
			}
		});
	}

}
