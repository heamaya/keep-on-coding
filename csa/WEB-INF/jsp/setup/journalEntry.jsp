	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:if test="actionClass == 'JournalIncomeEntryAction'" >
		<s:set name="title">Ingresos</s:set>
		<s:set name="dialogTitle">Ingreso</s:set>
		<s:set name="addTitle">Agregar Ingreso</s:set>
		<s:set name="editTitle">Editar Ingreso</s:set>
		<s:set name="removeTitle">Remover Ingreso</s:set>
		<s:set name="filterTitle">Filtrar Ingreso</s:set>
		<s:set name="refreshTitle">Refrescar Listado de Ingresos</s:set>
	</s:if>
	<s:elseif test="actionClass == 'JournalOutcomeEntryAction'" >
		<s:set name="title">Egresos</s:set>
		<s:set name="dialogTitle">Egreso</s:set>
		<s:set name="addTitle">Agregar Egreso</s:set>
		<s:set name="editTitle">Editar Egreso</s:set>
		<s:set name="removeTitle">Remover Egreso</s:set>
		<s:set name="filterTitle">Filtrar Egreso</s:set>
		<s:set name="refreshTitle">Refrescar Listado de Egresos</s:set>
	</s:elseif>
	
	<s:div cssClass="title">
		<s:property value="#title" />
	</s:div>
	
	<s:url id="addIconUrl" value="/icons/add.png" />
	<s:url id="searchIconUrl" value="/icons/search.png" />

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:url id="url"  action="%{actionClass}_add" namespace="/setup" />
				<sj:a cssClass="listButton" href="%{#url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:div cssClass="button">
						<img src='<s:property value="%{#addIconUrl}" />' title='<s:property value="%{#addTitle}" />' />
					</s:div>					
				</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_search" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:div cssClass="button">
						<img src='<s:property value="%{#searchIconUrl}" />' title='<s:property value="%{#filterTitle}" />' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_listAll" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="result" button="true" requestType="GET" onCompleteTopics="completeRefresh">
					<s:url id="iconUrl" value="/icons/refresh.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='<s:property value="%{#refreshTitle}" />' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>

	<sj:dialog id="currentDialog" width="700" modal="true" draggable="true" position="center" resizable="true" title="%{#dialogTitle}" autoOpen="false" onBeforeTopics="beforeOpenCurrentDialog">
		<s:div id="currentFormDiv">
		</s:div>
	</sj:dialog>

	<s:div id="result">
		<jsp:include page="journalEntryList.jsp" />
	</s:div>