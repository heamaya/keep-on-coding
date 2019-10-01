package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class SystematizationPhoto implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private Systematization systematization;
	private String systematizationPhotoFileName;
	private String systematizationPhotoContentType;
	private String descriptionSpanish;
	private String descriptionEnglish;
	private String descriptionPortuguese;
	
	public SystematizationPhoto() {
		super();
	}
	
	public SystematizationPhoto(Systematization systematization, String systematizationPhotoFileName, String systematizationPhotoContentType, String descriptionSpanish) {
		super();
		this.systematization = systematization;
		this.systematizationPhotoFileName = systematizationPhotoFileName;
		this.systematizationPhotoContentType = systematizationPhotoContentType;
		this.descriptionSpanish = descriptionSpanish;
	}

	@JSON(serialize=false)
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
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public void setCreated(Date created) {
		this.created = created;
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

	@JSON(serialize=true)
	public String getSystematizationPhotoFileName() {
		return systematizationPhotoFileName;
	}

	@JSON(serialize=true)
	public String getSystematizationPhotoContentType() {
		return systematizationPhotoContentType;
	}

	public void setSystematizationPhotoFileName(String systematizationPhotoFileName) {
		this.systematizationPhotoFileName = systematizationPhotoFileName;
	}

	public void setSystematizationPhotoContentType(String systematizationPhotoContentType) {
		this.systematizationPhotoContentType = systematizationPhotoContentType;
	}

	@Override
	public int compareTo(Object object) {

		if(object instanceof SystematizationPhoto) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (SystematizationPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((systematizationPhotoFileName == null) ? 0 : systematizationPhotoFileName.hashCode());
		
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
		
		SystematizationPhoto other = (SystematizationPhoto) obj;
		
		if (created == null) {
		
			if (other.created != null)
				return false;
			
		} else if (!created.equals(other.created))
			return false;
		
		if (systematizationPhotoFileName == null) {
			
			if (other.systematizationPhotoFileName != null)
				return false;
			
		} else if (!systematizationPhotoFileName.equals(other.systematizationPhotoFileName))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "SystematizationPhoto [created=" + created + ", systematizationPhotoFileName=" + systematizationPhotoFileName + "]";
	}

	@JSON(serialize=false)
	public Systematization getSystematization() {
		return systematization;
	}

	public void setSystematization(Systematization systematization) {
		this.systematization = systematization;
	}
	
}