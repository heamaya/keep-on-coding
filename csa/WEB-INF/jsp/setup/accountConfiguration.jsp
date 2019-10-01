	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>

	<s:div cssClass="title">
		Configuración de mi Cuenta
	</s:div>

	<table class="accountConfigurationMenu">
		<tr>
			<td>
				<s:url id="url"  action="%{actionClass}_edit" namespace="/setup" />
				<sj:a cssClass="listButton" href="%{#url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/edit.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Editar Mi Cuenta' />
					</s:div>					
				</sj:a>
			</td>

			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>
	
	<s:div id="result" cssClass="accountConfiguration">
		<jsp:include page="accountConfigurationReadOnlyForm.jsp" />
	</s:div>
	
	<s:div id="quickProvinceFormDialog">
		<s:div id="quickProvinceFormDiv">
		</s:div>
	</s:div>
	
	<s:div id="quickCityFormDialog">
		<s:div id="quickCityFormDiv">
		</s:div>
	</s:div>

	<sj:dialog id="currentDialog" width="750" modal="true" draggable="true" position="center" resizable="true" title="Editar Configuración de mi Cuenta" autoOpen="false" onBeforeTopics="beforeOpenCurrentDialog">
		<s:div id="currentFormDiv">
		</s:div>
	</sj:dialog>