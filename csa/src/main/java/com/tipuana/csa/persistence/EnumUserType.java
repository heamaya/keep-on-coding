package com.tipuana.csa.persistence;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.type.StringType;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.util.ReflectHelper;

public class EnumUserType implements EnhancedUserType, ParameterizedType {

	@SuppressWarnings("rawtypes")
	private Class<Enum> enumClass;

    @SuppressWarnings("unchecked")
	public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty("enumClassName");
       
        try {
            enumClass = ReflectHelper.classForName(enumClassName);
        } catch (ClassNotFoundException classNotFoundException) {
            throw new HibernateException("Enum class not found", classNotFoundException);
        }
    }

	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
        return enumClass;
    }

    public int[] sqlTypes() {
        return new int[] { StringType.INSTANCE.sqlType() };
    }

    public boolean isMutable() {
        return false;
    }

    public Object deepCopy(Object value) {
        return value;
    }


	@SuppressWarnings("rawtypes")
	public Serializable disassemble(Object value) {
        return (Enum) value;
    }

    public Object replace(Object original, Object target, Object owner) {
        return original;
    }

    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }

    public boolean equals(Object x, Object y) {
        return x==y;
    }

    public int hashCode(Object x) {
        return x.hashCode();
    }

    @SuppressWarnings("unchecked")
	public Object fromXMLString(String xmlValue) {
        return Enum.valueOf(enumClass, xmlValue);
    }


	@SuppressWarnings("rawtypes")
	public String objectToSQLString(Object value) {
        return '\'' + ( (Enum) value ).name() + '\'';
    }


	@SuppressWarnings("rawtypes")
	public String toXMLString(Object value) {
        return ( (Enum) value ).name();
    }

    @SuppressWarnings("unchecked")
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
            throws SQLException 
    {
        String name = rs.getString( names[0] );
       
        return rs.wasNull() ? null : Enum.valueOf(enumClass, name);
    }

    
	@SuppressWarnings("rawtypes")
	public void nullSafeSet(PreparedStatement st, Object value, int index)
            throws SQLException 
    {
    
    	if (value == null) {
            st.setNull(index, StringType.INSTANCE.sqlType() );
        } else {
            st.setString( index, ( (Enum) value ).name() );
        }
    }

}