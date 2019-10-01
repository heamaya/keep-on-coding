	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="photoIconUrl" value="/icons/afterPhoto-16x16.png" />
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${consortiums}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		 <d:column title="Nombre" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.name}" />
    		</sj:a> 
 		</d:column>

 		<d:column title="Fecha de Creación" sortable="true" >
   			<s:date name="%{#attr.row.creationDate}" format="dd/MM/yyyy" />
 		</d:column>
 		
 		 <d:column title="Es Consorcio Destacado (Se Muestra a los Visitantes)" sortable="false" >
   			<s:if test="#attr.row.starred == true">
   				Sí
   			</s:if>
   			<s:else>
   				No
   			</s:else>
 		</d:column>
 			
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#editIconUrl}" />' title='Editar Consorcio <s:property value="%{#attr.row.name}" />' />
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#removeIconUrl}" />' title='Eliminar Consorcio <s:property value="%{#attr.row.name}" />' />
				</s:div>
			</sj:a>
			<s:url id="url" action="ConsortiumPhotoAction" namespace="/setup" escapeAmp="false">
   				<s:param name="consortiumId" value="%{#attr.row.id}" />
			</s:url>
			<s:a cssClass="listButton photoButton" href="%{url}"> 
				<s:div cssClass="button">
					<img src='<s:property value="%{#photoIconUrl}" />' title='Editar Fotos de Consorcio <s:property value="%{#attr.row.name}" />' />
				</s:div>
			</s:a> 
    	</d:column>		
	</d:table>
	
	<jsp:include page="pagination.jsp"></jsp:include> 
	
	<script type="text/javascript">
		$(".photoButton").button({});
	</script>