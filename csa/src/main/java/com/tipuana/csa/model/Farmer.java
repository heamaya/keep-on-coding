package com.tipuana.csa.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

@SuppressWarnings("serial")
public class Farmer extends Person {

	private Set<CommercialSociety> commercialSocieties = new HashSet<CommercialSociety>();
	private LegalPerson legalPerson;

	public Farmer() {
		super();
	}

	public Farmer(Set<CommercialSociety> commercialSocieties,
			LegalPerson legalPerson) {
		super();
		this.commercialSocieties = commercialSocieties;
		this.legalPerson = legalPerson;
	}

	@JSON(serialize=false)
	public Set<CommercialSociety> getCommercialSocieties() {
		return commercialSocieties;
	}

	@JSON(serialize=false)
	public LegalPerson getLegalPerson() {
		return legalPerson;
	}

	public void setCommercialSocieties(Set<CommercialSociety> commercialSocieties) {
		this.commercialSocieties = commercialSocieties;
	}

	public void setLegalPerson(LegalPerson legalPerson) {
		this.legalPerson = legalPerson;
	}
		
	@Override
	public int compareTo(Object object) {

		if(object instanceof Farmer) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (Farmer) object).getCreated().getTime()));
		}
		
		return 0;
	}

	@Override
	public String toString() {
		
		return new StringBuilder("Farmer [").append(getLastName())
        .append(getFirstName())
        .append(" ")
        .append(getAddress().getProvince().getName())
        .append(" ")
        .append(getAddress().getCity().getName())
        .append("]")
        .toString();
	}
	
	
}