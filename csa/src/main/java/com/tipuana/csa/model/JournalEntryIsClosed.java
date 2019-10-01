package com.tipuana.csa.model;

public enum JournalEntryIsClosed {
	OPEN(1, "Abierto"),
	CLOSED(2, "Cerrado");
		
	private final Integer id;
	private final String value;
	
	private JournalEntryIsClosed(Integer id, String value) {
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