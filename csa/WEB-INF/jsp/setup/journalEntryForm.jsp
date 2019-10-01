	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		
 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
 			<s:select id="user" list="superAdministrators" label="Usuario" listKey="id" listValue="summary" value="user.id" name="user" emptyOption="true" required="true" />
 			<sj:datepicker id="entryDate" label="Fecha de Entrada" required="true" name="entryDate" changeYear="true" changeMonth="true" size="40" />
 			
 			<s:if test="actionClass == 'JournalIncomeEntryAction'">
 				<s:select id="entryType" list="incomeTypes" label="Tipo de Ingreso" listKey="id" listValue="value" value="incomeType.id" name="incomeType" emptyOption="true" required="true" />
 			</s:if>
 			<s:else>
 				<s:select id="entryType" list="outcomeTypes" label="Tipo de Egreso" listKey="id" listValue="value" value="outcomeType.id" name="outcomeType" emptyOption="true" required="true" />
 			</s:else>
 				
			<s:textarea id="description" label="Descripción" required="true" name="description" readonly="%{readOnly}" rows="7" cols="40" maxlength="4096" />
			
			<s:if test='amount == null || amount == ""'>
				<s:textfield id="amount" label="Monto ($)" name="amount" readonly="%{readOnly}" required="true" maxlength="100" size="40"/>
 			</s:if>
 			<s:else>
 				<s:textfield id="amount" label="Monto ($)" name="amount" readonly="%{readOnly}" required="true" maxlength="100" value='%{getText("format.number", {amount})}' size="40" />
 			</s:else>
 		</s:if>
		<s:elseif test='actionMethod == "find"'>
 			<s:select id="user" list="superAdministrators" label="Usuario" listKey="id" listValue="summary" value="user.id" name="user" emptyOption="true" required="false" />
 			<sj:datepicker id="fromEntryDate" label="Fecha de Entrada Desde" required="false" name="fromToEntryDate" changeYear="true" changeMonth="true" size="40" />
 			<sj:datepicker id="toEntryDate" label="Fecha de Entrada Hasta" required="false" name="fromToEntryDate" changeYear="true" changeMonth="true" size="40" />
 			
 			<s:if test="actionClass == 'JournalIncomeEntryAction'">
 				<s:select id="entryType" list="incomeTypes" label="Tipo de Ingreso" listKey="id" listValue="value" name="incomeType" emptyOption="true" required="false" />
 			</s:if>
 			<s:elseif test="actionClass == 'JournalOutcomeEntryAction'">
 				<s:select id="entryType" list="outcomeTypes" label="Tipo de Egreso" listKey="id" listValue="value" name="outcomeType" emptyOption="true" required="false" />
 			</s:elseif>
 			
 			<s:select label="Modo de Filtro de Texto" list="matchModes" name="matchMode" emptyOption="false" listKey="id" listValue="value" />
 			<s:select id="isClosedEntry" list="journalEntryIsClosedOptions" label="Estado" listKey="id" listValue="value" name="journalEntryIsClosed" emptyOption="true" />
			<s:textarea id="description" label="Descripción" required="false" name="description" readonly="%{readOnly}" rows="7" cols="40" maxlength="2048" />
			<s:textfield id="amount" name="amount" label="Monto" required="false" maxlength="255" size="40" />
		</s:elseif>
		<s:else>
 			<s:textfield id="user" label="Usuario" value="%{user.summary}" readonly="%{readOnly}" required="false" maxlength="100" />
 			
 			<s:if test='entryDate == null || entryDate == ""'>
				<s:textfield id="entryDate" label="Fecha de Entrada" value="" readonly="%{readOnly}" required="false" maxlength="100" /> 				
 			</s:if>
 			<s:else>
	 			<s:textfield id="entryDate" label="Fecha de Entrada" value='%{getText("format.date", {entryDate})}' readonly="%{readOnly}" required="false" maxlength="100" />
 			</s:else>
 			
 			<s:if test="actionClass == 'JournalIncomeEntryAction'">
 				<s:textfield id="entryType" label="Tipo de Ingreso" value="%{incomeType.value}" readonly="%{readOnly}" required="false" />
 			</s:if>
 			<s:else>
				<s:textfield id="entryType" label="Tipo de Egreso" value="%{outcomeType.value}" readonly="%{readOnly}" required="false" />
 			</s:else>
 				
			<s:textarea id="description" label="Descripción" required="false" name="description" readonly="%{readOnly}" rows="7" cols="40" maxlength="2048" />
			
			<s:if test='amount == null || amount == ""'>
				<s:textfield id="amount" label="Monto ($)" value="" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="amount" label="Monto ($)" readonly="%{readOnly}" required="false" maxlength="100" value='%{getText("format.number", {amount})}' size="40" />
 			</s:else>
		</s:else>
		
		<jsp:include page="formSubmit.jsp" />
	</s:form>
		
	<script type="text/javascript">
			
		    $(function() {
	          	var actionMethod = $("#actionMethod").val();
	          	  	          	  
	  			if(actionMethod == "create" || actionMethod == "update") {
	  				
	  				$("select").selectmenu({style:"dropdown", width:400});

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
					
		  			$("#entryDate").rules("add", {required:true});
		  			$("#user").rules("add", {required:true});
		  			$("#entryType").rules("add", {required:true});
		  			$("#description").rules("add", {required:true, maxlength:1024});
		  			$("#amount").rules("add", {required:true, number:true, range: [0.01, 1000000], maxlength:10});
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