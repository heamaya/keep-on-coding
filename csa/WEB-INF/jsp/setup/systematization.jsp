	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:div cssClass="title">
		Sistematizaciones
	</s:div>

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:url id="url"  action="%{actionClass}_add" namespace="/setup" />
				<sj:a cssClass="listButton" href="%{#url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Agregar Sistematización' />
					</s:div>					
				</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_search" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/search.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Filtrar Sistematizaciones' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_listAll" namespace="/setup" />
    			<sj:a cssClass="listButton" href="%{url}" targets="result" button="true" requestType="GET" onCompleteTopics="completeRefresh">
					<s:url id="iconUrl" value="/icons/refresh.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title='Refrescar Listado de Sistematizaciones' />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>

	<sj:dialog id="currentDialog" width="800" modal="true" draggable="true" position="center" resizable="true" title="Sistematización" autoOpen="false" onBeforeTopics="beforeOpenCurrentDialog">
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
	
	<s:div id="quickLandFormDialog">
		<s:div id="quickLandFormDiv">
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
		<jsp:include page="systematizationList.jsp" />
	</s:div>
	
	<div id="systematizationMap">
		<div class="googleEarthContentPane" id="googleEarthContentPane">
			<div class="setupGoogleEarthTree">
				<div id="kmlTree" class="googleEarthKmlTree">
					<input type="checkbox" id="currentKmlLayers" checked="checked"><span id="currentKmlName"></span> 
				</div>
				<div id="displayOptionsTree" class="googleEarthKmlTree"></div>
				<div id="layersTree" class="googleEarthKmlTree"></div>
			</div>
			<div id="map3d" class="googleEarthMap"></div> 
		</div>
	</div>
	
	<script type="text/javascript">
		$(
			function() {
				var	googleEarthIE6 = "El complemento de Google Earth funciona en Internet Explorer 6, de todos modos te recomendamos que actualices tu versión de Internet Explorer o que uses Firefox, Chrome u Opera.";
			
				$("#currentKmlLayers").attr("disabled", "disabled");
			
				$("#currentKmlLayers").click(
					function(event) {
					
						if($(this).is(':checked')) {
							turnOnCurrentKmlObject();	
						} else {
							turnOffCurrentKmlObject();	
						}
					}
				);	
				
				if($.browser.msie && ($.browser.version).substring(0, 1) == "6") {
			
					$("div.systematizationMap").prepend(					
						'<div class="ui-widget">' +
	  						'<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">' +
	  							'<p class="paragraph"><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>' +
								'<strong>' + googleEarthIE6 + '</strong></p>' +
								'</div>' +
  							'</div>'
					);
				}
				
				init();
			}
		);
	</script>