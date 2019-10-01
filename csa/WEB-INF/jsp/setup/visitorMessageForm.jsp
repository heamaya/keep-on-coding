	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	 	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		
		<s:textfield label="Apellido" id="lastName" value="%{lastName}" readonly="%{readOnly}" required="false" maxlength="256" cssClass="message" size="50" />
		<s:textfield label="Nombre" id="firstName" value="%{firstName}" readonly="%{readOnly}" required="false" maxlength="256" cssClass="message" size="50" />
		<s:textfield id="email" label="Correo Electrónico" readonly="%{readOnly}" required="false" value="%{email}" maxlength="512" cssClass="message" size="50" />
		<s:textfield label="Provincia" value="%{province.name}" readonly="%{readOnly}" maxlength="100" size="50" />
		<s:textfield label="Ciudad" value="%{city.name}" readonly="%{readOnly}" maxlength="100" size="50" />
		<s:textarea id="message" label="Mensaje" required="false" readonly="%{readOnly}" value="%{message}" rows="5" cols="50" cssClass="message" />
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
	
	<script type="text/javascript">
		$(
			function() {
				$("#currentForm").ajaxForm({
					url: $("#currentForm").attr("action"),
					type:"POST",
					target:$("#result"),
					success: function() {
						$("#currentDialog").dialog("close");
					} 
				});	
			}
		);
	</script>