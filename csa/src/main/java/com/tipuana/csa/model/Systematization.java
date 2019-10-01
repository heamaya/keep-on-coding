package com.tipuana.csa.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class Systematization implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	
	private Company company;
	private Land land;
	private String descriptionSpanish;
	private String descriptionEnglish;
	private String descriptionPortuguese;
	private String systematizationFileName;
	private String systematizationContentType;
	private Set<SystematizationBeforePhoto> beforePhotos = new HashSet<SystematizationBeforePhoto>();
	private Set<SystematizationAfterPhoto> afterPhotos = new HashSet<SystematizationAfterPhoto>();
	private Boolean starred;
	private Set<SystematizationProject> systematizationProjects = new HashSet<SystematizationProject>();
	
	public Systematization() {
		super();
	}
	
	public Systematization(Company company, Land land, String descriptionSpanish,
			String descriptionPortuguese, String descriptionEnglish,
			String systematizationFileName, String systematizationContentType,
			Set<SystematizationBeforePhoto> beforePhotos,
			Set<SystematizationAfterPhoto> afterPhotos, Boolean starred) {
		super();
		this.company = company;
		this.land = land;
		this.descriptionSpanish = descriptionSpanish;
		this.descriptionEnglish = descriptionEnglish;
		this.descriptionPortuguese = descriptionPortuguese;
		this.systematizationFileName = systematizationFileName;
		this.systematizationContentType = systematizationContentType;
		this.beforePhotos = beforePhotos;
		this.afterPhotos = afterPhotos;
		this.starred = starred;
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
	public Company getCompany() {
		return company;
	}
	
	@JSON(serialize=false)
	public Set<SystematizationBeforePhoto> getBeforePhotos() {
		return beforePhotos;
	}
	
	@JSON(serialize=false)
	public Set<SystematizationAfterPhoto> getAfterPhotos() {
		return afterPhotos;
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
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public void setBeforePhotos(Set<SystematizationBeforePhoto> beforePhotos) {
		this.beforePhotos = beforePhotos;
	}
	
	public void setAfterPhotos(Set<SystematizationAfterPhoto> afterPhotos) {
		this.afterPhotos = afterPhotos;
	}
	
	@JSON(serialize=true)
	public String getSystematizationFileName() {
		return systematizationFileName;
	}

	public void setSystematizationFileName(String systematizationFileName) {
		this.systematizationFileName = systematizationFileName;
	}

	@JSON(serialize=true)
	public String getSystematizationContentType() {
		return systematizationContentType;
	}

	public void setSystematizationContentType(String systematizationContentType) {
		this.systematizationContentType = systematizationContentType;
	}

	@JSON(serialize=true)
	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}
	
	@JSON(serialize=false)
	public String getPath() {
		
		return new StringBuilder(getLand().getPath()).
					append(File.separator).
					append("Systematization").toString();
	}
	
	@JSON(serialize=false)
	public String getBeforePhotoPath() {
		
		return new StringBuilder(getLand().getPath()).
					append(File.separator).
					append("Systematization").
					append(File.separator).
					append("BeforePhotos").		
					toString();
		
	}
	
	@JSON(serialize=false)
	public String getAfterPhotoPath() {
		
		return new StringBuilder(getLand().getPath()).
					append(File.separator).
					append("Systematization").
					append(File.separator).
					append("AfterPhotos").		
					toString();
		
	}

	@Override
	public int compareTo(Object object) {

		if(object instanceof Systematization) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (Systematization) object).getCreated().getTime()));
		}
		
		return 0;
	}
	
	@Override
	public String toString() {
		return "Systematization [company=" + company + ", land=" + land + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((land == null) ? 0 : land.hashCode());
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
		Systematization other = (Systematization) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;
		return true;
	}

	public Boolean getStarred() {
		return starred;
	}

	public void setStarred(Boolean starred) {
		this.starred = starred;
	}

	@JSON(serialize=true)
	public String getDescriptionSpanish() {
		return descriptionSpanish;
	}

	@JSON(serialize=true)
	public String getDescriptionEnglish() {
		return descriptionEnglish;
	}

	@JSON(serialize=true)
	public String getDescriptionPortuguese() {
		return descriptionPortuguese;
	}

	public void setDescriptionSpanish(String descriptionSpanish) {
		this.descriptionSpanish = descriptionSpanish;
	}

	public void setDescriptionEnglish(String descriptionEnglish) {
		this.descriptionEnglish = descriptionEnglish;
	}

	public void setDescriptionPortuguese(String descriptionPortuguese) {
		this.descriptionPortuguese = descriptionPortuguese;
	}

	public Set<SystematizationProject> getSystematizationProjects() {
		return systematizationProjects;
	}

	public void setSystematizationProjects(
			Set<SystematizationProject> systematizationProjects) {
		this.systematizationProjects = systematizationProjects;
	}
	
	@JSON(serialize=true, name="summary")
	public String getSummary() {
		StringBuilder summaryStringBuilder = 
			new StringBuilder(getCompany().getName())
				.append(", ")
				.append(getLand().getName());
						
		return summaryStringBuilder.toString();
	}
}