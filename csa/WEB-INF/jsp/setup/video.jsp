	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>

	<s:div cssClass="title">
		Videos de Presentación
	</s:div>

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:url id="url"  action="%{actionClass}_add" namespace="/setup" />
				<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Agregar Video' />
					</s:div>					
				</sj:a>
			</td>
			<!-- 
			<td>
				<s:url id="url" action="%{actionClass}_search" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/search.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Filtrar Noticias'/>
					</s:div>					
    			</sj:a>
			</td>
			 -->
			<td>
				<s:url id="url" action="%{actionClass}_list" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="result" button="true" requestType="GET" onCompleteTopics="completeRefresh">
					<s:url id="iconUrl" value="/icons/refresh.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Refrescar Listado de Videos' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>

	<sj:dialog id="currentDialog" width="750" modal="true" draggable="true" position="center" resizable="true" title="Video de Presentación" autoOpen="false" onBeforeTopics="beforeOpenCurrentDialog" onOpenTopics="onOpenCurrentDialog" zindex="1">
		<s:div id="currentFormDiv">
		</s:div>
	</sj:dialog>

	<s:div id="result">
		<jsp:include page="videoList.jsp" />
	</s:div>