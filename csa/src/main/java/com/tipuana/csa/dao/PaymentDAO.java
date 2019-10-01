package com.tipuana.csa.dao;

import java.util.List;
import java.util.Map;

import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.Payment;

public interface PaymentDAO extends GenericDAO<Payment, Long> {
	
	public List<Payment> find(long userId);
	
	public List<Payment> find(final Payment payment, final Map<String, FromToDateField> fromToDateFieldMap);

}
