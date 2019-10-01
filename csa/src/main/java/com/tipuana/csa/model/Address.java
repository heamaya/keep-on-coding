package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@SuppressWarnings("serial")
public class Address implements Serializable, Comparable<Object> {
	private Long id;
	private int version = 0;
	private Province province;
	private City city;
	private String street;
	private Integer streetNumber;
	private String department;
	private Integer floor;
	private Date created = new Date();
	
	public Address() {
		super();
	}

	public Address(Province province, City city, String street,
			Integer streetNumber, String department, Integer floor) {
		super();
		this.province = province;
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		this.department = department;
		this.floor = floor;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getFloor() {
		return floor;
	}

	@IntRangeFieldValidator(type=ValidatorType.FIELD, key="floor.range", min="0", max="160")
	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public Date getCreated() {
		return created;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result
				+ ((streetNumber == null) ? 0 : streetNumber.hashCode());
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
		if (!(obj instanceof Address)) {
			return false;
		}
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (department == null) {
			if (other.department != null) {
				return false;
			}
		} else if (!department.equals(other.department)) {
			return false;
		}
		if (floor == null) {
			if (other.floor != null) {
				return false;
			}
		} else if (!floor.equals(other.floor)) {
			return false;
		}
		if (province == null) {
			if (other.province != null) {
				return false;
			}
		} else if (!province.equals(other.province)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		if (streetNumber == null) {
			if (other.streetNumber != null) {
				return false;
			}
		} else if (!streetNumber.equals(other.streetNumber)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Address [city=" + city
				+ ", department=" + department + ", floor=" + floor
				+ ", province=" + province + ", street=" + street + ", streetNumber="
				+ streetNumber + "]";
	}
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof Address) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (Address) object).getCreated().getTime()));
		}
		
		return 0;
	}	
	
}