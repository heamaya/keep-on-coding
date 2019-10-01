	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${journalUtilityList}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
		<d:column title="Fecha" sortable="true" >
			<s:date name="%{#attr.row.created}" format="dd/MM/yyyy" />
 		</d:column>
	
 		<d:column title="Nombre Archivo Cierres" sortable="true" >
			<s:property value="%{#attr.row.journalFileName}" /> 
 		</d:column>
 		
 		<d:column title="Descripción" sortable="true" >
			<s:property value="%{#attr.row.description}" />
 		</d:column>
	</d:table>
	
	<jsp:include page="pagination.jsp"></jsp:include>