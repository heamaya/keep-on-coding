package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class JournalEntryUtility implements Serializable, Comparable<Object> {
	private Long id;
	private Date created = new Date();
	private int version = 0;
	private String description;
	private String journalEntriesFileName;
	private String journalEntriesContentType;
	
	@Override
	public String toString() {
		return "JournalUtility [description=" + description
				+ ", journalEntriesFileName=" + journalEntriesFileName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());

		result = prime
				* result
				+ ((journalEntriesContentType == null) ? 0
						: journalEntriesContentType.hashCode());
		result = prime
				* result
				+ ((journalEntriesFileName == null) ? 0
						: journalEntriesFileName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JournalEntryUtility other = (JournalEntryUtility) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;

		if (journalEntriesContentType == null) {
			if (other.journalEntriesContentType != null)
				return false;
		} else if (!journalEntriesContentType
				.equals(other.journalEntriesContentType))
			return false;
		if (journalEntriesFileName == null) {
			if (other.journalEntriesFileName != null)
				return false;
		} else if (!journalEntriesFileName.equals(other.journalEntriesFileName))
			return false;
		
		return true;
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof JournalEntryUtility) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (JournalEntryUtility) object).getCreated().getTime()));
		}
		
		return 0;
	}

	public Long getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public int getVersion() {
		return version;
	}

	public String getJournalEntriesFileName() {
		return journalEntriesFileName;
	}

	public String getJournalEntriesContentType() {
		return journalEntriesContentType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setJournalEntriesFileName(String journalEntriesFileName) {
		this.journalEntriesFileName = journalEntriesFileName;
	}

	public void setJournalEntriesContentType(String journalEntriesContentType) {
		this.journalEntriesContentType = journalEntriesContentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}