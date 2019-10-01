package com.tipuana.csa.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class Journal implements Serializable, Comparable<Object> {
	private Long id;
	private Date created = new Date();
	private int version = 0;
	private Date fromDate;
	private Date toDate;
	private Set<JournalIncomeEntry> incomes = new HashSet<JournalIncomeEntry>();
	private Set<JournalOutcomeEntry> outcomes = new HashSet<JournalOutcomeEntry>();
	private Double incomesAmount;
	private Double outcomesAmount;
	private Double revenue;
	private Set<UserJournal> usersJournal = new HashSet<UserJournal>();
	
	@JSON(serialize=true)
	public Long getId() {
		return id;
	}

	@JSON(serialize=false)
	public Date getCreated() {
		return created;
	}

	@JSON(serialize=false)
	public int getVersion() {
		return version;
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
	
	@JSON(serialize=true)
	public Date getFromDate() {
		return fromDate;
	}

	@JSON(serialize=true)
	public Date getToDate() {
		return toDate;
	}

	@JSON(serialize=false)
	public Set<JournalIncomeEntry> getIncomes() {
		return incomes;
	}

	@JSON(serialize=false)
	public Set<JournalOutcomeEntry> getOutcomes() {
		return outcomes;
	}

	@JSON(serialize=false)
	public Double getIncomesAmount() {
		return this.incomesAmount;
	}

	@JSON(serialize=false)
	public Double getOutcomesAmount() {
		return this.outcomesAmount;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setIncomes(Set<JournalIncomeEntry> incomes) {
		this.incomes = incomes;
	}

	public void setOutcomes(Set<JournalOutcomeEntry> outcomes) {
		this.outcomes = outcomes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
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
		Journal other = (Journal) obj;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Journal [fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof Journal) {
			
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (Journal) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@JSON(serialize=true)
	public Double getRevenue() {
		return revenue;
	}

	public void setIncomesAmount(Double incomesAmount) {
		this.incomesAmount = incomesAmount;
	}

	public void setOutcomesAmount(Double outcomesAmount) {
		this.outcomesAmount = outcomesAmount;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	@JSON(serialize=true)
	public Set<UserJournal> getUsersJournal() {
		return usersJournal;
	}

	public void setUsersJournal(Set<UserJournal> usersJournal) {
		this.usersJournal = usersJournal;
	}
	
	public String getSummary() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder sb = new StringBuilder();
		sb.append(dateFormat.format(getFromDate()));
		sb.append(" - ");
		sb.append(dateFormat.format(getToDate()));
	
		return sb.toString();
	}
}