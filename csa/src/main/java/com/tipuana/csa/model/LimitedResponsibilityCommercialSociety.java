package com.tipuana.csa.model;

@SuppressWarnings("serial")
public class LimitedResponsibilityCommercialSociety extends CommercialSociety {

	public LimitedResponsibilityCommercialSociety() {
		super();
		setCompanyType(CompanyType.LIMITED_RESPONSIBILITY);
	}

	public LimitedResponsibilityCommercialSociety(String name,
			String tributeKey, Address address, TaxCondition taxCondition) {
		super(name, tributeKey, address, taxCondition);
		setCompanyType(CompanyType.LIMITED_RESPONSIBILITY);
	}
	
	@Override
	public int compareTo(Object object) {

		if(object instanceof LimitedResponsibilityCommercialSociety) {
			return Long.valueOf(this.getCreated().getTime()).compareTo(
				   Long.valueOf(( (LimitedResponsibilityCommercialSociety) object).getCreated().getTime()));
		}
		
		return 0;
	}

	
}