package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PresentationPhoto implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private String name;
	private String contentType;
	private String descriptionSpanish;
	private String descriptionEnglish;
	private String descriptionPortuguese; 
	
	public Long getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public String getName() {
		return name;
	}
	
	public String getContentType() {
		return contentType;
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
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getDescriptionSpanish() {
		return descriptionSpanish;
	}

	public String getDescriptionEnglish() {
		return descriptionEnglish;
	}

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

	@Override
	public int compareTo(Object object) {

		if(object instanceof PresentationPhoto) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (PresentationPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
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
		
		PresentationPhoto other = (PresentationPhoto) obj;
		
		if (created == null) {
		
			if (other.created != null)
				return false;
			
		} else if (!created.equals(other.created))
			return false;
		
		if (name == null) {
			
			if (other.name != null)
				return false;
			
		} else if (!name.equals(other.name))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "PresentationPhoto [created=" + created + ", name=" + name + "]";
	}
}