	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>

	<s:div cssClass="title">
		Contáctenos - Mensajes de Visitantes
	</s:div>

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:url id="url" action="%{actionClass}_list" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="result" button="true" requestType="GET" onCompleteTopics="completeRefresh">
					<s:url id="iconUrl" value="/icons/refresh.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title="Refrescar Listado de Fotos" />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>

	<sj:dialog id="currentDialog" width="750" modal="true" draggable="true" position="center" resizable="true" title="Mensaje de Visitante" autoOpen="false" onBeforeTopics="beforeOpenCurrentDialog" onOpenTopics="onOpenCurrentDialog">
		<s:div id="currentFormDiv">
		</s:div>
	</sj:dialog>

	<s:div id="result">
		<jsp:include page="visitorMessageList.jsp" />
	</s:div>