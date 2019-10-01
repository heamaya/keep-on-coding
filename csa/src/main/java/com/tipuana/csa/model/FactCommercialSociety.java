package com.tipuana.csa.model;

@SuppressWarnings("serial")
public class FactCommercialSociety extends CommercialSociety {

	public FactCommercialSociety() {
		super();
		setCompanyType(CompanyType.FACT);
	}

	public FactCommercialSociety(String name, String tributeKey,
			Address address, TaxCondition taxCondition) {
		super(name, tributeKey, address, taxCondition);
		setCompanyType(CompanyType.FACT);
	}
	
	@Override
	public int compareTo(Object object) {
		
		if(object instanceof FactCommercialSociety) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (FactCommercialSociety) object).getCreated().getTime()));
		}
		
		return 0;
	}
		
}