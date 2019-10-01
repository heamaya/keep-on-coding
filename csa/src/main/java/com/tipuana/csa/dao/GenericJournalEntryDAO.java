package com.tipuana.csa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.MatchMode;

import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.JournalEntry;

public interface GenericJournalEntryDAO<JE extends JournalEntry, ID extends Serializable> extends
		GenericDAO<JE, ID> {
	
	public List<JE> find(final JE journalEntry, final Map<String, FromToDateField> fromToDateFieldMap, final MatchMode matchMode);
	
}
