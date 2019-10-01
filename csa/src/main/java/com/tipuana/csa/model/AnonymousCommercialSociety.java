package com.tipuana.csa.model;

@SuppressWarnings("serial")
public class AnonymousCommercialSociety extends CommercialSociety {

	public AnonymousCommercialSociety() {
		super();
		setCompanyType(CompanyType.ANONYMOUS);
	}

	public AnonymousCommercialSociety(String name, String tributeKey,
			Address address, TaxCondition taxCondition) {
		super(name, tributeKey, address, taxCondition);
		setCompanyType(CompanyType.ANONYMOUS);
	}
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof AnonymousCommercialSociety) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (AnonymousCommercialSociety) object).getCreated().getTime()));
		}
		
		return 0;
	}
}