package com.tipuana.csa.model;

public enum DikeSlope {
	ONE_IN_ONE(1, "1:1"),
	ONE_AND_A_HALF_IN_ONE(2, "1,5:1"),
	TWO_IN_ONE(3, "2:1"),
	TWO_AND_A_HALF_IN_ONE(4, "2,5:1"),
	THREE_IN_ONE(5,"3:1"),
	THREE_AND_A_HALF_IN_ONE(6,"3,5:1"),
	FOUR_IN_ONE(7,"4:1");
	
	private final Integer id;
	private final String value;
	
	private DikeSlope(Integer id, String value) {
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