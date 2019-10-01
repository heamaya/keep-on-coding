package com.tipuana.csa.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;


@SuppressWarnings("serial")
public class Company implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private String name;
	private String tributeKey;
	private Address address;
	private TaxCondition taxCondition;
	private Set<Land> lands = new HashSet<Land>();
	private CompanyType companyType;
	
	public Company() {
		super();
	}
	
	public Company(String name, String tributeKey, Address address,
			TaxCondition taxCondition) {
		super();
		this.name = name;
		this.tributeKey = tributeKey;
		this.address = address;
		this.taxCondition = taxCondition;
	}

	@JSON(serialize=true)
	public Long getId() {
		return id;
	}

	@JSON(serialize=false)
	public int getVersion() {
		return version;
	}

	@JSON(serialize=false)
	public Date getCreated() {
		return created;
	}

	@JSON(serialize=true)
	public String getName() {
		return name;
	}

	@JSON(serialize=false)
	public String getTributeKey() {
		return tributeKey;
	}

	@JSON(serialize=false)
	public Address getAddress() {
		return address;
	}

	@JSON(serialize=false)
	public TaxCondition getTaxCondition() {
		return taxCondition;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setTributeKey(String tributeKey) {
		this.tributeKey = tributeKey;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setTaxCondition(TaxCondition taxCondition) {
		this.taxCondition = taxCondition;
	}
	
	@JSON(serialize=false)
	public Set<Farmer> getFarmers() {
		return null;
	}
	
	@JSON(serialize=false)
	public Farmer getFarmer() {
		return null;
	}
	
	@JSON(serialize=false)
	public Set<Land> getLands() {
		return lands;
	}

	public void setLands(Set<Land> lands) {
		this.lands = lands;
	}
	
	@JSON(serialize=true, name="summary")
	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	
	@JSON(serialize=true, name="summary")
	public String getSummary() {
		
		return new StringBuilder()
			.append(getCompanyType().getValue())
			.append(", ")
			.append(getName()).toString();
	}

	@JSON(serialize=false)
	public String getPath() {	
		return new StringBuilder(Constants.COMPANIES_PATH).append(File.separator).append(getName()).append(" ").append(getTributeKey()).toString();
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof Company) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (Company) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((tributeKey == null) ? 0 : tributeKey.hashCode());
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
		Company other = (Company) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tributeKey == null) {
			if (other.tributeKey != null)
				return false;
		} else if (!tributeKey.equals(other.tributeKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", tributeKey=" + tributeKey + "]";
	}	
}