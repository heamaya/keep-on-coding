	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${payments}"  class="list" uid="row" pagesize="1" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		 <d:column title="" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
				Empresa: <s:property value="%{#attr.row.systematizationProject.systematization.company.name}" /> <br />
    			Campo: <s:property value="%{#attr.row.systematizationProject.systematization.land.name}" /> <br />
    			Fecha del Contrato: <s:property value="%{#attr.row.systematizationProject.contract.contractDate}" /> <br />
    			Fecha de Pago: <s:property value="%{#attr.row.paymentDate}" />
    		</sj:a> 
 		</d:column>
 		
 		<d:column title="Cuota" sortable="true">
			<s:property value='%{#attr.row.feeNumber}' />
 		</d:column>
 		
 		<d:column title="Quintales Pagados" sortable="true">
 			<s:if test="#attr.row.quintalsPaid != null && #attr.row.quintalsPaid != '' ">
 				<s:property value='%{getText("format.number", {#attr.row.quintalsPaid})}' />
 			</s:if>
 			<s:else>
 				&nbsp;
 			</s:else> 			
 		</d:column>
 		
 		<d:column title="Precio de la Soja por Quintal" sortable="true">
 		 	<s:if test="#attr.row.soyaPriceByQuintal != null && #attr.row.soyaPriceByQuintal != '' ">
 				<s:property value='%{getText("format.number", {#attr.row.soyaPriceByQuintal})}' />
 			</s:if>
 			<s:else>
 				&nbsp;
 			</s:else>
 		</d:column>
 		
 		
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#editIconUrl}" />' title='Editar Pago' />
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#removeIconUrl}" />' title='Eliminar Pago" />' />
				</s:div>
			</sj:a>
    	</d:column>		
	</d:table>
	<jsp:include page="pagination.jsp"></jsp:include>