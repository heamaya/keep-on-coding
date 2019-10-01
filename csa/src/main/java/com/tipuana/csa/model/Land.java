package com.tipuana.csa.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class Land implements Serializable, Comparable<Object> {	
	private long id;
	private int version = 0;
	private Date created = new Date();
	private Company company;
	private String name;
	private String description;
	private Province nearestProvince;
	private City nearestCity;
	private Double nearestCityDistance;
	private Double surfaceArea;
	private String boundariesFileName;
	private String boundariesContentType;
	
	@JSON(serialize=true)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@JSON(serialize=false)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JSON(serialize=false)
	public City getNearestCity() {
		return nearestCity;
	}

	public void setNearestCity(City nearestCity) {
		this.nearestCity = nearestCity;
	}

	@JSON(serialize=false)
	public Province getNearestProvince() {
		return nearestProvince;
	}

	public void setNearestProvince(Province nearestProvince) {
		this.nearestProvince = nearestProvince;
	}

	@JSON(serialize=false)
	public Double getNearestCityDistance() {
		return nearestCityDistance;
	}

	public void setNearestCityDistance(Double nearestCityDistance) {
		this.nearestCityDistance = nearestCityDistance;
	}

	@JSON(serialize=false)
	public Double getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(Double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	@JSON(serialize=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSON(serialize=false)
	public String getBoundariesFileName() {
		return boundariesFileName;
	}

	public void setBoundariesFileName(
			String boundariesFileName) {
		this.boundariesFileName = boundariesFileName;
	}

	@JSON(serialize=false)
	public String getBoundariesContentType() {
		return boundariesContentType;
	}

	public void setBoundariesContentType(
			String boundariesContentType) {
		this.boundariesContentType = boundariesContentType;
	}
	
	@JSON(serialize=false)
	public int getVersion() {
		return version;
	}

	@JSON(serialize=false)
	public Date getCreated() {
		return created;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@JSON(serialize=false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@JSON(serialize=false)
	public String getPath() {
		
		if(getCompany() != null) {
		
			return new StringBuilder(getCompany().getPath()).
				append(File.separator).
				append(getName()).
				append(" ").
				append(getId()).toString();
			}
		
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((surfaceArea == null) ? 0 : surfaceArea.hashCode());
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
		Land other = (Land) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surfaceArea == null) {
			if (other.surfaceArea != null)
				return false;
		} else if (!surfaceArea.equals(other.surfaceArea))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof Land) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (Land) object ).getCreated().getTime()));
		}
		
		return 0;
	}	
}