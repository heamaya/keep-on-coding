package com.tipuana.csa.action.setup;

import java.util.Arrays;
import java.util.List;

import com.tipuana.csa.dao.JournalIncomeEntryDAO;
import com.tipuana.csa.model.JournalIncomeEntry;
import com.tipuana.csa.model.JournalIncomeEntryType;

@SuppressWarnings("serial")
public class JournalIncomeEntryAction extends JournalEntryAction<JournalIncomeEntry, JournalIncomeEntryDAO> {

	@Override
	public void prepare() throws Exception {
		
		long id = getRequestId(); 
		
		if(id == 0) {
			setModel(new JournalIncomeEntry());
		} else {
			setModel(getJournalEntryDAO().findById(id, true));
		}
		
	}
	
	public List<JournalIncomeEntryType> getIncomeTypes() {
		return Arrays.asList(JournalIncomeEntryType.values());
	}

}
