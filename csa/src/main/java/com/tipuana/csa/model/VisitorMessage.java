package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class VisitorMessage implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private String lastName;
	private String firstName;
	private String email;
	private Province province;
	private City city;
	private String message;
	
	public VisitorMessage() {
		super();
	}
		
	public VisitorMessage(String lastName, String firstName, String email,
			Province province, City city, String message) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.province = province;
		this.city = city;
		this.message = message;
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
	
	public String getEmail() {
		return email;
	}
	
	public String getMessage() {
		return message;
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
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "VisitorMessage [created=" + created + ", email=" + email
				+ ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		VisitorMessage other = (VisitorMessage) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}	
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof PresentationPhoto) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (VisitorMessage) object).getCreated().getTime()));
		}
		
		return 0;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
}