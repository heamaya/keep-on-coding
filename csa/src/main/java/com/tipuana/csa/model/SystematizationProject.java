package com.tipuana.csa.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class SystematizationProject implements Serializable, Comparable<Object> {
	private long id;
	private int version = 0;
	private Date created = new Date();
	private Systematization systematization;
	private Double assessedHectares;
	private Contract contract;
	private String farmAreaFileName;
	private String farmAreaContentType;
	private Terrace terrace;
	private GullyRecovery gullyRecovery;
	private Set<Dike> dikes = new HashSet<Dike>();
	private Set<Dike> headerGullyWorks = new HashSet<Dike>();
	private Set<Payment> payments = new HashSet<Payment>();
	private User user;
	
	public SystematizationProject() {
		super();
	}

	public int getVersion() {
		return version;
	}

	public Date getCreated() {
		return created;
	}

	public Systematization getSystematization() {
		return systematization;
	}

	public Contract getContract() {
		return contract;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setSystematization(Systematization systematization) {
		this.systematization = systematization;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getFarmAreaFileName() {
		return farmAreaFileName;
	}

	public String getFarmAreaContentType() {
		return farmAreaContentType;
	}

	public void setFarmAreaFileName(String farmAreaFileName) {
		this.farmAreaFileName = farmAreaFileName;
	}

	public void setFarmAreaContentType(String farmAreaContentType) {
		this.farmAreaContentType = farmAreaContentType;
	}
	
	public Set<Dike> getDikes() {
		return dikes;
	}

	public void setDikes(Set<Dike> dikes) {
		this.dikes = dikes;
	}
	
	public Terrace getTerrace() {
		return terrace;
	}

	public void setTerrace(Terrace terrace) {
		this.terrace = terrace;
	}
	
	public GullyRecovery getGullyRecovery() {
		return gullyRecovery;
	}

	public void setGullyRecovery(GullyRecovery gullyRecovery) {
		this.gullyRecovery = gullyRecovery;
	}
	
	public Set<Dike> getHeaderGullyWorks() {
		return headerGullyWorks;
	}

	public void setHeaderGullyWorks(Set<Dike> headerGullyWorks) {
		this.headerGullyWorks = headerGullyWorks;
	}
	
	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Double getAssessedHectares() {
		return assessedHectares;
	}

	public void setAssessedHectares(Double assessedHectares) {
		this.assessedHectares = assessedHectares;
	}
	
	public String getSummary() {
		StringBuilder strBuilder = new StringBuilder(getSystematization().getCompany().getName());
		strBuilder.append(", ")
				  .append(getSystematization().getLand().getName())
				  .append(", ")
				  .append(getContract().getContractDate())
		  		  .append(", ")
		  		  .append(getContract().getHiredHectares())
		  		  .append(" Has");
		return strBuilder.toString();
	}
	
	@JSON(serialize=false)
	public String getPath() {
		
		return new StringBuilder(getSystematization().getPath()).
					append(File.separator).
					append("SystematizationProject ").
					append(getId()).
					toString();
	}

	@Override
	public int compareTo(Object object) {

		if(object instanceof SystematizationProject) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (SystematizationProject) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String toString() {
		return "SystematizationProject [systematization=" + systematization
				+ ", contract=" + contract + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contract == null) ? 0 : contract.hashCode());
		result = prime * result
				+ ((systematization == null) ? 0 : systematization.hashCode());
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
		SystematizationProject other = (SystematizationProject) obj;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (systematization == null) {
			if (other.systematization != null)
				return false;
		} else if (!systematization.equals(other.systematization))
			return false;
		return true;
	}
	
}