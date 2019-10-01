package com.tipuana.csa.model;

import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class FinanceAuthority implements GrantedAuthority, Comparable<Object> {
	private Date created = new Date();

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof FinanceAuthority) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (FinanceAuthority) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String getAuthority() {
		return AuthorityConstants.FINANCE;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}