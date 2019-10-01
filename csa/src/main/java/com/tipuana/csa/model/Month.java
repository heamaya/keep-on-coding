package com.tipuana.csa.model;

public enum Month {
	JANUARY(1, "Enero"),
	FEBRAURY(2, "Febrero"),
	MARCH(3, "Marzo"),
	APRIL(4, "Abril"),
	MAY(5, "Mayo"),
	JUNE(6, "Junio"),
	JULY(7, "Julio"),
	AUGUST(8, "Agosto"),
	SEPTEMBER(9, "Septiembre"),
	OCTOBER(10, "Octubre"),
	NOVEMBER(11, "Noviembre"),
	DECEMBER(12, "Diciembre");
	
	private final Integer id;
	private final String value;
	
	private Month(Integer id, String value) {
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