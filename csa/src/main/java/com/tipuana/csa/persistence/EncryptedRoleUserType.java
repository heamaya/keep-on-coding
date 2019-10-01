package com.tipuana.csa.persistence;

import org.jasypt.hibernate.type.AbstractEncryptedAsStringType;

import com.tipuana.csa.model.Role;


public class EncryptedRoleUserType extends AbstractEncryptedAsStringType {

	@Override
	protected Object convertToObject(final String value) {
		
		if(value.equalsIgnoreCase("ROOT")){
			return Role.ROOT;
		} else if(value.equalsIgnoreCase("SUPERADMIN")){
			return Role.SUPERADMIN;
		} else if(value.equalsIgnoreCase("ADMIN")){
			return Role.ADMIN;
		} else if(value.equalsIgnoreCase("BASICADMIN")){
			return Role.BASICADMIN;
		} else if(value.equalsIgnoreCase("CUSTOMER")){
			return Role.CUSTOMER;
		}
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return Role.class;
	}

}
