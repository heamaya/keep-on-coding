package com.tipuana.csa.model;

public enum MatchMode {
	EXACT(1, "Palabra Exacta"),
	START(2, "Comienza Con"), 
	ANYWHERE(3, "En Cualquier Lado"), 
	END(4, "Termina Con");
		
	private final Integer id;
	private final String value;
	
	private MatchMode(Integer id, String value) {
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