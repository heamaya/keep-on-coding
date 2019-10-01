package com.tipuana.csa.model;

public enum Role {
	ROOT(1, "ROOT"),
	SUPERADMIN(2, "SUPERADMIN"),
	ADMIN(3, "ADMIN"), 
	BASICADMIN(4, "BASICADMIN"),
	CUSTOMER(5, "CUSTOMER");
	
	private final Integer id;
	private final String value;

	private Role(Integer id, String value) {
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