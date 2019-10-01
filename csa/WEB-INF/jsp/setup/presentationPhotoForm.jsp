	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	 	
 	<s:if test='actionMethod == "create"'>	
		<s:form id="currentForm" action="%{mappedRequest}" namespace="/setup" cssClass="ui-dialog-content" enctype="multipart/form-data; charset=UTF-8" >	
			<jsp:include page="createReadUpdateDeleteHidden.jsp" />
			<s:hidden key="downloadFileAction" value="%{downloadFileAction}" />
			
			<s:file id="photo" name="photo" label="Foto" required="true" size="50"/>
			<s:textarea id="descriptionSpanish" label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
			<s:textarea id="descriptionPortuguese" label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
			<s:textarea id="descriptionEnglish" label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
			
		</s:form>
	</s:if>
	<s:elseif test='actionMethod == "update"'>
		<s:form id="currentForm" cssClass="ui-dialog-content" action="%{mappedRequest}" namespace="/setup">
			<jsp:include page="createReadUpdateDeleteHidden.jsp" />
			<s:textarea id="descriptionSpanish" label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
			<s:textarea id="descriptionPortuguese" label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
			<s:textarea id="descriptionEnglish" label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
		</s:form>
	</s:elseif>
	<s:else>
		<s:form id="currentForm" cssClass="ui-dialog-content" action="%{mappedRequest}" namespace="/setup">
			<jsp:include page="createReadUpdateDeleteHidden.jsp" />
			<s:hidden key="downloadFileAction" value="%{downloadFileAction}" />
						
			<s:url id="presentationPhotoUrl" action="%{downloadFileAction}_getFile" namespace="/setup" escapeAmp="false">
				<s:param name="fileName" value="%{photoFileName}" />
				<s:param name="contentType" value="%{photoContentType}" />					 
			</s:url>
			
			<tr>
				<td class="tdLabel">
					&nbsp;
				</td>
				<td id="presentationPhotoTd">
					<img id="presentationPhotoImg" src="<s:property value='%{#presentationPhotoUrl}' />" width="384" height="288" class="photo ui-corner-all">
	 			</td>
	 		</tr>

			<s:textarea label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
			<s:textarea label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
			<s:textarea label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="1048" />
		</s:form>
	</s:else>
	
	<jsp:include page="formSubmit.jsp" />
	
	<script type="text/javascript">
			
		$(function() {
		    	          	  
	    	var actionMethod = $("#actionMethod").val();

	  		if(actionMethod == "create") {
	  			
	  			$("#photo").fileinput({
	  				buttonText: "Buscar",
	  				inputText: ""
	  			});

				$("#currentForm").validate({
					rules: {
						photo:{
							required:true,
							accept:"jpg|jpeg|tiff|bmp|png"
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
					messages: {
						photo: {
							accept: "Por favor, elige un valor aceptado de imagen: jpg, jpeg, tiff, bmp, png."
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
	  		} else if(actionMethod == "update") {
				$("#currentForm").validate({
					rules: {
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
				
				$("#currentForm").ajaxForm({
					type:"POST",
					target:$("#result"),
					success: function() {
						$("#currentDialog").dialog("close");
					} 
				});
			}
		});
	</script>