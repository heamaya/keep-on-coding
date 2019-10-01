package com.tipuana.csa.model;

public enum HeaderGullyWorkType {
	DUCKBILL(1, "Pico de Pato"),
	JUMP(2, "Salto"),
	INCLINEDPLAN(3, "Plano Inclinado");
	
	private final int id;
	private final String value;
	
	private HeaderGullyWorkType(int id, String value) {
		this.id  = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	public String toString() {
		return getValue();
	}
}