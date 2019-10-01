	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${systematizationProjects}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		<d:column title="Proyecto de Sistematización" sortable="true" >
 			<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
				Empresa: <s:property value="%{#attr.row.systematization.company.name}" /> <br />
    			Campo: <s:property value="%{#attr.row.systematization.land.name}" /> <br />
    			Fecha de Contrato: <s:property value="%{#attr.row.contract.contractDate}" />
    		</sj:a> 
 		</d:column>
 		
 		<d:column title="Hectáreas Contratadas" sortable="true">
 			<s:property value='%{getText("format.number", {#attr.row.contract.hiredHectares})}' />
 		</d:column>
 		
 		<d:column title="Cuotas Anuales" sortable="true">
 			<s:property value="%{#attr.row.contract.fees}" />
 		</d:column>
 		
 		<d:column title="Quintales por Hectárea" sortable="true">
 			<s:property value='%{getText("format.number", {#attr.row.contract.quintalsPerHectare})}' />
 		</d:column>
 		
 		<d:column title="Quintales a Pagar" sortable="true">
 			<s:property value='%{getText("format.number", {#attr.row.contract.quintalsToPay})}' />
 		</d:column>

  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#editIconUrl}" />' title='Editar Projecto de Sistematización' />
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#removeIconUrl}" />' title='Eliminar Proyecto de Sistematización' />
				</s:div>
			</sj:a>
    	</d:column>		
	</d:table>
	
	<jsp:include page="pagination.jsp"></jsp:include>