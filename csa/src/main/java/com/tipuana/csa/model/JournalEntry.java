package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class JournalEntry implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private Date entryDate; 
	private String description;
	private Double amount;
	private User user;
	private Boolean isClosed = new Boolean(false);

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof JournalEntry) {
		
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (JournalEntry) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((entryDate == null) ? 0 : entryDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		JournalEntry other = (JournalEntry) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (entryDate == null) {
			if (other.entryDate != null)
				return false;
		} else if (!entryDate.equals(other.entryDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public Date getCreated() {
		return created;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public String getDescription() {
		return description;
	}

	public Double getAmount() {
		return amount;
	}

	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

}