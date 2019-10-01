	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>

	<d:table name="${videos}"  class="list" uid="row" pagesize="3" sort="list" requestURI="" export="false" excludedParams="*">
		<d:column title="Nombre del Video" sortable="true" >
 		    <s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.nameSpanish}" />
    		</sj:a> 
			<br />
			<s:property value="%{#attr.row.namePortuguese}" />
			<br />
			<s:property value="%{#attr.row.nameEnglish}" />
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
 		
 		 <d:column title="Enlace Youtube (Ver en Youtube)" sortable="true" >
 		 	<s:set name="youTubeLink">http://www.youtube.com/watch?v=<s:property value="%{#attr.row.videoKey}" /></s:set>
 		  		 
 		 	<a href='<s:property value="%{#youTubeLink}" />' target="_blank"><s:property value="%{#youTubeLink}" /></a>
 		</d:column>
    		
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
    			<s:url id="iconUrl" value="/icons/edit.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title="Editar Video de Presentación"/>
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:url id="iconUrl" value="/icons/remove.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title="Remover Video de Presentación" />
				</s:div>
			</sj:a> 
    	</d:column>		
	</d:table>
	<jsp:include page="pagination.jsp"></jsp:include>