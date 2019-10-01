package com.tipuana.csa.model;

@SuppressWarnings("serial")
public class JournalIncomeEntry extends JournalEntry {
	private JournalIncomeEntryType incomeType;
	private Journal journal;

	public JournalIncomeEntryType getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(JournalIncomeEntryType incomeType) {
		this.incomeType = incomeType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((incomeType == null) ? 0 : incomeType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		JournalIncomeEntry other = (JournalIncomeEntry) obj;
		if (incomeType != other.incomeType)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Object object) {
		
		if(object instanceof JournalIncomeEntry) {
		
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (JournalIncomeEntry) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String toString() {
		return "JournalIncomeEntry [getIncomeType()=" + getIncomeType()
				+ ", getEntryDate()=" + getEntryDate() + ", getDescription()="
				+ getDescription() + ", getAmount()=" + getAmount()
				+ ", getUser()=" + getUser() + "]";
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}
}