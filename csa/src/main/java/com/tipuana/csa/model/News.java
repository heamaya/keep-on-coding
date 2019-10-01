package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class News implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private String imageFileName;
	private String imageContentType;
	
	private String imageDescriptionSpanish;
	private String imageDescriptionPortuguese;
	private String imageDescriptionEnglish;
	
	private String titleSpanish;
	private String titlePortuguese;
	private String titleEnglish;
	
	private String bodySpanish;
	private String bodyPortuguese;
	private String bodyEnglish;
		
	public News() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
	}
	
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
		
	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	@Override
	public int compareTo(Object object) {

		if(object instanceof News) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (News) object).getCreated().getTime()));
		}
		
		return 0;
	}

	public String getImageDescriptionSpanish() {
		return imageDescriptionSpanish;
	}

	public String getTitleSpanish() {
		return titleSpanish;
	}

	public String getBodySpanish() {
		return bodySpanish;
	}

	public String getImageDescriptionPortuguese() {
		return imageDescriptionPortuguese;
	}

	public String getTitlePortuguese() {
		return titlePortuguese;
	}

	public String getBodyPortuguese() {
		return bodyPortuguese;
	}

	public String getImageDescriptionEnglish() {
		return imageDescriptionEnglish;
	}

	public String getTitleEnglish() {
		return titleEnglish;
	}

	public String getBodyEnglish() {
		return bodyEnglish;
	}

	public void setImageDescriptionSpanish(String imageDescriptionSpanish) {
		this.imageDescriptionSpanish = imageDescriptionSpanish;
	}

	public void setTitleSpanish(String titleSpanish) {
		this.titleSpanish = titleSpanish;
	}

	public void setBodySpanish(String bodySpanish) {
		this.bodySpanish = bodySpanish;
	}

	public void setImageDescriptionPortuguese(String imageDescriptionPortuguese) {
		this.imageDescriptionPortuguese = imageDescriptionPortuguese;
	}

	public void setTitlePortuguese(String titlePortuguese) {
		this.titlePortuguese = titlePortuguese;
	}

	public void setBodyPortuguese(String bodyPortuguese) {
		this.bodyPortuguese = bodyPortuguese;
	}

	public void setImageDescriptionEnglish(String imageDescriptionEnglish) {
		this.imageDescriptionEnglish = imageDescriptionEnglish;
	}

	public void setTitleEnglish(String titleEnglish) {
		this.titleEnglish = titleEnglish;
	}

	public void setBodyEnglish(String bodyEnglish) {
		this.bodyEnglish = bodyEnglish;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((titleSpanish == null) ? 0 : titleSpanish.hashCode());
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
		News other = (News) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (titleSpanish == null) {
			if (other.titleSpanish != null)
				return false;
		} else if (!titleSpanish.equals(other.titleSpanish))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "News [titleSpanish=" + titleSpanish + ", bodySpanish="
				+ bodySpanish + "]";
	}
}