package com.tipuana.csa.model;

public enum JournalIncomeEntryType {
	PAYMENT(1, "Cobranza"),
	OTHER(2, "Otro");
		
	private final Integer id;
	private final String value;
	
	private JournalIncomeEntryType(Integer id, String value) {
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