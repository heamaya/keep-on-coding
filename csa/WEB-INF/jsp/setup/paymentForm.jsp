	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />

 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
 			<s:select id="paymentSystematizationProject" list="systematizationProjects" label="Proyecto de Sistematización" listKey="id" listValue="summary" value="systematizationProject.id" name="systematizationProject" emptyOption="true" required="true" />
 			<sj:datepicker id="paymentDate" label="Fecha de Pago" required="true" name="paymentDate" changeYear="true" changeMonth="true" size="40" />
			<s:textfield id="paymentFeeNumber" label="Cuota" name="feeNumber" readonly="%{readOnly}" required="true" maxlength="100" value="%{feeNumber}" size="40" /> 				

 		 	<s:if test='soyaPriceByQuintal == null || soyaPriceByQuintal == ""'>
				<s:textfield id="paymentSoyaPriceByQuintal" label="Precio de la Soja por Quintal" name="soyaPriceByQuintal" readonly="%{readOnly}" required="true" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="paymentSoyaPriceByQuintal" label="Precio de la Soja por Quintal" name="soyaPriceByQuintal" value='%{getText("format.number", {soyaPriceByQuintal})}' readonly="%{readOnly}" required="true" maxlength="100" size="40" />
 			</s:else>
 			
 		 	<s:if test='quintalsPaid == null || quintalsPaid == ""'>
				<s:textfield id="paymentQuintalsPaid" label="Quintales Pagados" name="quintalsPaid" readonly="%{readOnly}" required="true" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="paymentQuintalsPaid" label="Quintales Pagados" name="quintalsPaid" value='%{getText("format.number", {quintalsPaid})}' readonly="%{readOnly}" required="true" maxlength="100" size="40" />
 			</s:else>
		</s:if>
		<s:elseif test='actionMethod == "find"'>
		 	<s:select id="paymentSystematizationProject" list="systematizationProjects" label="Proyecto de Sistematización" listKey="id" listValue="summary" name="systematizationProject" emptyOption="true" required="false" />
 			<sj:datepicker id="paymentDateFrom" label="Fecha de Pago Desde" required="false" name="fromToPaymentDate" changeYear="true" changeMonth="true" size="40" />
 			<sj:datepicker id="paymentDateTo" label="Fecha de Pago Hasta" required="false" name="fromToPaymentDate" changeYear="true" changeMonth="true" size="40" />
			<s:textfield id="paymentFeeNumber" label="Cuota" name="feeNumber" readonly="%{readOnly}" required="false" maxlength="100" value="%{feeNumber}" size="40" /> 				 		 	
			<s:textfield id="paymentSoyaPriceByQuintal" label="Precio de la Soja por Quintal" name="soyaPriceByQuintal" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:textfield id="paymentQuintalsPaid" label="Quintales Pagados" name="quintalsPaid" readonly="%{readOnly}" required="false" maxlength="100" size="40" /> 			
		</s:elseif>
		<s:else>		
 			<s:textfield id="paymentSystematizationProject" label="Proyecto de Sistematización" value="%{systematizationProject.summary}" required="false" readonly="%{readOnly}" size="40" />	
			<s:textfield label="Fecha de Pago" value='%{getText("format.date", {paymentDate})}' readOnly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:textfield id="paymentFeeNumber" label="Cuota" readonly="%{readOnly}" required="false" maxlength="100" value="%{feeNumber}" size="40" />
					
 		 	<s:if test='soyaPriceByQuintal == null || soyaPriceByQuintal == ""'>
				<s:textfield id="paymentSoyaPriceByQuintal" label="Precio de la Soja por Quintal" value="" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="paymentSoyaPriceByQuintal" label="Precio de la Soja por Quintal" value='%{getText("format.number", {soyaPriceByQuintal})}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:else>
 			
 		 	<s:if test='quintalsPaid == null || quintalsPaid == ""'>
				<s:textfield id="paymentQuintalsPaid" label="Quintales Pagados" value="" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:if>
 			<s:else>
 				<s:textfield id="paymentQuintalsPaid" label="Quintales Pagados" value='%{getText("format.number", {quintalsPaid})}' readonly="%{readOnly}" required="false" maxlength="100" size="40" />
 			</s:else>
		</s:else>
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
		
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
					
		  			$("#paymentSystematizationProject").rules("add", {required:true});
		  			$("#paymentDate").rules("add", {required:true, date:true});
		  			$("#paymentFeeNumber").rules("add", {required:true, digits:true, range: [1, 10], maxlength:2});
		  			$("#paymentQuintalsPaid").rules("add", {required:true, number:true, range: [0.01, 100000], maxlength:9});
		  			$("#paymentSoyaPriceByQuintal").rules("add", {required:true, number:true, range: [0.01, 100000], maxlength:9});
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