	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
	 	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content" enctype="multipart/form-data; charset=UTF-8">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden key="previousImageFileName" value="%{previousImageFileName}" />
		
		<s:if test="actionMethod == 'create'">
			<s:url id="ckeditorConfigUrl" value="/js/ckeditor.config.js" />
			<s:textfield id="titleSpanish" label="T�tulo en Espa�ol" name="titleSpanish" readonly="%{readOnly}" required="true" size="80" />
			<s:textfield id="titlePortuguese" label="T�tulo en Portugu�s" name="titlePortuguese" readonly="%{readOnly}" required="false" size="80" />
			<s:textfield id="titleEnglish" label="T�tulo en Ingl�s" name="titleEnglish" readonly="%{readOnly}" required="false" size="80" />	
			<sjr:ckeditor id="bodySpanish" label="Cuerpo en Espa�ol" name="bodySpanish" rows="10" cols="80" width="750" editorLocal="es" toolbar="MyToolbar" customConfig="%{#ckeditorConfigUrl}" required="true" />
			<sjr:ckeditor id="bodyPortuguese" label="Cuerpo en Portugu�s" name="bodyPortuguese" rows="10" cols="80" width="750" editorLocal="pt" toolbar="MyToolbar" customConfig="%{#ckeditorConfigUrl}" required="true" />
			<sjr:ckeditor id="bodyEnglish" label="Cuerpo en Ingl�s" name="bodyEnglish" rows="10" cols="80" width="750" editorLocal="en" toolbar="MyToolbar" customConfig="%{#ckeditorConfigUrl}" required="true" />			
			<s:file id="newsImage" name="image" label="Imagen" />
			<s:textarea id="imageDescriptionSpanish" label="Descripci�n de la Imagen en Espa�ol" name="imageDescriptionSpanish" readonly="%{readOnly}"  rows="5" cols="80" required="false" />			
			<s:textarea id="imageDescriptionPortuguese" label="Descripci�n de la Imagen en Portugu�s" name="imageDescriptionPortuguese" readonly="%{readOnly}"  rows="5" cols="80" required="false" />
			<s:textarea id="imageDescriptionEnglish" label="Descripci�n de la Imagen en Ingl�s" name="imageDescriptionEnglish" readonly="%{readOnly}"  rows="5" cols="80" required="false" />
		</s:if>
		<s:elseif test="actionMethod == 'update'">
			<s:url id="ckeditorConfigUrl" value="/js/ckeditor.config.js" />
			<s:textfield id="titleSpanish" label="T�tulo" name="titleSpanish" readonly="%{readOnly}" required="true" size="80"/>
			<s:textfield id="titlePortuguese" label="T�tulo Portugu�s" name="titlePortuguese" readonly="%{readOnly}" required="true" size="80" />
			<s:textfield id="titleEnglish" label="T�tulo Ingl�s" name="titleEnglish" readonly="%{readOnly}" required="true" size="80" />
			<sjr:ckeditor id="bodySpanish" label="Cuerpo en Espa�ol" name="bodySpanish" rows="10" cols="80" width="750" editorLocal="es" toolbar="MyToolbar" customConfig="%{#ckeditorConfigUrl}" required="true" />
			<sjr:ckeditor id="bodyPortuguese" label="Cuerpo en Portugu�s" name="bodyPortuguese" rows="10" cols="80" width="750" editorLocal="pt" toolbar="MyToolbar" customConfig="%{#ckeditorConfigUrl}" required="true" />
			<sjr:ckeditor id="bodyEnglish" label="Cuerpo en Ingl�s" name="bodyEnglish" rows="10" cols="80" width="750" editorLocal="en" toolbar="MyToolbar" customConfig="%{#ckeditorConfigUrl}" required="true" />
		
			<s:if test="imageFileName == null || imageFileName == ''">
				<s:file id="newsImage" name="image" label="Imagen"/>
				<s:textarea id="imageDescriptionSpanish" label="Descripci�n de la Imagen" name="imageDescriptionSpanish" readonly="%{readOnly}"  rows="5" cols="80" required="false" />
				<s:textarea id="imageDescriptionPortuguese" label="Descripci�n de la Imagen en Portugu�s" name="imageDescriptionPortuguese" readonly="%{readOnly}"  rows="5" cols="80" required="false" />
				<s:textarea id="imageDescriptionEnglish" label="Descripci�n de la Imagen Ingl�s" name="imageDescriptionEnglish" readonly="%{readOnly}"  rows="5" cols="80" required="false" />
			</s:if>
			<s:else>
				<s:checkbox id="keepImage" value="true" name="keepImage" label="Mantener Imagen cargada"/>
				<s:file id="newsImage" name="image" label="Imagen" cssClass="replaceImage"/>
				<s:textarea id="imageDescriptionSpanish" label="Descripci�n de la Imagen" name="imageDescriptionSpanish" readonly="%{readOnly}"  rows="5" cols="80" required="false" cssClass="replaceImage" />
				<s:textarea id="imageDescriptionPortuguese" label="Descripci�n de la Imagen en Portugu�s" name="imageDescriptionPortuguese" readonly="%{readOnly}" rows="5" cols="80" required="false" cssClass="replaceImage" />
				<s:textarea id="imageDescriptionEnglish" label="Descripci�n de la Imagen Ingl�s" name="imageDescriptionEnglish" readonly="%{readOnly}" rows="5" cols="80" required="false" cssClass="replaceImage" />
			</s:else>			
		</s:elseif>
		<s:elseif test="actionMethod == 'find'">
			<s:select label="Modo de Filtro de Texto" list="matchModes" name="matchMode" emptyOption="false" listKey="id" listValue="value" value="3" />
			<s:textfield id="titleSpanish" label="T�tulo Espa�ol" name="titleSpanish" readonly="%{readOnly}" required="false" size="80" />
			<s:textfield id="titlePortuguese" label="T�tulo Portugu�s" name="titlePortuguese" required="false" size="80" />
			<s:textfield id="titleEnglish" label="T�tulo Ingl�s" name="titleEnglish"  required="false" size="80" />
			<s:textarea id="bodySpanish" label="Cuerpo en Espa�ol" name="bodySpanish" rows="5" cols="80" required="false" />
			<s:textarea id="bodyPortuguese" label="Cuerpo en Portugu�s" name="bodyPortuguese" rows="5" cols="80" required="false" />
			<s:textarea id="bodyEnglish" label="Cuerpo en Ingl�s" name="bodyEnglish" rows="5" cols="80" required="false" />
		</s:elseif>
		<s:else>
			<s:textfield label="T�tulo en Espa�ol" value="%{titleSpanish}" readonly="%{readOnly}" required="false" size="80" />
			<s:textfield label="T�tulo en Portugu�s" value="%{titlePortuguese}" readonly="%{readOnly}" required="false" size="80" />
			<s:textfield label="T�tulo en Ingl�s" value="%{titleEnglish}" readonly="%{readOnly}" required="false" size="80" />
			<tr>
    			<td class="tdLabel"><label class="label">Cuerpo en Espa�ol:</label></td>
    			<td><s:property value="bodySpanish" escapeHtml="false" /></td>
			</tr>
			
			<tr>
    			<td class="tdLabel"><label class="label">Cuerpo en Portugu�s:</label></td>
    			<td><s:property value="bodyPortuguese" escapeHtml="false" /></td>
			</tr>
						
			<tr>
    			<td class="tdLabel"><label class="label">Cuerpo en Ingl�s:</label></td>
    			<td><s:property value="bodyEnglish" escapeHtml="false" /></td>
			</tr>
			
			<s:if test="imageFileName != null && imageFileName != ''">
			
				<s:url id="url" action="NewsPresentationPhotoDownloadFileAction_getFile" namespace="/setup" escapeAmp="false">
					<s:param name="fileName" value="%{imageFileName}" />
					<s:param name="contentType" value="%{imageContentType}" />					 
				</s:url>
				
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
		 				<img src='<s:property value="%{#url}" />' width="384" height="288" class="ui-corner-all" />
		 			</td>
		 		</tr>
		 		
		 		<s:textarea label="Descripci�n de la Imagen en Espa�ol" name="imageDescriptionSpanish" readonly="%{readOnly}"  rows="5" cols="80" required="false" wrap="true"/>
	 			<s:textarea label="Descripci�n de la Imagen en Portugu�s" name="imageDescriptionPortuguese" readonly="%{readOnly}"  rows="5" cols="80" required="false" wrap="true"/>
	 			<s:textarea label="Descripci�n de la Imagen en Ingl�s" name="imageDescriptionEnglish" readonly="%{readOnly}"  rows="5" cols="80" required="false" wrap="true"/>
	 		</s:if>
		</s:else>
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
	
	<script type="text/javascript">
			
		$(function() {
		    	          	  
	    	var actionMethod = $("#actionMethod").val();
	          	  
	  		if(actionMethod == "create" || actionMethod == "update") {
	  			
	  			$("#newsImage").fileinput({
	  				buttonText: "Buscar",
	  				inputText: ""
	  			});
	  			
	  			if(actionMethod == "update") {
	  				$(".replaceImage").attr("disabled", "disabled");
					$("#keepImage").click(
						
						function(event) {
						
							if($(this).is(':checked')) {
								$(".replaceImage").attr("disabled", "disabled");	
							} else {
								$(".replaceImage").removeAttr("disabled");	
							}
						}
					);	
	  			}

				$("#currentForm").validate({
					rules: {
						titleSpanish:{
							required:true,
							maxlength:512
						},
						bodySpanish: {
							maxlength:10480
						},
						titlePortuguese:{
							required:true,
							maxlength:512
						},
						bodyPortuguese: {
							maxlength:10480
						},				
						titleEnglish:{
							required:true,
							maxlength:512
						},
						bodyEnglish: {
							maxlength:10480
						},				
						imageDescriptionSpanish: {
							maxlength:2048
						},
						imageDescriptionPortuguese: {
							maxlength:2048
						},
						imageDescriptionEnglish: {
							maxlength:2048
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
				
				if(actionMethod == "find") {
					$("select").selectmenu({style:"dropdown", width:400});
				}
					
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