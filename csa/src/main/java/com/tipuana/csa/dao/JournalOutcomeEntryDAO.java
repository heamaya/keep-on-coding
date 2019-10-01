package com.tipuana.csa.dao;

import java.util.List;

import com.tipuana.csa.model.FromToDateField;
import com.tipuana.csa.model.JournalOutcomeEntry;

public interface JournalOutcomeEntryDAO extends GenericJournalEntryDAO<JournalOutcomeEntry, Long> {

	public List<Object []> getOutcomesReport(final FromToDateField fromToDateField);
	
	public List<JournalOutcomeEntry> find(final FromToDateField fromToDateField);
}
