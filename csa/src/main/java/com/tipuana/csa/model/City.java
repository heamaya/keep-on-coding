package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@SuppressWarnings("serial")
public class City implements Serializable, Comparable<Object> {
	
	private Long id;
	
	private int version = 0;
	private Date created = new Date();
	
	private String name;
	private Province province;
	
	private Set<Address> addresses = new HashSet<Address>();

	
	public City() {
		super();

	}

	public City(String name, int version, Province province, Set<Address> addresses) {
		super();
		this.name = name;
		this.version = version;
		this.province = province;
		this.addresses = addresses;
	}

	@JSON(serialize=true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JSON(serialize=false)
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
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
	public String getName() {
		return name;
	}
	
	@RequiredStringValidator(type=ValidatorType.FIELD, key="name.required", shortCircuit=true)
	@StringLengthFieldValidator(type=ValidatorType.FIELD, key="name.length", minLength="1", maxLength="256")
	public void setName(String name) {
		this.name = name;
	}

	@JSON(serialize=false)
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
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
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (province == null) {
			if (other.province != null) {
				return false;
			}
		} else if (!province.equals(other.province)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", province=" + province + "]";
	}
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof City) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (City) object).getCreated().getTime()));
		}
		
		return 0;
	}	
}