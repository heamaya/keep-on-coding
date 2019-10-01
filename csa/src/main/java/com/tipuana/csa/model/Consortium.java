package com.tipuana.csa.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;


@SuppressWarnings("serial")
public class Consortium implements Serializable, Comparable<Object>{
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private String name;
	private String descriptionSpanish;
	private String descriptionEnglish;
	private String descriptionPortuguese;
	private Date creationDate;
	private String northernLimit;
	private String easternLimit;
	private String southernLimit;
	private String westernLimit;
	private String limitFileName;
	private String limitContentType;
	private Boolean starred;
	private Set<ConsortiumPhoto> consortiumPhotos = new HashSet<ConsortiumPhoto>();
	
	public Consortium() {
		super();
	}
	
	public Consortium(String name, Date creationDate, String northernLimit,
			String easternLimit, String southernLimit, String westernLimit,
			String limitFileName, String limitContentType,
			Set<ConsortiumPhoto> consortiumPhotos, 
			String descriptionSpanish, 
			String descriptionPortuguese, 
			String descriptionEnglish, 
			Boolean starred) {
		super();
		this.name = name;
		this.descriptionSpanish = descriptionSpanish;
		this.descriptionPortuguese = descriptionPortuguese;
		this.descriptionEnglish = descriptionEnglish;
		this.creationDate = creationDate;
		this.northernLimit = northernLimit;
		this.easternLimit = easternLimit;
		this.southernLimit = southernLimit;
		this.westernLimit = westernLimit;
		this.limitFileName = limitFileName;
		this.limitContentType = limitContentType;
		this.consortiumPhotos = consortiumPhotos;
		this.starred = starred;
	}

	@JSON(serialize=true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JSON(serialize=false)
	public String getNorthernLimit() {
		return northernLimit;
	}
	
	public void setNorthernLimit(String northernLimit) {
		this.northernLimit = northernLimit;
	}
	
	@JSON(serialize=false)
	public String getEasternLimit() {
		return easternLimit;
	}
	
	public void setEasternLimit(String easternLimit) {
		this.easternLimit = easternLimit;
	}
	
	@JSON(serialize=false)
	public String getSouthernLimit() {
		return southernLimit;
	}
	
	public void setSouthernLimit(String southernLimit) {
		this.southernLimit = southernLimit;
	}
	
	@JSON(serialize=false)
	public String getWesternLimit() {
		return westernLimit;
	}
	
	public void setWesternLimit(String westernLimit) {
		this.westernLimit = westernLimit;
	}

	@JSON(serialize=true, format="dd/MM/yyyy", name="creationDate")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@JSON(serialize=false)
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@JSON(serialize=false)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof Consortium) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (Consortium) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String toString() {
		return "Consortium [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Consortium other = (Consortium) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@JSON(serialize=true)
	public String getLimitFileName() {
		return limitFileName;
	}

	public void setLimitFileName(String limitFileName) {
		this.limitFileName = limitFileName;
	}

	@JSON(serialize=true)
	public String getLimitContentType() {
		return limitContentType;
	}

	public void setLimitContentType(String limitContentType) {
		this.limitContentType = limitContentType;
	}

	@JSON(serialize=false)
	public Set<ConsortiumPhoto> getConsortiumPhotos() {
		return consortiumPhotos;
	}

	public void setConsortiumPhotos(Set<ConsortiumPhoto> consortiumPhotos) {
		this.consortiumPhotos = consortiumPhotos;
	}

	@JSON(serialize=false)
	public Boolean getStarred() {
		return starred;
	}

	public void setStarred(Boolean starred) {
		this.starred = starred;
	}
	
	@JSON(serialize=false)
	public String getPath() {

		return new StringBuilder(Constants.CONSORTIUM_PATH).
			append(File.separator).
			append(getName()).
			append(" ").
			append(getId()).toString();
		
	}
	
	@JSON(serialize=false)
	public String getPhotosPath() {
		
		return new StringBuilder(Constants.CONSORTIUM_PATH).
			append(File.separator).
			append(getName()).
			append(" ").
			append(getId()).
			append(File.separator).
			append("photos").			
			toString();
	}

	@JSON(serialize=true)
	public Long getId() {
		return id;
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
}