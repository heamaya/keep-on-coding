package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@SuppressWarnings("serial")
public class Person implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Date created = new Date();	
	
	private String firstName;
	private String lastName;
	private String email;
	private String primaryTelephoneNumber;
	private String secondaryTelephoneNumber;
	private String primaryCellPhoneNumber;
	private String secondaryCellPhoneNumber;
	private String secondaryEmail;
	private String comment;
	private Address address;
	
	public Person() {
		super();
	}

	public Person(String firstName, String lastName, String email,
			String primaryTelephoneNumber, String secondaryTelephoneNumber,
			String primaryCellPhoneNumber, String secondaryCellPhoneNumber,
			String secondaryEmail, String comment, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.primaryTelephoneNumber = primaryTelephoneNumber;
		this.secondaryTelephoneNumber = secondaryTelephoneNumber;
		this.primaryCellPhoneNumber = primaryCellPhoneNumber;
		this.secondaryCellPhoneNumber = secondaryCellPhoneNumber;
		this.secondaryEmail = secondaryEmail;
		this.comment = comment;
		this.address = address;
	}

	@JSON(serialize=false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JSON(serialize=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JSON(serialize=false)
	public String getPrimaryTelephoneNumber() {
		return primaryTelephoneNumber;
	}

	public void setPrimaryTelephoneNumber(String primaryTelephoneNumber) {
		this.primaryTelephoneNumber = primaryTelephoneNumber;
	}
	
	@JSON(serialize=false)
	public String getSecondaryTelephoneNumber() {
		return secondaryTelephoneNumber;
	}

	public void setSecondaryTelephoneNumber(String secondaryTelephoneNumber) {
		this.secondaryTelephoneNumber = secondaryTelephoneNumber;
	}

	@JSON(serialize=false)
	public String getPrimaryCellPhoneNumber() {
		return primaryCellPhoneNumber;
	}

	public void setPrimaryCellPhoneNumber(String primaryCellPhoneNumber) {
		this.primaryCellPhoneNumber = primaryCellPhoneNumber;
	}

	@JSON(serialize=false)
	public String getSecondaryCellPhoneNumber() {
		return secondaryCellPhoneNumber;
	}

	@StringLengthFieldValidator(type=ValidatorType.FIELD, key="secondaryCellPhoneNumber.length", minLength="1", maxLength="255")
	public void setSecondaryCellPhoneNumber(String secondaryCellPhoneNumber) {
		this.secondaryCellPhoneNumber = secondaryCellPhoneNumber;
	}

	@JSON(serialize=false)
	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	@JSON(serialize=false)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object object) {

		if(object instanceof Person) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (Person) object).getCreated().getTime()));
		}
		
		return 0;
	}
	
	@JSON(serialize=false)
	public String getPath() {
		return getLastName() + getFirstName() + getId();
	}

	@JSON(serialize=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JSON(serialize=true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@JSON(serialize=false)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@JSON(serialize=true, name="summary")
	public String getSummary() {
		return new StringBuilder().append(getFirstName())
								  .append(" ")
								  .append(getLastName())
								  .append(" de ")
								  .append(getAddress().getCity().getName())
								  .toString();
	}
}