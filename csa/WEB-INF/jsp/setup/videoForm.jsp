	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	 	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content" enctype="multipart/form-data; charset=UTF-8" >
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />

 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
			<s:textfield id="nameSpanish" label="Nombre en Español" name="nameSpanish" readonly="%{readOnly}" required="true" size="50" />
			<s:textfield id="namePortuguese" label="Nombre en Portugués" name="namePortuguese" readonly="%{readOnly}" size="50" required="false" />
			<s:textfield id="nameEnglish" label="Nombre en Inglés" name="nameEnglish" readonly="%{readOnly}" size="50" required="false" />
			<s:textfield id="videoKey" label="Clave del Video en YouTube" name="videoKey" readonly="%{readOnly}" size="50" required="true" /> 		
 		</s:if>
 		<s:else>
			<s:textfield id="nameSpanish" label="Nombre en Español" name="nameSpanish" readonly="%{readOnly}" size="50" required="false" />
			<s:textfield id="namePortuguese" label="Nombre en Portugués" name="namePortuguese" readonly="%{readOnly}" size="50" required="false" />
			<s:textfield id="nameEnglish" label="Nombre en Inglés" name="nameEnglish" readonly="%{readOnly}" size="50" required="false" />
			<s:textfield id="videoKey" label="Clave del Video en YouTube" name="videoKey" readonly="%{readOnly}" size="50" required="true" /> 		
 		</s:else>
 		
 		<s:textarea id="descriptionSpanish" label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
		<s:textarea id="descriptionPortuguese" label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
		<s:textarea id="descriptionEnglish" label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
	
	<script type="text/javascript">
			
		$(function() {
		    	          	  
	    	var actionMethod = $("#actionMethod").val();
	          	  
	  		if(actionMethod == "create" || actionMethod == "update") {

				$("#currentForm").validate({
					rules: {
						nameSpanish:{
							required:true,
							maxlength:512
						},
						namePortuguese:{
							required:false,
							maxlength:512
						},
						nameEnglish:{
							required:false,
							maxlength:512
						},
						videoKey: {
							required:true,
							maxlength:512
						},
						descriptionSpanish: {
							maxlength:1024
						},
						descriptionPortuguese: {
							maxlength:1024
						},
						descriptionEnglish: {
							maxlength:1024
						}
					},
					submitHandler: function(form) {
						$(form).ajaxSubmit({
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
	  			
			} else if(actionMethod == "delete") {
				$("#currentForm").ajaxForm(
					{
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