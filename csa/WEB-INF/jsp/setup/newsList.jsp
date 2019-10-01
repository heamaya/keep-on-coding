	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>

	<d:table name="${news}"  class="list" uid="row" pagesize="3" sort="list" requestURI="" export="false" excludedParams="*">
		<d:column title="Título" sortable="true" >
 		    <s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.titleSpanish}" />
    		</sj:a> 
    		<br />
			<s:property value="%{#attr.row.titlePortuguese}" />
    		<br />
			<s:property value="%{#attr.row.titleEnglish}" />
 		</d:column>
 		
 		 <d:column title="Fecha" sortable="true" >
   			<s:date name="%{#attr.row.created}" format="dd/MM/yyyy hh:mm:ss a" />
 		</d:column>
 		
 		 <d:column title="Cuerpo Español" sortable="false" >
 		 	<div class="paragraph">
   				<s:property value="%{#attr.row.bodySpanish}" escapeHtml="false" />
   			</div>
 		</d:column>
 		
 		 <d:column title="Cuerpo Portugués" sortable="false" >
 		 	<div class="paragraph">
   				<s:property value="%{#attr.row.bodyPortuguese}" escapeHtml="false" />
   			</div>
 		</d:column>
 		
 		 <d:column title="Cuerpo Inglés" sortable="false" >
 		 	<div class="paragraph">
   				<s:property value="%{#attr.row.bodyEnglish}" escapeHtml="false" />
   			</div>
 		</d:column>
 		
 		<d:column>
 			<s:if test="#attr.row.imageFileName != null && #attr.row.imageFileName != ''">
 				
 				<s:url id="url" action="NewsPresentationPhotoDownloadFileAction_getFile" namespace="/setup" escapeAmp="false">
					<s:param name="fileName" value="%{#attr.row.imageFileName}" />
					<s:param name="contentType" value="%{#attr.row.imageContentType}" />					 
				</s:url>
 			
		 		<img src='<s:property value="%{#url}" />' width="192" height="144" class="ui-corner-all" />
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
    			<s:url id="iconUrl" value="/icons/edit.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title="Editar Noticia"/>
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:url id="iconUrl" value="/icons/remove.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title="Remover Noticia" />
				</s:div>
			</sj:a> 
    	</d:column>		
	</d:table> 
	<jsp:include page="pagination.jsp"></jsp:include>