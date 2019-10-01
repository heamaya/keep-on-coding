package com.tipuana.csa.model;

public enum CompanyType {
	LEGAL_PERSON(1, "Persona Legal"), 
	FACT(2, "Sociedad de Hecho"), 
	ANONYMOUS(3, "Sociedad Anónima"),
	LIMITED_RESPONSIBILITY(4, "Sociedad de Responsabilidad Limitada");
		
	private final Integer id;
	private final String value;
	
	private CompanyType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}
		
	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
		
	public String toString() {
		return getValue();
	}
}