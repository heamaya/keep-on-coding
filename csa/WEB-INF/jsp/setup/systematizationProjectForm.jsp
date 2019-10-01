	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content" enctype="multipart/form-data; charset=UTF-8">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden key="previousFarmAreaFileName" value="%{previousFarmAreaFileName}" />
		
 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
 			<s:select id="systematization" list="systematizations" label="Sistematizaciones" listKey="id" listValue="summary" value="systematization.id" name="systematization" emptyOption="true" required="true" />
 			<jsp:include page="quickSystematization.jsp" />
 				
 			<sj:datepicker id="contractDate" label="Fecha del Contrato" required="true" name="contract.contractDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {contract.contractDate})}' size="40" />
 		
 		 	<s:if test='assessedHectares == null || assessedHectares == ""'>
				<s:textfield id="assessedHectares" label="Hectáreas Medidas" name="assessedHectares" readonly="%{readOnly}" required="true" maxlength="100" size="40"/>
 			</s:if>
 			<s:else>
 				<s:textfield id="assessedHectares" label="Hectáreas Medidas" name="assessedHectares" value='%{getText("format.number", {assessedHectares})}' readonly="%{readOnly}" required="true" maxlength="100" size="40"/>
 			</s:else>
 		
 			<s:textfield id="hiredHectares" label="Hectáreas Contratadas" name="contract.hiredHectares" value='%{getText("format.number", {contract.hiredHectares})}' readonly="%{readOnly}" required="true" maxlength="100" size="40"/>
 			<s:textfield id="fees" label="Cuotas Anuales" name="contract.fees" readonly="%{readOnly}" required="true" maxlength="100" value="%{contract.fees}" />
			<s:textfield id="quintalsPerHectare" label="Quintales por Hectárea" name="contract.quintalsPerHectare" value='%{getText("format.number", {contract.quintalsPerHectare})}' readonly="%{readOnly}" required="true" maxlength="100" size="40"/>
 						
			<s:if test="farmAreaFileName == null || farmAreaFileName == ''">
				<s:file id="farmArea" name="farmArea" label="Lotes a Sistematizar (KMZ o KML)" />
			</s:if>
			<s:else>
				<s:checkbox id="keepFarmArea" value="true" name="keepFarmArea" label="Mantener Archivo de Lotes a Sistematizar del Campo" />
				<s:file id="farmArea" name="farmArea" label="Lotes a Sistematizar del Campo (KMZ o KML)" />
			</s:else>
			
			<s:if test="terrace == null || terrace.groundVolume == null || terrace.groundVolume == ''">					
				<s:textfield id="groundVolume" label="Volumen de Tierra Movido Terrazas" name="terrace.groundVolume" value="" readonly="%{readOnly}" required="false" size="40"/>
			</s:if>
			<s:else>
				<s:textfield id="groundVolume" label="Volumen de Tierra Movido Terrazas" name="terrace.groundVolume" value='%{getText("format.number", {terrace.groundVolume})}' readonly="%{readOnly}" required="false" size="40"/>			
			</s:else>
			
			<s:if test="terrace == null || terrace.linearMeters == null || terrace.linearMeters == ''">					
				<s:textfield id="linearMeters" label="Metros Lineales de Terrazas" name="terrace.linearMeters" value="" readonly="%{readOnly}" required="false" size="40"/>
			</s:if>
			<s:else>
				<s:textfield id="linearMeters" label="Metros Lineales de Terrazas" name="terrace.linearMeters" value='%{getText("format.number", {terrace.linearMeters})}' readonly="%{readOnly}" required="false" size="40"/>			
			</s:else>
			
			<s:textfield id="designedChannelCount" label="Cantidad de Canales Diseñados" name="terrace.designedChannelCount" required="false" maxlength="100" value="%{terrace.designedChannelCount}" size="40"/>
			
			<s:select id="terraceWorkState" label="Estado de Terrazas" name="terrace.workState" readonly="%{readOnly}" required="false" list="terraceWorkStates" listKey="id" listValue="value" value="terrace.workState.id" emptyOption="true" />
			
  		    <s:if test='terrace == null || terrace.workStateDate == null || terrace.workStateDate.projectedDate == null || terrace.workStateDate.projectedDate == ""'>
				<sj:datepicker id="projectedDate" label="Fecha de comienzo del Diseño" required="false" name="terrace.workStateDate.projectedDate" value="" changeYear="true" changeMonth="true" size="40"/>
 			</s:if>
 			<s:else>
 				<sj:datepicker id="projectedDate" label="Fecha de comienzo del Diseño" required="false" name="terrace.workStateDate.projectedDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {terrace.workStateDate.projectedDate})}' size="40"/>
 			</s:else>
 			
  		    <s:if test='terrace == null || terrace.workStateDate == null || terrace.workStateDate.inProgressDate == null || terrace.workStateDate.inProgressDate == ""'>
				<sj:datepicker id="inProgressDate" label="Fecha de Comienzo de las Obras" required="false" name="terrace.workStateDate.inProgressDate" value="" changeYear="true" changeMonth="true" size="40"/>
 			</s:if>
 			<s:else>
				<sj:datepicker id="inProgressDate" label="Fecha de Comienzo de las Obras" required="false" name="terrace.workStateDate.inProgressDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {terrace.workStateDate.inProgressDate})}' size="40"/>
 			</s:else>
 			
  		    <s:if test='terrace == null || terrace.workStateDate == null || terrace.workStateDate.finishedDate == null || terrace.workStateDate.finishedDate == ""'>
				<sj:datepicker id="finishedDate" label="Fecha de Finalización de las Obras" required="false" name="terrace.workStateDate.finishedDate" value="" changeYear="true" changeMonth="true" size="40" />
 			</s:if>
 			<s:else>
				<sj:datepicker id="finishedDate" label="Fecha de Finalización de las Obras" required="false" name="terrace.workStateDate.finishedDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {terrace.workStateDate.finishedDate})}' size="40"/>
 			</s:else>
			
			<s:if test="gullyRecovery == null || gullyRecovery.gulliesSurface == null || gullyRecovery.gulliesSurface == ''">					
				<s:textfield id="gulliesSurface" label="Superficie de Cárcavas (Has)" name="gullyRecovery.gulliesSurface" readonly="%{readOnly}" required="false" value="" size="40" />	
			</s:if>
			<s:else>
				<s:textfield id="gulliesSurface" label="Superficie de Cárcavas (Has)" name="gullyRecovery.gulliesSurface" readonly="%{readOnly}" required="false" value='%{getText("format.number", {gullyRecovery.gulliesSurface})}' size="40" />
			</s:else>			
			
			<s:if test="gullyRecovery == null || gullyRecovery.recoveredSurface== null || gullyRecovery.recoveredSurface == ''">					
				<s:textfield id="recoveredSurface" label="Superficie Recuperada (Has)" name="gullyRecovery.recoveredSurface" readonly="%{readOnly}" required="false" value="" size="40" />	
			</s:if>
			<s:else>
				<s:textfield id="recoveredSurface" label="Superficie Recuperada (Has)" name="gullyRecovery.recoveredSurface" readonly="%{readOnly}" required="false" value='%{getText("format.number", {gullyRecovery.recoveredSurface})}' size="40" />
			</s:else>
		</s:if>
		<s:elseif test='actionMethod == "find"'>
			<s:select id="systematization" list="systematizations" label="Sistematizaciones" listKey="id" listValue="summary" name="systematization" emptyOption="true" required="false" />
 			<sj:datepicker id="fromContractDate" label="Fecha del Contrato Desde" required="false" name="fromToContractDate" changeYear="true" changeMonth="true" size="40" />
 			<sj:datepicker id="toContractDate" label="Fecha del Contrato Hasta" required="false" name="fromToContractDate" changeYear="true" changeMonth="true" size="40" />
 			<s:textfield id="assessedHectares" label="Hectáreas Medidas" name="assessedHectares" readonly="%{readOnly}" required="false" maxlength="100" size="40"/>
 			<s:textfield id="hiredHectares" label="Hectáreas Contratadas" name="contract.hiredHectares" value='%{getText("format.number", {contract.hiredHectares})}' readonly="%{readOnly}" required="false" maxlength="100" size="40"/>
 			<s:textfield id="fees" label="Cuotas Anuales" name="contract.fees" readonly="%{readOnly}" required="false" maxlength="100" value="%{contract.fees}" />
			<s:textfield id="quintalsPerHectare" label="Quintales por Hectárea" name="contract.quintalsPerHectare" value='%{getText("format.number", {contract.quintalsPerHectare})}' readonly="%{readOnly}" required="false" maxlength="100" size="40"/>					
			<s:textfield id="groundVolume" label="Volumen de Tierra Movido Terrazas" name="terrace.groundVolume" value="" readonly="%{readOnly}" required="false" size="40"/>
			<s:textfield id="linearMeters" label="Metros Lineales de Terrazas" name="terrace.linearMeters" value="" readonly="%{readOnly}" required="false" size="40"/>
			<s:textfield id="designedChannelCount" label="Cantidad de Canales Diseñados" name="terrace.designedChannelCount" required="false" maxlength="100" value="%{terrace.designedChannelCount}" size="40"/>			
			<s:select id="terraceWorkState" label="Estado de Terrazas" name="terrace.workState" readonly="%{readOnly}" required="false" list="terraceWorkStates" listKey="id" listValue="value" value="terrace.workState.id" emptyOption="true" />
			<sj:datepicker id="fromProjectedDate" label="Fecha de comienzo del Diseño Desde" required="false" name="fromToProjectedDate" changeYear="true" changeMonth="true" size="40" />
			<sj:datepicker id="toProjectedDate" label="Fecha de comienzo del Diseño Hasta" required="false" name="fromToProjectedDate" changeYear="true" changeMonth="true" size="40" />
			<sj:datepicker id="fromInProgressDate" label="Fecha de Comienzo de las Obras Desde" required="false" name="fromToInProgressDate" changeYear="true" changeMonth="true" size="40"/>
			<sj:datepicker id="toInProgressDate" label="Fecha de Comienzo de las Obras Hasta" required="false" name="fromToInProgressDate" changeYear="true" changeMonth="true" size="40"/>
			<sj:datepicker id="fromFinishedDate" label="Fecha de Finalización de las Obras Desde" required="false" name="fromToFinishedDate" changeYear="true" changeMonth="true" size="40" />
			<sj:datepicker id="toFinishedDate" label="Fecha de Finalización de las Obras Hasta" required="false" name="fromToFinishedDate" changeYear="true" changeMonth="true" size="40" />
			<s:textfield id="gulliesSurface" label="Superficie de Cárcavas (Has)" name="gullyRecovery.gulliesSurface" readonly="%{readOnly}" required="false" value="" size="40" />	
			<s:textfield id="recoveredSurface" label="Superficie Recuperada (Has)" name="gullyRecovery.recoveredSurface" readonly="%{readOnly}" required="false" value="" size="40" />	
		</s:elseif>
		<s:else>
			<s:textfield label="Empresa Agropecuaria" value="%{systematization.company.name}" required="false" maxlength="512" readOnly="%{readOnly}" size="40"/>
			<s:textfield label="Campo" value="%{systematization.land.name}" required="false" readOnly="%{readOnly}" maxlength="100" size="40"/>
			<s:textfield label="Fecha del Contrato" value='%{getText("format.date", {contract.contractDate})}' readOnly="%{readOnly}" required="false" maxlength="100" size="40"/>
			<s:textfield label="Hectáreas Medidas" value='%{getText("format.number", {assessedHectares})}' required="false" readonly="%{readOnly}" maxlength="100" size="40"/>
			<s:textfield label="Hectáreas Contratadas" value='%{getText("format.number", {contract.hiredHectares})}' readonly="%{readOnly}" required="false" maxlength="100" size="40"/>
			<s:textfield label="Cuotas Anuales" value="%{contract.fees}" readonly="%{readOnly}" readonly="%{readOnly}" required="false" maxlength="100" size="40"/>
			<s:textfield label="Quintales por Hectárea" value='%{getText("format.number", {contract.quintalsPerHectare})}'  readonly="%{readOnly}" required="false" maxlength="100" size="40"/>
			<s:textfield label="Deuda Total en Quintales" value='%{getText("format.number", {contract.quintalsToPay})}' readonly="%{readOnly}" required="false" maxlength="100" size="40"/>
			<s:textfield label="Quintales por Cuota" value='%{getText("format.number", {contract.quintalsPerFee})}' readonly="%{readOnly}" required="false" maxlength="100" size="40"/>
			
			<s:if test="terrace == null || terrace.groundVolume == null || terrace.groundVolume == ''">					
				<s:textfield label="Volumen de Tierra Movido Terrazas" value="" readonly="%{readOnly}" required="false" size="40"/>
			</s:if>
			<s:else>
				<s:textfield label="Volumen de Tierra Movido Terrazas" value='%{getText("format.number", {terrace.groundVolume})}' readonly="%{readOnly}" required="false" size="40"/>			
			</s:else>
			
			<s:if test="terrace == null || terrace.linearMeters == null || terrace.linearMeters == ''">					
				<s:textfield label="Metros Lineales de Terrazas" value="" readonly="%{readOnly}" required="false" size="40"/>
			</s:if>
			<s:else>
				<s:textfield label="Metros Lineales de Terrazas" value='%{getText("format.number", {terrace.linearMeters})}' readonly="%{readOnly}" required="false" size="40"/>			
			</s:else>
			
			<s:textfield id="designedChannelCount" label="Cantidad de Canales" name="%{terrace.designedChannelCount}" readonly="%{readOnly}" required="false" maxlength="100" value="%{terrace.designedChannelCount}" size="40" />
			<s:textfield label="Estado de Terrazas" value='%{terrace.workState.toString()}' readonly="%{readOnly}" required="false" size="40" />
			
			<s:if test='terrace == null || terrace.workStateDate == null || terrace.workStateDate.projectedDate == null || terrace.workStateDate.projectedDate == ""'>
				<s:textfield label="Fecha de comienzo del Diseño" required="false" value="" readonly="%{readOnly}" size="40"/>
 			</s:if>
 			<s:else>
				<s:textfield label="Fecha de comienzo del Diseño" required="false" value='%{getText("format.date", {terrace.workStateDate.projectedDate})}' readonly="%{readOnly}" size="40"/>
 			</s:else>
 			
  		    <s:if test='terrace == null || terrace.workStateDate == null || terrace.workStateDate.inProgressDate == null || terrace.workStateDate.inProgressDate == ""'>
				<s:textfield label="Fecha de Comienzo de las Obras" required="false" value="" readonly="%{readOnly}" size="40"/>
 			</s:if>
 			<s:else>
				<s:textfield label="Fecha de Comienzo de las Obras" required="false" value='%{getText("format.date", {terrace.workStateDate.inProgressDate})}' readonly="%{readOnly}" size="40"/>
 			</s:else>
 			
  		    <s:if test='terrace == null || terrace.workStateDate == null || terrace.workStateDate.finishedDate == null || terrace.workStateDate.finishedDate == ""'>
				<s:textfield label="Fecha de Finalización de las Obras" required="false" value="" readonly="%{readOnly}" size="40"/>
 			</s:if>
 			<s:else>
				<s:textfield label="Fecha de Finalización de las Obras" required="false" value='%{getText("format.date", {terrace.workStateDate.finishedDate})}' readonly="%{readOnly}" size="40"/>
 			</s:else>
			
			<s:if test="gullyRecovery == null || gullyRecovery.gulliesSurface == null || gullyRecovery.gulliesSurface == ''">					
				<s:textfield id="gulliesSurface" label="Superficie de Cárcavas (Has)" value="" readonly="%{readOnly}" required="false" size="40"/>	
			</s:if>
			<s:else>
				<s:textfield id="gulliesSurface" label="Superficie de Cárcavas (Has)" value="" readonly="%{readOnly}" required="false" value='%{getText("format.number", {gullyRecovery.gulliesSurface})}' size="40"/>
			</s:else>			
			
			<s:if test="gullyRecovery == null || gullyRecovery.recoveredSurface == null || gullyRecovery.recoveredSurface == ''">					
				<s:textfield id="recoveredSurface" label="Superficie Recuperada (Has)" value="" readonly="%{readOnly}" required="false" size="40"/>	
			</s:if>
			<s:else>
				<s:textfield id="recoveredSurface" label="Superficie Recuperada (Has)" readonly="%{readOnly}" required="false" value='%{getText("format.number", {gullyRecovery.recoveredSurface})}' size="40"/>
			</s:else>
			
		</s:else>
		
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
		
	<script type="text/javascript">
			
		    $(function() {
		    	
	          	var actionMethod = $("#actionMethod").val();
	          	  	          	  
	  			if(actionMethod == "create" || actionMethod == "update") {
	  				
		  			$("#farmArea").fileinput({
		  				buttonText: "Buscar",
		  				inputText: ""
		  			});
		  			
		  			$("select").selectmenu({style:"dropdown", width:400});
		  			
		  			if(actionMethod == "update") {
		  			
						$("#farmArea").attr("disabled", "disabled");
						$("#keepFarmArea").click(
							function(event) {
						
								if($(this).is(':checked')) {
									$("#farmArea").attr("disabled", "disabled");
								} else {
									$("#farmArea").removeAttr("disabled");
								}	
							}
						);	
		  			}
		  			
			 		if($("#systematization").val() == "") {
						$("#quickSystematizationEdit, #quickSystematizationShow").button("disable");
						$("#quickSystematizationAdd").button("enable");
					} else {
						$("#quickSystematizationAdd, #quickSystematizationEdit, #quickSystematizationShow").button("enable");
					} 
		  			
					$("#systematization").change(
						function(event) {
				 	         		    
				 			if($("#systematization").val() == "") {
				 		    	$("#quickSystematizationEdit, #quickSystematizationShow").button("disable");
				 		    } else {
				 		    	$("#quickSystematizationEdit, #quickSystematizationShow").button("enable");
				 		    } 
						}
					);
		  		
					$("#currentForm").validate({
						submitHandler: function(form) {
							$(form).ajaxSubmit({
								url: $("#currentForm").attr("action"),
								type:"POST",
								target:$("#result"),
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
					
		  			$("#systematization").rules("add", {required:true});
		  			$("#contractDate").rules("add",{required:true, date:true});
		  			$("#assessedHectares").rules("add", {required:true, number:true, range: [0.01, 10000], maxlength:8});
		  			$("#hiredHectares").rules("add",{required:true, number:true, range: [0.01, 10000], maxlength:8});
		  			$("#fees").rules("add", {required:true, digits:true, range: [1, 10], maxlength:2});
		  			$("#designedChannelCount").rules("add", {required:false, digits:true, range: [0, 100], maxlength:3});
		  			$("#quintalsPerHectare").rules("add", {required:true, number:true, range: [0.01, 10], maxlength:5});
		  			$("#farmArea").rules("add", {accept:"kmz|kml", messages: {accept:"Por favor, elige un valor aceptado de archivo google earth: kml, kmz."}});
		  			$("#groundVolume").rules("add", {required:false, number: true, range: [0.01, 100000], maxlength:9});
		  			$("#linearMeters").rules("add", {required:false, number: true, range: [0.01, 100000], maxlength:9});
		  			$("#projectedDate").rules("add", {required:false, date:true});
					$("#inProgressDate").rules("add", {required:false, date:true});
		  			$("#finishedDate").rules("add", {required:false, date:true});
		  			$("#gulliesSurface").rules("add", {required:false, number: true, range: [0.01, 10000], maxlength:8});
		  			$("#recoveredSurface").rules("add", {required:false, number: true, range: [0.01, 10000], maxlength:8});
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