package com.tipuana.csa.model;

@SuppressWarnings("serial")
public class JournalOutcomeEntry extends JournalEntry {
	private JournalOutcomeEntryType outcomeType;
	private Journal journal;

	public JournalOutcomeEntryType getOutcomeType() {
		return outcomeType;
	}

	public void setOutcomeType(JournalOutcomeEntryType outcomeType) {
		this.outcomeType = outcomeType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((outcomeType == null) ? 0 : outcomeType.hashCode());
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
		JournalOutcomeEntry other = (JournalOutcomeEntry) obj;
		if (outcomeType != other.outcomeType)
			return false;
		return true;
		
	}
	
	@Override
	public int compareTo(Object object) {
		
		if(object instanceof JournalOutcomeEntry) {
		
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (JournalOutcomeEntry) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String toString() {
		return "JournalOutcomeEntry [outcomeType=" + outcomeType
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