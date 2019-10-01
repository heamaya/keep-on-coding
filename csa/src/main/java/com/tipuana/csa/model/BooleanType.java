package com.tipuana.csa.model;

public enum BooleanType {
	YES(1, "Si"), 
	NO(2, "No");
		
	private final Integer id;
	private final String value;
	
	private BooleanType(Integer id, String value) {
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