	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>

	<d:table name="${lands}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
		
		<d:column title="Nombre" sortable="true" >
 		    <s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.name}" />
    		</sj:a> 
 		</d:column>
 		
 		 <d:column title="Empresa Agropecuaria" sortable="true" >
   			<s:property value="%{#attr.row.company.name}" />
 		</d:column>
 		
 		<d:column title="Superficie (Has)" sortable="true" >
 		
 			<s:if test="#attr.row.surfaceArea != null" >
 				<s:property value='%{getText("format.number", {#attr.row.surfaceArea})}' />
			</s:if>
			<s:else>
				<s:property value="%{#attr.row.surfaceArea}" />
			</s:else>	
 		
 		</d:column>
 		
 		<d:column title="Provincia" sortable="true" >
   			<s:property value="%{#attr.row.nearestProvince}" />
 		</d:column>

 		<d:column title="Ciudad Más Cercana" sortable="true" >
   			<s:property value="%{#attr.row.nearestCity}" />
 		</d:column>
 			
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
    			<s:url id="iconUrl" value="/icons/edit.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title='Editar Campo' />
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:url id="iconUrl" value="/icons/remove.png" />
				<s:div cssClass="button">
					<img src='<s:property value="%{#iconUrl}" />' title='Remover Campo' />
				</s:div>
			</sj:a> 
    	</d:column>		
	</d:table>
	<jsp:include page="pagination.jsp"></jsp:include>