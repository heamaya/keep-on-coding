	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${journals}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		 <d:column title="Fecha Desde" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
				<s:date name="%{#attr.row.fromDate}" format="dd/MM/yyyy" />
    		</sj:a> 
 		</d:column>
 		
 		 <d:column title="Fecha Hasta" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
				<s:date name="%{#attr.row.toDate}" format="dd/MM/yyyy" />
    		</sj:a> 
 		</d:column>
 		
 		<d:column title="Total Ingresos" sortable="true">
 		 	<s:if test="#attr.row.incomesAmount != null">
 				<s:property value='%{getText("format.number", {#attr.row.incomesAmount})}' />
 			</s:if>
 			<s:else>
 				&nbsp;
 			</s:else>
 		</d:column>
 		
 		<d:column title="Total Egresos" sortable="true">
 		 	<s:if test="#attr.row.outcomesAmount != null">
 				<s:property value='%{getText("format.number", {#attr.row.outcomesAmount})}' />
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
					<img src='<s:property value="%{#editIconUrl}" />' title='Editar Libro Mensual' />
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#removeIconUrl}" />' title='Eliminar Libro Mensual' />
				</s:div>
			</sj:a>
    	</d:column>		
	</d:table>
	
	<jsp:include page="pagination.jsp"></jsp:include>