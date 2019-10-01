	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		
 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
 			<sj:datepicker id="fromDate" label="Fecha Inicial" required="true" name="fromDate" changeYear="true" changeMonth="true" size="30" />
 			<sj:datepicker id="toDate" label="Fecha Final" required="true" name="toDate" changeYear="true" changeMonth="true" size="30" />
 		</s:if>
		<s:elseif test='actionMethod == "find"'>
 			<sj:datepicker id="fromFromDate" label="Fecha Inicial Desde" required="false" name="fromFromToDate" changeYear="true" changeMonth="true" size="30" />
 			<sj:datepicker id="toFromDate" label="Fecha Inicial Hasta" required="false" name="fromFromToDate" changeYear="true" changeMonth="true" size="30" />
 			<sj:datepicker id="fromToDate" label="Fecha Final Desde" required="false" name="toFromToDate" changeYear="true" changeMonth="true" size="30" />
 			<sj:datepicker id="toToDate" label="Fecha Final Hasta" required="false" name="toFromToDate" changeYear="true" changeMonth="true" size="30" />
		</s:elseif>
		<s:else>
			<s:textfield id="fromDate" label="Fecha Desde" name="fromDate" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
			<s:textfield id="toDate" label="Fecha Hasta" name="toDate" readonly="%{readOnly}" required="false" maxlength="100" size="40" />
		
			<s:if test='incomesAmount == null'>
				<s:textfield id="incomesAmount" label="Total Ingresos ($)" readonly="%{readOnly}" required="false" maxlength="100" size="40" value="0,00" />
 			</s:if>
 			<s:else>
 				<s:textfield id="incomesAmount" label="Total Ingresos ($)" readonly="%{readOnly}" required="false" maxlength="100" value='%{getText("format.number", {incomesAmount})}' size="40" />
 			</s:else>
 			
 			<s:if test='outcomesAmount == null'>
				<s:textfield id="outcomesAmount" label="Total Egresos ($)" readonly="%{readOnly}" required="false" maxlength="100" size="40" value="0,00" />
 			</s:if>
 			<s:else>
 				<s:textfield id="outcomesAmount" label="Total Egresos ($)" readonly="%{readOnly}" required="false" maxlength="100" value='%{getText("format.number", {outcomesAmount})}' size="40" />
 			</s:else>	
		</s:else>
		
		<jsp:include page="formSubmit.jsp" />
	</s:form>
		
	<script type="text/javascript">
			
		    $(function() {
	          	var actionMethod = $("#actionMethod").val();
	          	  	          	  
	  			if(actionMethod == "create" || actionMethod == "update") {
	  				
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
					
		  			$("#fromDate").rules("add", {required:true});
		  			$("#toDate").rules("add", {required:true});
				} else if(actionMethod == "delete" || actionMethod == "find") {
					
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