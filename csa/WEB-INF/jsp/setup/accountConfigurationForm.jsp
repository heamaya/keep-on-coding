	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
	
		<s:if test='actionMethod == "update"'>
			<s:textfield label="Nombre de Usuario" id="username" name="username" readonly="%{readOnly}" required="true" maxlength="512" size="40" />			
			<s:textfield label="Apellido" id="lastName" name="lastName" readonly="%{readOnly}" required="true" maxlength="256" size="40" />
			<s:textfield label="Nombre" id="firstName" name="firstName" readonly="%{readOnly}" required="true" maxlength="256" size="40" />
			<s:checkbox id="keepPassword" fieldValue="true" value="true" name="keepPassword" label="Mantener la contraseña anterior" />
			<s:password id="password" label="Contraseña" id="password" name="password" readonly="%{readOnly}" required="true" maxlength="128" cssClass="replace" size="40" />
			<s:password id="repeatPassword" label="Repetir Contraseña" id="repeatPassword" name="repeatPassword" readonly="%{readOnly}" required="true" maxlength="128" cssClass="replace" size="40" />
		</s:if>
		<s:else>
			<s:textfield label="Apellido" name="lastName" readonly="%{readOnly}" required="false" maxlength="256" size="40" />
			<s:textfield label="Nombre" name="firstName" readonly="%{readOnly}" required="false" maxlength="256" size="40" />
		</s:else>
	
		<jsp:include page="addressFormFields.jsp" />
		
		<s:textfield name="primaryTelephoneNumber" label="Teléfono Primario" id="primaryTelephoneNumber" name="primaryTelephoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Teléfono Secundario" id="secondaryTelephoneNumber" name="secondaryTelephoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Celular Primario" id="primaryCellPhoneNumber" name="primaryCellPhoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40"  />
		<s:textfield label="Celular Secundario" id="secondaryCellPhoneNumber" name="secondaryCellPhoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Correo Electrónico Secundario" id="secondaryEmail" name="secondaryEmail" readonly="%{readOnly}" required="false" maxlength="512" size="40" />
		<s:textarea label="Comentario" id="comment" name="comment" readonly="%{readOnly}" required="false" rows="5" cols="40" /> 
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
	
	<script type="text/javascript">
			
	    $(function() {
	    	
			$(".replace").attr("disabled", "disabled");
			
			$("#keepPassword").live("click",	
				
				function(event) {
					
					if($(this).is(':checked')) {
						$(".replace").attr("disabled", "disabled");	
					} else {
						$(".replace").removeAttr("disabled");	
					}
						
				}
			);	
		    	          	  
			var actionMethod = $("#actionMethod").val();
	          	  
	  		if(actionMethod == "update") {

	  			$("select").selectmenu({style:'dropdown', width:400});
	  			
				$("#currentForm").validate({
					rules: {
						username: {
							required:true,
							email:true,
							minlength:1,
							maxlength:512
						},
						lastName: {
							required:true,
							minlength:1,
							maxlength:256
						},
						firstName: {
							required:true,
							minlength:1,
							maxlength:256
						},
						password: {
							required:true,
							minlength: 5,
							maxlength: 128 
						},
						repeatPassword: {
							required:true,
							minlength: 5,
							maxlength: 128,
							equalTo: "#password"
						},

						primaryTelephoneNumber: {
							digits:true,
							maxlength:50							
						},
						secondaryTelephoneNumber: {
							digits:true,
							maxlength:50							
						},
						primaryCellPhoneNumber: {
							digits:true,
							maxlength:50							
						},
						secondaryCellPhoneNumber: {
							digits:true,
							maxlength:50							
						},
						secondaryEmail: {
							email:true,
							minlength:1,
							maxlength:512
						},
						comment:{
							maxlength:2048
						},
						province:"required",
						street: {
							maxlength:512							
						},
						streetNumber: {
							digits:true,
							maxlength:10							
						},
						floor: {
							digits:true,
							maxlength:3							
						},
						department: {
							maxlength:255							
						}
					}, 

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
			} 
		});
	</script>