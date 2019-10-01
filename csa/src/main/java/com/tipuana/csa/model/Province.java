package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * @author henry
 *
 */
@SuppressWarnings("serial")
public class Province implements Serializable, Comparable<Object> {
	private Long id;
	
	private int version = 0;
	private Date created = new Date();
	
	private String name;
	
	private Set<Address> addresses = new HashSet<Address>();
	private Set<City> cities = new HashSet<City>();

	public Province() {
		super();
	}
	
	public Province(String name, Set<City> cities, Set<Address> addresses) {
		super();
		this.name = name;
		this.cities = cities;
		this.addresses = addresses;
	}

	public void addCity(City city) {
		this.getCities().add(city);
		city.setProvince(this);
	}
	
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

	@JSON(serialize=false)
	public Date getCreated() {
		return created;
	}

	public String getName() {
		return name;
	}
	
	@RequiredStringValidator(type=ValidatorType.FIELD, key="name.required", shortCircuit=true)
	@StringLengthFieldValidator(type=ValidatorType.FIELD, key="name.length", minLength="1", maxLength="256")
	public void setName(String name) {
		this.name = name;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "State [name=" + name + "]";
	}

	@JSON(serialize=false)
	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Province)) {
			return false;
		}
		Province other = (Province) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Object object) {

		if(object instanceof Province) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (Province) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@JSON(serialize=false)
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}	
}