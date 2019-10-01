<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>

	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content" method="post" >
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
	
		<s:if test='actionMethod == "create"'>
			<s:textfield label="Nombre de Usuario" name="username" readonly="%{readOnly}" required="true" maxlength="50" cssClass="disableAutoComplete" size="40" />
			<s:password label="Contraseña" id="password" name="password" readonly="%{readOnly}" maxlength="128" required="true" cssClass="disableAutoComplete" size="40" />
			<s:password label="Repetir Contraseña" name="repeatPassword" readonly="%{readOnly}" maxlength="128" required="true" cssClass="disableAutoComplete" size="40" />
			<s:select id="role" label="Tipo de Usuario" list="roles" name="role" listKey="id" listValue="value" emptyOption="true" required="true" value="role.id" />				
			<s:textfield label="Apellido" name="lastName" readonly="%{readOnly}" required="true" maxlength="50" size="40" />
			<s:textfield label="Nombre" name="firstName" readonly="%{readOnly}" required="true" maxlength="50" size="40" />
		</s:if>
		<s:elseif test='actionMethod == "update"'>
			<s:textfield label="Nombre de Usuario" name="username" readonly="%{readOnly}" required="true" maxlength="50" cssClass="disableAutoComplete" size="40" />
			<s:checkbox label="Mantener Contraseña" id="keepPassword" name="keepPassword" value="true" fieldValue="true" />	
			<s:password label="Contraseña" id="password" name="password" readonly="%{readOnly}" maxlength="128" required="true" cssClass="disableAutoComplete changePassword" disabled="true" size="40" />
			<s:password label="Repetir Contraseña" key="repeatPassword" readonly="%{readOnly}" maxlength="128" required="true" cssClass="disableAutoComplete changePassword" disabled="true" size="40" />
			<s:select id="role" label="Tipo de Usuario" list="roles" name="role" listKey="id" listValue="value" emptyOption="true" required="true" value="role.id" />	
			<s:textfield label="Apellido" name="lastName" readonly="%{readOnly}" required="true" maxlength="50" size="40" />
			<s:textfield label="Nombre" name="firstName" readonly="%{readOnly}" required="true" maxlength="50" size="40" />
		</s:elseif>
		<s:elseif test='actionMethod == "find"' >
			<s:textfield label="Nombre de Usuario" name="username" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
			<s:textfield label="Apellido" name="lastName" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
			<s:textfield label="Nombre" name="firstName" readonly="%{readOnly}" required="false" maxlength="50" size="40" />	
		</s:elseif>
		<s:else>
			<s:textfield label="Nombre de Usuario" name="username" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
			<s:textfield label="Apellido" name="lastName" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
			<s:textfield label="Nombre" name="firstName" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		</s:else>
	
		<jsp:include page="addressFormFields.jsp" />
		<s:textfield label="Teléfono Primario" name="primaryTelephoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Teléfono Secundario" name="secondaryTelephoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Teléfono Celular Primario" name="primaryCellPhoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Teléfono Celular Secundario" name="secondaryCellPhoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Correo Electrónico Secundario" name="secondaryEmail" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textarea label="Comentario" name="comment" readonly="%{readOnly}" required="false" rows="5" cols="40" /> 
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
	
	<script type="text/javascript">
			
		$(function() {

			$(".disableAutoComplete").attr("autocomplete", "off");
			
			$("#keepPassword").click(
				function(event) {
					var disabled = $(".changePassword").attr("disabled");
					$(".changePassword").attr("disabled", !disabled);
				}
			);
			
			var actionMethod = $("#actionMethod").val();

			if(actionMethod == "create" || actionMethod == "update") {
				
				$("select").selectmenu({style:'dropdown', width:400});


				$("#currentForm").validate({
					rules: {
						username: {
							required: true,
							email: true,
							minlength: 1,
							maxlength:50
						},
						password: {
							required: true,
							minlength: 1,
							maxlength:50
						},
						repeatPassword: {
							required: true,
							minlength: 1,
							maxlength:50,
							equalTo: "#password"
						},
						lastName: {
							required: true,
							minlength: 1,
							maxlength:50
						},
						firstName: {
							required: true,
							minlength: 1,
							maxlength:50
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
							email: true,
							minlength: 1,
							maxlength:50
						},
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
						},
						comment: {
							maxlength:512							
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
			} else if(actionMethod == "delete" || actionMethod == "find") {
				$("#currentForm").ajaxForm({
					url: $("#currentForm").attr("action"),
					type:"POST",
					target:$("#result"),
					success: function() {
						$("#currentDialog").dialog("close");
					} 
				});
			}
		});
	</script>