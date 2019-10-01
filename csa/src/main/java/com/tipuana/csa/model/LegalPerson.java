package com.tipuana.csa.model;

@SuppressWarnings("serial")
public class LegalPerson extends Company {
	
	private Farmer farmer;

	public LegalPerson() {
		super();
		setCompanyType(CompanyType.LEGAL_PERSON);
	}

	public LegalPerson(String name, String tributeKey, Address address,
			TaxCondition taxCondition, Farmer farmer) {
		super(name, tributeKey, address, taxCondition);
		
		setFarmer(farmer);
		setCompanyType(CompanyType.LEGAL_PERSON);
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof LegalPerson) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (LegalPerson) object).getCreated().getTime()));
		}
		
		return 0;
	}
}