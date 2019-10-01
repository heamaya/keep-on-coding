<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
<%@ taglib prefix="security" uri='http://www.springframework.org/security/tags' %>

	<s:url id="iconUrl" value="/icons/remove.png" />

	<d:table name="${visitorMessages}"  class="list" uid="row" pagesize="20" sort="list" requestURI="" export="false" excludedParams="*">
	 		
		<d:column title="Fecha" sortable="true" >
   			<s:date name="%{#attr.row.created}"  format="dd/MM/yyyy hh:mm:ss a" />
 		</d:column>
	
		<d:column title="Nombre" sortable="true" >
 		    <s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.firstName}" /> <s:property value="%{#attr.row.lastName}" />
    		</sj:a> 
 		</d:column>

 		 <d:column title="Correo Electrónico" sortable="true" >
   			<s:property value="%{#attr.row.email}" />
 		</d:column>
 		
 		 <d:column title="Provincia" sortable="true" >
   			<s:property value="%{#attr.row.province.name}" />
 		</d:column>
 		
 		<d:column title="Ciudad" sortable="true" >
   			<s:property value="%{#attr.row.city.name}" />
 		</d:column>
 		
 		 <d:column title="Mensaje" sortable="true" >
   			<s:property value="%{#attr.row.message}" />
 		</d:column>
    	<security:authorize ifAllGranted="USERS">
  			<d:column title="">
  			
				<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
	   				<s:param name="requestId" value="%{#attr.row.id}" />
				</s:url>
				<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title="Eliminar Mensaje" />
					</s:div>
				</sj:a>
    		</d:column>
    	</security:authorize>		
	</d:table>
	<jsp:include page="pagination.jsp"></jsp:include>