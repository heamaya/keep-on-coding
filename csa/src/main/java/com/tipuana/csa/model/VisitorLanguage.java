package com.tipuana.csa.model;

public enum VisitorLanguage {
	SPANISH(1, "spanish"),
	ENGLISH(2, "english"),
	PORTUGUESE(3, "portuguese"); 
	
	private final Integer id;
	private final String value;

	private VisitorLanguage(Integer id, String value) {
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