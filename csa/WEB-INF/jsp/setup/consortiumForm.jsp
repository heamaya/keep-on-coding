	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content" enctype="multipart/form-data; charset=UTF-8" method="post" >
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden key="previousLimitFileName" value="%{previousLimitFileName}" />
		<s:hidden key="previousPath" value="%{previousPath}" />
		 	
 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
			
			<s:textfield id="name" label="Nombre" name="name" required="true" maxlength="512" size="40" />
			<sj:datepicker id="creationDate" label="Fecha de Creación" required="true" name="creationDate" changeYear="true" changeMonth="true" size="40" />
			<s:textfield id="northernLimit" label="Límite Norte" name="northernLimit" required="false" maxlength="512" size="40" />
			<s:textfield id="southernLimit" label="Límite Sur" name="southernLimit" required="false" maxlength="512" size="40" />
			<s:textfield id="westernLimit" label="Límite Oeste" name="westernLimit" required="false" maxlength="512" size="40" />
			<s:textfield id="easternLimit" label="Límite Este" name="easternLimit" required="false" maxlength="512" size="40" />
			
			<s:textarea id="descriptionSpanish" label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			<s:textarea id="descriptionPortuguese" label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			<s:textarea id="descriptionEnglish" label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			
			<s:if test="limitFileName == null || limitFileName == ''">
				<s:file id="limit" name="limit" label="Límites del Consorcio (KMZ o KML)"/>
			</s:if>
			<s:else>
				<s:checkbox id="keepLimit" fieldValue="true" value="true" name="keepLimit" label="Mantener Archivo de Límites del Consorcio" />
				<s:file id="limit" name="limit" label="Límites del Consorcio (KMZ o KML)" />
			</s:else>	
			
			<s:checkbox label="Mostrar como Consorcio Destacado" name="starred" fieldValue="true" required="true"/>
		</s:if>
		<s:elseif test='actionMethod == "find"'>	
		</s:elseif>
		<s:else>
			<s:textfield label="Nombre" value="%{name}" required="false" maxlength="512" readOnly="%{readOnly}" size="40" />
			
			<s:if test="creationDate != null">
				<s:textfield label="Fecha de Creación" value='%{getText("format.date", {creationDate})}' readOnly="%{readOnly}" required="false" maxlength="100" size="40" />
			</s:if>
			<s:else>
				<s:textfield label="Fecha de Creación" value='%{creationDate}' readOnly="%{readOnly}" required="false" maxlength="100" size="40" />
			</s:else>
			
			<s:textfield label="Límite Norte" value="%{northernLimit}" required="false" maxlength="512" readOnly="%{readOnly}" size="40" />
			<s:textfield label="Límite Sur" value="%{southernLimit}" required="false" maxlength="512" readOnly="%{readOnly}" size="40" />
			<s:textfield label="Límite Oeste" value="%{westernLimit}" required="false" maxlength="512" readOnly="%{readOnly}" size="40" />
			<s:textfield label="Límite Este" value="%{esternLimit}" required="false" maxlength="512" readOnly="%{readOnly}" size="40" />
			<s:textarea id="descriptionSpanish" label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			<s:textarea id="descriptionPortuguese" label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			<s:textarea id="descriptionEnglish" label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			
			<s:if test="starred == true">
   				<s:set name="isStarred">Sí</s:set>
   			</s:if>
   			<s:else>
   				<s:set name="isStarred">No</s:set>
   			</s:else>
   			
   			<s:textfield label="Es un Consorcio destacado" value="%{#isStarred}" required="false" readOnly="%{readOnly}" />
   			

		</s:else>
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
	
		<script type="text/javascript">
			
		    $(function() {
		    	          	  
	          	var actionMethod = $("#actionMethod").val();
	          	  
	  			if(actionMethod == "create" || actionMethod == "update") {
	  				
		  			$("#limit").fileinput({
		  				buttonText: "Buscar",
		  				inputText: ""
		  			});
		  			
		  			if(actionMethod == "update") {
			  			
						$("#limit").attr("disabled", "disabled");
						$("#keepLimit").click(
							function(event) {
						
								if($(this).is(':checked')) {
									$("#limit").attr("disabled", "disabled");
								} else {
									$("#limit").removeAttr("disabled");
								}	
							}
						);	
		  			}

					$("#currentForm").validate({
						rules: {
							name: {
								required:true,
								minlength:1,
								maxlength:512
							},
							limit: {
								accept:"kmz|kml"
							},
							creationDate:{
								required:true,
								date:true
							},
							descriptionSpanish: {
								maxlength:10240
							},
							descriptionPortuguese: {
								maxlength:10240
							},
							descriptionEnglish: {
								maxlength:10240
							},
							northernLimit: {
								maxlength:512
							},
							southernLimit: {
								maxlength:512
							},
							easternLimit: {
								maxlength:512
							},
							westernLimit: {
								maxlength:512
							}
						}, 
						messages: {
							limit: {
								accept:"Por favor, elige un valor aceptado de archivo google earth: kml, kmz."
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