package com.tipuana.csa.model;

public enum JournalOutcomeEntryType {
	SERVICES(1, "Servicios"),
	TAXES(2, "Impuestos"),
	SALARY(3, "Salarios"),
	HONORARIA(4, "Honorarios"),
	RENT(5, "Alquiler"),
	OFFICE(6, "Artículos Oficina"),
	VICTUALS(7, "Víveres"),
	BUY(8, "Compras"),
	MAINTENANCE(9, "Mantenimiento"),
	CLEANING(10, "Limpieza"),
	INSURANCE(11, "Seguro"),
	MENSURATION(12, "Mensura"),
	BANK(13, "Banco"),
	COMISSION(14, "Comisión"),
	OTHER(14, "Otro");
		
	private final Integer id;
	private final String value;
	
	private JournalOutcomeEntryType(Integer id, String value) {
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