package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class UserJournal implements Serializable, Comparable<Object> {
	private Id id;
	private User user;
	private Journal journal;
	private Date created = new Date();
	private int version = 0;
	private Double incomesAmount;
	private Double outcomesAmount;
	private Double revenue;
	private Double realRevenue;
	
	public UserJournal() {
		super();
	}
	
	public UserJournal(
		Journal journal, 
		User user, 
		Double incomesAmount, 
		Double outcomesAmount, 
		Double revenue,
		Double realRevenue) 
	{
		this.id = new Id(user.getId(), journal.getId());
		this.user = user;
		this.journal = journal;
		this.incomesAmount = incomesAmount;
		this.outcomesAmount = outcomesAmount;
		this.revenue = revenue;
		this.realRevenue = realRevenue;
		journal.getUsersJournal().add(this);
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof UserJournal) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (UserJournal) object).getCreated().getTime()));
		}
		
		return 0;
	}
	
	public static class Id implements Serializable {
		private Long userId;
		private Long journalId;
		
		public Id() {
			super();
		}
		
		public Id(Long userId, Long journalId) {
			super();
			this.userId = userId;
			this.journalId = journalId;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((journalId == null) ? 0 : journalId.hashCode());
			result = prime * result
					+ ((userId == null) ? 0 : userId.hashCode());
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
			Id other = (Id) obj;
			if (journalId == null) {
				if (other.journalId != null)
					return false;
			} else if (!journalId.equals(other.journalId))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}

		public Long getUserId() {
			return userId;
		}

		public Long getJournalId() {
			return journalId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public void setJournalId(Long journalId) {
			this.journalId = journalId;
		}	
	}

	@JSON(serialize=false)
	public Date getCreated() {
		return created;
	}

	@JSON(serialize=false)
	public int getVersion() {
		return version;
	}

	@JSON(serialize=true)
	public Double getIncomesAmount() {
		return incomesAmount;
	}

	@JSON(serialize=true)
	public Double getOutcomesAmount() {
		return outcomesAmount;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setIncomesAmount(Double incomesAmount) {
		this.incomesAmount = incomesAmount;
	}

	public void setOutcomesAmount(Double outcomesAmount) {
		this.outcomesAmount = outcomesAmount;
	}
	
	@JSON(serialize=true)
	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	
	@JSON(serialize=false)
	public Id getId() {
		return id;
	}

	@JSON(serialize=true)
	public User getUser() {
		return user;
	}

	@JSON(serialize=false)
	public Journal getJournal() {
		return journal;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	
	public Double getRealRevenue() {
		return realRevenue;
	}

	public void setRealRevenue(Double realRevenue) {
		this.realRevenue = realRevenue;
	}

	@Override
	public String toString() {
		return "UserJournal [incomesAmount=" + incomesAmount
				+ ", outcomesAmount=" + outcomesAmount + ", revenue=" + revenue
				+ ", realRevenue=" + realRevenue
				+ "]";
	}
}