package com.tipuana.csa.model;

public enum WorkState {
	PROJECTED(1, "Obra Proyectada"), 
	IN_PROGRESS(2, "Obra en Progreso"),
	FINISHED(3, "Obra Finalizada"); 
	
	private final int id;
	private final String state;
	
	private WorkState(int id, String state) {
		this.id = id;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	
	public String getState() {
		return state;
	}
	
	public String toString() {
		return getState();
	}
	
	public String getValue() {
		return getState();
	}

}