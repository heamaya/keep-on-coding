<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>


	<d:table name="${photos}"  class="list" uid="row" pagesize="3" sort="list" requestURI="" export="false" excludedParams="requestId mappedRequest actionMethod descriptionSpanish descriptionPortuguese descriptionEnglish">
		
		<d:column title="Nombre" sortable="true" >
 		    <s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
				<s:param name="systematizationId" value="%{#request.systematizationId}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.systematizationPhotoFileName}" />
    		</sj:a> 
 		</d:column>
 		
 		 <d:column title="Descripción Español" sortable="true" >
   			<s:property value="%{#attr.row.descriptionSpanish}" />
 		</d:column>
 		
		<d:column title="Descripción Portugués" sortable="true" >
   			<s:property value="%{#attr.row.descriptionPortuguese}" />
 		</d:column>
 		
 		 <d:column title="Descripción Inglés" sortable="true" >
   			<s:property value="%{#attr.row.descriptionEnglish}" />
 		</d:column>
 		
 		<d:column>
 		
			<s:if test="actionClass == 'SystematizationBeforePhotoAction'">
	 			<s:url id="url" action="SystematizationBeforePhotoDownloadFileAction_getFile" namespace="/setup" escapeAmp="false">
					<s:param name="fileName" value="%{#attr.row.systematizationPhotoFileName}" />
					<s:param name="contentType" value="%{#attr.row.systematizationPhotoContentType}" />					
					<s:param name="systematizationId" value="%{#attr.row.systematization.id}" /> 
				</s:url>
			</s:if>
 			<s:else>
 				<s:url id="url" action="SystematizationAfterPhotoDownloadFileAction_getFile" namespace="/setup" escapeAmp="false">
					<s:param name="fileName" value="%{#attr.row.systematizationPhotoFileName}" />
					<s:param name="contentType" value="%{#attr.row.systematizationPhotoContentType}" />					
					<s:param name="systematizationId" value="%{#attr.row.systematization.id}" /> 
				</s:url>
 			</s:else>
 		
	 		<img src='<s:property value="%{#url}" />' width="192" height="144" class="ui-corner-all" />
 		</d:column>
    		
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    			<s:param name="systematizationId" value="%{#request.systematizationId}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
    			<s:url id="iconUrl" value="/icons/edit.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title="Editar Foto"/>
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
   				<s:param name="systematizationId" value="%{#request.systematizationId}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:url id="iconUrl" value="/icons/remove.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title="Remover Foto" />
				</s:div>
			</sj:a> 
    	</d:column>		
	</d:table> 
	<jsp:include page="pagination.jsp"></jsp:include>