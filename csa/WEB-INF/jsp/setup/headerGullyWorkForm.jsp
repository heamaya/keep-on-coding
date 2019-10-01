	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		
 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
 			<s:select id="hgwSystematizationProject" list="systematizationProjects" label="Proyecto de Sistematización" listKey="id" listValue="summary" value="systematizationProject.id" name="systematizationProject" emptyOption="true" required="true" />	
			<s:select id="hgwType" label="Tipo de Obra" name="type" readonly="%{readOnly}" required="true" list="headerGullyWorkTypes" listKey="id" listValue="value" value="type.id" emptyOption="true"/>
			<s:textfield id="hgwName" label="Nombre" name="name" value='%{name}' readonly="%{readOnly}" required="true" maxlength="100" size="40" />
			<s:textfield id="hgwConcrete" label="Cantidad de Bolsas de Cemento" name="concrete" value='%{concrete}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:textfield id="hgwBrickCount" label="Cantidad de Ladrillos" name="brickCount" readonly="%{readOnly}" required="false" maxlength="100" value="%{brickCount}" size="40" />
			<s:textfield id="hgwBlockCount" label="Cantidad de Bloques" name="blockCount" readonly="%{readOnly}" required="false" maxlength="100" value="%{blockCount}" size="40" />
			<s:textfield id="hgwIron" label="Cantidad de Barras de Hierro" name="iron" readonly="%{readOnly}" required="false" maxlength="100" value="%{iron}" size="40" />
 			
 		 	<s:if test='ironThickness == null || ironThickness == ""'>
				<s:textfield id="hgwIronThickness" label="Grosor del Hierro (mm)" name="ironThickness" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="hgwIronThickness" label="Grosor del Hierro (mm)" name="ironThickness" value='%{getText("format.number", {ironThickness})}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:else>

 		 	<s:if test='gabions == null || gabions == ""'>
				<s:textfield id="hgwGabions" label="Gaviones (m³)" name="gabions" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="hgwGabions" label="Gaviones (m³)" name="gabions" value='%{getText("format.number", {gabions})}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:else>
			
			<s:select id="hgwWorkState" label="Estado de la Obra de Cabecera de Cárcava" name="workState" readonly="%{readOnly}" required="false" list="workStates" listKey="id" listValue="value" value="workState.id" emptyOption="true" />
			
  		    <s:if test='workStateDate == null || workStateDate.projectedDate == null || workStateDate.projectedDate == ""'>
				<sj:datepicker id="hgwProjectedDate" label="Fecha de comienzo del Diseño" required="false" name="workStateDate.projectedDate" value="" changeYear="true" changeMonth="true" size="40" />
 			</s:if>
 			<s:else>
 				<sj:datepicker id="hgwProjectedDate" label="Fecha de comienzo del Diseño" required="false" name="workStateDate.projectedDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {workStateDate.projectedDate})}' size="40" />
 			</s:else>
 			
  		    <s:if test='workStateDate == null || workStateDate.inProgressDate == null || workStateDate.inProgressDate == ""'>
				<sj:datepicker id="hgwInProgressDate" label="Fecha de Comienzo de las Obras" required="false" name="workStateDate.inProgressDate" value="" changeYear="true" changeMonth="true" size="40" />
 			</s:if>
 			<s:else>
				<sj:datepicker id="hgwInProgressDate" label="Fecha de Comienzo de las Obras" required="false" name="workStateDate.inProgressDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {workStateDate.inProgressDate})}' size="40" />
 			</s:else>
 			
  		    <s:if test='workStateDate == null || workStateDate.finishedDate == null || workStateDate.finishedDate == ""'>
				<sj:datepicker id="hgwFinishedDate" label="Fecha de Finalización de las Obras" required="false" name="workStateDate.finishedDate" value="" changeYear="true" changeMonth="true" size="40" />
 			</s:if>
 			<s:else>
				<sj:datepicker id="hgwFinishedDate" label="Fecha de Finalización de las Obras" required="false" name="workStateDate.finishedDate" changeYear="true" changeMonth="true" value='%{getText("format.date", {workStateDate.finishedDate})}' size="40" />
 			</s:else>
		</s:if>
		<s:elseif test='actionMethod == "find"'>
 			<s:select id="hgwSystematizationProject" list="systematizationProjects" label="Proyecto de Sistematización" listKey="id" listValue="summary" value="systematizationProject.id" name="systematizationProject" emptyOption="true" required="false" />	
			<s:select id="hgwType" label="Tipo de Obra" name="type" readonly="%{readOnly}" required="false" list="headerGullyWorkTypes" listKey="id" listValue="value" value="type.id" emptyOption="true"/>
			<s:textfield id="hgwName" label="Nombre" name="name" value='%{name}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:textfield id="hgwConcrete" label="Cantidad de Bolsas de Cemento" name="concrete" value='%{concrete}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:textfield id="hgwBrickCount" label="Cantidad de Ladrillos" name="brickCount" readonly="%{readOnly}" required="false" maxlength="100" value="%{brickCount}" size="40" />
			<s:textfield id="hgwBlockCount" label="Cantidad de Bloques" name="blockCount" readonly="%{readOnly}" required="false" maxlength="100" value="%{blockCount}" size="40" />
			<s:textfield id="hgwIron" label="Cantidad de Barras de Hierro" name="iron" readonly="%{readOnly}" required="false" maxlength="100" value="%{iron}" size="40" />
			<s:textfield id="hgwIronThickness" label="Grosor del Hierro (mm)" name="ironThickness" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:textfield id="hgwGabions" label="Gaviones (m³)" name="gabions" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:select id="hgwWorkState" label="Estado de la Obra de Cabecera de Cárcava" name="workState" readonly="%{readOnly}" required="false" list="workStates" listKey="id" listValue="value" value="workState.id" emptyOption="true" />
			<sj:datepicker id="fromProjectedDate" label="Fecha de comienzo del Diseño Desde" required="false" name="fromToProjectedDate" changeYear="true" changeMonth="true" size="40"/>
			<sj:datepicker id="toProjectedDate" label="Fecha de comienzo del Diseño Hasta" required="false" name="fromToProjectedDate" changeYear="true" changeMonth="true" size="40" />
			<sj:datepicker id="fromInProgressDate" label="Fecha de Comienzo de las Obras Desde" required="false" name="fromToInProgressDate" changeYear="true" changeMonth="true" size="40"/>
			<sj:datepicker id="toInProgressDate" label="Fecha de Comienzo de las Obras Hasta" required="false" name="fromToInProgressDate" changeYear="true" changeMonth="true" size="40"/>
			<sj:datepicker id="fromFinishedDate" label="Fecha de Finalización de las Obras Desde" required="false" name="fromToFinishedDate" changeYear="true" changeMonth="true" size="40" />
			<sj:datepicker id="toFinishedDate" label="Fecha de Finalización de las Obras Hasta" required="false" name="fromToFinishedDate" changeYear="true" changeMonth="true" size="40" />	
		</s:elseif>
		<s:else>		
 			<s:textfield id="hgwSystematizationProject" label="Proyecto de Sistematización" value="%{systematizationProject.summary}" required="false" readonly="%{readOnly}" size="40" />	
			<s:textfield id="hgwType" value="%{type.value}" label="Tipo de Obra" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield id="hgwName" label="Nombre" name="name" value='%{name}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:textfield id="hgwConcrete" label="Cantidad de Bolsas de Cemento" name="concrete" value='%{concrete}' readonly="%{readOnly}" required="false" maxlength="100" size="40"  />
			<s:textfield id="hgwBrickCount" label="Cantidad de Ladrillos" readonly="%{readOnly}" required="false" maxlength="100" value="%{brickCount}" size="40" />
			<s:textfield id="hgwBlockCount" label="Cantidad de Bloques" readonly="%{readOnly}" required="false" maxlength="100" value="%{blockCount}" size="40" />
			<s:textfield id="hgwIron" label="Cantidad de Hierro" readonly="%{readOnly}" required="false" maxlength="100" value="%{iron}" size="40" />
					
			<s:if test='ironThickness == null || ironThickness == ""'>
				<s:textfield id="hgwIronThickness" label="Grosor del Hierro" value="%{ironThickness}" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="hgwIronThickness" label="Grosor del Hierro" value='%{getText("format.number", {ironThickness})}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:else>
 			
 			<s:if test='gabions == null || gabions == ""'>
				<s:textfield id="hgwGabions" label="Gaviones" value="%{gabions}" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="hgwGabions" label="Gaviones" value='%{getText("format.number", {gabions})}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:else>
			
			<s:textfield id="hgwWorkState" label="Estado de la Obra de Cabecera de Cárcava" readonly="%{readOnly}" required="false" value="%{workState.value}" size="40" />
			
  		    <s:if test='workStateDate == null || workStateDate.projectedDate == null || workStateDate.projectedDate == ""'>
				<s:textfield id="hgwProjectedDate" label="Fecha de comienzo del Diseño" required="false" value="" readonly="%{readOnly}" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="hgwProjectedDate" label="Fecha de comienzo del Diseño" required="false" value='%{getText("format.date", {workStateDate.projectedDate})}' readonly="%{readOnly}" size="40" />
 			</s:else>
 			
  		    <s:if test='workStateDate == null || workStateDate.inProgressDate == null || workStateDate.inProgressDate == ""'>
				<s:textfield id="hgwInProgressDate" label="Fecha de Comienzo de la Obra" required="false" value="" readonly="%{readOnly}" size="40" />
 			</s:if>
 			<s:else>
				<s:textfield id="hgwInProgressDate" label="Fecha de Comienzo de la Obra" required="false" value='%{getText("format.date", {workStateDate.inProgressDate})}' readonly="%{readOnly}" size="40" />
 			</s:else>
 			
  		    <s:if test='workStateDate == null || workStateDate.finishedDate == null || workStateDate.finishedDate == ""'>
				<s:textfield id="hgwFinishedDate" label="Fecha de Finalización de la Obra" required="false" value="" readonly="%{readOnly}" size="40" />
 			</s:if>
 			<s:else>
				<sj:textfield id="hgwFinishedDate" label="Fecha de Finalización de la Obra" required="false" value='%{getText("format.date", {workStateDate.finishedDate})}' readonly="%{readOnly}" size="40" />
 			</s:else>
		</s:else>
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
		
	<script type="text/javascript">
			
		    $(function() {
		    	
	          	var actionMethod = $("#actionMethod").val();
	          	  	          	  
	  			if(actionMethod == "create" || actionMethod == "update") {
	  				
	  				$("select").not("[multiple='multiple']").selectmenu({style:"dropdown", width:400});

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
					
		  			$("#hgwSystematizationProject").rules("add", {required:true});
		  			$("#hgwType").rules("add", {required:true});
		  			$("#hgwName").rules("add", {required:true});
		  			$("#hgwConcrete").rules("add", {required:false, digits:true, range: [1, 5000], maxlength:7});
		  			$("#hgwBrickCount").rules("add", {required:false, digits:true, range:[1,10000], maxlength:5});
		  			$("#hgwBlockCount").rules("add", {required:false, digits:true, range:[1,10000], maxlength:5});
		  			$("#hgwIron").rules("add", {required:false, digits:true, range:[1,10000], maxlength:5});
		  			$("#hgwIronThickness").rules("add", {required:false, number:true, range: [0.01, 90], maxlength:5});
		  			$("#hgwGabions").rules("add", {required:false, number:true, range: [0.01, 5000], maxlength:7});
		  			$("#hgwProjectedDate").rules("add", {required:false, date:true});
					$("#hgwInProgressDate").rules("add", {required:false, date:true});
		  			$("#hgwFinishedDate").rules("add", {required:false, date:true});

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