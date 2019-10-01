package com.tipuana.csa.action.setup;

import java.util.Arrays;
import java.util.List;

import com.tipuana.csa.dao.JournalOutcomeEntryDAO;
import com.tipuana.csa.model.JournalOutcomeEntry;
import com.tipuana.csa.model.JournalOutcomeEntryType;

@SuppressWarnings("serial")
public class JournalOutcomeEntryAction extends JournalEntryAction<JournalOutcomeEntry, JournalOutcomeEntryDAO> {

	@Override
	public void prepare() throws Exception {
		
		long id = getRequestId(); 
		
		if(id == 0) {
			setModel(new JournalOutcomeEntry());
		} else {
			setModel(getJournalEntryDAO().findById(id, true));
		}
		
	}
		
	public List<JournalOutcomeEntryType> getOutcomeTypes() {
		return Arrays.asList(JournalOutcomeEntryType.values());
	}

}