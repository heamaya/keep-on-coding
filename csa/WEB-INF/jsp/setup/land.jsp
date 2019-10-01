	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:div cssClass="title">
		Campos
	</s:div>

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:url id="url"  action="%{actionClass}_add" namespace="/setup" />
				<sj:a cssClass="listButton" href="%{#url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Agregar Campo' />
					</s:div>					
				</sj:a>
			</td>

			<td>
				<s:url id="url" action="%{actionClass}_search" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/search.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Filtrar Campos' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_listAll" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="result" button="true" requestType="GET" onCompleteTopics="completeRefresh">
					<s:url id="iconUrl" value="/icons/refresh.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Refrescar Listado de Campos' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>

	<sj:dialog id="currentDialog" 
	           width="800" 
	           modal="true" 
	           draggable="true" 
	           position="center" 
	           resizable="true" 
	           title="Campo" 
	           autoOpen="false" 
	           onBeforeTopics="beforeOpenCurrentDialog"
	           buttons=""
	>
	
		<s:div id="currentFormDiv">
		</s:div>
		
	</sj:dialog>
	
	<s:div id="quickCompanyFormDialog">
		<s:div id="quickCompanyFormDiv">
		</s:div>
	</s:div>
	
	<s:div id="quickNearestProvinceFormDialog">
		<s:div id="quickNearestProvinceFormDiv">
		</s:div>
	</s:div>
	
	<s:div id="quickNearestCityFormDialog">
		<s:div id="quickNearestCityFormDiv">
		</s:div>
	</s:div>
	
	<s:div id="quickProvinceFormDialog">
		<s:div id="quickProvinceFormDiv">
		</s:div>
	</s:div>
	
	<s:div id="quickCityFormDialog">
		<s:div id="quickCityFormDiv">
		</s:div>
	</s:div>

	<s:div id="result">
		<jsp:include page="landList.jsp" />
	</s:div>