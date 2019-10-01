	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:if test="actionClass == 'AnonymousCommercialSocietyAction'" >
		<s:set name="editCompany">Editar Sociedad Anónima</s:set>
		<s:set name="removeCompany">Remover Sociedad Anónima</s:set>
	</s:if>
	<s:elseif test="actionClass == 'LimitedResponsabilityCommercialSocietyAction'">
		<s:set name="editCompany">Editar Sociedad de Responsabilidad Limitada</s:set>
		<s:set name="removeCompany">Remover Sociedad de Responsabilidad Limitada</s:set>
	</s:elseif>
	<s:elseif test="actionClass == 'FactCommercialSocietyAction'">
		<s:set name="editCompany">Editar Sociedad de Hecho</s:set>
		<s:set name="removeCompany">Remover Sociedad de Hecho</s:set>	
	</s:elseif>
	<s:elseif test="actionClass == 'LegalPersonAction'">
		<s:set name="editCompany">Editar Persona Legal</s:set>
		<s:set name="removeCompany">Remover Persona Legal</s:set>	
	</s:elseif>

	<d:table name="${companies}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
		
		<d:column title="Nombre" sortable="true" >
 		    <s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.name}" />
    		</sj:a> 
 		</d:column>
 		
 		 <d:column title="CUIT" sortable="true" >
   			<s:property value="%{#attr.row.tributeKey}" />
 		</d:column>

 		 <d:column title="Condición frente a la AFIP" sortable="true" >
   			<s:property value="%{#attr.row.taxCondition.value}" />
 		</d:column>
 		
 		 <d:column title="Provincia" sortable="true" >
   			<s:property value="%{#attr.row.address.province.name}" />
 		</d:column>
 		
 		 <d:column title="Ciudad" sortable="true" >
   			<s:property value="%{#attr.row.address.city.name}" />
 		</d:column>
    		
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
    			<s:url id="iconUrl" value="/icons/edit.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title='<s:property value="%{#editCompany}" />' />
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:url id="iconUrl" value="/icons/remove.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title='<s:property value="%{#removeCompany}" />' />
				</s:div>
			</sj:a> 
    	</d:column>		
	</d:table>
	<jsp:include page="pagination.jsp"></jsp:include>