package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class ConsortiumPhoto implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private Consortium consortium;
	private String photoFileName;
	private String photoContentType;
	private String descriptionSpanish;
	private String descriptionEnglish;
	private String descriptionPortuguese;
	
	public ConsortiumPhoto() {
		super();
	}
	
	public ConsortiumPhoto(Consortium consortium, String photoFileName,
			String photoContentType, String descriptionSpanish, String descriptionEnglish, String descriptionPortuguese) {
		super();
		this.consortium = consortium;
		this.photoFileName = photoFileName;
		this.photoContentType = photoContentType;
		this.descriptionSpanish = descriptionSpanish;
		this.descriptionEnglish = descriptionEnglish;
		this.descriptionPortuguese = descriptionPortuguese;
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

	@Override
	public int compareTo(Object object) {

		if(object instanceof ConsortiumPhoto) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (ConsortiumPhoto) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@JSON(serialize=false)
	public Consortium getConsortium() {
		return consortium;
	}

	public void setConsortium(Consortium consortium) {
		this.consortium = consortium;
	}

	@JSON(serialize=true)
	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	@JSON(serialize=true)
	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consortium == null) ? 0 : consortium.hashCode());
		result = prime * result
				+ ((descriptionSpanish == null) ? 0 : descriptionSpanish.hashCode());
		result = prime
				* result
				+ ((photoContentType == null) ? 0 : photoContentType.hashCode());
		result = prime * result
				+ ((photoFileName == null) ? 0 : photoFileName.hashCode());
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
		ConsortiumPhoto other = (ConsortiumPhoto) obj;
		if (consortium == null) {
			if (other.consortium != null)
				return false;
		} else if (!consortium.equals(other.consortium))
			return false;
		if (photoContentType == null) {
			if (other.photoContentType != null)
				return false;
		} else if (!photoContentType.equals(other.photoContentType))
			return false;
		if (photoFileName == null) {
			if (other.photoFileName != null)
				return false;
		} else if (!photoFileName.equals(other.photoFileName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsortiumPhoto [consortium=" + consortium + ", photoFileName="
				+ photoFileName + "]";
	}
}