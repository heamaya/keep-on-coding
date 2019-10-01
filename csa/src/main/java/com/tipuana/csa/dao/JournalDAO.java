package com.tipuana.csa.dao;

import java.util.List;
import java.util.Map;

import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.Journal;

public interface JournalDAO extends GenericDAO<Journal, Long> {
	
	public List<Journal> find(final Journal journal, final Map<String, FromToDateField> fromToDateFieldMap);

}
