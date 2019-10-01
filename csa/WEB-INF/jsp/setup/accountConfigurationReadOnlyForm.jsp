	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	
	<s:form id="currentReadOnlyForm">
		<s:textfield label="Nombre de Usuario" name="username" readonly="true" required="false" maxlength="256" size="40" />
		<s:textfield label="Apellido" name="lastName" readonly="true" required="false" maxlength="256"size="40" />
		<s:textfield label="Nombre" name="firstName" readonly="true" required="false" maxlength="256" size="40" />
		<s:textfield label="Provincia" value="%{province.name}" readonly="true" maxlength="50" size="40" />
		<s:textfield label="Ciudad" value="%{city.name}" readonly="true" maxlength="50" size="40" />
		<s:textfield label="Calle" name="street" readonly="true" maxlength="512" size="40" />
		<s:textfield label="Número" name="streetNumber" readonly="true" maxlength="10" size="40" />
		<s:textfield label="Piso" name="floor" readonly="true" maxlength="3" size="40" />
		<s:textfield label="Departamento" name="department" readonly="true" maxlength="255" size="40" />	
		<s:textfield name="primaryTelephoneNumber" label="Teléfono Primario" name="primaryTelephoneNumber" readonly="true" required="false" maxlength="50" size="40" />
		<s:textfield label="Teléfono Secundario" name="secondaryTelephoneNumber" readonly="true" required="false" maxlength="50" size="40" />
		<s:textfield label="Celular Primario" name="primaryCellPhoneNumber" readonly="true" required="false" maxlength="50" size="40" />
		<s:textfield label="Celular Secundario" name="secondaryCellPhoneNumber" readonly="true" required="false" maxlength="50" size="40" />
		<s:textfield label="Correo Electrónico Secundario" name="secondaryEmail" readonly="true" required="false" maxlength="512" size="40" />
		<s:textarea label="Comentario" name="comment" readonly="true" required="false" rows="5" cols="56" /> 
	</s:form>