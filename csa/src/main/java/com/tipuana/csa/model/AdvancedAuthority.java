package com.tipuana.csa.model;

import java.util.Date;

import org.springframework.security.core.GrantedAuthority;


@SuppressWarnings("serial")
public class AdvancedAuthority implements GrantedAuthority, Comparable<Object> {
	private Date created = new Date();

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof AdvancedAuthority) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (AdvancedAuthority) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String getAuthority() {
		return AuthorityConstants.ADVANCED;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}