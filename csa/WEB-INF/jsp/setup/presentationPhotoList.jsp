<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>


	<d:table name="${presentationPhotos}"  class="list" uid="row" pagesize="3" sort="list" requestURI="" export="false" excludedParams="*">
		<d:column title="Nombre" sortable="true" >
 		    <s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
				<s:param name="downloadFileAction" value="%{downloadFileAction}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.name}" />
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
 		 	<s:url id="url" action="%{downloadFileAction}_getFile" namespace="/setup" escapeAmp="false">
				<s:param name="fileName" value="%{#attr.row.name}" />
				<s:param name="contentType" value="%{#attr.row.contentType}" />					 
			</s:url>

	 		<img src='<s:property value="%{#url}" />' width="192" height="144" class="ui-corner-all" />
 		</d:column>
    		
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    			<s:param name="downloadFileAction" value="%{downloadFileAction}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
    			<s:url id="iconUrl" value="/icons/edit.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title="Editar Foto"/>
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
   				<s:param name="downloadFileAction" value="%{downloadFileAction}" />
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