	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:set name="singularTitle">Foto Consorcio <s:property value="%{consortiumName}"/></s:set>
	<s:set name="pluralTitle">Fotos Consorcio <s:property value="%{consortiumName}"/></s:set>

	<s:div cssClass="title">
		<s:property value="pluralTitle" />  
	</s:div>

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:url id="url"  action="%{actionClass}_add" namespace="/setup">
					<s:param name="consortiumId" value="%{#request.consortiumId}" />
				</s:url>
				<sj:a cssClass="listButton" href="%{#url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Foto"/>
					</s:div>					
				</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_list" namespace="/setup">
					<s:param name="consortiumId" value="%{#request.consortiumId}" />
				</s:url>
				
    			<sj:a cssClass="listButton" href="%{url}" targets="result" button="true" requestType="GET" onCompleteTopics="completeRefresh">
					<s:url id="iconUrl" value="/icons/refresh.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title="Refrescar Listado de Fotos" />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:url id="url" action="ConsortiumAction" namespace="/setup" />
					
   				<s:a id="backButton" href="%{url}" cssClass="listButton">
					<s:url id="iconUrl" value="/icons/back.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Volver a Consorcios' />
					</s:div>					
   				</s:a>
   				<script type="text/javascript">
   					$(
   					   function() {
   					      $("#backButton").button({});
   					   }
   					);
   				</script>
			</td>
			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>

	<sj:dialog id="currentDialog" width="750" modal="true" draggable="true" position="center" resizable="true" title="%{#singularTitle}" autoOpen="false" onBeforeTopics="beforeOpenCurrentDialog">
		<s:div id="currentFormDiv">
		</s:div>
	</sj:dialog>

	<s:div id="result">
		<jsp:include page="consortiumPhotoList.jsp" />
	</s:div>