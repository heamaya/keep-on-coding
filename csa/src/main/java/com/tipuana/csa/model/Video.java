package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Video implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private String nameSpanish;
	private String namePortuguese;
	private String nameEnglish;
	private String descriptionSpanish;
	private String descriptionEnglish;
	private String descriptionPortuguese;
	private String videoKey;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}

	public String getNameSpanish() {
		return nameSpanish;
	}

	public String getNamePortuguese() {
		return namePortuguese;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameSpanish(String nameSpanish) {
		this.nameSpanish = nameSpanish;
	}

	public void setNamePortuguese(String namePortuguese) {
		this.namePortuguese = namePortuguese;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
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

	public String getVideoKey() {
		return videoKey;
	}

	public void setVideoKey(String videoKey) {
		this.videoKey = videoKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((videoKey == null) ? 0
						: videoKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Video other = (Video) obj;
		
		if (videoKey == null) {
			if (other.videoKey != null)
				return false;
		} else if (!videoKey.equals(other.videoKey)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Video [videoKey=" + videoKey + "]";
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof Video) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (Video) object).getCreated().getTime()));
		}
		
		return 0;
	}

}