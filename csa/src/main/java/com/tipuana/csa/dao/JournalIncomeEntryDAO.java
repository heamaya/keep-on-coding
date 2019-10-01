package com.tipuana.csa.dao;

import java.util.List;

import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.JournalIncomeEntry;

public interface JournalIncomeEntryDAO extends GenericJournalEntryDAO<JournalIncomeEntry, Long> {
	
	public List<Object []> getIncomesReport(final FromToDateField fromToDateField);
	
	public List<JournalIncomeEntry> find(final FromToDateField fromToDateField);
	
}
