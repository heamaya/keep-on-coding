package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class JournalUtility implements Serializable, Comparable<Object> {
	private Long id;
	private Date created = new Date();
	private int version = 0;
	private String description;
	private String journalFileName;
	private String journalContentType;
	
	@Override
	public String toString() {
		return "JournalUtility [description=" + description
				+ ", journalFileName=" + journalFileName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime
				* result
				+ ((journalContentType == null) ? 0 : journalContentType
						.hashCode());
		result = prime * result
				+ ((journalFileName == null) ? 0 : journalFileName.hashCode());
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
		JournalUtility other = (JournalUtility) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (journalContentType == null) {
			if (other.journalContentType != null)
				return false;
		} else if (!journalContentType.equals(other.journalContentType))
			return false;
		
		if (journalFileName == null) {
			if (other.journalFileName != null)
				return false;
		} else if (!journalFileName.equals(other.journalFileName))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof JournalUtility) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (JournalUtility) object).getCreated().getTime()));
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

	public String getJournalFileName() {
		return journalFileName;
	}

	public String getJournalContentType() {
		return journalContentType;
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

	public void setJournalFileName(String journalFileName) {
		this.journalFileName = journalFileName;
	}

	public void setJournalContentType(String journalContentType) {
		this.journalContentType = journalContentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}