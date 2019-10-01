package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@SuppressWarnings("serial")
public class User implements Serializable, Comparable<Object>, UserDetails {
	private Long id;
	private int version = 0;
	private Date created = new Date();
	private String username;
	private String password;
	private Person person;
	private boolean enabled;
	private Role role;
	
	public User() {
		super();
	}

	@Override
	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	@RequiredStringValidator(type=ValidatorType.FIELD, key="password.required", shortCircuit=true)
	@StringLengthFieldValidator(type=ValidatorType.FIELD, key="password.length", minLength="4", maxLength="16")
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return "User [username=" + person.getEmail() + ", role=" + getRole().getValue() + "]";
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {		
		return Authorities.getAuthorities(getRole());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof User) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (User) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String getSummary() {
		
		return new StringBuilder().append(getPerson().getFirstName())
						   .append(" ")
						   .append(getPerson().getLastName()) 						   
						   .toString();
	}
}