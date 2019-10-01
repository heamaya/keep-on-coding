package com.tipuana.csa.model;

import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class IntermediateAuthority implements GrantedAuthority, Comparable<Object> {
	private Date created = new Date();

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof IntermediateAuthority) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (IntermediateAuthority) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String getAuthority() {
		return AuthorityConstants.INTERMEDIATE;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}