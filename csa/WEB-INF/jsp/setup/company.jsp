	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>

	<s:if test="actionClass == 'AnonymousCommercialSocietyAction'" >
		<s:set name="companyTitle">Sociedad Anónima</s:set>
		<s:set name="companiesTitle">Sociedades Anónimas</s:set>
		<s:set name="addCompanies">Agregar Sociedad Anónima</s:set>
		<s:set name="filterCompany">Filtrar Sociedades Anónimas</s:set>
		<s:set name="refreshCompany">Refrescar Listado de Sociedades Anónimas</s:set>
	</s:if>
	<s:elseif test="actionClass == 'LimitedResponsibilityCommercialSocietyAction'">
		<s:set name="companyTitle">Sociedad de Responsabilidad Limitada</s:set>
		<s:set name="companiesTitle">Sociedades de Responsabilidad Limitada</s:set>
		<s:set name="addCompanies">Agregar Sociedad de Responsabilidad Limitada</s:set>
		<s:set name="filterCompany">Filtrar Sociedades de Responsabilidad Limitada</s:set>
		<s:set name="refreshCompany">Refrescar Listado de Sociedades de Responsabilidad Limitada</s:set>
	</s:elseif>
	<s:elseif test="actionClass == 'FactCommercialSocietyAction'">
		<s:set name="companyTitle">Sociedad de Hecho</s:set>
		<s:set name="companiesTitle">Sociedades de Hecho</s:set>
		<s:set name="addCompanies">Agregar Sociedad de Hecho</s:set>
		<s:set name="filterCompany">Filtrar Sociedades de Hecho</s:set>
		<s:set name="refreshCompany">Refrescar Listado de Sociedades de Hecho</s:set>	
	</s:elseif>
	<s:elseif test="actionClass == 'LegalPersonAction'">
		<s:set name="companyTitle">Persona Legal</s:set>
		<s:set name="companiesTitle">Personas Legales</s:set>
		<s:set name="addCompanies">Agregar Persona Legal</s:set>
		<s:set name="filterCompany">Filtrar Personas Legales</s:set>
		<s:set name="refreshCompany">Refrescar Listado de Personas Legales</s:set>	
	</s:elseif>
	
	<s:div cssClass="title">
		<s:property value="companiesTitle" />
	</s:div>

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:url id="url"  action="%{actionClass}_add" namespace="/setup" />
				<sj:a cssClass="listButton" href="%{#url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='<s:property value="%{#addCompanies}" />' />
					</s:div>					
				</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_search" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/search.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='<s:property value="%{#filterCompany}" />' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_listAll" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="result" button="true" requestType="GET" onCompleteTopics="completeRefresh">
					<s:url id="iconUrl" value="/icons/refresh.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='<s:property value="%{#refreshCompany}" />' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>

	<sj:dialog id="currentDialog" width="850" modal="true" draggable="true" position="center" resizable="true" title="%{#companyTitle}" autoOpen="false" onBeforeTopics="beforeOpenCurrentDialog">
		<s:div id="currentFormDiv">
		</s:div>
	</sj:dialog>
	
	<s:div id="quickProvinceFormDialog">
		<s:div id="quickProvinceFormDiv">
		</s:div>
	</s:div>
	
	<s:div id="quickCityFormDialog">
		<s:div id="quickCityFormDiv">
		</s:div>
	</s:div>
	
	<s:div id="quickFarmerFormDialog">
		<s:div id="quickFarmerFormDiv">
		</s:div>
	</s:div>
		
	<s:div id="quickFarmerProvinceFormDialog">
		<s:div id="quickFarmerProvinceFormDiv">
		</s:div>
	</s:div>
	
	<s:div id="quickFarmerCityFormDialog">
		<s:div id="quickFarmerCityFormDiv">
		</s:div>
	</s:div>

	<s:div id="result">
		<jsp:include page="companyList.jsp" />
	</s:div>