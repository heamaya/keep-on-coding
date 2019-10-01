	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${dikes}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		 <d:column title="" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
				Empresa: <s:property value="%{#attr.row.systematizationProject.systematization.company.name}" /> <br />
    			Campo: <s:property value="%{#attr.row.systematizationProject.systematization.land.name}" /> <br />
    			Fecha de Contrato: <s:property value="%{#attr.row.systematizationProject.contract.contractDate}" /> <br />
    			Dique: <s:property value="%{#attr.row.name}" />
    		</sj:a> 
 		</d:column>
 		
 		<d:column title="Altura Máxima" sortable="true">
 			<s:if test="#attr.row.maximumHeight != null && #attr.row.maximumHeight != '' ">
 				<s:property value='%{getText("format.number", {#attr.row.maximumHeight})}' />
 			</s:if>
 			<s:else>
 				&nbsp;
 			</s:else> 			
 		</d:column>
 		
 		<d:column title="Ancho Máximo" sortable="true">
 			<s:if test="#attr.row.maximumWidth != null && #attr.row.maximumWidth != '' ">
 				<s:property value='%{getText("format.number", {#attr.row.maximumWidth})}' />
 			</s:if>
 			<s:else>
 				&nbsp;
 			</s:else> 			
 		</d:column>
 		
 		<d:column title="Longitud" sortable="true">
 		 	<s:if test="#attr.row.length != null && #attr.row.length != '' ">
 				<s:property value='%{getText("format.number", {#attr.row.length})}' />
 			</s:if>
 			<s:else>
 				&nbsp;
 			</s:else>
 		</d:column>
 		
 		 <d:column title="Talud" sortable="true">
 			<s:property value='%{#attr.row.dikeSlope.value}' />
 		</d:column>
 		
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#editIconUrl}" />' title='Editar Dique <s:property value="%{#attr.row.dikeName}" />' />
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#removeIconUrl}" />' title='Eliminar Dique <s:property value="%{#attr.row.dikeName}" />' />
				</s:div>
			</sj:a>
    	</d:column>		
	</d:table>
	<jsp:include page="pagination.jsp"></jsp:include>