package com.tipuana.csa.model;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CommercialSociety extends Company {

	private Set<Farmer> farmers = new HashSet<Farmer>();

	public CommercialSociety() {
		super();
	}

	public CommercialSociety(
			String name, 
			String tributeKey, Address address,
			TaxCondition taxCondition) 
	{
		super(name, tributeKey, address, taxCondition);
	}

	public Set<Farmer> getFarmers() {
		return farmers;
	}

	public void setFarmers(Set<Farmer> farmers) {
		this.farmers = farmers;
	}
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof CommercialSociety) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (CommercialSociety) object).getCreated().getTime()));
		}
		
		return 0;
	}

}