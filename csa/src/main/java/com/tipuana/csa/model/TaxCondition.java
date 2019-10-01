package com.tipuana.csa.model;

public enum TaxCondition {
	REGISTERED_RESPONSIBLE(1, "Responsable Inscripto"),
	MONOTRIBUTE(2, "Monotributista"),
	FREE(3, "Exento");
	
	private final Integer id;
	private final String value;
	
	private TaxCondition(Integer id, String value) {
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
