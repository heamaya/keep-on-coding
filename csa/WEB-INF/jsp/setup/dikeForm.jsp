	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		
 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
 			<s:select id="dikeSystematizationProject" list="systematizationProjects" label="Proyecto de Sistematización" listKey="id" listValue="summary" value="systematizationProject.id" name="systematizationProject" emptyOption="true" required="true" />	
			<s:textfield id="dikeName" name="name" label="Nombre del Dique" required="true" maxlength="255" />
					
 		 	<s:if test='maximumHeight == null || maximumHeight == ""'>
				<s:textfield id="dikeMaximumHeight" label="Altura Máxima (m)" name="maximumHeight" readonly="%{readOnly}" required="false" maxlength="100" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeMaximumHeight" label="Altura Máxima (m)" name="maximumHeight" value='%{getText("format.number", {maximumHeight})}' readonly="%{readOnly}" required="false" maxlength="100"  />
 			</s:else>
 			
 		 	<s:if test='groundVolume == null || groundVolume == ""'>
				<s:textfield id="dikeGroundVolume" label="Volumen de Tierra (m³)" name="groundVolume" readonly="%{readOnly}" required="false" maxlength="100" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeGroundVolume" label="Volumen de Tierra (m³)" name="groundVolume" value='%{getText("format.number", {groundVolume})}' readonly="%{readOnly}" required="false" maxlength="100"  />
 			</s:else>

 		 	<s:if test='slopeSurface == null || slopeSurface == ""'>
				<s:textfield id="dikeSlopeSurface" label="Superficie de Talud (m²)" name="slopeSurface" readonly="%{readOnly}" required="false" maxlength="100" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeSlopeSurface" label="Supeficie de Talud (m²)" name="slopeSurface" value='%{getText("format.number", {slopeSurface})}' readonly="%{readOnly}" required="false" maxlength="100"  />
 			</s:else>

 		 	<s:if test='baseSurface == null || baseSurface == ""'>
				<s:textfield id="dikeBaseSurface" label="Superficie de Coronamiento (m²)" name="baseSurface" readonly="%{readOnly}" required="false" maxlength="100" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeBaseSurface" label="Superficie de Coronamiento (m²)" name="baseSurface" value='%{getText("format.number", {baseSurface})}' readonly="%{readOnly}" required="false" maxlength="100"  />
 			</s:else>
 			
 		 	<s:if test='length == null || length == ""'>
				<s:textfield id="dikeLength" label="Longitud (m)" name="length" readonly="%{readOnly}" required="false" maxlength="100" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeLength" label="Longitud (m)" name="length" value='%{getText("format.number", {length})}' readonly="%{readOnly}" required="false" maxlength="100"  />
 			</s:else>
 			
 		 	<s:if test='maximumWidth == null || maximumWidth == ""'>
				<s:textfield id="dikeMaximumWidth" label="Ancho máximo (m)" name="maximumWidth" readonly="%{readOnly}" required="false" maxlength="100" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeMaximumWidth" label="Ancho máximo (m)" name="maximumWidth" value='%{getText("format.number", {maximumWidth})}' readonly="%{readOnly}" required="false" maxlength="100"  />
 			</s:else>
			
			<s:select id="dikeWorkState" label="Estado del Dique" name="workState" readonly="%{readOnly}" required="false" list="dikeWorkStates" listKey="id" listValue="value" value="workState.id" emptyOption="true" />
			
  		    <s:if test='workStateDate == null || workStateDate.projectedDate == null || workStateDate.projectedDate == ""'>
				<sj:datepicker id="dikeProjectedDate" label="Fecha de comienzo del Diseño" required="false" name="workStateDate.projectedDate" value="" changeYear="true" changeMonth="true" />
 			</s:if>
 			<s:else>
 				<sj:datepicker id="dikeProjectedDate" label="Fecha de comienzo del Diseño" required="false" name="workStateDate.projectedDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {workStateDate.projectedDate})}' />
 			</s:else>
 			
  		    <s:if test='workStateDate == null || workStateDate.inProgressDate == null || workStateDate.inProgressDate == ""'>
				<sj:datepicker id="dikeInProgressDate" label="Fecha de Comienzo de las Obras" required="false" name="workStateDate.inProgressDate" value="" changeYear="true" changeMonth="true" />
 			</s:if>
 			<s:else>
				<sj:datepicker id="dikeInProgressDate" label="Fecha de Comienzo de las Obras" required="false" name="workStateDate.inProgressDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {workStateDate.inProgressDate})}' />
 			</s:else>
 			
  		    <s:if test='workStateDate == null || workStateDate.finishedDate == null || workStateDate.finishedDate == ""'>
				<sj:datepicker id="dikeFinishedDate" label="Fecha de Finalización de las Obras" required="false" name="workStateDate.finishedDate" value="" changeYear="true" changeMonth="true" />
 			</s:if>
 			<s:else>
				<sj:datepicker id="dikeFinishedDate" label="Fecha de Finalización de las Obras" required="false" name="workStateDate.finishedDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {workStateDate.finishedDate})}' />
 			</s:else>
			
			<s:select id="dikeSlope" label="Talud por Metro de Altura" name="dikeSlope" readonly="%{readOnly}" required="false" list="dikeSlopes" listKey="id" listValue="value" value="dikeSlope.id" emptyOption="true" />

			<s:url id="addIconUrl" value="/icons/add.png" />
			<s:url id="removeAllIconUrl" value="/icons/removeAll.png" />
			
			<s:select id="dikeOutlets" label="Descargadores de fondo" name="outlets" list="outlets" listKey="toString()" listValue="summary" emptyOption="false" size="5" multiple="true" />

			<tr>
				<td class="tdLabel">
				</td>
				<td> 
					<s:div id="quickOutletActions">
						<s:div id="quickOutletAdd" cssClass="quickOutletButton button">
							<s:div>
								<img src='<s:property value="%{#addIconUrl}" />' title="Agregar Descargadores de Fondo" />
							</s:div>					
						</s:div>			
						<s:div id="quickOutletRemoveAll" cssClass="quickOutletButton button" >
							<s:url id="iconUrl" value="/icons/removeAll.png" />
							<s:div>
								<img src='<s:property value="%{#removeAllIconUrl}" />' title="Quitar Todos los Descargadores de Fondo" />
							</s:div>					
						</s:div>
					</s:div>
				</td>
			</tr> 
			
			<s:select id="dikeSpillways" label="Vertederos de Emergencia" name="spillways" required="false" list="spillways" listKey="toString()" listValue="summary" emptyOption="false" size="2" multiple="true" />
			
			<tr>
				<td class="tdLabel">
				</td>
				<td> 
					<s:div id="quickSpillwayActions">
						<s:div id="quickSpillwayAdd" cssClass="quickSpillwayButton button">
							<s:div>
								<img src='<s:property value="%{#addIconUrl}" />' title="Agregar Vertederos de Emergencia" />
							</s:div>					
						</s:div>			
						<s:div id="quickSpillwayRemoveAll" cssClass="quickSpillwayButton button" >
							<s:div>
								<img src='<s:property value="%{#removeAllIconUrl}" />' title="Quitar Todos los Vertederos de Emergencia" />
							</s:div>					
						</s:div>
					</s:div>
				</td>
			</tr>
		</s:if>
		<s:elseif test='actionMethod == "find"'>
			<s:select id="dikeSystematizationProject" list="systematizationProjects" label="Proyecto de Sistematización" listKey="id" listValue="summary" value="systematizationProject.id" name="systematizationProject" emptyOption="true" required="true" />	
			<s:textfield id="dikeName" name="name" label="Nombre del Dique" required="false" readonly="%{readOnly}" size="50" />
			<s:textfield id="dikeMaximumHeight" label="Altura Máxima (m)" name="maximumHeight" readonly="%{readOnly}" required="false" maxlength="100" size="50" />
			<s:textfield id="dikeGroundVolume" label="Volumen de Tierra (m³)" name="groundVolume" readonly="%{readOnly}" required="false" maxlength="100" size="50" />
			<s:textfield id="dikeSlopeSurface" label="Superficie de Talud (m²)" name="slopeSurface" readonly="%{readOnly}" required="false" maxlength="100" size="50" />
			<s:textfield id="dikeBaseSurface" label="Superficie de Coronamiento (m²)" name="baseSurface" readonly="%{readOnly}" required="false" size="50" />
			<s:textfield id="dikeLength" label="Longitud (m)" name="length" readonly="%{readOnly}" required="false" size="50" />
			<s:textfield id="dikeMaximumWidth" label="Ancho máximo (m)" name="maximumWidth" readonly="%{readOnly}" required="false" maxlength="100" size="50" />
			<s:select id="dikeWorkState" label="Estado del Dique" name="workState" readonly="%{readOnly}" required="false" list="dikeWorkStates" listKey="id" listValue="value" value="workState.id" emptyOption="true" />
			<sj:datepicker id="fromProjectedDate" label="Fecha de comienzo del Diseño Desde" required="false" name="fromToProjectedDate" changeYear="true" changeMonth="true" size="40"/>
			<sj:datepicker id="toProjectedDate" label="Fecha de comienzo del Diseño Hasta" required="false" name="fromToProjectedDate" changeYear="true" changeMonth="true" size="40" />
			<sj:datepicker id="fromInProgressDate" label="Fecha de Comienzo de las Obras Desde" required="false" name="fromToInProgressDate" changeYear="true" changeMonth="true" size="40"/>
			<sj:datepicker id="toInProgressDate" label="Fecha de Comienzo de las Obras Hasta" required="false" name="fromToInProgressDate" changeYear="true" changeMonth="true" size="40"/>
			<sj:datepicker id="fromFinishedDate" label="Fecha de Finalización de las Obras Desde" required="false" name="fromToFinishedDate" changeYear="true" changeMonth="true" size="40" />
			<sj:datepicker id="toFinishedDate" label="Fecha de Finalización de las Obras Hasta" required="false" name="fromToFinishedDate" changeYear="true" changeMonth="true" size="40" />			
			<s:select id="dikeSlope" label="Talud por Metro de Altura" name="dikeSlope" readonly="%{readOnly}" required="false" list="dikeSlopes" listKey="id" listValue="value" value="dikeSlope.id" emptyOption="true" />
		</s:elseif>
		<s:else>		
 			<s:textfield id="dikeSystematizationProject" label="Proyecto de Sistematización" value="%{systematizationProject.summary}" required="false" readonly="%{readOnly}" size="50" />	
			<s:textfield id="dikeName" value="%{name}" label="Nombre del Dique" required="false" readonly="%{readOnly}" size="50" />
					
 		 	<s:if test='maximumHeight == null || maximumHeight == ""'>
				<s:textfield id="dikeMaximumHeight" label="Altura Máxima (m)" value="" readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeMaximumHeight" label="Altura Máxima (m)" value='%{getText("format.number", {maximumHeight})}' readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:else>
 			
 		 	<s:if test='groundVolume == null || groundVolume == ""'>
				<s:textfield id="dikeGroundVolume" label="Volumen de Tierra (m³)" value="" readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeGroundVolume" label="Volumen de Tierra (m³)" value='%{getText("format.number", {groundVolume})}' readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:else>

 		 	<s:if test='slopeSurface == null || slopeSurface == ""'>
				<s:textfield id="dikeSlopeSurface" label="Superficie de Talud (m²)" value="" readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeSlopeSurface" label="Supeficie de Talud (m²)" value='%{getText("format.number", {slopeSurface})}' readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:else>

 		 	<s:if test='baseSurface == null || baseSurface == ""'>
				<s:textfield id="dikeBaseSurface" label="Superficie de Coronamiento (m²)" value="" readonly="%{readOnly}" required="false" size="50" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeBaseSurface" label="Superficie de Coronamiento (m²)" value='%{getText("format.number", {baseSurface})}' readonly="%{readOnly}" required="false" size="50" />
 			</s:else>
 			
 		 	<s:if test='length == null || length == ""'>
				<s:textfield id="dikeLength" label="Longitud (m)" value="" readonly="%{readOnly}" required="false" size="50" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeLength" label="Longitud (m)" value='%{getText("format.number", {length})}' readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:else>
 			
 		 	<s:if test='maximumWidth == null || maximumWidth == ""'>
				<s:textfield id="dikeMaximumWidth" label="Ancho máximo (m)" value="" readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeMaximumWidth" label="Ancho máximo (m)" value='%{getText("format.number", {maximumWidth})}' readonly="%{readOnly}" required="false" maxlength="100" size="50" />
 			</s:else>
			
			<s:textfield id="dikeWorkState" label="Estado del Dique" readonly="%{readOnly}" required="false" value="%{workState.value}" size="50" />
			
  		    <s:if test='workStateDate == null || workStateDate.projectedDate == null || workStateDate.projectedDate == ""'>
				<s:textfield id="dikeProjectedDate" label="Fecha de comienzo del Diseño" required="false" value="" readonly="%{readOnly}" size="50" />
 			</s:if>
 			<s:else>
 				<s:textfield id="dikeProjectedDate" label="Fecha de comienzo del Diseño" required="false" value='%{getText("format.date", {workStateDate.projectedDate})}' readonly="%{readOnly}" size="50" />
 			</s:else>
 			
  		    <s:if test='workStateDate == null || workStateDate.inProgressDate == null || workStateDate.inProgressDate == ""'>
				<s:textfield id="dikeInProgressDate" label="Fecha de Comienzo de la Obra" required="false" value="" readonly="%{readOnly}" size="50" />
 			</s:if>
 			<s:else>
				<s:textfield id="dikeInProgressDate" label="Fecha de Comienzo de la Obra" required="false" value='%{getText("format.date", {workStateDate.inProgressDate})}' readonly="%{readOnly}" size="50" />
 			</s:else>
 			
  		    <s:if test='workStateDate == null || workStateDate.finishedDate == null || workStateDate.finishedDate == ""'>
				<s:textfield id="dikeFinishedDate" label="Fecha de Finalización de la Obra" required="false" value="" readonly="%{readOnly}" size="50" />
 			</s:if>
 			<s:else>
				<sj:textfield id="dikeFinishedDate" label="Fecha de Finalización de la Obra" required="false" value='%{getText("format.date", {workStateDate.finishedDate})}' readonly="%{readOnly}" size="50" />
 			</s:else>
			
			<s:textfield id="dikeSlope" label="Talud por Metro de Altura" value="%{dikeSlope.value}" readonly="%{readOnly}" required="false" size="50" />
			
			<tr>
				<td class="tdLabel">
					<label for="outletsTable" class="label">
						Descargadores de Fondo:
					</label>
				</td>
				<td>
					<d:table id="outletsTable" name="${outlets}" class="list" uid="row" requestURI="" export="false" excludedParams="*" >
						<d:column title="Diámetro" sortable="true">
							<s:property value='%{getText("format.number", {#attr.row.diameter})}' />
						</d:column>
						<d:column title="Altura de Entrada" sortable="true">
							<s:property value='%{getText("format.number", {#attr.row.inletHeight})}' />
						</d:column>
						<d:column title="Altura de Salida" sortable="true">
							<s:property value='%{getText("format.number", {#attr.row.outletHeight})}' />
						</d:column>
					</d:table>
				</td>
			</tr>
			
			<tr>
				<td class="tdLabel">
					<label for="spillwaysTable" class="label">
						Vertederos de Emergencia:
					</label>
				</td>
				<td>
					<d:table id="spillwaysTable" name="${spillways}" class="list" uid="row" requestURI="" export="false" excludedParams="*">
						<d:column title="Longitud" sortable="true">
							<s:property value='%{getText("format.number", {#attr.row.length})}' />
						</d:column>
						<d:column title="Altura" sortable="true">
							<s:property value='%{getText("format.number", {#attr.row.height})}' />
						</d:column>
					</d:table>
				</td>
			</tr>
		</s:else>
		
		<jsp:include page="formSubmit.jsp" />
	</s:form>
		
	<script type="text/javascript">
			
		    $(function() {
	          	var actionMethod = $("#actionMethod").val();
	          	  	          	  
	  			if(actionMethod == "create" || actionMethod == "update") {
	  				
	  				$(".quickOutletButton, .quickSpillwayButton").button({});
	  				$("select").not("[multiple='multiple']").selectmenu({style:"dropdown", width:400});
	  				
	  				if(actionMethod == "create") {
	  					$("#quickOutletRemoveAll, #quickSpillwayRemoveAll").button("disable");	
	  				} else {
			  			var dikeOutletsCount = $('#dikeOutlets option').size();
			  			
			  			if(dikeOutletsCount > 0) {
			  				
				  			if(dikeOutletsCount == 5) {
				  				$("#quickOutletAdd").button("disable");
				  				$("#quickOutletRemoveAll").button("enable");
				  			} else {
			  					$("#quickOutletAdd").button("enable");
			  					$("#quickOutletRemoveAll").button("enable");
				  			}
				  			
	  						$('#dikeOutlets option').each(
	  	  						function(index, element) {
									element.selected = "selected";	
	  	  						}
							);
			  			} else {
			  				$("#quickOutletRemoveAll").button("disable");
			  				$("#quickOutletAdd").button("enable");
	  					}
			  			
			  			var dikeSpillwaysCount = $('#dikeSpillways option').size();
			  			
			  			if(dikeSpillwaysCount > 0) {
			  				
				  			if(dikeSpillwaysCount == 2) {
				  				$("#quickSpillwayAdd").button("disable");
				  				$("#quickSpillwayRemoveAll").button("enable");
				  			} else {
			  					$("#quickSpillwayAdd").button("enable");
			  					$("#quickSpillwayRemoveAll").button("enable");
				  			}
				  			
	  						$('#dikeSpillways option').each(
	  	  						function(index, element) {
									element.selected = "selected";	
	  	  						}
							);
			  			} else {
			  				$("#quickSpillwayRemoveAll").button("disable");
			  				$("#quickSpillwayAdd").button("enable");
	  					}
	  				
	  				}

					$("#currentForm").validate({
						submitHandler: function(form) {
							$(form).ajaxSubmit({
								url: $("#currentForm").attr("action"),
								type:"POST",
								target:$("#result"),
								beforeSubmit:function(arr, $form, options) {
			  						$('#dikeOutlets option').each(
				  						function(index, element) {
				  							element.selected = "selected";	
				  						}
				  					);
								},
								success: function() {
									$("#currentDialog").dialog("close");
								}		
							});
						}, invalidHandler: function(form, validator) {
							$("#currentDialog").parent().find('.ui-dialog-buttonpane button:first-child').button("enable").show();
			        	    $("#currentDialog").parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("enable").show();
			        	    $("#currentDialog").parent().find("#ajaxLoader").replaceWith("");
						}
					});
					
		  			$("#dikeSystematizationProject").rules("add", {required:true});
		  			$("#dikeName").rules("add", {required:true, maxlength:255});
		  			$("#dikeMaximumHeight").rules("add", {required:false, number:true, range: [0.25, 5], maxlength:4});
		  			$("#dikeGroundVolume").rules("add", {required:false, number:true, range: [0.01, 15000], maxlength:8});
		  			$("#dikeSlopeSurface").rules("add", {required:false, number:true, range: [0.01, 30000], maxlength:8});
		  			$("#dikeBaseSurface").rules("add", {required:false, number:true, range: [0.01, 30000], maxlength:8});
		  			$("#dikeLength").rules("add", {required:false, number:true, range: [0.01, 5000], maxlength:7});
		  			$("#dikeMaximumWidth").rules("add", {required:false, number:true, range: [0.01, 50], maxlength:5});
		  			$("#dikeProjectedDate").rules("add", {required:false, date:true});
					$("#dikeInProgressDate").rules("add", {required:false, date:true});
		  			$("#dikeFinishedDate").rules("add", {required:false, date:true});
		  			
		  			$("#quickOutletAdd").click(
		  				function(event) {
		  					var dikeOutletsCount = $('#dikeOutlets option').size();
		  					var outletsCountToAdd = '';
		  					
		  					for(var c = 1; c <= 5 - dikeOutletsCount; c++) {
		  						outletsCountToAdd += '<option value="' + c + '">' + c + '</option>';
		  					}
		  					
		  					$('<form id="outletsForm">').append(
		  						'<tr>' +
		  							'<td class="tdLabel">' +
		  								'<label for="outletDiameter" class="label">' +
		  									'Cantidad:' +
		  								'</label>' +
		  							'</td>' +
		  							'<td>' +
		  								'<select id="outletCount" name=outletCount>' +
		  									outletsCountToAdd +
		  								'</select>' +
		  							'</td>' +
		  						'</tr>'
		  					).append(
		  						'<tr>' +
		  							'<td class="tdLabel">' +
		  								'<label for="outletDiameter" class="label">' +
		  									'Diámetro (m):' +
		  								'</label>' +
		  							'</td>' +
		  							'<td>' +
		  								'<select id="outletDiameter" name=diameter>' +
		  									'<option value="1,00">1,00</option>' +
		  									'<option value="0,90">0,90</option>' +
		  									'<option value="0,80">0,80</option>' +
		  									'<option value="0,70">0,70</option>' +
		  									'<option value="0,60">0,60</option>' +
		  									'<option value="0,50">0,50</option>' +
		  								'</select>' +
		  							'</td>' +
		  						'</tr>'
		  					).append(
		  						'<tr>' +
		  							'<td class="tdLabel">' +
		  								'<label for="inletHeight" class="label">' +
		  									'Altura de Entrada (m):' +
		  								'</label>' +
		  							'</td>' +
		  							'<td>' +
	  									'<select id="inletHeight" name=inletHeight>' +
  											'<option value="0,00">0,00</option>' +
		  									'<option value="0,10">0,10</option>' +
  											'<option value="0,15">0,15</option>' +
  											'<option value="0,20">0,20</option>' +
  											'<option value="0,25">0,25</option>' +
  											'<option value="0,30">0,30</option>' +
  											'<option value="0,35">0,35</option>' +
  											'<option value="0,40">0,40</option>' +
  											'<option value="0,45">0,45</option>' +
  											'<option value="0,50">0,50</option>' +
  											'<option value="0,55">0,55</option>' +
  											'<option value="0,60">0,60</option>' +
  											'<option value="0,65">0,65</option>' +
  											'<option value="0,70">0,70</option>' +
  											'<option value="0,75">0,75</option>' +
  											'<option value="0,80">0,80</option>' +
  											'<option value="0,85">0,85</option>' +
  											'<option value="0,90">0,90</option>' +
  											'<option value="0,95">0,95</option>' +
  											'<option value="1,00">1,00</option>' +
  										'</select>' +
		  							'</td>' +
		  						'</tr>'
		  					).append(
		  						'<tr>' +
		  							'<td class="tdLabel">' +
		  								'<label for="outletHeight" class="label">' +
		  									'Altura de Salida (m):' +
		  								'</label>' +
		  							'</td>' +
		  							'<td>' +
	  									'<select id="outletHeight" name=outletHeight>' +
											'<option value="0,00">0,00</option>' +
	  										'<option value="0,10">0,10</option>' +
											'<option value="0,15">0,15</option>' +
											'<option value="0,20">0,20</option>' +
											'<option value="0,25">0,25</option>' +
											'<option value="0,30">0,30</option>' +
											'<option value="0,35">0,35</option>' +
											'<option value="0,40">0,40</option>' +
											'<option value="0,45">0,45</option>' +
											'<option value="0,50">0,50</option>' +
											'<option value="0,55">0,55</option>' +
											'<option value="0,60">0,60</option>' +
											'<option value="0,65">0,65</option>' +
											'<option value="0,70">0,70</option>' +
											'<option value="0,75">0,75</option>' +
											'<option value="0,80">0,80</option>' +
											'<option value="0,85">0,85</option>' +
											'<option value="0,90">0,90</option>' +
											'<option value="0,95">0,95</option>' +
											'<option value="1,00">1,00</option>' +
										'</select>' +
		  							'</td>' +
		  						'</tr>'
		  					).dialog(
		  						{
		  							autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:500,
									title:"Descargador de Fondo",
									open: function(event, ui) {
						    	          	  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $(this).dialog('destroy');
						    	          	  	  }
									    	  );
						    	          	  
						    	          	  $("select").not("[multiple='multiple']").selectmenu({style:"dropdown", width:400});
								    	      //$("#outletAjaxLoader").empty();
										  },
									buttons: {
										"Agregar": 
											function() {
												var options = '';
												var currentOptionsCount = 0;
								    		  
												$("#dikeOutlets option").each(
												    function(index, element) {
												    	options += '<option selected="selected" value="' + element.value + '">' + element.text + '</option>';
												    	currentOptionsCount += 1;
												    }
												);
														
												var newOptionValue = $('#outletDiameter').val() + ';' + 
												 $('#inletHeight').val() + ';' + 
												 $('#outletHeight').val();
							
												var newOptionText = 'Diámetro: ' + $('#outletDiameter').val() + ' (m) |' +
												' Altura Entrada: ' + $('#inletHeight').val() + ' (m) | ' +
												' Altura Salida: ' + $('#outletHeight').val() + ' (m)';
												
												var outletCount = $('#outletCount').val().trim(0, 1);
												
												var to = parseInt(outletCount) + currentOptionsCount;
												var from = currentOptionsCount + 1;
												
												for(var i = from; i <= to; i++) {
													options += '<option selected="selected" value="' + i + ";" + newOptionValue + '">' + newOptionText + '</option>';	
												}
							    	      		
							    	      		$("#dikeOutlets").html(options);
							    	      		
							    	      		$(this).remove("#outletsForm");
							    	      		$("#dikeOutlets").trigger("change");
							    	      		$(this).dialog("destroy");
							    		  	},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
									    		if($("#outlets").val() == "") {
									    			$("#quickOutletAdd").button("enable");
									    		} else {
									    			$(".quickOutletButton").button("enable");
									    		}
											}
										}
									}
								);
	  						}
	  					);
		  			
		  			$("#quickOutletRemoveAll").click(
				  		function(event) {
				  			$("#dikeOutlets").html("");
				  			$("#dikeOutlets").trigger("change");
				  		}
					);
		  			
		  			$("#dikeOutlets").change(
				  		function(event) {
				  			
				  			var dikeOutletsCount = $('#dikeOutlets option').size();
				  			
				  			if(dikeOutletsCount == 5) {
				  				$("#quickOutletAdd").button("disable");
				  				$("#quickOutletRemoveAll").button("enable");
				  			} else if(dikeOutletsCount == 0) {
				  				$("#quickOutletRemoveAll").button("disable");
				  				$("#quickOutletAdd").button("enable");
				  			} else {
				  				$("#quickOutletAdd").button("enable");
				  				$("#quickOutletRemoveAll").button("enable");
				  			}
				  			
				  		}
					);
		  			
		  			$("#dikeOutlets").click(
		  				function() {
		  					
		  					if($('#dikeOutlets option').size() > 0) {
		  						$('#dikeOutlets option').each(
		  							function(index, element) {
		  								element.selected = "selected";	
		  							}
		  						);
		  					}
		  				}
		  			);
		  			
				//Spillways
				

				$("#quickSpillwayAdd").click(function() {
					
				
  					var dikeSpillwaysCount = $('#dikeSpillways option').size();
  					var spillwaysCountToAdd = '';
  					
  					for(var c = 1; c <= 2 - dikeSpillwaysCount; c++) {
  						spillwaysCountToAdd += '<option value="' + c + '">' + c + '</option>';
  					}
  					
  					$('<form id="spillwaysForm">').append(
  						'<tr>' +
  							'<td class="tdLabel">' +
  								'<label for="spillwayCount" class="label">' +
  									'Cantidad:' +
  								'</label>' +
  							'</td>' +
  							'<td>' +
  								'<select id="spillwayCount" name=spillwayCount>' +
  									spillwaysCountToAdd +
  								'</select>' +
  							'</td>' +
  						'</tr>'
  					).append(
  						'<tr>' +
  							'<td class="tdLabel">' +
  								'<label for="spillwayLength" class="label">' +
  									'Longitud (m):' +
  								'</label>' +
  							'</td>' +
  							'<td>' +
  								'<select id="spillwayLength" name=length>' +
  									'<option value="1,00">1,00</option>' +
  									'<option value="2,00">2,00</option>' +
  									'<option value="3,00">3,00</option>' +
  									'<option value="4,00">4,00</option>' +
  									'<option value="5,00">5,00</option>' +
  									'<option value="6,00">6,00</option>' +
  									'<option value="7,00">7,00</option>' +
  									'<option value="8,00">8,00</option>' +
  									'<option value="9,00">9,00</option>' +
  									'<option selected="selected" value="10,00">10,00</option>' +
  									'<option value="11,00">11,00</option>' +
  									'<option value="12,00">12,00</option>' +
  									'<option value="13,00">13,00</option>' +
  									'<option value="14,00">14,00</option>' +
  									'<option value="15,00">15,00</option>' +
  									'<option value="16,00">16,00</option>' +
  									'<option value="17,00">17,00</option>' +
  									'<option value="18,00">18,00</option>' +
  									'<option value="19,00">19,00</option>' +
  									'<option value="20,00">20,00</option>' +
  								'</select>' +
  							'</td>' +
  						'</tr>'
  					).append(
  						'<tr>' +
  							'<td class="tdLabel">' +
  								'<label for="spillwayHeight" class="label">' +
  									'Altura (m):' +
  								'</label>' +
  							'</td>' +
  							'<td>' +
									'<select id="spillwayHeight" name=spillwayHeight>' +
										'<option value="0,05">0,05</option>' +
  										'<option value="0,10">0,10</option>' +
										'<option value="0,15">0,15</option>' +
										'<option value="0,20">0,20</option>' +
										'<option value="0,25">0,25</option>' +
										'<option value="0,30">0,30</option>' +
										'<option selected="selected" value="0,35">0,35</option>' +
										'<option value="0,40">0,40</option>' +
										'<option value="0,45">0,45</option>' +
										'<option value="0,50">0,50</option>' +
										'<option value="0,55">0,55</option>' +
										'<option value="0,60">0,60</option>' +
										'<option value="0,65">0,65</option>' +
										'<option value="0,70">0,70</option>' +
										'<option value="0,75">0,75</option>' +
										'<option value="0,80">0,80</option>' +
										'<option value="0,85">0,85</option>' +
										'<option value="0,90">0,90</option>' +
										'<option value="0,95">0,95</option>' +
										'<option value="1,00">1,00</option>' +
									'</select>' +
  							'</td>' +
  						'</tr>'
  					).dialog(
  						{
  							autoOpen:true, 
							stack:true, 
							zIndex:2, 
							position:"center", 
							draggable:true, 
							resizable:true, 
							modal:true,
							width:500,
							title: "Vertedero de Emergencia",
							open: function(event, ui) {
								$('.ui-dialog-titlebar-close').bind('click', 
							    	function(event) {
										$(this).dialog('destroy');
							     	}
							     );
				    	          	  
								$("select").not("[multiple='multiple']").selectmenu({style:"dropdown", width:400});
							},
							buttons: {
								"Agregar": 
									function() {
										var options = '';
										var currentOptionsCount = 0;
						    		  
										$("#dikeSpillways option").each(
										    function(index, element) {
										    	options += '<option selected="selected" value="' + element.value + '">' + element.text + '</option>';
										    	currentOptionsCount += 1;
										    }
										);
												
										var newOptionValue = $('#spillwayLength').val() + ';' + 
										 $('#spillwayHeight').val();
					
										var newOptionText = 'Longitud: ' + $('#spillwayLength').val() + ' (m) |' +
										' Altura: ' + $('#spillwayHeight').val() + ' (m)';
										
										var spillwayCount = $('#spillwayCount').val().trim(0, 1);
										
										var from = currentOptionsCount + 1;
										var to = parseInt(spillwayCount) + currentOptionsCount;
										
										for(var i = from; i <= to; i++) {
											options += '<option selected="selected" value="' + i + ";" + newOptionValue + '">' + newOptionText + '</option>';	
										}
					    	      		
					    	      		$("#dikeSpillways").html(options);
					    	      		
					    	      		$(this).remove("#spillwaysForm");
					    	      		$("#dikeSpillways").trigger("change");
					    	      		$(this).dialog("destroy");
					    		  	},
								"Cancelar":
									function() {
										$(this).dialog("destroy");
										
							    		if($("#spillways").val() == "") {
							    			$("#quickSpillwayAdd").button("enable");
							    		} else {
							    			$(".quickSpillwayButton").button("enable");
							    		}
									}
								}
							}
						);
				});
						
  			
  			$("#quickSpillwayRemoveAll").click(
		  		function(event) {
		  			$("#dikeSpillways").html("");
		  			$("#dikeSpillways").trigger("change");
		  		}
			);
  			
  			$("#dikeSpillways").change(
		  		function(event) {
		  			
		  			var dikeSpillwaysCount = $('#dikeSpillways option').size();
		  			
		  			if(dikeSpillwaysCount == 2) {
		  				$("#quickSpillwayAdd").button("disable");
		  				$("#quickSpillwayRemoveAll").button("enable");
		  			} else if(dikeSpillwaysCount == 0) {
		  				$("#quickSpillwayRemoveAll").button("disable");
		  				$("#quickSpillwayAdd").button("enable");
		  			} else {
		  				$("#quickSpillwayAdd").button("enable");
		  				$("#quickSpillwayRemoveAll").button("enable");
		  			}
		  			
		  		}
			);
  			
  			$("#dikeSpillways").trigger("change");
  			$("#dikeSpillways").click(
  				function() {
  					
  					if($('#dikeSpillways option').size() > 0) {
  						$('#dikeSpillways option').each(
  							function(index, element) {
  								element.selected = "selected";	
  							}
  						);
  					}
  				}
  			);

				} else if(actionMethod == "delete" || actionMethod == "find") {
					
					if(actionMethod == "find") {
						$("select").selectmenu({style:"dropdown", width:400});	
					}
					
					$("#currentForm").ajaxForm(
						{
							url: $("#currentForm").attr("action"),
							type:"POST",
							target:$("#result"),
							success: function() {
								$("#currentDialog").dialog("close");
							} 
						}
					);
				}

		});
	</script>